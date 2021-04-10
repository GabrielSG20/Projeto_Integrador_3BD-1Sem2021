package com.airplan.api.controller;

import com.airplan.api.model.FlagModel;
import com.airplan.api.service.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/createCodeList")
public class FlagController {

    @Autowired
    private FlagService flagService;

    @PostMapping
    public ResponseEntity<Void> cadastrarFlag(@RequestBody FlagModel flagModel){
        flagService.createFlag(flagModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
