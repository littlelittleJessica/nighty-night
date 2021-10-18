package com.example.nighty.mapper;

import com.example.nighty.domain.VoiceFile;
import com.example.nighty.domain.VoiceFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoiceFileMapper {
    long countByExample(VoiceFileExample example);

    int deleteByExample(VoiceFileExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VoiceFile record);

    int insertSelective(VoiceFile record);

    List<VoiceFile> selectByExample(VoiceFileExample example);

    VoiceFile selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VoiceFile record, @Param("example") VoiceFileExample example);

    int updateByExample(@Param("record") VoiceFile record, @Param("example") VoiceFileExample example);

    int updateByPrimaryKeySelective(VoiceFile record);

    int updateByPrimaryKey(VoiceFile record);
}