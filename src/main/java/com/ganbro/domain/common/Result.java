package com.ganbro.domain.common;

import lombok.Getter;

@Getter
public class Result<T> {
    // 示例中的 Getter 方法，可以根据实际需要添加其他字段的 Getter 方法
    private int code;
    private String message;
    private T data;

    // 私有构造函数，禁止直接创建对象，通过静态方法创建对象
    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 静态方法，创建成功响应对象
    public static <T> Result<T> success(T data) {
        return new Result<>(0, "Success", data);
    }

    // 静态方法，创建失败响应对象
    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(1, message, null);
    }
}
