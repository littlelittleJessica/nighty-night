package com.example.nighty.service;

import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.Daily;
import com.example.nighty.mapper.DailyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/21
 */
@Service
public class AdminService {

    @Resource
    private DailyMapper dailyMapper;


    public ServerResponse updateDaily(Daily daily){
        dailyMapper.insert(daily);
        return ServerResponse.createBySuccess("Update daily succeeded", daily);
    }
}
