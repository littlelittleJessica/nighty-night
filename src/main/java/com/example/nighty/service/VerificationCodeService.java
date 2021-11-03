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
     * send email verification code
     * The same email address can not sent two emails in one minute
     */
    public ServerResponse sendCode(String email) {
        VerificationCodeExample example = new VerificationCodeExample();
        VerificationCodeExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email)
                .andStatusEqualTo(CodeStatusEnum.NOT_USED.getCode())
                .andAtGreaterThan(new Date(new Date().getTime() - 1 * 1000));
        List<VerificationCode> verificationCodeList = verificationCodeMapper.selectByExample(example);

        if (verificationCodeList == null || verificationCodeList.size() == 0) {
            saveAndSend(email);
            return ServerResponse.createBySuccess("Email Sent!");
        } else {
            LOG.info("Email sent too frequent: {}", email);
            return ServerResponse.createByErrorMessage("Email sent too frequently!");
        }
    }

    /**
     * save and send verification code
     */
    private void saveAndSend(String email) {
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
     * verify code
     */
    public ServerResponse validCode(CodeValidReq codeValidReq) {
        VerificationCodeExample example = new VerificationCodeExample();
        VerificationCodeExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(codeValidReq.getEmail()).
                andAtGreaterThan(new Date(new Date().getTime() - 600 * 1000));
        List<VerificationCode> verificationCodeList = verificationCodeMapper.selectByExample(example);

        if (verificationCodeList != null && verificationCodeList.size() > 0) {
            VerificationCode verificationCodeDb = verificationCodeList.get(0);
            String codeDB = verificationCodeDb.getCode();
            if (!codeDB.equals(codeValidReq.getCode())) {
                LOG.warn("Incorrect verification code, code in database{}, wrong code: {}", codeDB, codeValidReq.getCode());
                return ServerResponse.createByErrorMessage("Wrong Verification Code!");
            } else {
                verificationCodeDb.setStatus(CodeStatusEnum.USED.getCode());
                verificationCodeMapper.updateByPrimaryKey(verificationCodeDb);
            }
        } else {
            LOG.warn("The verification code doesn't exist or has expired! \" +\n" +
                    "                    \"Please resend one!");
            return ServerResponse.createByErrorMessage("The verification code doesn't exist or has expired! " +
                    "Please resend one!");
        }
        return ServerResponse.createBySuccess("Verification code correct!");
    }
}
