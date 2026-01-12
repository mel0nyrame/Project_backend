package com.mel0ny.springboot.service.impl;

import com.mel0ny.springboot.exception.DataNoFoundException;
import com.mel0ny.springboot.exception.OperationFailureException;
import com.mel0ny.springboot.mapper.CourseMapper;
import com.mel0ny.springboot.mapper.ScoreMapper;
import com.mel0ny.springboot.pojo.Course;
import com.mel0ny.springboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    /**
     * 获取全部的课程信息
     *
     * @return 全部的学生信息
     */
    @Override
    public List<Course> getAllCourse() {
        return courseMapper.allCourse();
    }

    /**
     * 删除学生信息
     * 附带事务回滚
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCourse(Long courseId) {
        //查询课程信息
        Course course = courseMapper.selectCourseByCourseId(courseId);

        //若课程信息不存在,则抛出异常
        if (course == null) {
            throw new DataNoFoundException("未找到课程:" + courseId);
        }

        //通过课程id删除成绩
        int scoreRow = scoreMapper.deleteScoreByCourseId(courseId);

        //若变化的行数小于成绩的行数,则表明操作失败
        if (scoreRow < scoreMapper.selectCountByCourseId(courseId)) {
            throw new OperationFailureException("删除成绩失败,操作成功的数目小于原有的数目");
        }

        //通过课程id删除课程
        int courseRow = courseMapper.deleteCourseByCourseId(courseId);

        //如果变化行数为0,则表明操作失败
        if (courseRow == 0) {
            throw new OperationFailureException("删除课程失败");
        }
    }
}
