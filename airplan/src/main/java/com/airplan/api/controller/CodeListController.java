package com.airplan.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.airplan.api.model.CodeListModel;
import com.airplan.api.service.CodeListService;

@RequestMapping("/createCodeList")
@RestController
public class CodeListController {

    @Autowired
    private CodeListService codelistService;

    @PostMapping
    public ResponseEntity<Void> cadastrarCodeList(@RequestBody CodeListModel codeListModel){
        codelistService.createCodelist(codeListModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
