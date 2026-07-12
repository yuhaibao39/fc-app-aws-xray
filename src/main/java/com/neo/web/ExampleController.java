package com.neo.web;

import com.neo.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Thymeleaf 示例控制器 —— 演示常用模板语法。
 */
@Controller
public class ExampleController {

    /**
     * GET /string — 字符串与表达式示例。
     */
    @GetMapping("/string")
    public String string(ModelMap map) {
        map.addAttribute("userName", "ityouknow");
        return "string";
    }

    /**
     * GET /if — 条件判断 th:if / th:unless。
     */
    @GetMapping("/if")
    public String ifunless(ModelMap map) {
        map.addAttribute("flag", "yes");
        return "if";
    }

    /**
     * GET /list — th:each 循环遍历 Users 列表。
     */
    @GetMapping("/list")
    public String list(ModelMap map) {
        map.addAttribute("users", getUserList());
        return "list";
    }

    /**
     * GET /url — URL 表达式构建示例。
     */
    @GetMapping("/url")
    public String url(ModelMap map) {
        map.addAttribute("type", "link");
        map.addAttribute("pageId", "springcloud/2017/09/11/");
        map.addAttribute("img", "http://www.ityouknow.com/assets/images/neo.jpg");
        return "url";
    }

    /**
     * GET /eq — 比较运算与三元表达式示例。
     */
    @GetMapping("/eq")
    public String eq(ModelMap map) {
        map.addAttribute("name", "neo");
        map.addAttribute("age", 30);
        map.addAttribute("flag", "yes");
        return "eq";
    }

    /**
     * GET /switch — th:switch / th:case 分支示例。
     */
    @GetMapping("/switch")
    public String switchcase(ModelMap map) {
        map.addAttribute("sex", "woman");
        return "switch";
    }

    private List<User> getUserList() {
        List<User> list = new ArrayList<>();
        list.add(new User("大牛", 12, "123456"));
        list.add(new User("小牛", 6, "123563"));
        list.add(new User("Bosicloud", 66, "666666"));
        return list;
    }

}
