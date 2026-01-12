package com.mel0ny.springboot.mapper;

import com.mel0ny.springboot.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {


    /**
     * 查询所有学生的信息
     *
     * @return 学生信息集合
     */
    @Select("SELECT student_id,student_name,student_gender,student_birthday,student_phone,student_email,student_major FROM student")
    List<Student> allStudent();

    /**
     * 通过学生学号查询学生信息
     *
     * @param studentId 学生学号
     * @return 学生对象
     */
    @Select("SELECT student_id,student_name,student_gender,student_birthday,student_phone,student_email,student_major FROM student WHERE student_id = #{studentId}")
    Student selectStudentByStudentId(Long studentId);

    /**
     * 通过学生学号删除学生信息
     *
     * @param studentId 学生学号
     * @return 影响的行数
     */
    @Delete("DELETE FROM student WHERE student_id = #{studentId}")
    int deleteStudentByStudentId(Long studentId);
}
