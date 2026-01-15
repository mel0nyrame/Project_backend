package com.mel0ny.springboot.service.impl;

import com.mel0ny.springboot.exception.DataErrorException;
import com.mel0ny.springboot.exception.OperationFailureException;
import com.mel0ny.springboot.mapper.ScoreMapper;
import com.mel0ny.springboot.pojo.Course;
import com.mel0ny.springboot.pojo.Score;
import com.mel0ny.springboot.pojo.Student;
import com.mel0ny.springboot.service.ScoreService;
import com.mel0ny.springboot.utils.DataCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private DataCheckUtil dataCheckUtil;

    /**
     * 获取全部人的成绩信息
     *
     * @return 全部人的成绩信息
     */
    @Override
    public List<Score> getAllScore() {
        return scoreMapper.allScore();
    }

    /**
     * 通过学生学号查询学生成绩
     *
     * @param studentId 学生学号
     * @return 成绩对象
     */
    @Override
    public Score selectScoreByStudentId(Long studentId) {
        return scoreMapper.selectScoreByStudentId(studentId);
    }

    /**
     * 通过学生学号删除成绩
     *
     * @param studentId 学生学号
     * @return 影响的行数
     */

    @Override
    public int deleteScoreByStudentId(Long studentId) {
        return scoreMapper.deleteScoreByStudentId(studentId);
    }

    /**
     * 通过课程id删除成绩
     *
     * @param courseId 课程id
     * @return 影响的行数
     */
    @Override
    public int deleteScoreByCourseId(Long courseId) {
        return scoreMapper.deleteScoreByCourseId(courseId);
    }


    /**
     * 通过课程id更新成绩
     *
     * @param courseId 课程id
     * @param course 课程对象
     * @return 影响的行数
     */
    @Override
    public int updateScoreByCourseId(Long courseId, Course course) {
        return scoreMapper.updateScoreByCourseId(courseId, course);
    }

    /**
     * 通过课程id删除成绩
     *
     * @param studentId 学生学号
     * @param student 学生对象
     * @return 影响的行数
     */
    @Override
    public int updateScoreByStudentId(Long studentId, Student student) {
        return scoreMapper.updateScoreByStudentId(studentId, student);
    }

    /**
     * 根据主键更新学生成绩
     *
     * @param studentId 学生学号
     * @param courseId  课程id
     * @param score     成绩对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateScoreByIds(Integer studentId, Integer courseId, Score score) {

        //检查字段合法性
        String errorMsg = dataCheckUtil.scoreChecker(score);
        if (errorMsg != null) {
            throw new DataErrorException(errorMsg);
        }

        int row = scoreMapper.updateScoreByIds(studentId, courseId, score);

        if (row == 0) {
            throw new OperationFailureException("操作失败");
        }
    }

}
