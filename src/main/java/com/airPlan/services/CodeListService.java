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
    private CodeListRepository repo;

    public void save(CodeList codeList) {repo.save(codeList);}

    public CodeList get(CodeList codeList) {return repo.findById(codeList.getCdl_id()).get();}

    public void delete(CodeList codeList) {
        repo.deleteById(codeList.getCdl_id());
    }

    public List<CodeList> listAll() {
        return repo.findAll();
    }

    public List<CodeList> filtrar(String manualId, String flgSecundary, String cdlBlockNumber){
        if (flgSecundary.equals("") && cdlBlockNumber.equals("")) {
            return repo.filtroManual(Integer.parseInt(manualId));
        } else if(cdlBlockNumber.equals("")){
            return repo.filtroSecundary(Integer.parseInt(manualId), flgSecundary);
        } else if (flgSecundary.equals("")){
            return repo.filtroBloco(Integer.parseInt(manualId), Integer.parseInt(cdlBlockNumber));
        } else {
            return repo.filtroAll(Integer.parseInt(manualId), flgSecundary, Integer.parseInt(cdlBlockNumber));
        }
    }
}
