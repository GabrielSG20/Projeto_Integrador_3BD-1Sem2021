package com.airPlan.controllers;

import com.airPlan.entities.*;
import com.airPlan.services.LepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String createLep(@ModelAttribute("lep") Lep lep,
                            RedirectAttributes redirAttrs) throws IOException {


        if(!lepService.checkIntegrity(lep)) {
            redirAttrs.addFlashAttribute("error", "Incorrect data, check" +
                    " fields integrity, eg.: all fields are filled?");
            return "redirect:/lep-create";
        }

        lepService.createLep1(lep);
        redirAttrs.addFlashAttribute("success", "LEP successfully created!");

        return "redirect:/lep-create";
    }
}
