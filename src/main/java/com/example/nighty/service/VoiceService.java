package com.example.nighty.service;

import com.example.nighty.Req.PageReq;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.Voice;
import com.example.nighty.domain.VoiceExample;
import com.example.nighty.mapper.VoiceMapper;
import com.example.nighty.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
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
        if (!StringUtils.isEmpty(catogory) && catogory.length() > 0) {
            voiceExample.createCriteria().andCategoryEqualTo(catogory);
            List<Voice> voiceList = voiceMapper.selectByExample(voiceExample);
            return CopyUtil.copyList(voiceList, Voice.class);
        } else {
            String[] categorys = {"M","S","W"};
            voiceExample.createCriteria().andCategoryIn(Arrays.asList(categorys));
            List<Voice> voiceList = voiceMapper.selectByExample(voiceExample);
            return CopyUtil.copyList(voiceList, Voice.class);
        }
    }

    /**
     * save voice
     */
    public ServerResponse save(Voice voice) {
        if (StringUtils.isEmpty(voice.getId())) {
            voiceMapper.insert(voice);

            VoiceExample voiceExample = new VoiceExample();
            VoiceExample.Criteria criteria = voiceExample.createCriteria();
            criteria.andNameEqualTo(voice.getName());
            List<Voice> voiceList = voiceMapper.selectByExample(voiceExample);
            Long id = voiceList.get(0).getId();

            voice.setId(id);
        } else {
            voiceMapper.updateByPrimaryKey(voice);

        }
        return ServerResponse.createBySuccess("Save voice succeeded", voice);
    }

    /**
     * delete
     */
    public void delete(Long id) {
        voiceMapper.deleteByPrimaryKey(id);
    }

    /**
     * save cover
     */
    public ServerResponse saveCover(String imageFilePath, Long id) {
        Voice voice = voiceMapper.selectByPrimaryKey(id);
        voice.setCover(imageFilePath);
        return ServerResponse.createBySuccess("Update cover succeeded", voice);
    }
}
