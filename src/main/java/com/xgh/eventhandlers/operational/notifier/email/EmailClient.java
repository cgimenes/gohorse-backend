package com.xgh.eventhandlers.operational.notifier.email;

import com.xgh.infra.JUnitIdentifier;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.xgh.eventhandlers.operational.notifier.email.templates.EmailTemplate;

import javax.mail.PasswordAuthentication;

@Component
public class EmailClient {
	private String smtpServer = "smtp.gmail.com";
	private String smtpServerPort = "465";
	private String username = "espaco.animal.noreply@gmail.com";
	private String password = "vaicavalo";
	private String from = "espaco.animal.noreply@gmail.com";

	public EmailClient() {

	}

	public EmailClient(String smtpServer, String smtpServerPort, String username, String password) {
		this.smtpServer = smtpServer;
		this.smtpServerPort = smtpServerPort;
		this.username = username;
		this.password = password;
	}

	public void sendEmail(String to, EmailTemplate template) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.port", smtpServerPort);
        props.put("mail.smtp.timeout", 5000);
        props.put("mail.smtp.connectiontimeout", 5000);
        props.put("mail.smtp.socketFactory.port", smtpServerPort);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
            message.setSubject(template.getSubject());
            message.setContent(template.getBody(),"text/html; charset=utf-8");

            if (!JUnitIdentifier.isJUnitTest()) {
                Transport.send(message);
            }

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}
}