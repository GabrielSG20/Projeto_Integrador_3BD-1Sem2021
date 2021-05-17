package com.airPlan.controllers;

import com.airPlan.entities.*;
import com.airPlan.services.CodeListService;
import com.airPlan.services.LepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class LEPController {

    @Autowired
    private LepService lepService;

    @RequestMapping("/lep-create")
    public String showLepCreatePage(Model model) {
        Lep lep = new Lep();
        model.addAttribute("lep", lep);

        System.out.println("ok request lep-create...");

        return "lep-create";
    }

    @RequestMapping(value = "/lep-create", method = RequestMethod.POST)
    public String createLep(@ModelAttribute("lep") Lep lep) throws IOException {

        System.out.println(lep.toString());

        lepService.populateRevisionDates(lep);


        return "redirect:/lep-create";
    }

   /* @GetMapping("/lep-delete")
    public String lepDreate() {

        return "lep-delete";
    }*/
}
