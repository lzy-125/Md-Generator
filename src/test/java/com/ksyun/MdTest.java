package com.ksyun;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author：zongYu.Liu
 * @Date：2024/11/2 12:08
 */
public class MdTest {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        MdGenerator.generateMarkdown(TestController.class);
    }
}
