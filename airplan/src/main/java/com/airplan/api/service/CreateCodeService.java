package com.airplan.api.service;

import com.airplan.api.model.CodeListModel;
import com.airplan.api.model.FlagModel;
import com.airplan.api.repository.CreateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airplan.api.controller.CreateCodeController;

@Service
public class CreateCodeService {

    @Autowired
    private CreateCodeRepository createCodeRepository;


    public void create(CodeListModel codelistModel) {
        createCodeRepository.save(codelistModel);
    }

}
