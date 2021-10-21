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
    //send html email
    public void sendHtmlMail(MimeMessage message,String username,String recipient, String subject, String content) {
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(content, true);
            System.out.println("Mail sending...");
        } catch (MessagingException e) {
            System.out.println("Send email failed!");
        }
    }
}