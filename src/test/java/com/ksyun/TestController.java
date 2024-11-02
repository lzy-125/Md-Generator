package com.ksyun;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author：zongYu.Liu
 * @Date：2024/11/2 12:08
 */
public class TestController {

    @RequestMapping("/test")
    @Markdown
    public TestResp test(TestParam param) {
        return null;
    }
}
