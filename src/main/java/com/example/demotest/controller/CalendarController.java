package com.example.demotest.controller;

import com.example.demotest.Service.CalendarService;
import com.example.demotest.Service.UserService;
import com.example.demotest.domain.Application;
import com.example.demotest.domain.Calendar;
import com.example.demotest.domain.Person;
import com.example.demotest.repository.ApplicationRepo;
import com.example.demotest.repository.CalendarRepo;
import com.example.demotest.repository.UserRepo;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CalendarController {

    @Autowired
    ApplicationRepo appRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CalendarRepo calendarRepo;
    @Autowired
    UserService userService;

    @Autowired
    CalendarService calendarService;

    @GetMapping("/application/{firstDate}/{lastDate}/{type}/{user}")
    public String addApp(@PathVariable Date firstDate, @PathVariable Date lastDate, @PathVariable String type, @PathVariable String user, Application app, @AuthenticationPrincipal UserDetails currentUser, Map<String, Object> model) {

        Person person = userRepo.findByUsername(currentUser.getUsername());
        Person userId = userRepo.findByUsername(user);
        app.setUserId(person);
        app.setStartDate(firstDate);
        app.setLastDate(lastDate);
        app.setApplicationType(type);
        app.setId_SubstitutePerson(userId);
        app.setActive(false);
        Date date = new Date();
        app.setDateSubmission(date);
        Person currentPerson = userRepo.findByUsername(currentUser.getUsername());

        appRepo.save(app);
        userService.active(person,app.getApplication_Id());


        return "redirect:/calendar";
    }

    @RequestMapping(value = {"/calendar"}, method = RequestMethod.GET)
    public String calendar(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Iterable<Calendar> allHolidayDates = calendarRepo.findAll();
        Iterable<Application> allApplicationsDate = appRepo.findAll();
        Person person = userRepo.findByUsername(currentUser.getUsername());
        Iterable<Person> allUsers = userRepo.findAll();
        ArrayList<Application> allUserApp = appRepo.findAllByUserId(person);


        model.addAttribute("allUserApp", allUserApp);
        model.addAttribute("allWeekendDays", calendarService.getDaysFromFirstToLast(allUserApp));
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("allDates", allHolidayDates);
        model.addAttribute("applicationsDate", allApplicationsDate);
        return "calendar";
    }
    @PostMapping("/calendar/{application_Id}")
    public String accept(@PathVariable Long application_Id)
    {
        Application app = appRepo.findById(application_Id).get();
        app.setActive(true);
        appRepo.save(app);

        return "redirect:/calendar";
    }
    @GetMapping("/acceptator")
    public String acceptUser(@RequestParam Map<String, String> reqParam, Model model)
    {
        String id = reqParam.get("param1");
        Long application_id = Long.parseLong(id);
        Application app = appRepo.findById(application_id).get();
        model.addAttribute("app",app);


        return "acceptator";
    }

}
