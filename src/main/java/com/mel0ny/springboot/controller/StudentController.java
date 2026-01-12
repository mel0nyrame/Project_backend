package com.mel0ny.springboot.controller;

import com.mel0ny.springboot.pojo.Result;
import com.mel0ny.springboot.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;


    /**
     * 获取所有学生的信息
     *
     * @return 学生的信息
     */
    @GetMapping("/")
    public Result allStudent() {
        return Result.success(studentServiceImpl.getAllStudent());
    }
}
