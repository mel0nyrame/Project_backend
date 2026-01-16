package com.mel0ny.springboot.service.impl;

import com.mel0ny.springboot.exception.DataErrorException;
import com.mel0ny.springboot.exception.DataNoFoundException;
import com.mel0ny.springboot.exception.OperationFailureException;
import com.mel0ny.springboot.mapper.CourseMapper;
import com.mel0ny.springboot.mapper.ScoreMapper;
import com.mel0ny.springboot.mapper.StudentMapper;
import com.mel0ny.springboot.pojo.Course;
import com.mel0ny.springboot.pojo.Student;
import com.mel0ny.springboot.service.StudentService;
import com.mel0ny.springboot.utils.DataCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private DataCheckUtil dataCheckUtil;

    /**
     * 获得所有学生信息
     *
     * @return 所有学生的信息
     */
    @Override
    public List<Student> getAllStudent() {
        return studentMapper.allStudent();
    }

    /**
     * 通过学生学号查询学生信息
     *
     * @param studentId 学生学号
     * @return 学生对象
     */
    @Override
    public Student selectStudentByStudentId(Long studentId) {
        return studentMapper.selectStudentByStudentId(studentId);
    }

    /**
     * 通过关键词查询学生信息
     *
     * @param keyword 关键词
     * @return 学生集合
     */
    @Override
    public List<Student> selectStudentByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return studentMapper.allStudent();
        }
        return studentMapper.selectStudentByKeyword(keyword.trim());
    }


    /**
     * 删除学生信息
     * 附带事务回滚
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteStudentByStudentId(Long studentId) {
        //查询学生信息
        Student student = studentMapper.selectStudentByStudentId(studentId);

        //若学生信息不存在,则抛出异常
        if (student == null) {
            throw new DataNoFoundException("学生数据未找到:" + studentId);
        }

        //通过学号删除学生成绩信息
        int scoreRow = scoreMapper.deleteScoreByStudentId(studentId);

        //若变化行数为0,则表明操作失败
        if (scoreRow == 0) {
            throw new OperationFailureException("删除学生失败");
        }

        //通过学号删除学生信息
        int studentRow = studentMapper.deleteStudentByStudentId(studentId);

        //若变化行数为0,则表明操作失败
        if (studentRow == 0) {
            throw new OperationFailureException("删除学生失败");
        }
    }

    /**
     * 通过学生学号更新学生信息
     *
     * @param studentId 学生学号
     * @param student   学生信息对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStudentByStudentId(Long studentId, Student student) {
        //查询学生信息
        Student selectStudent = studentMapper.selectStudentByStudentId(studentId);

        //若学生信息不存在,则抛出异常
        if (selectStudent == null) {
            throw new DataNoFoundException("未找到学生:" + studentId);
        }

        //检查字段合法性
        String errorMsg = dataCheckUtil.studentChecker(student);
        if (errorMsg != null) {
            throw new DataErrorException(errorMsg);
        }

        //通过学号更新学生基本信息
        int studentRow = studentMapper.updateStudentByStudentId(studentId, student);

        //若变化行数为0,则表明操作失败
        if (studentRow == 0) {
            throw new OperationFailureException("更新学生失败");
        }

        //通过学号更新学生成绩信息
        int scoreRow = scoreMapper.updateScoreByStudentId(studentId, student);

        //若更新的行数不等于成绩行数,则表明操作失败
        if (scoreRow != scoreMapper.selectCountByStudentId(studentId)) {
            throw new OperationFailureException("更新学生成绩失败");
        }
    }

}
