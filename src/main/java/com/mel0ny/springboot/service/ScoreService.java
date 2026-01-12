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
}
