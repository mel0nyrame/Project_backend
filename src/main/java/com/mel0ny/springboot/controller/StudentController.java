package com.mel0ny.springboot.controller;

import com.mel0ny.springboot.pojo.Result;
import com.mel0ny.springboot.pojo.Student;
import com.mel0ny.springboot.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;


    /**
     * 获取所有学生的信息
     *
     * @return 学生的信息
     */
    @GetMapping("/")
    public Result allStudent() {
        return Result.success(studentServiceImpl.getAllStudent());
    }

    /**
     * 通过关键词查询学生信息
     *
     * @param keyword 关键词
     * @return 学生对象
     */
    @GetMapping("/search")
    public Result searchStudents(@RequestParam String keyword) {
        List<Student> students = studentServiceImpl.selectStudentByKeyword(keyword);
        return Result.success(students);
    }

    /**
     * 获取学生信息
     *
     * @param studentId 学生学号
     * @return Result类封装的学生对象
     */
    @GetMapping("/{studentId}")
    public Result selectStudent(@PathVariable Long studentId) {
        return Result.success(studentServiceImpl.selectStudentByStudentId(studentId));
    }

    /**
     * 删除学生信息
     *
     * @param studentId 学生学号
     * @return Result类封装的成功结果
     */
    @DeleteMapping("/{studentId}")
    public Result deleteStudent(@PathVariable Long studentId) {
        studentServiceImpl.deleteStudentByStudentId(studentId);
        return Result.success();
    }

    /**
     * 更新学生信息
     *
     * @param studentId 学生学号
     * @param student   学生对象
     * @return Result类封装的成功结果
     */
    @PutMapping("/{studentId}")
    public Result updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
        studentServiceImpl.updateStudentByStudentId(studentId, student);
        return Result.success();
    }

    /**
     * 插入学生信息
     *
     * @param student 学生对象
     * @return Result封装的成功结果
     */
    @PostMapping("/")
    public Result insertStudent(@RequestBody Student student) {
        studentServiceImpl.insertStudent(student);
        return Result.success();
    }
}
