package com.example.nighty.service;

import com.example.nighty.Req.CodeValidReq;
import com.example.nighty.Resp.CodeResp;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.VerificationCode;
import com.example.nighty.domain.VerificationCodeExample;
import com.example.nighty.enums.CodeStatusEnum;
import com.example.nighty.mapper.VerificationCodeMapper;
import com.example.nighty.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/13
 */
@Service
public class VerificationCodeService {

    private static final Logger LOG = LoggerFactory.getLogger(VerificationCodeService.class);

    @Resource
    private VerificationCodeMapper verificationCodeMapper;

    @Resource
    private MailService mailService;


    /**
     * 发送邮件验证码
     * 同邮箱同操作1分钟内不能重复发送邮件
     */
    public ServerResponse sendCode(String email) {
        VerificationCodeExample example = new VerificationCodeExample();
        VerificationCodeExample.Criteria criteria = example.createCriteria();
        //查找1分钟内有没有同邮箱同操作发送记录且没有被用过
        criteria.andEmailEqualTo(email)
                .andStatusEqualTo(CodeStatusEnum.NOT_USED.getCode())
                .andAtGreaterThan(new Date(new Date().getTime() - 60 * 1000));
        List<VerificationCode> verificationCodeList = verificationCodeMapper.selectByExample(example);

        if (verificationCodeList == null || verificationCodeList.size() == 0) {
            saveAndSend(email);
            return ServerResponse.createBySuccess("Email Sent!");
        } else {
            LOG.info("邮件请求过于频繁：{}", email);
            return ServerResponse.createByErrorMessage("Email sent too frequently!");
        }
    }

    /**
     * 保存并发送验证码
     */
    private void saveAndSend(String email) {
        //生成6位数字
        String code = String.valueOf((int) (((Math.random() * 9) + 1) * 100000));
        CodeResp codeResp = new CodeResp();
        codeResp.setCode(code);
        codeResp.setEmail(email);
        codeResp.setAt(new Date());
        codeResp.setStatus(CodeStatusEnum.NOT_USED.getCode());
        VerificationCode verificationCode = CopyUtil.copy(codeResp, VerificationCode.class);
        verificationCodeMapper.insert(verificationCode);

        String content = "<h2>Verification</h2>" +
                "<h3>Welcome to start your life with Nighty Night</h3>" +
                "<h3>The verification code for registration is: </h3>" + code;
        String recipient = email;

        mailService.checkProject(content, recipient);

    }

    /**
     * 校验邮件验证码，且操作类型要一致
     */
    public ServerResponse validCode(CodeValidReq codeValidReq) {
        VerificationCodeExample example = new VerificationCodeExample();
        VerificationCodeExample.Criteria criteria = example.createCriteria();
        //查找1分钟内同邮箱同操作发送记录
        criteria.andEmailEqualTo(codeValidReq.getEmail()).
                andAtGreaterThan(new Date(new Date().getTime() - 60 * 1000));
        List<VerificationCode> verificationCodeList = verificationCodeMapper.selectByExample(example);

        if (verificationCodeList != null && verificationCodeList.size() > 0) {
            VerificationCode verificationCodeDb = verificationCodeList.get(0);
            String codeDB = verificationCodeDb.getCode();
            if (!codeDB.equals(codeValidReq.getCode())) {
                LOG.warn("邮件验证码不正确，数据库验证码：{}，输入验证码：{}", codeDB, codeValidReq.getCode());
                return ServerResponse.createByErrorMessage("Wrong Verification Code!");
            } else {
                verificationCodeDb.setStatus(CodeStatusEnum.USED.getCode());
                verificationCodeMapper.updateByPrimaryKey(verificationCodeDb);
            }
        } else {
            LOG.warn("邮件验证码不存在或已过期，请重新发送邮件");
            return ServerResponse.createByErrorMessage("The verification code doesn't exist or has expired! " +
                    "Please resend one!");
        }
        return ServerResponse.createBySuccess("Verification code correct!");
    }
}
