package com.example.nighty.controller;

import com.example.nighty.Req.PageReq;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.Voice;
import com.example.nighty.service.VoiceService;
import com.example.nighty.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

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
    @GetMapping("list/{category}")
    public ServerResponse listByCategory(@PathVariable String category) {
        PageReq pageReq = new PageReq();
        pageReq.setPage(1);
        pageReq.setSize(8);
        return ServerResponse.createBySuccess("Query voice list success", voiceService.listByCategory(pageReq, category));
    }

    /**
     * search voice by name
     */
    @GetMapping("search/{name}")
    public ServerResponse searchByName(@PathVariable String name) {
        PageReq pageReq = new PageReq();
        pageReq.setPage(1);
        pageReq.setSize(8);
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

    /**
     * save the cover of the voice
     * todo:上传oss直接传链接
     */
    @RequestMapping("saveCover")
    @ResponseBody
    public ServerResponse saveCover(MultipartFile fileImage, Long id) throws IOException {
        LOG.info(fileImage.getOriginalFilename());

        final String imagePathRoot = "D:\\CS353\\cover\\";
        File file = new File(imagePathRoot);

        if (!file.exists()) {
            file.mkdirs();
        }

        String fileName = fileImage.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        String uuid = UuidUtil.getShortUuid();
        String imageFilePath = imagePathRoot + uuid + fileType;
        fileImage.transferTo(new File(imageFilePath));

        return voiceService.saveCover(imageFilePath, id);
    }


}
