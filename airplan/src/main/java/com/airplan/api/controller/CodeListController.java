package com.airplan.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.airplan.api.model.CodeListModel;
import com.airplan.api.service.CodeListService;

import java.util.List;

@RequestMapping("/createCodeList")
@RestController
public class CodeListController {

    @Autowired
    private CodeListService codelistService;

    @GetMapping
    public ResponseEntity<List<CodeListModel>> listarCadastrados(CodeListModel codeListModel){
        List<CodeListModel> list = codelistService.listar();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarCodeList(@RequestBody CodeListModel codeListModel){
        codelistService.createCodelist(codeListModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
