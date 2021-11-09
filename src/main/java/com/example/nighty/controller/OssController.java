package com.example.nighty.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.AppendObjectRequest;
import com.aliyun.oss.model.AppendObjectResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.example.nighty.Req.FileReq;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.service.FileService;
import com.example.nighty.util.Base64ToMultipartFile;
import com.example.nighty.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/9/14
 */
@RestController
@RequestMapping("/oss")
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

    @PostMapping("/oss-append")
    public ServerResponse<FileReq> fileUpload(@RequestBody FileReq fileReq) throws Exception {
        LOG.info("上传文件开始");
        String key = fileReq.getKey();
        String suffix = fileReq.getSuffix();
        Integer shardIndex = fileReq.getShardIndex();
        Integer shardSize = fileReq.getShardSize();
        String shardBase64 = fileReq.getShard();
        MultipartFile shard = Base64ToMultipartFile.base64ToMultipart(shardBase64);

        String path = new StringBuffer(key)
                .append(".")
                .append(suffix)
                .toString();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ObjectMetadata meta = new ObjectMetadata();
        // 指定上传的内容类型。
        meta.setContentType("text/plain");

        // 通过AppendObjectRequest设置多个参数。
        AppendObjectRequest appendObjectRequest = new AppendObjectRequest(bucket, path, new ByteArrayInputStream(shard.getBytes()), meta);

        // 追加。
        // 设置文件的追加位置。
        appendObjectRequest.setPosition((long) ((shardIndex - 1) * shardSize));
        AppendObjectResult appendObjectResult = ossClient.appendObject(appendObjectRequest);
        // 文件的64位CRC值。此值根据ECMA-182标准计算得出。
        System.out.println(appendObjectResult.getObjectCRC());
        System.out.println(JSONObject.toJSONString(appendObjectResult));

        // 关闭OSSClient。
        ossClient.shutdown();

        LOG.info("保存文件记录开始");
        fileReq.setPath(path);
        fileService.save(fileReq);

        fileReq.setPath(ossDomain + path);
        return ServerResponse.createBySuccess("Upload succeeded", fileReq);

    }


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

        return ServerResponse.createBySuccess("Upload succeeded", fileReq);
    }
}

