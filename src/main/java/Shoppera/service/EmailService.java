package Shoppera.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    @Value("${spring.mail.host}")
    private String mailHost;

    final
    JavaMailSender javaMailSender;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String email, String name){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailHost);
        message.setTo(email);
        message.setSubject("You are successfully signed up...");
        message.setText("Hi "+ name + ",\n\n" + "Thanks for signing in. We're happy to see you onboard.");
        javaMailSender.send(message);

    }
}
