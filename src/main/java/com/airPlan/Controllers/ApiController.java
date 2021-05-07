package com.airPlan.Controllers;

import com.airPlan.Entities.*;
import com.airPlan.Services.FlagService;
import com.airPlan.Services.ManualFlagService;
import com.airPlan.Services.ManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApiController {

    @Autowired
    private ManualService manualService;
    @Autowired
    private FlagService flagService;
    @Autowired
    private ManualFlagService manualFlagService;


    @RequestMapping("/code-create")
    public String showCodeCreatePage(Model model) {
        General general = new General();
        model.addAttribute("general", general);
        System.out.println(general.toString());
        return "code-create";
    }

    @RequestMapping(value = "/code-create", method = RequestMethod.POST)
    public String saveManual(@ModelAttribute("general") General general) {
        Manual manual = new Manual(general.getMnl_name());

        Flag flag = new Flag(general.getFlg_secundary_id(), general.getFlg_tag());
        System.out.println(manual.toString());
        ManualFlag manualFlag = new ManualFlag(new ManualFlagId(manual.getMnl_id(), flag.getFlg_secundary_id()));

        manualService.save(manual);
        flagService.save(flag);
        manualFlagService.save(manualFlag);

        return "code-create";
    }


}
