package com.mel0ny.springboot.service;

import com.mel0ny.springboot.mapper.CourseMapper;
import com.mel0ny.springboot.pojo.Course;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 获得全部的课程信息
     *
     * @return 全部的学生信息
     */
    public List<Course> getAllCourse() {
        return courseMapper.allCourse();
    }
}
