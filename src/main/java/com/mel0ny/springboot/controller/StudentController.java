package com.mel0ny.springboot.controller;

import com.mel0ny.springboot.mapper.StudentMapper;
import com.mel0ny.springboot.pojo.Result;
import com.mel0ny.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    /**
     * 获取所有学生的信息
     *
     * @return 学生的信息
     */
    @GetMapping("/")
    public Result allUser() {
        return Result.success(studentService.getAllStudent());
    }
}
