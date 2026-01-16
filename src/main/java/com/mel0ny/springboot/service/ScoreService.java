package com.mel0ny.springboot.service;

import com.mel0ny.springboot.pojo.Course;
import com.mel0ny.springboot.pojo.Score;
import com.mel0ny.springboot.pojo.Student;

import java.util.List;

public interface ScoreService {

    /**
     * 获取全部人的成绩信息
     *
     * @return 全部人的成绩信息
     */
    List<Score> getAllScore();

    /**
     * 通过关键词查询成绩
     *
     * @param keyword 关键词
     * @return 成绩集合
     */
    List<Score> selectScoreByKeyword(String keyword);

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

    /**
     * 通过课程id更新成绩
     *
     * @param courseId 课程id
     * @param course   课程对象
     * @return 影响的行数
     */
    int updateScoreByCourseId(Long courseId, Course course);

    /**
     * 通过学生学号更新成绩
     *
     * @param studentId 学生id
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
     */
    void updateScoreByIds(Integer studentId,Integer courseId,Score score);

    /**
     * 新增成绩
     *
     * @param score 成绩对象
     */
    void insertScore(Score score);

}
