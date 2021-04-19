package airPlan.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import airPlan.General;



@Controller
public class GeneralController {
	
	@GetMapping("/codeCreate")
	public String codeListForm(Model model) {
		model.addAttribute("general", new General());
		
		return "/codeCreate";
	}
	
	
	@RequestMapping(value="/codeCreate",
			method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void createRole(@ModelAttribute General general) {
		
		GeneralCreate.create(general);
			
	}
	
	@GetMapping("/codeDelete")
	public String codeListForm2(Model model) {
		model.addAttribute("general", new General());
		
		return "/codeDelete";
	}
	
	@RequestMapping(value="/codeDelete",
			method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void createRole2(@ModelAttribute General general) {
		
		GeneralDelete.delete(general);
			
	}
}