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
    student表的映射
 */

public class Student {

    /*
    表中字段:student_id,student_name,student_gender,student_birthday,student_phone,student_email,student_major
     */

    private long studentId;
    private String studentName;
    private String studentGender;
    private String studentBirthday;
    private long studentPhone;
    private String studentEmail;
    private String studentMajor;
}