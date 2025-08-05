package cat.linky.linkycat_api.core.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String destination, String subject, String body) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("Linky Cat <no-reply@linky.cat>");
        mail.setTo(destination);
        mail.setSubject(subject);
        mail.setText(body);
        mailSender.send(mail);
    }

}
