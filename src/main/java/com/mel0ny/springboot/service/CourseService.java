package com.mel0ny.springboot.service;

import com.mel0ny.springboot.pojo.Course;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseService {

    /**
     * 获取全部的课程信息
     *
     * @return 全部的学生信息
     */
    List<Course> getAllCourse();

    /**
     * 通过关键词查询课程信息
     *
     * @param keyword 关键词
     * @return 课程集合
     */
    List<Course> selectCourseByKeyword(String keyword);

    /**
     * 通过课程id查询课程信息
     *
     * @param courseId 课程id
     * @return 课程对象
     */
    @Select("SELECT course_id,course_name FROM course")
    Course selectCourseByCourseId(Long courseId);

    /**
     * 删除课程信息
     *
     * @param courseId 课程id
     */
    void deleteCourseByCourseId(Long courseId);

    /**
     * 更新课程信息
     *
     * @param courseId 学生学号
     */
    void updateCourseByCourseId(Long courseId,Course course);

    /**
     * 插入课程
     *
     * @param course 课程对象
     */
    void insertCourse(Course course);
}
