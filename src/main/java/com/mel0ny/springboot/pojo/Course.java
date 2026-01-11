package com.mel0ny.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/*
@Data:设置全部字段的setter和getter方法
@AllArgsConstructor:有参构造器
@NoArgsConstructor:无参构造器
@Repository:将对象交给容器管理
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository

/*
 course表的映射
 */
public class Course {

    /*
    表中字段:course_id,course_name
     */

    private long courseId;
    private String courseName;
}
