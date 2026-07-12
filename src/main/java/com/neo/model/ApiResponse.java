package com.neo.model;

/**
 * 统一 API 响应体，所有 REST 接口均使用此结构返回数据。
 *
 * @param <T> 响应数据的类型
 */
public class ApiResponse<T> {

    /** 状态码，200 表示成功 */
    private int code;

    /** 状态描述信息 */
    private String message;

    /** 响应数据负载 */
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构建成功响应。
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "success", data);
    }

    /**
     * 构建错误响应。
     */
    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    // --- getters & setters ---

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
