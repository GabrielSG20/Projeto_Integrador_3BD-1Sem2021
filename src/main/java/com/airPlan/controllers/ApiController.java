package com.airPlan.controllers;

import com.airPlan.entities.*;
import com.airPlan.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.servlet.ModelAndView;


import java.util.List;


@Controller
public class ApiController {

    @Autowired
    private ManualService manualService;
    @Autowired
    private FlagService flagService;
    @Autowired
    private CodeListService codeListService;
    @Autowired
    private ImportCodeList importCodeList;
    @Autowired
    private ManualFlagService manualFlagService;

    public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";

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
    public String saveCodeList(@ModelAttribute("general") General general){

        Manual manual = new Manual(general.getMnl_name());
        manualService.save(manual);

        manual.setMnl_id(manualService.findManualByName(manual.getMnl_name()));

        Flag flag = new Flag(general.getFlg_secundary_id(), general.getFlg_tag());
        flagService.save(flag, manual.getMnl_id());

        CodeList codeList = new CodeList(manual.getMnl_id(), general.getFlg_secundary(),
                                                        general.getCdl_section(),
                                                        Integer.parseInt(general.getCdl_block_number()),
                                                        general.getCdl_sub_section(), general.getCdl_block_name(),
                                                        Integer.parseInt(general.getCdl_code()));
        codeListService.save(codeList);

        return "code-create";
    }

    @GetMapping("/code-import")
    public String showCodeImportPage(Model model) {
        model.addAttribute("manual", new Manual());
        return "code-import";
    }

    @RequestMapping("/code-import")
    public String importCodeList(@ModelAttribute Manual manual, Model model, @RequestParam("files") MultipartFile[] files) {
        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("msg", "Succesfully uploaded files " + fileNames.toString());

        importCodeList.getCellData(manual.getMnl_name(), fileNames.toString());

        return "code-import";
    }



    @RequestMapping("/edit/{id}")
    public ModelAndView showCodeEditPage (@PathVariable(name="id") Integer id) {

        ModelAndView mav = new ModelAndView("code-edit");
        CodeList codeList = codeListService.get(id);
        System.out.println(codeList);
        mav.addObject("codelist", codeList);

        return mav;
    }

    @RequestMapping(value = "/save-edit", method = RequestMethod.POST)
    public String saveCodeListEdit(@ModelAttribute("codelist") CodeList codeList){

        if(codeList.getCdl_sub_section().equals("")) {
            codeList.setCdl_sub_section(null);
        }
        codeListService.save(codeList);

        return "redirect:/code-consult";
    }


    @RequestMapping("/code-consult")
    public String listCodelists(Model model) {
        return listaPaginas(model, 1);
    }

    @GetMapping("/page/{pageNumber}")
    public String listaPaginas(Model model, @PathVariable("pageNumber") int currentPage){
        Page<CodeList> page = codeListService.listAll(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<CodeList> codelists = page.getContent();

        List<Manual> manuals = manualService.listAll();

        model.addAttribute("manual", manuals);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("codeList", codelists);

        return "code-consult";
    }

    @PostMapping("**/filtro")
    public String filtrar(@RequestParam("flg_secundary") String flg_secundary, @RequestParam("mnl_name") String mnl_name,
                          @RequestParam("cdl_block_number") String cdl_block_number, Model model){
        int currentPage = 1;
        if (mnl_name.equals("")) {
            return listaPaginas(model, 1);
        } else {
            Manual manualModel = new Manual(manualService.findManualByName(mnl_name),mnl_name);

            List<CodeList> codelists = codeListService.filtrar(String.valueOf(manualModel.getMnl_id()), flg_secundary, cdl_block_number);
            long totalItems = codelists.size();
            int totalPages = 1;

            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("codeList", codelists);
        }
        List<Manual> manuals = manualService.listAll();

        model.addAttribute("manual", manuals);
        return "code-consult";
    }

    @GetMapping("/delete/{id}")
    public String deleteCodeList(@PathVariable(name = "id") Integer id) {
        codeListService.delete(id);
        return "redirect:/code-consult";
    }

    @GetMapping("/delete-manual/{id}")
    public String deleteManual(@PathVariable(name = "id") Integer id) {
        manualService.delete(id);
        return "redirect:/code-consult";
    }
}