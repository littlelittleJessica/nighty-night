package com.example.nighty.util;

import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/13
 */
public class SendMail {
    //发送html格式邮件
    public void sendHtmlMail(MimeMessage message,String username,String recipient, String subject, String content) {
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(content, true);
            System.out.println("html格式邮件发送中...");
        } catch (MessagingException e) {
            System.out.println("html格式邮件发送失败！");
        }
    }
}