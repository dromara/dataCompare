package com.vince.xq.project.system.email.service;

import com.vince.xq.project.system.instance.domain.Instance;

import javax.mail.MessagingException;

public interface IEmailService {
    public void sendEmail(Instance instance, String email) throws MessagingException;
}
