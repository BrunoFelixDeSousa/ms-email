package io.github.brunofelixdesousa.email.services;

import io.github.brunofelixdesousa.email.enums.StatusEmail;
import io.github.brunofelixdesousa.email.models.Email;
import io.github.brunofelixdesousa.email.repositories.EmailRepository;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings("ALL")
@Service
public class EmailService {

    final EmailRepository emailRepository;

    final JavaMailSender emailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = mailSender;
    }



    public Email sendEmail(Email email) {

        email.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(email);
        }
    }
}
