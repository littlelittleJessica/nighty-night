package com.example.nighty.mapper;

import com.example.nighty.domain.role;
import com.example.nighty.domain.roleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface roleMapper {
    long countByExample(roleExample example);

    int deleteByExample(roleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(role record);

    int insertSelective(role record);

    List<role> selectByExample(roleExample example);

    role selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") role record, @Param("example") roleExample example);

    int updateByExample(@Param("record") role record, @Param("example") roleExample example);

    int updateByPrimaryKeySelective(role record);

    int updateByPrimaryKey(role record);
}