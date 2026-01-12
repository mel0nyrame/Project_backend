package com.mel0ny.springboot.mapper;

import com.mel0ny.springboot.pojo.Score;
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
}
