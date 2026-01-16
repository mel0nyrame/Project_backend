package com.mel0ny.springboot.service;

import com.mel0ny.springboot.pojo.Student;

import java.util.List;

public interface StudentService {

    /**
     * 获取所有学生信息
     *
     * @return 所有学生的信息
     */
    List<Student> getAllStudent();

    /**
     * 通过学生学号查询学生信息
     *
     * @param studentId 学生学号
     * @return 学生对象
     */
    Student selectStudentByStudentId(Long studentId);

    /**
     * 通过关键词查询学生信息
     *
     * @param keyword 关键词
     * @return 学生集合
     */
    List<Student> selectStudentByKeyword(String keyword);

    /**
     * 通过学生学号删除学生信息
     *
     * @param studentId 学生学号
     */
    void deleteStudentByStudentId(Long studentId);

    /**
     * 通过学生学号更新学生信息
     *
     * @param studentId 学生学号
     * @param student   学生信息
     */
    void updateStudentByStudentId(Long studentId,Student student);
}
