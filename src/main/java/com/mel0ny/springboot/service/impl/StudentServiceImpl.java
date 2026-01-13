package com.mel0ny.springboot.service.impl;

import com.mel0ny.springboot.exception.DataNoFoundException;
import com.mel0ny.springboot.exception.OperationFailureException;
import com.mel0ny.springboot.mapper.ScoreMapper;
import com.mel0ny.springboot.mapper.StudentMapper;
import com.mel0ny.springboot.pojo.Student;
import com.mel0ny.springboot.service.StudentService;
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
     * 删除学生信息
     * 附带事务回滚
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteStudent(Long studentId) {
        //查询学生信息
        Student student = studentMapper.selectStudentByStudentId(studentId);

        //若学生信息不存在,则抛出异常
        if (student == null) {
            throw new DataNoFoundException("学生数据未找到:" + studentId);
        }

        //通过学号删除学生成绩信息
        int ScoreRow = scoreMapper.deleteScoreByStudentId(studentId);

        //若变化行数为0,则表明操作失败
        if (ScoreRow == 0) {
            throw new OperationFailureException("删除学生失败");
        }

        //通过学号删除学生信息
        int StudentRow = studentMapper.deleteStudentByStudentId(studentId);

        //若变化行数为0,则表明操作失败
        if (StudentRow == 0) {
            throw new OperationFailureException("删除学生失败");
        }
    }

    /**
     * 通过学生学号更新学生信息
     *
     * @param studentId 学生学号
     * @param student   学生信息
     * @return 变化的行数
     */
    @Override
    public int updateStudentByStudentId(Long studentId, Student student) {
        return studentMapper.updateStudentByStudentId(studentId, student);
    }

}
