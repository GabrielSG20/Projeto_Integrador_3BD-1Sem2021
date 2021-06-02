package com.airPlan.controllers;

import com.airPlan.entities.CodeList;
import com.airPlan.entities.General;
import com.airPlan.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/create-user")
    public String createUser() {

        return "create-user";
    }

    @RequestMapping("/create-user")
    public String showCodeCreatePage(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "code-user";
    }



}
