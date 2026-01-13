package com.mel0ny.springboot.controller;

import com.mel0ny.springboot.pojo.Result;
import com.mel0ny.springboot.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseServiceImpl;

    /**
     * 获取所有课程的信息
     *
     * @return 所有课程的信息
     */
    @GetMapping("/")
    public Result allCourse() {
        return Result.success(courseServiceImpl.getAllCourse());
    }

    /**
     * 根据课程id删除课程信息
     *
     * @param courseId 课程id
     * @return Result封装的成功结果
     */
    @DeleteMapping("/{courseId}")
    public Result deleteCourse(@PathVariable Long courseId) {
        courseServiceImpl.deleteCourseByCourseId(courseId);
        return Result.success();
    }
}
