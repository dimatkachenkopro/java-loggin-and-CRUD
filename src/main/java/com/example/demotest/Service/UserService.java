package com.example.demotest.Service;

import com.example.demotest.domain.Person;
import com.example.demotest.repository.RoleRepo;
import com.example.demotest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private MailSender mailSender;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public void active(Person currentUser, Long aplication_id) {
        //UUID.randomUUID().toString();
        Person acceptor = userRepo.findByRoles(roleRepo.findByRole("ROLE_ACCEPT"));
        String mailMessageToAcceptor = currentUser.getFirstName() + " " + currentUser.getLastName() + " want to get vacation. Go to the link http://localhost:2222/acceptator/?param1=" + aplication_id;
        mailSender.send(acceptor, mailMessageToAcceptor);
        String mailMessageToUSer = "The Acceptator got a message";
        mailSender.send(currentUser, mailMessageToUSer);
    }


}
