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
    score表的映射
 */
public class Score {

    /*
    表中字段:student_id,course_id,score
     */

    private int studentId;
    private int courseId;
    private double score;
}