package com.example.nighty.mapper;

import com.example.nighty.domain.Daily;
import com.example.nighty.domain.DailyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyMapper {
    long countByExample(DailyExample example);

    int deleteByExample(DailyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Daily record);

    int insertSelective(Daily record);

    List<Daily> selectByExample(DailyExample example);

    Daily selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Daily record, @Param("example") DailyExample example);

    int updateByExample(@Param("record") Daily record, @Param("example") DailyExample example);

    int updateByPrimaryKeySelective(Daily record);

    int updateByPrimaryKey(Daily record);
}