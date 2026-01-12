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
}
