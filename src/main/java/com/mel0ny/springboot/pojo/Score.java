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
    score表的映射
 */
public class Score {

    /*
    表中字段:student_id,course_id,score
     */

    private Long studentId;
    private Long courseId;
    private Double score;
}