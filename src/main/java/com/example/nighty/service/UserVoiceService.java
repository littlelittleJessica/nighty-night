package com.example.nighty.service;

import com.example.nighty.Req.PageReq;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.*;
import com.example.nighty.mapper.UserVoiceMapper;
import com.example.nighty.mapper.VoiceMapper;
import com.example.nighty.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
public class UserVoiceService {

    private static final Logger LOG = LoggerFactory.getLogger(UserVoiceService.class);

    @Resource
    private UserVoiceMapper userVoiceMapper;

    @Resource
    private VoiceMapper voiceMapper;

    /**
     * List the favorite voice of the user by category
     */
    public PageReq listFavorite(User user, PageReq pageReq, String category) {

        UserVoiceExample userVoiceExample = new UserVoiceExample();
        userVoiceExample.createCriteria().andUserIdEqualTo(user.getId());
        List<UserVoice> userVoiceList = userVoiceMapper.selectByExample(userVoiceExample);

        Long[] voiceId = new Long[userVoiceList.size()];
        if (!CollectionUtils.isEmpty(userVoiceList) && userVoiceList.size() > 0) {
            for (int i = 0; i < voiceId.length; i++) {
                voiceId[i] = userVoiceList.get(i).getVoiceId();
            }
        }
        PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        VoiceExample voiceExample = new VoiceExample();
        voiceExample.createCriteria().andCategoryEqualTo(category)
                .andIdIn(Arrays.asList(voiceId));
        List<Voice> voiceList = voiceMapper.selectByExample(voiceExample);

        PageInfo<Voice> pageInfo = new PageInfo<>(voiceList);
        pageReq.setTotal(pageInfo.getTotal());
        pageReq.setList(voiceList);
        return pageReq;
    }

    /**
     * search voice by name
     */
    public PageReq searchByName(User user, PageReq pageReq, String name) {
        UserVoiceExample userVoiceExample = new UserVoiceExample();
        userVoiceExample.createCriteria().andUserIdEqualTo(user.getId());
        List<UserVoice> userVoiceList = userVoiceMapper.selectByExample(userVoiceExample);

        Long[] voiceId = new Long[userVoiceList.size()];
        if (!CollectionUtils.isEmpty(userVoiceList) && userVoiceList.size() > 0) {
            for (int i = 0; i < voiceId.length; i++) {
                voiceId[i] = userVoiceList.get(i).getVoiceId();
            }
        }

        PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        VoiceExample voiceExample = new VoiceExample();
        voiceExample.createCriteria().andNameLike("%" + name + "%")
                .andIdIn(Arrays.asList(voiceId));

        List<Voice> voiceList = voiceMapper.selectByExample(voiceExample);

        PageInfo<Voice> pageInfo = new PageInfo<>(voiceList);
        pageReq.setTotal(pageInfo.getTotal());
        pageReq.setList(voiceList);
        return pageReq;
    }

    /**
     * Favorite voice
     */
    public ServerResponse favorite(UserVoice userVoice) {
        UserVoice userVoiceDB = this.select(userVoice.getUserId(), userVoice.getVoiceId());
        if (userVoiceDB == null) {
            userVoiceMapper.insert(userVoice);
        }
        return ServerResponse.createBySuccessMessage("收藏成功");
    }

    /**
     * Unfavorite voice
     */
    public ServerResponse unfavorite(UserVoice userVoice) {
        UserVoice userVoiceDB = this.select(userVoice.getUserId(), userVoice.getVoiceId());
        if (userVoiceDB != null) {
            UserVoiceExample example = new UserVoiceExample();
            example.createCriteria()
                    .andUserIdEqualTo(userVoice.getUserId())
                    .andVoiceIdEqualTo(userVoice.getVoiceId());
            userVoiceMapper.deleteByExample(example);
        }
        return ServerResponse.createBySuccessMessage("取消收藏成功");
    }

    /**
     * search by userId and voiceId
     */
    public UserVoice select(Long userId, Long voiceId) {
        UserVoiceExample example = new UserVoiceExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andVoiceIdEqualTo(voiceId);
        List<UserVoice> userVoiceList = userVoiceMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(userVoiceList)) {
            return null;
        } else {
            return userVoiceList.get(0);
        }
    }


}
