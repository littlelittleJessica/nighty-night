package com.example.nighty.service;

import com.example.nighty.Req.PageReq;
import com.example.nighty.common.VoiceCategory;
import com.example.nighty.domain.Voice;
import com.example.nighty.domain.VoiceExample;
import com.example.nighty.mapper.VoiceMapper;
import com.example.nighty.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/18
 */
@Service
public class VoiceService {

    @Resource
    private VoiceMapper voiceMapper;

    /**
     * query voice by category
     */
    public List<Voice> listByCategory(PageReq pageReq, String catogory) {
        PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        VoiceExample voiceExample = new VoiceExample();
        voiceExample.createCriteria().andCategoryEqualTo(catogory);
        List<Voice> voiceList = voiceMapper.selectByExample(voiceExample);
        return CopyUtil.copyList(voiceList, Voice.class);
    }
}
