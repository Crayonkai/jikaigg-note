package com.jikaigg.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result {
    private String success;
    private String code;
    private String message;
    private Object data;

    public static Result ok(Object data) {
        Result result = new Result();
        result.setSuccess("success");
        result.setCode("0000");
        result.setMessage("请求成功");
        result.setData(data);
        return result;
    }

    public static Result fail(String code, String message) {
        Result result = new Result();
        result.setSuccess("fail");
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}
