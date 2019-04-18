package com.example.demotest.controller;


import com.example.demotest.Service.UserService;
import com.example.demotest.domain.*;
import com.example.demotest.domain.Calendar;
import com.example.demotest.repository.*;
import com.example.demotest.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PositionRepo positionRepo;
    @Autowired
    CalendarRepo calendarRepo;
    @Autowired
    EquipmentRepo equipmentRepo;
    @Autowired
    private UserService userSevice;


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = {"/personLis"}, method = RequestMethod.GET)
    public String personLis(Model model) {

        Iterable<Person> usery = userRepo.findAll().stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .collect(Collectors.toList());

        Iterable<Position> stanowiskos = positionRepo.findAll();
        model.addAttribute("stanowiskos", stanowiskos);
        model.addAttribute("usery", usery);

        return "personLis";
    }

    @PostMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView delete(@PathVariable("id") Long id) {

        userRepo.deleteById(id);

        return new ModelAndView("redirect:/personLis");
    }

    @RequestMapping(value = {"/infoPerson/{username}"}, method = RequestMethod.GET)
    public String infoPerson(@PathVariable("username") String username, Model model) {

        Person person = userRepo.findByUsername(username);
        model.addAttribute("person", person);

        Iterable<Position> allUsers = positionRepo.findAll();
        ArrayList<String> st = new ArrayList<>();
        for (Position a : allUsers) {
            st.add(a.getPosition());
        }

        model.addAttribute("allUsers", st);

        return "infoPerson";
    }

    @RequestMapping("/infoPerson")
    public String dashboardPageList(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Person person = (Person) userRepo.findByUsername(currentUser.getUsername());
        model.addAttribute("person", person);
        ArrayList<Equipment> userEquipment = equipmentRepo.findAllByUser(person);
        model.addAttribute("sprets", userEquipment);

        return "infoPerson";
    }

    @RequestMapping(value = {"/updatePerson/{id}"}, method = RequestMethod.GET)
    public String updatePerson(@ModelAttribute("person") Person person, @PathVariable("id") Long id) {

        Person personForm = userRepo.findById(id).get();

        personForm.setFirstName(person.getFirstName());
        personForm.setLastName(person.getLastName());
        personForm.setPosition(person.getPosition());
        personForm.setPhoneNumber(person.getPhoneNumber());
        personForm.setEmail(person.getEmail());
        personForm.setWorkplace(person.getWorkplace());

        userRepo.save(personForm);

        return "redirect:/personLis";
    }

    @RequestMapping(value = {"/addHoliday"}, method = RequestMethod.GET)
    public String addcalendar(Model model) {

        return "addHoliday";
    }

    @GetMapping("/addUser")
    public String registration() {
        return "addUser";
    }

    @RequestMapping(value = {"/addHoliday/{name}/{date}"}, method = RequestMethod.GET)
    public String getDate(@PathVariable Date date, @PathVariable(value = "name") String name, Calendar calendar, Model model) {

        calendar.setHoliday(name);
        calendar.setData(date);
        calendarRepo.save(calendar);

        return "redirect:/calendar";
    }

    @PostMapping("/addUser")
    public String addUser(Person user, Map<String, Object> model) {
        Person userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "addUser";
        }
        Role role = roleRepo.findByRole("ROLE_USER");
        user.setActive(true);

        user.setRoles(new HashSet<Role>(Arrays.asList(role)));

        userRepo.save(user);

        return "redirect:/personLis";
    }



}
