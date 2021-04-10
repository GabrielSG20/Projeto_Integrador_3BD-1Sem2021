package com.airplan.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airplan.api.model.ManualModel;
import com.airplan.api.service.ManualService;

@RequestMapping("/createManual")
@RestController
public class ManualController {
	@Autowired
    private ManualService manualService;

    @PostMapping
    public ResponseEntity<Void> cadastrarCodeList(@RequestBody ManualModel manualModel){
        manualService.create(manualModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
