package com.mel0ny.springboot.mapper;

import com.mel0ny.springboot.pojo.Score;
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
    @Delete("DELETE FROM student WHERE student_id = #{studentId}")
    int deleteScoreByStudentId(Long studentId);
}
