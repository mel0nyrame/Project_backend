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
     * 删除学生信息
     */
    void deleteStudent(Long studentId);

    /**
     * 通过学生学号更新学生信息
     *
     * @param studentId 学生学号
     * @param student   学生信息
     * @return 变化的行数
     */
    int updateStudentByStudentId(Long studentId,Student student);
}
