package com.airplan.api.controller;

import com.airplan.api.model.ManualModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.airplan.api.model.ManualFlagModel;
import com.airplan.api.service.ManualFlagService;

import java.util.List;

@RequestMapping("/createManualFlag")
@RestController
public class ManualFlagController {

	@Autowired
	private ManualFlagService manualflagService;

	@GetMapping
	public ResponseEntity<List<ManualFlagModel>> listarCadastrados(ManualFlagModel manualFlagModel){
		List<ManualFlagModel> list =  manualflagService.lista(manualFlagModel);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> cadastrarManualFlag(@RequestBody ManualFlagModel manualflagModel){
	manualflagService.createManualFlag(manualflagModel);
	return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
