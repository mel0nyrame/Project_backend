package com.mel0ny.springboot.service.impl;

import com.mel0ny.springboot.mapper.CourseMapper;
import com.mel0ny.springboot.pojo.Course;
import com.mel0ny.springboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 获得全部的课程信息
     *
     * @return 全部的学生信息
     */
    @Override
    public List<Course> getAllCourse() {
        return courseMapper.allCourse();
    }
}
