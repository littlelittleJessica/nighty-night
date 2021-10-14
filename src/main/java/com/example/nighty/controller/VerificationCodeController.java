package com.example.nighty.controller;

import com.example.nighty.Req.CodeReq;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.service.VerificationCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/13
 */
@RestController
@RequestMapping("/code/")
public class VerificationCodeController {

    private static final Logger LOG = LoggerFactory.getLogger(VerificationCodeController.class);

    @Resource
    private VerificationCodeService verificationCodeService;

    @RequestMapping(value = "send", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse send(CodeReq codeReq) {
        LOG.info("发送短信请求开始：{}", codeReq);
        verificationCodeService.sendCode(codeReq);
        LOG.info("发送短信请求结束");
        return ServerResponse.createBySuccess("验证码已发送");
    }
}
