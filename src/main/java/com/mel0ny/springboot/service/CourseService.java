package com.mel0ny.springboot.service;

import com.mel0ny.springboot.pojo.Course;

import java.util.List;

public interface CourseService {

    /**
     * 获取全部的课程信息
     *
     * @return 全部的学生信息
     */
    List<Course> getAllCourse();
}
