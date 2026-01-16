package com.mel0ny.springboot.controller;

import com.mel0ny.springboot.pojo.Result;
import com.mel0ny.springboot.pojo.Score;
import com.mel0ny.springboot.service.impl.ScoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/score")
@CrossOrigin(origins = "http://localhost:5173")
public class ScoreController {

    @Autowired
    private ScoreServiceImpl scoreServiceImpl;

    /**
     * 获取所有学生的成绩信息
     *
     * @return 所有学生的成绩信息
     */
    @GetMapping("/")
    public Result allScore() {
        return Result.success(scoreServiceImpl.getAllScore());
    }

    /**
     * 通过关键词查询成绩信息
     *
     * @param keyword 关键词
     * @return 成绩对象
     */
    @GetMapping("/search")
    public Result searchScores(@RequestParam String keyword) {
        List<Score> scores = scoreServiceImpl.selectScoreByKeyword(keyword);
        return Result.success(scores);
    }

    /**
     * 根据主键更新学生成绩
     *
     * @param studentId 学生学号
     * @param courseId  课程id
     * @param score     成绩对象
     * @return Result封装的成功结果
     */
    @PutMapping("/{studentId}/{courseId}")
    public Result updateScore(@PathVariable Integer studentId, @PathVariable Integer courseId, @RequestBody Score score) {
        scoreServiceImpl.updateScoreByIds(studentId, courseId, score);
        return Result.success();
    }

    /**
     * 通过学生学号删除成绩
     *
     * @param studentId 学生学号
     * @return Result封装的成功结果
     */
    @DeleteMapping("/{studentId}")
    public Result deleteScore(@PathVariable Long studentId){
        scoreServiceImpl.deleteScoreByStudentId(studentId);
        return Result.success();
    }

    @PostMapping("/")
    public Result insertScore(@RequestBody Score score) {
        scoreServiceImpl.insertScore(score);
        return Result.success();
    }
}
