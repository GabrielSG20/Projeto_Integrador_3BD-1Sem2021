package com.airPlan.services;

import com.airPlan.repository.CodeListRepository;
import com.airPlan.entities.CodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CodeListService {

    @Autowired
    private CodeListRepository repo;

    public List<CodeList> listAll() {return repo.findAll();}

    public void save(CodeList codeList) {repo.save(codeList);}

    public CodeList get(CodeList codeList) {return repo.findById(codeList.getCdl_id()).get();}

    public void delete(CodeList codeList) {
        repo.deleteById(codeList.getCdl_id());
    }
}
