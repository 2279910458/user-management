package com.example.user.management.util;

import lombok.Data;

@Data
public class DataResult<T> {

    private Integer code;

    private String message;

    private T data;

    private DataResult(){}

    public DataResult(Integer code) {
        this.code = code;
    }

    public DataResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public DataResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public DataResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static<T> DataResult<T> success(){
        return new DataResult(ResponseCode.OK);
    }

    public static<T> DataResult<T> success(T data){
        return new DataResult(ResponseCode.OK, data);
    }

    public static<T> DataResult<T> fail(){
        return new DataResult<>(ResponseCode.ERROR);
    }

    public static<T> DataResult<T> fail(String message){
        return new DataResult<>(ResponseCode.ERROR, message);
    }
}
