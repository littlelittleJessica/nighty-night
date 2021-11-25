package com.example.nighty.mapper;

import com.example.nighty.domain.DailyContent;
import com.example.nighty.domain.DailyContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyContentMapper {
    long countByExample(DailyContentExample example);

    int deleteByExample(DailyContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DailyContent record);

    int insertSelective(DailyContent record);

    List<DailyContent> selectByExampleWithBLOBs(DailyContentExample example);

    List<DailyContent> selectByExample(DailyContentExample example);

    DailyContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DailyContent record, @Param("example") DailyContentExample example);

    int updateByExampleWithBLOBs(@Param("record") DailyContent record, @Param("example") DailyContentExample example);

    int updateByExample(@Param("record") DailyContent record, @Param("example") DailyContentExample example);

    int updateByPrimaryKeySelective(DailyContent record);

    int updateByPrimaryKeyWithBLOBs(DailyContent record);
}