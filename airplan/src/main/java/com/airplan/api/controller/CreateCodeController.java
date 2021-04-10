package com.airplan.api.controller;

import com.airplan.api.model.FlagModel;
import com.airplan.api.service.CreateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/createCodeList")
@Controller
public class CreateCodeController {

    @Autowired
    private CreateCodeService createCodeService;



    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody FlagModel createCodeModel){
        createCodeService.create(createCodeModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
