package com.example.demotest.controller;

import com.example.demotest.domain.Equipment;
import com.example.demotest.domain.Person;
import com.example.demotest.repository.EquipmentRepo;
import com.example.demotest.repository.SprzetSortRepo;
import com.example.demotest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Controller
public class SprzetController {
    @Autowired
    EquipmentRepo equipmentRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    SprzetSortRepo sprzetSortRepo;

    @RequestMapping(value = {"/sprzet"}, method = RequestMethod.GET)
    public String getEmployees(@PageableDefault(size = 10, sort = "equipment_id") Pageable pageable,
                               Model model) {
        /*Page<Equipment> page = sprzetSortRepo.findAll(pageable);
        List<Sort.Order> sortOrders = page.getSort().stream().collect(Collectors.toList());
        if (sortOrders.size() > 0) {
            Sort.Order order = sortOrders.get(0);
            model.addAttribute("sortProperty", order.getProperty());
            model.addAttribute("sortDesc", order.getDirection() == Sort.Direction.DESC);
        }
        model.addAttribute("page", page);*/

        Iterable<Equipment> sprzet = equipmentRepo.findAll();
        model.addAttribute("sprzety", sprzet);
        ArrayList<String> ll = new ArrayList<>();
        //ArrayList<Equipment> a = new ArrayList<>();
        TreeSet<String> aa = new TreeSet<>();
        for (Equipment ww : sprzet) {
            ll.add(ww.getModel());
            aa.add(ww.getModel());
        }
        model.addAttribute("aa", aa);
        Iterable<Person> user = userRepo.findAll();
        model.addAttribute("usery", user);

        return "sprzet";
    }

    @GetMapping("departments")
    public String getDepartments(@SortDefault(sort = "model", direction = Sort.Direction.ASC)
                                         Sort sort, Model model) {
        List<String> models = sprzetSortRepo.findAllDepartments(sort);
        model.addAttribute("models", models);
        return "model-page";
    }
//    public String personLis(Model model) {
//
//        Iterable<Equipment> sprzet = sprzetRepo.findAll();
//        model.addAttribute("sprzety", sprzet);
//        ArrayList<String> ll = new ArrayList<>();
//        //ArrayList<Equipment> a = new ArrayList<>();
//        TreeSet<String> aa = new TreeSet<>();
//        for(Equipment ww : sprzet)
//        {
//            ll.add(ww.getModel());
//            aa.add(ww.getModel());
//        }
//        model.addAttribute("aa", aa);
//        Iterable<Person> user = userRepo.findAll();
//        model.addAttribute("usery", user);
//
//        return "sprzet";
//    }

    @RequestMapping(value = {"/addSprzet"}, method = RequestMethod.GET)
    public String personLisAdd(Model model) {

        Iterable<Equipment> sprzet = equipmentRepo.findAll();
        model.addAttribute("sprzets", sprzet);
        Iterable<Person> user = userRepo.findAll();
        model.addAttribute("users", user);

        return "addSprzet";
    }

    @PostMapping(value = "/deleteS/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView delete(@PathVariable("id") long id) {

        equipmentRepo.deleteById(id);

        return new ModelAndView("redirect:/sprzet");
    }

    @RequestMapping(value = {"/infoSprzet/{nrseryjny}"}, method = RequestMethod.GET)
    public String infoSprzet(@PathVariable("nrseryjny") String nrseryjny, Model model) {
        Equipment personEquipment = equipmentRepo.findBySerialNumber(nrseryjny);
        model.addAttribute("personSprzet", personEquipment);

        Iterable<Person> user = userRepo.findAll();
        model.addAttribute("usery", user);

        return "infoSprzet";
    }
   /* @RequestMapping("/infoSprzet")
    public String dashboardPageList(Model model, @AuthenticationPrincipal UserDetails currentUser ) {
        Person person = (Person) userRepo.findByUsername(currentUser.getUsername());
        model.addAttribute("person", person);

        return "infoPerson";
    } */

    @RequestMapping(value = {"/updateSprzet/{id}"}, method = RequestMethod.GET)
    public String updateSprzet(@ModelAttribute("personSprzet") Equipment personEquipment, @PathVariable("id") long id) {

        Equipment personForm = equipmentRepo.findById(id).get();

        personForm.setCategory(personEquipment.getCategory());
        personForm.setNameEquipment(personEquipment.getNameEquipment());
        personForm.setModel(personEquipment.getModel());
        personForm.setPossession(personEquipment.getPossession());
        personForm.setActive(personEquipment.getActive());
        personForm.setSerialNumber(personEquipment.getSerialNumber());


        equipmentRepo.save(personForm);

        return "redirect:/sprzet";
    }

    @GetMapping("/add")
    public String registration() {
        return "add";
    }

    @PostMapping("/add")
    public String addSprzet(Equipment equipment, Map<String, Object> model) {
        Equipment equipmentFromDb = equipmentRepo.findBySerialNumber(equipment.getSerialNumber());

        if (equipmentFromDb != null) {
            model.put("message", "Equipment exists!");
            return "addSprzet";
        }
        equipmentRepo.save(equipment);

        return "redirect:/sprzet";
    }
//    @PostMapping("/addSprzet")
//    public String personLisAddError(Equipment sprzetM, Map<String, Object> modelM) {
//        Equipment userFromDb = sprzetRepo.findByNrseryjny(sprzetM.getNrseryjny());
//
//        if (userFromDb != null) {
//            modelM.put("message", "Error!");
//            return "addSprzet";
//        }
//        return  "redirect:/Equipment";
//    }

}
