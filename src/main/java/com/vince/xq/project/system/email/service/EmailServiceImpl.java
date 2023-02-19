package com.vince.xq.project.system.email.service;

import com.vince.xq.common.constant.Constants;
import com.vince.xq.project.system.instance.controller.InstanceController;
import com.vince.xq.project.system.instance.domain.Instance;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;

@Service
public class EmailServiceImpl implements IEmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.enabled}")
    private boolean mailEnabled;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendEmail(Instance instance, String email) throws MessagingException {
        if (mailEnabled && StringUtils.isNotBlank(email)) {
            boolean numFlag = instance.getPvDiff().equals("0") && instance.getUvDiff().equals("0") ? true : false;
            boolean consistencyFlag = instance.getCountDiff().equals(instance.getOriginTableCount()) && instance.getCountDiff().equals(instance.getToTableCount()) ? true : false;
            String content = buildTemplate(numFlag, consistencyFlag);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom(fromEmail);
                helper.setTo("xiaoqiu2017wy@163.com");
                helper.setSubject(Constants.EMAIL_TITLE);
                // 设置邮件内容，第二个参数设置是否支持 text/html 类型
                helper.setText(content, true);
                javaMailSender.send(mimeMessage);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            //do nothing
        }
    }

    private String buildTemplate(boolean numFlag, boolean consistencyFlag) {
        //加载邮件html模板
        Resource resource = new ClassPathResource("EmailTemplate.txt");
        InputStream inputStream = null;
        BufferedReader fileReader = null;
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            inputStream = resource.getInputStream();
            fileReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            log.info("发送邮件读取模板失败{}", e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //替换html模板中的参数
        return MessageFormat.format(buffer.toString(), numFlag, consistencyFlag);
    }
}
