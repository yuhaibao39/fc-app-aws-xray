package com.neo.web;

import com.neo.model.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查控制器 —— 为 Kubernetes 就绪探针 / 存活探针提供端点。
 */
@RestController
public class HealthController {

    /**
     * GET /api/health — 健康检查，返回服务运行状态。
     */
    @GetMapping("/api/health")
    public ApiResponse<String> health() {
        return ApiResponse.success("UP");
    }
}
