package airPlan.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import airPlan.data.JdbcCodeListRepository;
import airPlan.data.SpringJdbcConfig;
import airPlan.model.CodeList;
import airPlan.model.General;
import airPlan.model.Manual;



@Controller
public class GeneralController {
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
	
	@GetMapping("/codeCreate")
	public String codeListForm(Model model) {
		model.addAttribute("general", new General());
		
		return "codeCreate";
	}
	
	
	@RequestMapping(value="/codeCreate",
			method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void createRole(@ModelAttribute General general, ModelMap model) {
		
		boolean result = GeneralCreate.create(general);
		
		if(result) {
			model.addAttribute("succesMsg", "Code List created...");
		}
		
	}
	
	@GetMapping("/codeDelete")
	public String codeListForm2(Model model) {
		model.addAttribute("general", new General());
		
		return "codeDelete";
	}
	
	@RequestMapping(value="/codeDelete",
			method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void createRole2(@ModelAttribute General general) {
		
		GeneralDelete.delete(general);
			
	}
	
	
	@GetMapping("/codeEdit")
	public String codeListForm3(Model model) {
		model.addAttribute("general", new General());
		
		return "codeEdit";
	}
	
	@RequestMapping(value="/codeEdit",
			method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void createRole3(@ModelAttribute General general) {
		
		GeneralEdit.edit(general);
			
	}
	
	@RequestMapping("/codeConsult")
	public String listCodelists(Model model) {
		SpringJdbcConfig jdbcConfig = new SpringJdbcConfig();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(jdbcConfig.mysqlDataSource());
		JdbcCodeListRepository codeListRepository = new JdbcCodeListRepository(jdbcTemplate);
		List<CodeList> codelists = codeListRepository.list();

		model.addAttribute("codeList", codelists);
		
		return "codeConsult";
	}
	
	@GetMapping("/codeImport")
	public String codeImport(Model model) {
		model.addAttribute("manual", new Manual());
		return "codeImport";
	}
	
	@RequestMapping("/codeImport")
	public String upload(@ModelAttribute Manual manual, Model model, @RequestParam("files") MultipartFile[] files) {
		StringBuilder fileNames = new StringBuilder();
		for(MultipartFile file: files) {
			Path fileNameAndPath = Paths.get(uploadDirectory,file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename());
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("msg", "Succesfully uploaded files "+fileNames.toString());
		
		GeneralImport.getCellData(manual.getMnl_name(), fileNames.toString());
		return "codeImport";
	}
	
}