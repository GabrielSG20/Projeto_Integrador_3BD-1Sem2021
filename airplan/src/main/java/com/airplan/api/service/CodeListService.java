package com.airplan.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airplan.api.model.CodeListModel;
import com.airplan.api.repository.CodeListRepository;

@Service
public class CodeListService {

    @Autowired
    private CodeListRepository codelistRepository;


    public void createCodelist(CodeListModel codelistModel) {
        codelistRepository.save(codelistModel);
    }

}
