package com.airplan.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.airplan.api.model.ManualModel;
import com.airplan.api.service.ManualService;

import java.util.List;

@RequestMapping("/createManual")
@RestController
public class ManualController {
	@Autowired
    private ManualService manualService;

	@GetMapping
    public ResponseEntity<List<ManualModel>> listarCadastrados(ManualModel manualModel){
	    List<ManualModel> list = manualService.lista(manualModel);
	    return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Void> cadastrarCodeList(@RequestBody ManualModel manualModel){
        manualService.createManual(manualModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
