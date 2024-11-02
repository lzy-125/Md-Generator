package com.ksyun;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author：zongYu.Liu
 * @Date：2024/10/31 19:44
 * @description：接口markdown生成器
 */
public class MdGenerator {

    private MdGenerator() {

    }

    /**
     * 生成markdown文件
     * @param jsonPath json文件路径
     * @param classes 接口类
     * @throws IOException ignore
     */
    public static void generateMarkdown(String jsonPath, Class<?>... classes) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Class<?> clazz : classes) {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Markdown.class)) {
                    Parameter[] parameters = method.getParameters();
                    List<Map<String, String[][]>> table = new ArrayList<>();
                    //接口方法上只能有一个参数
                    if (parameters.length == 1) {
                        Field[] fields = parameters[0].getType().getDeclaredFields();
                        Class<?> subClazz = parameters[0].getType();
                        fillDataTable(table, subClazz, null,fields.length + 1);
                    }
                    String markdown = doBuildMarkdown(method, parameters, table, jsonPath);
                    sb.append(markdown).append("\r\n");
                }
            }
        }
        System.out.println(sb);
        Path path = Paths.get(jsonPath + "/" + "api.md");
        Files.write(path, sb.toString().getBytes());
    }

    private static String doBuildMarkdown(Method method, Parameter[] parameters, List<Map<String, String[][]>> table, String jsonPath) throws IOException {
        MdKiller.SectionBuilder md = MdKiller.of()
                .bigTitle("Action=" + upperCaseFirstChar(method.getName()))
                .title("请求方式:")
                .text(getRequestMethod(method))
                .title("Content-Type:");
        String contentType;
        if (parameters.length == 1) {
            contentType = parameters[0].isAnnotationPresent(RequestBody.class) ?
                    "application/json" :
                    "application/x-www-form-urlencoded";
        } else {
            contentType = "application/x-www-form-urlencoded";
        }
        md.text(contentType);
        for (int i = table.size() - 1; i >= 0 ; i--) {
            Map<String, String[][]> map = table.get(i);
            Map.Entry<String, String[][]> entry = map.entrySet().iterator().next();
            String key = entry.getKey();
            String[][] data = entry.getValue();
            data[0][0] = "参数名";
            data[0][1] = "类型";
            data[0][2] = "是否必填";
            data[0][3] = "描述";
            data[0][4] = "备注";
            if (i == table.size() - 1) {
                md.table().data(data).endTable();
                continue;
            }
            md.subTitle(key + " 数据类型");
            md.ref().table()
                    .data(data)
                    .endTable()
                    .endRef();
        }
        Path path = Paths.get(jsonPath + "/" + method.getReturnType().getSimpleName() + ".java.json");
        return md.title("返回结果：")
                .text("```json\r\n" + new String(Files.readAllBytes(path)) + "\r\n```")
                .build();
    }

    private static String getRequestMethod(Method method) {
        if (method.isAnnotationPresent(PostMapping.class)) {
            return "POST";
        } else if (method.isAnnotationPresent(GetMapping.class)) {
            return "GET";
        } else if (method.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            if (requestMapping.method().length != 0) {
                StringBuilder sb = new StringBuilder();
                for (RequestMethod requestMethod : requestMapping.method()) {
                    sb.append(requestMethod.name()).append("/");
                }
                return sb.substring(0, sb.length() - 1);
            }
            return "GET/POST";
        } else {
            return "GET";
        }
    }

    /**
     * 填充markdown所需的table数据
     * @param dataTable key:嵌套类的属性名 value:嵌套类中的字段信息
     * @param clazz 类成员信息
     * @param propertyName 嵌套类的属性名
     * @param len table的行数
     */
    private static void fillDataTable(List<Map<String, String[][]>> dataTable, Class<?> clazz, String propertyName, int len) {
        String[][] data = new String[len][5];
        Field[] fields = clazz.getDeclaredFields();
        int row = 0;
        int column = 0;
        int count = 0;
        for (Field field : fields) {
            if (basicType(field.getType())) {
                row++;
                data[row][column] = upperCaseFirstChar(field.getName());
                data[row][column + 1] = getPropertyTypeMapping(field.getType().getSimpleName());
            }
            else if (field.getType() == List.class) {
                Class<?> genericClass = (Class<?>) getGenericClass(field);
                if (basicType(genericClass)) {
                    row++;
                    data[row][column] = upperCaseFirstChar(field.getName());
                    data[row][column + 1] = "Array of " + getPropertyTypeMapping(genericClass.getSimpleName());
                }
                else {
                    row++;
                    data[row][column] = upperCaseFirstChar(field.getName());
                    data[row][column + 1] = "Array of Object";
                    fillDataTable(dataTable, genericClass, field.getName(), genericClass.getDeclaredFields().length + 1);
                }
            } else {
                row++;
                data[row][column] = upperCaseFirstChar(field.getName());
                data[row][column + 1] = "Object";
                fillDataTable(dataTable, field.getType(), field.getName(),field.getType().getDeclaredFields().length + 1);
            }
            //一个类中的所有字段都遍历完了，添加到dataTable中
            if (count++ == fields.length - 1) {
                dataTable.add(Collections.singletonMap(upperCaseFirstChar(propertyName), data));
            }
        }
    }

    private static String getPropertyTypeMapping(String propertyType) {
        switch (propertyType) {
            case "Integer":
                return "int";
            case "Long":
                return "long";
            case "BigDecimal":
            case "Double":
            case "Float":
                return "float";
            case "Boolean":
                return "boolean";
            case "String":
                return "string";
            default:
                return propertyType;
        }
    }

    private static String upperCaseFirstChar(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        if (!Character.isUpperCase(str.charAt(0))) {
            char[] cs = str.toCharArray();
            cs[0] -= 32;
            str = String.valueOf(cs);
            return str;
        }
        return str;
    }

    private static boolean basicType(Class<?> clz) {
        return clz.isPrimitive()
                || clz == String.class
                || clz == Boolean.class
                || clz == Integer.class
                || clz == Byte.class
                || clz == Double.class
                || clz == Float.class
                || clz == Character.class
                || clz == Short.class
                || clz == Long.class
                || clz == BigDecimal.class;
    }

    private static Type getGenericClass(Field f) {
        return Optional.of(f).map(Field::getGenericType)
                .filter(g -> g instanceof ParameterizedType)
                .map(g -> ((ParameterizedType) g).getActualTypeArguments())
                .filter(ArrayUtils::isNotEmpty)
                .map(a -> a[0])
                .orElse(null);
    }
}
