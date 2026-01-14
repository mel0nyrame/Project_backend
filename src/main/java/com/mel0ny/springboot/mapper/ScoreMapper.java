package com.mel0ny.springboot.mapper;

import com.mel0ny.springboot.pojo.Course;
import com.mel0ny.springboot.pojo.Score;
import com.mel0ny.springboot.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScoreMapper {

    /**
     * 查询所有学生的成绩
     *
     * @return 学生成绩的集合
     */
    @Select("SELECT student_id,course_id,score FROM score")
    List<Score> allScore();

    /**
     * 通过学生学号查看成绩数目
     *
     * @param studentId 学生学号
     * @return 成绩数目
     */
    @Select("SELECT COUNT(*) FROM score WHERE student_id = #{studentId}")
    int selectCountByStudentId(Long studentId);

    /**
     * 通过课程id查看成绩数目.
     *
     * @param courseId 课程id
     * @return 成绩数目
     */
    @Select("SELECT COUNT(*) FROM score WHERE course_id = #{courseId}")
    int selectCountByCourseId(Long courseId);

    /**
     * 通过学生学号查询学生成绩
     *
     * @param studentId 学生学号
     * @return 成绩对象
     */
    @Select("SELECT student_id,course_id,score FROM score WHERE student_id = #{studentId}")
    Score selectScoreByStudentId(Long studentId);

    /**
     * 通过学生学号删除成绩
     *
     * @param studentId 学生学号
     * @return 影响的行数
     */
    @Delete("DELETE FROM score WHERE student_id = #{studentId}")
    int deleteScoreByStudentId(Long studentId);


    /**
     * 通过课程id删除成绩
     *
     * @param courseId 课程id
     * @return 影响的行数
     */
    @Delete("DELETE FROM score WHERE course_id = #{courseId}")
    int deleteScoreByCourseId(Long courseId);

    /**
     * 通过课程id更新成绩信息
     *
     * @param courseId 课程id
     * @param course   课程对象
     * @return 影响的行数
     */
    int updateScoreByCourseId(Long courseId, Course course);

    /**
     * 通过学生学号更新成绩信息
     *
     * @param studentId 学生学号
     * @param student   学生对象
     * @return 影响的行数
     */
    int updateScoreByStudentId(Long studentId, Student student);

    /**
     * 根据主键更新学生成绩
     *
     * @param studentId 学生学号
     * @param courseId  课程id
     * @param score     成绩对象
     * @return 影响的行数
     */
    int updateScoreByIds(Integer studentId,Integer courseId,Score score);

}
