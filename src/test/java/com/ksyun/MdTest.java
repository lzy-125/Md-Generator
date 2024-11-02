package com.ksyun;

import java.io.IOException;

/**
 * @Author：zongYu.Liu
 * @Date：2024/11/2 12:08
 */
public class MdTest {
    public static void main(String[] args) throws IOException {
        MdGenerator.generateMarkdown("/Users/liuzongyu/Library/Application Support/JetBrains/IntelliJIdea2024.1/scratches", TestController.class);
    }
}
