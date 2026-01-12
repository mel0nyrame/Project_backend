package com.mel0ny.springboot.service.impl;

import com.mel0ny.springboot.mapper.ScoreMapper;
import com.mel0ny.springboot.pojo.Score;
import com.mel0ny.springboot.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    /**
     * 获取全部人的成绩信息
     *
     * @return 全部人的成绩信息
     */
    @Override
    public List<Score> getAllScore() {
        return scoreMapper.allScore();
    }
}
