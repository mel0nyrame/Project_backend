package com.mel0ny.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer id;
    private String msg;
    private Object data;

    public static Result success() {
        return new Result(1,"success",null);
    }

    public static Result success(Object data){
        return new Result(1,"success",data);
    }

    public static Result success(String msg,Object data) {
        return new Result(1,msg,data);
    }

    public static Result error() {
        return new Result(0,"error",null);
    }

    public static Result error(Object data){
        return new Result(0,"error",data);
    }

    public static Result error(String msg,Object data) {
        return new Result(0,msg,data);
    }
}
