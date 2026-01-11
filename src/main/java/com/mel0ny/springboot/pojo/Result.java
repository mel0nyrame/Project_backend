package com.mel0ny.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    @Data:设置全部字段的setter和getter方法
    @AllArgsConstructor:有参构造器
    @NoArgsConstructor:无参构造器
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

/*
    统一返回给前端的结果
 */
public class Result {

    /*
        分别为状态码,返回信息,返回数据
     */

    private Integer id;
    private String msg;
    private Object data;

    /**
     * Success result.
     *
     * @return the result
     */
    public static Result success() {
        return new Result(1,"success",null);
    }

    /**
     * Success result.
     *
     * @param data the data
     * @return the result
     */
    public static Result success(Object data){
        return new Result(1,"success",data);
    }

    /**
     * Success result.
     *
     * @param msg  the msg
     * @param data the data
     * @return the result
     */
    public static Result success(String msg,Object data) {
        return new Result(1,msg,data);
    }

    /**
     * Error result.
     *
     * @return the result
     */
    public static Result error() {
        return new Result(0,"error",null);
    }

    /**
     * Error result.
     *
     * @param data the data
     * @return the result
     */
    public static Result error(Object data){
        return new Result(0,"error",data);
    }

    /**
     * Error result.
     *
     * @param msg  the msg
     * @param data the data
     * @return the result
     */
    public static Result error(String msg,Object data) {
        return new Result(0,msg,data);
    }
}
