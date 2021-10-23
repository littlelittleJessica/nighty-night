package com.example.nighty.controller;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetMezzanineInfoResponse;
import com.example.nighty.Req.FileReq;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.service.FileService;
import com.example.nighty.util.Base64ToMultipartFile;
import com.example.nighty.util.VodUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/23
 */
public class UploadController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);

    @Value("${vod.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.domain}")
    private String OSS_DOMAIN;

    @Value("${vod.accessKeySecret}")
    private String accessKeySecret;

    @Value("${file.domain}")
    private String FILE_DOMAIN;

    @Value("${file.path}")
    private String FILE_PATH;

    @Resource
    private FileService fileService;

    @RequestMapping("/upload")
    public ServerResponse upload(@RequestBody FileReq fileReq) throws IOException {
        LOG.info("上传文件开始");
        String key = fileReq.getKey();
        String suffix = fileReq.getSuffix();
        String shardBase64 = fileReq.getShard();
        MultipartFile shard = Base64ToMultipartFile.base64ToMultipart(shardBase64);

        //如果文件夹不存在则创建
        File fullDir = new File(FILE_PATH);
        if (!fullDir.exists()) {
            fullDir.mkdir();
        }

        String path = File.separator + key + "." + suffix;
        String fullPath = FILE_PATH + path;
        File dest = new File(fullPath);
        shard.transferTo(dest);
        LOG.info(dest.getAbsolutePath());

        LOG.info("保存文件记录开始");
        fileReq.setPath(path);
        fileService.save(fileReq);
        fileReq.setPath(FILE_DOMAIN + path);

        return ServerResponse.createBySuccess("Upload file succeeded", fileReq);
    }

    @GetMapping("/merge")
    public void merge(FileReq fileReq) throws Exception {
        LOG.info("合并分片开始");
        String path = fileReq.getPath();
        path = path.replace(FILE_DOMAIN, "");
        Integer shardTotal = fileReq.getShardTotal();
        File newFile = new File(FILE_PATH + path);
        FileOutputStream outputStream = new FileOutputStream(newFile, true);//文件追加写入
        FileInputStream fileInputStream = null;//分片文件
        byte[] byt = new byte[10 * 1024 * 1024];
        int len;

        try {
            for (int i = 0; i < shardTotal; i++) {
                fileInputStream = new FileInputStream(FILE_PATH + path + "." + (i + 1));
                while ((len = fileInputStream.read(byt)) != -1) {
                    outputStream.write(byt, 0, len);
                }
            }
        } catch (IOException e) {
            LOG.error("分片合并异常", e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                outputStream.close();
            } catch (Exception e) {
                LOG.error("IO流关闭", e);
            }
        }

        LOG.info("合并分片结束");

        System.gc();
        Thread.sleep(100);

        //删除分片
        LOG.info("删除分片开始");
        for (int i = 0; i < shardTotal; i++) {
            String filePath = FILE_PATH + path + "." + (i + 1);
            File file = new File(filePath);
            boolean result = file.delete();
            LOG.info("删除{}，{}", filePath, result ? "成功" : "失败");
        }
        LOG.info("删除分片结束");
    }

    @GetMapping("/check/{key}")
    public ServerResponse check(@PathVariable String key) throws Exception {
        LOG.info("检查上传分片开始：{}", key);
        FileReq fileReq = fileService.findByKey(key);
        if (fileReq != null) {
            if (StringUtils.isEmpty(fileReq.getVod())) {
                fileReq.setPath(OSS_DOMAIN + fileReq.getPath());
            } else {
                DefaultAcsClient vodClient = VodUtil.initVodClient(accessKeyId, accessKeySecret);
                GetMezzanineInfoResponse response = VodUtil.getMezzanineInfo(vodClient, fileReq.getVod());
                System.out.println("获取视频信息, response : " + JSON.toJSONString(response));
                String fileUrl = response.getMezzanine().getFileURL();
                fileReq.setPath(fileUrl);
            }
        }
        return ServerResponse.createBySuccess(fileReq);
    }
}
