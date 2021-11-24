package com.example.nighty.controller;

import com.example.nighty.Req.PageReq;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.Voice;
import com.example.nighty.service.VoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
     * List all voice by category
     */
    @PostMapping("list/{category}")
    public ServerResponse listByCategory(@PathVariable String category, @RequestBody PageReq page) {
        PageReq pageReq = new PageReq();
        pageReq.setPage(page.getPage());
        pageReq.setSize(page.getSize());
        return ServerResponse.createBySuccess("Query voice list success", voiceService.listByCategory(pageReq, category));
    }

    /**
     * search voice by name
     */
    @GetMapping("search/{name}")
    public ServerResponse searchByName(@PathVariable String name, @RequestBody PageReq page) {
        PageReq pageReq = new PageReq();
        pageReq.setPage(page.getPage());
        pageReq.setSize(page.getSize());
        return ServerResponse.createBySuccess("Search voice list success", voiceService.searchByName(pageReq, name));
    }

    /**
     * save voice
     */
    @RequestMapping("save")
    public ServerResponse save(@RequestBody Voice voice) {
        return voiceService.save(voice);
    }

    /**
     * delete voice
     */
    @DeleteMapping("delete/{id}")
    public ServerResponse delete(@PathVariable Long id) {
        voiceService.delete(id);
        return ServerResponse.createBySuccessMessage("Delete voice succeeded");
    }
}
