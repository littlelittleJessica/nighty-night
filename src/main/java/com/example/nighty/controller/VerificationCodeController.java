package com.example.nighty.controller;

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

    @PostMapping(value = "send/{email}")
    public ServerResponse send(@PathVariable String email) {
        LOG.info("Starting the request of sending email：{}", email);
        ServerResponse response = verificationCodeService.sendCode(email);
        return response;
    }
}
