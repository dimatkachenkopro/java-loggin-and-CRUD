package com.example.demotest.Service;

import com.example.demotest.domain.Person;
import com.example.demotest.repository.RoleRepo;
import com.example.demotest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    @Value("poczta mail")
    private String username;


    public void send(Person currentUser, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(currentUser.getEmail());
        mailMessage.setSubject("NOVARIS Sp. z o.o.");
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }


}
