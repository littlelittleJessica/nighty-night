package com.example.nighty.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.example.nighty.Req.FileReq;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.service.FileService;
import com.example.nighty.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.util.Date;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/9/14
 */
@RestController
@RequestMapping("/admin")
public class OssController {

    private static final Logger LOG = LoggerFactory.getLogger(OssController.class);

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.bucket}")
    private String bucket;

    @Value("${oss.domain}")
    private String ossDomain;

    @Resource
    private FileService fileService;

    @PostMapping("/oss-simple")
    public ServerResponse<FileReq> fileUpload(@RequestParam MultipartFile file) throws Exception {
        LOG.info("上传文件开始");
        String key = UuidUtil.getShortUuid();
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        String path = key + "." + suffix;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, path, new ByteArrayInputStream(file.getBytes()));

        // 上传字符串。
        ossClient.putObject(putObjectRequest);

        FileReq fileReq = new FileReq();
        fileReq.setPath(ossDomain + path);
        fileReq.setName(fileName);
        fileReq.setCreatedAt(new Date());
        fileReq.setSize((int) file.getSize());
        fileReq.setSuffix(suffix);

        fileService.save(fileReq);


        return ServerResponse.createBySuccess("Upload succeeded", fileReq);
    }
}

