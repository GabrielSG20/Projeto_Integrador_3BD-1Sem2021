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
    private CodeListRepository repo;

    public void save(CodeList codeList) {

        if(codeList.getFlg_secundary().contains(",")) {
            String[] parts = codeList.getFlg_secundary().split(",");

            for (String part : parts) {
                CodeList newCodeList = new CodeList(codeList.getMnl_id(), part,
                        codeList.getCdl_section(), codeList.getCdl_block_number(),
                        codeList.getCdl_sub_section(), codeList.getCdl_block_name(),
                        codeList.getCdl_code());
                repo.save(newCodeList);
            }
        } else {
            repo.save(codeList);
        }

    }

    public CodeList get(Integer id) {return repo.findById(id).get();}

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public Page<CodeList> listAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1,10);
        return repo.findAll(pageable);
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
