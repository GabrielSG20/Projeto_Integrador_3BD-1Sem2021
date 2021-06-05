package com.airPlan.controllers;

import com.airPlan.entities.Pdf;
import com.airPlan.services.PdfService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class PdfController {


    private final PdfService service;

    public PdfController(PdfService service) {
        this.service = service;
    }

    @RequestMapping("/pdf-full")
    public String pdfFull(Model model) {

        model.addAttribute("pdf", new Pdf());

        return "pdf-full";
    }

    @RequestMapping(value = "/pdf-full", method = RequestMethod.POST)
    public String createFULL(@ModelAttribute("pdf") Pdf pdf, RedirectAttributes redirAttrs) throws IOException {

        if(!PdfService.checkIntegrity(pdf)) {
            redirAttrs.addFlashAttribute("error", "Incorrect data, check" +
                    " fields integrity, eg.: all fields are filled?");
            return "redirect:/pdf-full";
        }

        service.full(pdf);
        redirAttrs.addFlashAttribute("success", "FULL pdf successfully created!");
        return "redirect:/pdf-full";

    }

    @RequestMapping("/pdf-delta")
    public String pdfDelta(Model model) {

        model.addAttribute("pdf", new Pdf());

        return "pdf-delta";
    }

    @RequestMapping(value = "/pdf-delta", method = RequestMethod.POST)
    public String createDELTA(@ModelAttribute("pdf") Pdf pdf, RedirectAttributes redirAttrs) throws IOException {

        if(!PdfService.checkIntegrity(pdf)) {
            redirAttrs.addFlashAttribute("error", "Incorrect data, check" +
                    " fields integrity, eg.: all fields are filled?");
            return "redirect:/pdf-delta";
        }

        service.delta(pdf);
        redirAttrs.addFlashAttribute("success", "DELTA pdf successfully created!");
        return "redirect:/pdf-delta";

    }
}
