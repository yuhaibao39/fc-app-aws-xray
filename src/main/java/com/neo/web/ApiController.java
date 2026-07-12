package com.neo.web;

import com.neo.model.ApiResponse;
import com.neo.model.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * REST API 控制器 —— 提供 JSON 格式的数据接口。
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    private static final List<User> USERS = new ArrayList<>();

    static {
        USERS.add(new User("大牛", 12, "123456"));
        USERS.add(new User("小牛", 6, "123563"));
        USERS.add(new User("Bosicloud", 66, "666666"));
    }

    /**
     * GET /api/users — 获取全部用户列表。
     */
    @GetMapping("/users")
    public ApiResponse<List<User>> getUsers() {
        return ApiResponse.success(USERS);
    }

    /**
     * GET /api/users/{index} — 按索引获取单个用户。
     */
    @GetMapping("/users/{index}")
    public ApiResponse<User> getUser(@PathVariable int index) {
        if (index < 0 || index >= USERS.size()) {
            return ApiResponse.error(404, "User not found at index: " + index);
        }
        return ApiResponse.success(USERS.get(index));
    }

    /**
     * GET /api/info — 获取应用基本信息。
     */
    @GetMapping("/info")
    public ApiResponse<String> info() {
        return ApiResponse.success("fc-app-aws-xray — Spring Boot + Thymeleaf tracing demo");
    }
}
