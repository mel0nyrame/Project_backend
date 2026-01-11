package com.mel0ny.springboot.service;

import com.mel0ny.springboot.mapper.StudentMapper;
import com.mel0ny.springboot.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 获取所有学生信息
     *
     * @return 所有学生的信息
     */
    public List<Student> getAllStudent() {
        return studentMapper.allStudent();
    }

}
