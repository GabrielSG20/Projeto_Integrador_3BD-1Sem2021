package airPlan.web;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import airPlan.General;



@Controller
public class GeneralController {
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
	
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
	
	
	@GetMapping("/codeEdit")
	public String codeListForm3(Model model) {
		model.addAttribute("general", new General());
		
		return "/codeEdit";
	}
	
	@RequestMapping(value="/codeEdit",
			method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void createRole3(@ModelAttribute General general) {
		
		GeneralEdit.edit(general);
			
	}
	
	@GetMapping("/codeImport")
	public String codeImport(Model model) {
		return "codeImport";
	}
	@RequestMapping("/codeImport")
	public String upload(Model model, @RequestParam("files") MultipartFile[] files) {
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
		
		GeneralImport.getCellData();
		return "codeImport";
	}
	
}