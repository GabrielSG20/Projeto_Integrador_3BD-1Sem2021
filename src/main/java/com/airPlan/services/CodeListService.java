package com.airPlan.services;

import com.airPlan.entities.CodeList;
import com.airPlan.repository.CodeListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CodeListService {

    @Autowired
    CodeListRepository codeListRepository;

    public Page<CodeList> listAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1,10);
        return codeListRepository.findAll(pageable);
    }

    public List<CodeList> filtrar(String manualId, String flgSecundary, String cdlBlockNumber){
        if (flgSecundary.equals("") && cdlBlockNumber.equals("")) {
            return codeListRepository.filtroManual(Integer.parseInt(manualId));
        } else if(cdlBlockNumber.equals("")){
            return codeListRepository.filtroSecundary(Integer.parseInt(manualId), flgSecundary);
        } else if (flgSecundary.equals("")){
            return codeListRepository.filtroBloco(Integer.parseInt(manualId), Integer.parseInt(cdlBlockNumber));
        } else {
            return codeListRepository.filtroAll(Integer.parseInt(manualId), flgSecundary, Integer.parseInt(cdlBlockNumber));
        }
    }
}
