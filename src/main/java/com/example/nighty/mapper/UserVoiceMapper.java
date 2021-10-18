package com.example.nighty.mapper;

import com.example.nighty.domain.UserVoice;
import com.example.nighty.domain.UserVoiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserVoiceMapper {
    long countByExample(UserVoiceExample example);

    int deleteByExample(UserVoiceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserVoice record);

    int insertSelective(UserVoice record);

    List<UserVoice> selectByExample(UserVoiceExample example);

    UserVoice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserVoice record, @Param("example") UserVoiceExample example);

    int updateByExample(@Param("record") UserVoice record, @Param("example") UserVoiceExample example);

    int updateByPrimaryKeySelective(UserVoice record);

    int updateByPrimaryKey(UserVoice record);
}