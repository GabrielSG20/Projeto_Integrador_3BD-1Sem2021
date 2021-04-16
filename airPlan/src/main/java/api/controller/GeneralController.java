package api.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import api.model.General;
import api.service.GeneralService;



@Controller

public class GeneralController {
	
	@GetMapping("/index")
	public String codeListForm(Model model) {
		model.addAttribute("general", new General());
		
		return "/index";
	}
	
	
	@RequestMapping(value="/index",
			method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void createRole(@ModelAttribute General general) {
		
		GeneralService.create(general);
			
	}
}
