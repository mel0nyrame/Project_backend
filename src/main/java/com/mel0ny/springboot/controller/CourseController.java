package com.mel0ny.springboot.controller;

import com.mel0ny.springboot.pojo.Course;
import com.mel0ny.springboot.pojo.Result;
import com.mel0ny.springboot.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@CrossOrigin(origins = "http://localhost:5173")
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
     * 通过关键词查询课程信息
     *
     * @param keyword 关键词
     * @return 课程对象
     */
    @GetMapping("/search")
    public Result searchCourses(@RequestParam String keyword) {
        List<Course> courses = courseServiceImpl.selectCourseByKeyword(keyword);
        return Result.success(courses);
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

    /**
     * 根据课程id更新课程信息
     *
     * @param courseId 课程id
     * @param course   课程对象
     * @return Result封装的成功结果
     */
    @PutMapping("/{courseId}")
    public Result updateCourse(@PathVariable Long courseId, @RequestBody Course course) {
        courseServiceImpl.updateCourseByCourseId(courseId,course);
        return Result.success();
    }

    @PostMapping("/")
    public Result insertCourse(@RequestBody Course course) {
        courseServiceImpl.insertCourse(course);
        return Result.success();
    }
}
