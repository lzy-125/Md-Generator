# MdGenerator

## 介绍
MdGenerator -- 接口markdown文档生成工具，方便基于SpringMVC工程下的接口文档编写

MdGenerator 是根据各个xxController下声明接口的入参、返回值生成markdown文档的工具。

此工具借鉴了github上开源的markdown文档生成工具[MdKiller](https://github.com/kymotz/mdkiller)，底层构造markdown文本基于此工具实现
## 使用教程

环境：JDK1.8+

1、依赖Spring框架中的注解

2、依赖Jackson框架

3、使用示例：

然后进行如下代码操作：

```java
import com.ksyun.MdGenerator;

@AssistAnnotation
@RequestMapping(params = {"Action=Test"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.HEAD})
@Markdown
public InstancesResponse test(@Valid @RequestBody ReqCreateContainerGroupParam param) {
    return null;
}

public static void main(String[] args) throws IOException {
    MdGenerator.generateMarkdown(ContainerGroupController.class);
}
```

在Project根目录下生成名为`api.md`文件

输出的markdown文档内容如下：

```markdown
## Action=Test
### 请求方式:
GET/POST
### Content-Type:
application/x-www-form-urlencoded

参数名 | 类型 | 是否必填 | 描述 | 备注
- | - | - | - | -
Name | string | 否 |  名字 | 
Age | int | 否 |  年龄  | 
Address | string | 否 |  地址  | 
Person | Object | 否 |  | 

#### Person 数据类型
> 
> 参数名 | 类型 | 是否必填 | 描述 | 备注
> - | - | - | - | -
> Mail | string | 否 |  邮箱 | 
> Phone | int | 否 |  电话  | 


### 返回结果：
```json
{
  "name" : " ",
  "age" : 0,
  "address" : " ",
  "object" : { },
  "map" : { },
  "teacher" : [ {
    "book" : " ",
    "age" : 0
  } ],
  "student" : {
    "password" : " ",
    "subList" : [ " " ],
    "intList" : [ 0 ]
  }
}
```
## 说明
可以解析多个xxController文件，但需要将要生成文档的接口加上`@Markdown`注解，否则不会生成对应接口文档。

接口入参的类中，尽量不要使用静态内部类，比如声明 public static class XXX {} 这种，这样内部类中字段的注释无法被解析到md文档中

