package com.example.nighty.service;

import com.example.nighty.util.SendMail;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/13
 */
@Service
public class MailService implements com.example.nighty.util.MailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public String checkProject(String content, String recipient) {
        try {
            SendMail sendMail = new SendMail();
            MimeMessage message = javaMailSender.createMimeMessage();
            sendMail.sendHtmlMail(message,
                    "446067382@qq.com",
                    recipient,
                    "Verification", content);
            javaMailSender.send(message);
        } catch (Exception e) {
            return "CheckError";
        }
        return "CheckSuccess";
    }
}