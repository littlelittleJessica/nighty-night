package com.example.nighty.controller;

import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.Daily;
import com.example.nighty.service.AdminService;
import com.example.nighty.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/21
 */
@RestController
@RequestMapping("/admin/")
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Resource
    private AdminService adminService;

    /**
     * upload image file
     */
    @RequestMapping("update_daily")
    public ServerResponse updateDaily(MultipartFile fileImage) throws IOException {
        LOG.info(fileImage.getOriginalFilename());

        final String imagePathRoot = "D:\\CS353\\daily\\";
        File file = new File(imagePathRoot);

        if (!file.exists()) {
            file.mkdirs();
        }

        String fileName = fileImage.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        String uuid = UuidUtil.getShortUuid();
        String imageFilePath = imagePathRoot + uuid + fileType;
        fileImage.transferTo(new File(imageFilePath));

        Daily daily = new Daily();
        daily.setPic(imageFilePath);
        daily.setDate(new Date());

        return adminService.updateDaily(daily);
    }
}
