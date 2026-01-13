package com.mel0ny.springboot.service;

import com.mel0ny.springboot.pojo.Score;

import java.util.List;

public interface ScoreService {

    /**
     * 获取全部人的成绩信息
     *
     * @return 全部人的成绩信息
     */
    List<Score> getAllScore();

    /**
     * 通过学生学号查询学生成绩
     *
     * @param studentId 学生学号
     * @return 成绩对象
     */
    Score selectScoreByStudentId(Long studentId);

    /**
     * 通过学生学号删除成绩
     *
     * @param studentId 学生学号
     * @return 影响的行数
     */
    int deleteScoreByStudentId(Long studentId);

    /**
     * 通过课程id删除成绩
     *
     * @param courseId 课程id
     * @return 影响的行数
     */
    int deleteScoreByCourseId(Long courseId);
}
