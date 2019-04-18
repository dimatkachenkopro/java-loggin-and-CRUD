package com.example.demotest.controller;

import com.example.demotest.domain.Person;
import com.example.demotest.domain.Role;
import com.example.demotest.repository.RoleRepo;
import com.example.demotest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Person user, Map<String, Object> model) {
        Person userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }
        Role role = roleRepo.findByRole("ROLE_USER");
        user.setActive(true);

        user.setRoles(new HashSet<Role>(Arrays.asList(role)));

        userRepo.save(user);

        return "redirect:/login";
    }
}