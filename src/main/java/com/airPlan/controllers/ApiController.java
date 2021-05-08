package com.airPlan.controllers;

import com.airPlan.entities.*;
import com.airPlan.services.CodeListService;
import com.airPlan.services.FlagService;
import com.airPlan.services.ManualFlagService;
import com.airPlan.services.ManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ApiController {

    @Autowired
    private ManualService manualService;
    @Autowired
    private FlagService flagService;
    @Autowired
    private ManualFlagService manualFlagService;
    @Autowired
    private CodeListService codeListService;

    @GetMapping("/menu")
    public String menu() {

        return "menu";
    }

    @RequestMapping("/code-create")
    public String showCodeCreatePage(Model model) {
        General general = new General();
        model.addAttribute("general", general);
        System.out.println(general.toString());
        return "code-create";
    }

    @RequestMapping(value = "/code-create", method = RequestMethod.POST)
    public String saveManual(@ModelAttribute("general") General general) throws SQLException {

        Manual manual = new Manual(general.getMnl_name());

        manualService.save(manual);


        manual.setMnl_id(manualService.findManualByName(manual.getMnl_name()));

        Flag flag = new Flag(general.getFlg_secundary_id(), general.getFlg_tag());
        System.out.println(manual.toString());
        ManualFlag manualFlag = new ManualFlag(new ManualFlagId(manual.getMnl_id(), flag.getFlg_secundary_id()));

        flagService.save(flag);
        manualFlagService.save(manualFlag);

        return "code-create";
    }

    @GetMapping("/code-delete")
    public String codeListForm2(Model model) {
        General general = new General();
        model.addAttribute("general", general);

        return "code-delete";
    }

    @GetMapping("/code-edit")
    public String codeListForm3(Model model) {
        General general = new General();
        model.addAttribute("general", general);

        return "code-edit";
    }

    @RequestMapping("/code-consult")
    public String listCodelists(Model model) {

        List<CodeList> codelists = codeListService.listAll();

        model.addAttribute("codeList", codelists);

        return "code-consult";
    }

    @GetMapping("/code-import")
    public String codeImport(Model model) {
        model.addAttribute("manual", new Manual());
        return "code-import";
    }
}