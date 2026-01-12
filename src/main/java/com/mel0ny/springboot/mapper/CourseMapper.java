package com.mel0ny.springboot.mapper;

import com.mel0ny.springboot.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Select("SELECT course_id,course_name FROM course")
    List<Course> allCourse();

}
