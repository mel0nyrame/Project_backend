package com.mel0ny.springboot.controller;

import com.mel0ny.springboot.pojo.Result;
import com.mel0ny.springboot.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    /**
     * 获取所有学生的成绩信息
     *
     * @return 所有学生的成绩信息
     */
    @GetMapping("/")
    public Result allScore() {
        return Result.success(scoreService.getAllScore());
    }
}
