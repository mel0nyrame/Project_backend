package com.mel0ny.springboot.mapper;

import com.mel0ny.springboot.pojo.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {

    /**
     * 查询所有的课程信息
     *
     * @return 课程信息集合
     */
    @Select("SELECT course_id,course_name FROM course")
    List<Course> allCourse();

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
     * @return 课程信息对象
     */
    @Select("SELECT course_id,course_name FROM course WHERE course_id = #{courseId}")
    Course selectCourseByCourseId(Long courseId);

    /**
     * 通过课程id删除课程信息
     *
     * @param courseId 课程id
     * @return 影响的行数
     */
    @Delete("DELETE FROM course WHERE course_id = #{courseId}")
    int deleteCourseByCourseId(Long courseId);

    /**
     * 通过课程id更新课程信息
     *
     * @param courseId 课程id
     * @param course   课程信息对象
     * @return 影响的行数
     */
    int updateCourseByCourseId(Long courseId,Course course);

}
