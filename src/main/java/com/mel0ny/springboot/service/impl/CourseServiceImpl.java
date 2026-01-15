package com.mel0ny.springboot.service.impl;

import com.mel0ny.springboot.exception.DataErrorException;
import com.mel0ny.springboot.exception.DataNoFoundException;
import com.mel0ny.springboot.exception.OperationFailureException;
import com.mel0ny.springboot.mapper.CourseMapper;
import com.mel0ny.springboot.mapper.ScoreMapper;
import com.mel0ny.springboot.pojo.Course;
import com.mel0ny.springboot.service.CourseService;
import com.mel0ny.springboot.utils.DataCheckUtil;
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

    @Autowired
    private DataCheckUtil dataCheckUtil;

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
     * 通过课程id查询课程信息
     *
     * @param courseId 课程id
     * @return 课程对象
     */
    @Override
    public Course selectCourseByCourseId(Long courseId) {
        return courseMapper.selectCourseByCourseId(courseId);
    }

    /**
     * 删除学生信息
     * 附带事务回滚
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCourseByCourseId(Long courseId) {
        //查询课程信息
        Course course = courseMapper.selectCourseByCourseId(courseId);

        //若课程信息不存在,则抛出异常
        if (course == null) {
            throw new DataNoFoundException("未找到课程:" + courseId);
        }

        //通过课程id删除成绩
        scoreMapper.deleteScoreByCourseId(courseId);

        //通过课程id删除课程
        int courseRow = courseMapper.deleteCourseByCourseId(courseId);

        //如果变化行数为0,则表明操作失败
        if (courseRow == 0) {
            throw new OperationFailureException("删除课程失败");
        }
    }

    /**
     * 更新课程信息
     *
     * @param courseId 学生学号
     * @param course 课程对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCourseByCourseId(Long courseId,Course course) {
        //查询课程信息
        Course selectCourse = courseMapper.selectCourseByCourseId(courseId);

        //若课程信息不存在,则抛出异常
        if (selectCourse == null) {
            throw new DataNoFoundException("课程信息未找到:" + courseId);
        }

        //检查字段合法性
        String errorMsg = dataCheckUtil.courseChecker(course);
        if (errorMsg != null) {
            throw new DataErrorException(errorMsg);
        }

        //通过课程id更新课程
        int courseRow = courseMapper.updateCourseByCourseId(courseId, course);

        //如果变化行数为0,则表明操作失败
        if (courseRow == 0) {
            throw new OperationFailureException("更新课程信息失败");
        }

        //通过课程id更新成绩
        int scoreRow = scoreMapper.updateScoreByCourseId(courseId, course);

        //如果变化行数不等于实际的成绩行数,则操作失败
        if (scoreRow != scoreMapper.selectCountByCourseId(courseId)) {
            throw new OperationFailureException("更新成绩信息失败");
        }
    }
}
