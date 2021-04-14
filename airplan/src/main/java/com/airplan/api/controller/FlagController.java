package com.airplan.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.airplan.api.model.FlagModel;
import com.airplan.api.service.FlagService;

import java.util.List;


@RestController
@RequestMapping("/createFlag")
public class FlagController {

    @Autowired
    private FlagService flagService;

    @GetMapping
    public  ResponseEntity<List<FlagModel>> listarCadastrados(FlagModel flagModel){
        List<FlagModel> list = flagService.listar();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarFlag(@RequestBody FlagModel flagModel){
        flagService.createFlag(flagModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
