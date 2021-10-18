package com.example.nighty.controller;

import com.example.nighty.Req.PageReq;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.Voice;
import com.example.nighty.service.VoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/18
 */
@RestController
@RequestMapping("/voice/")
public class VoiceController {

    private static final Logger LOG = LoggerFactory.getLogger(VoiceController.class);

    @Resource
    private VoiceService voiceService;

    /**
     * 根据传入的分类查询音乐
     */
    @GetMapping("list")
    public ServerResponse listByCategory(PageReq pageReq, String category) {
        List<Voice> voiceList = voiceService.listByCategory(pageReq, category);
        return ServerResponse.createBySuccess("查询音乐成功", voiceList);
    }


}
