package com.neo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页控制器 —— 渲染首页欢迎信息。
 */
@Controller
public class HelloController {

    /**
     * GET / — 首页，展示欢迎信息。
     */
    @GetMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("message", "Feng Ce project");
        return "hello";
    }

}
