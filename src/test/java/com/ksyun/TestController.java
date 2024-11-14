package com.ksyun;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author：zongYu.Liu
 * @Date：2024/11/2 12:08
 */
@Markdown
public class TestController {

    @RequestMapping("/test")
    public TestResp test(TestParam param) {
        return null;
    }

    @RequestMapping("/test")
    public TestResp test2(TestParam param) {
        return null;
    }
}
