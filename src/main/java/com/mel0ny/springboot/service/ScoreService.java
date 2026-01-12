package com.mel0ny.springboot.service;

import com.mel0ny.springboot.mapper.ScoreMapper;
import com.mel0ny.springboot.pojo.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    /**
     * 获得全部人的成绩信息
     *
     * @return 全部人的成绩信息
     */
    public List<Score> getAllScore() {
        return scoreMapper.allScore();
    }
}
