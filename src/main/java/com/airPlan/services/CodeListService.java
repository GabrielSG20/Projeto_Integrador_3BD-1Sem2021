package com.airPlan.services;

import com.airPlan.entities.CodeList;
import com.airPlan.repository.CodeListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CodeListService {

    @Autowired
    CodeListRepository codeListRepository;

    public List<CodeList> listAll() {
        return codeListRepository.findAll();
    }
}
