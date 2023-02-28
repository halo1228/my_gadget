package com.lg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuga
 * @date 2023/02/08 11:36
 * Description:
 */
@RestController
@RequestMapping("/test/hello")
public class HelloController {

    @GetMapping("get")
    public String get() {
        return "hello";
    }
}
