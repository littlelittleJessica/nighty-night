package com.example.nighty.service;

import com.example.nighty.Req.PageReq;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.Voice;
import com.example.nighty.domain.VoiceExample;
import com.example.nighty.mapper.VoiceMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageReq listByCategory(PageReq pageReq, String category) {
        PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        VoiceExample voiceExample = new VoiceExample();
        if ("All".equals(category)) {
            String[] categorys = {"M", "S", "W"};
            voiceExample.createCriteria().andCategoryIn(Arrays.asList(categorys));
            List<Voice> voiceList = voiceMapper.selectByExample(voiceExample);
            PageInfo<Voice> pageInfo = new PageInfo<>(voiceList);
            pageReq.setTotal(pageInfo.getTotal());
            pageReq.setList(voiceList);
            return pageReq;
        } else if (!StringUtils.isEmpty(category) && category.length() > 0) {
            voiceExample.createCriteria().andCategoryEqualTo(category);
            List<Voice> voiceList = voiceMapper.selectByExample(voiceExample);
            PageInfo<Voice> pageInfo = new PageInfo<>(voiceList);
            pageReq.setTotal(pageInfo.getTotal());
            pageReq.setList(voiceList);
            return pageReq;
        } else {
            return null;
        }
    }

    /**
     * search voice by name
     */
    public PageReq searchByName(PageReq pageReq, String name) {
        PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        VoiceExample voiceExample = new VoiceExample();
        voiceExample.createCriteria().andNameLike("%" + name + "%");
        List<Voice> voiceList = voiceMapper.selectByExample(voiceExample);
        PageInfo<Voice> pageInfo = new PageInfo<>(voiceList);
        pageReq.setTotal(pageInfo.getTotal());
        pageReq.setList(voiceList);
        return pageReq;
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
