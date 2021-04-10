package com.airplan.api.controller;

import com.airplan.api.model.CodeListModel;
import com.airplan.api.model.FlagModel;
import com.airplan.api.service.CreateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping()
@RestController
public class CreateCodeController {

    @Autowired
    private CreateCodeService createCodeService;

    @PostMapping
    public ResponseEntity<Void> cadastrarCodeList(@RequestBody CodeListModel codeListModel){
        createCodeService.create(codeListModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
