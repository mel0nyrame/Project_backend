package com.mel0ny.springboot.mapper;

import com.mel0ny.springboot.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {


    /**
     * 查询所有学生
     *
     * @return 学生集合
     */
    @Select("SELECT student_id,student_name,student_gender,student_birthday,student_phone,student_email,student_major FROM student")
    List<Student> allStudent();
}
