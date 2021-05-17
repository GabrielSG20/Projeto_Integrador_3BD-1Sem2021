package com.airPlan.controllers;

import com.airPlan.entities.*;
import com.airPlan.services.LepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class LEPController {

    @Autowired
    private LepService lepService;

    @RequestMapping("/lep-create")
    public String showLepCreatePage(Model model) {
        Lep lep = new Lep();
        model.addAttribute("lep", lep);


        return "lep-create";
    }

    @RequestMapping(value = "/lep-create", method = RequestMethod.POST)
    public String createLep(@ModelAttribute("lep") Lep lep) throws IOException {


        lepService.populateRevisionDates(lep);


        return "redirect:/lep-create";
    }
}
