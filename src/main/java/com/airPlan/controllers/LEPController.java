package com.airPlan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LEPController {
    @GetMapping("/lep-create")
    public String lepCreate() {

        return "lep-create";
    }
}
