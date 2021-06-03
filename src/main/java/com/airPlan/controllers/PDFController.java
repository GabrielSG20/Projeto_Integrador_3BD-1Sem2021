package com.airPlan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PDFController {

    @GetMapping("/pdf-full")
    public String pdfFull() {

        return "pdf-full";
    }

    @GetMapping("/pdf-delta")
    public String pdfDelta() {

        return "pdf-delta";
    }
}
