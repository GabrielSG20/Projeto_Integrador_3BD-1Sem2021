package com.airPlan.services;

import com.airPlan.entities.CodeList;
import com.airPlan.entities.General;
import com.airPlan.entities.Manual;
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

    public void saveCodeList(CodeList[] codeList, int n, Manual manual) {
            for (int j = 0; j < n; j++){
                codeList[j].setMnl_id(manual.getMnl_id());
                if(codeList[j].getFlg_secundary().contains(",")) {
                    String[] parts = codeList[j].getFlg_secundary().split(",");

                    for (String part : parts) {
                        CodeList newCodeList = new CodeList(codeList[j].getMnl_id(), part,
                                codeList[j].getCdl_section(), codeList[j].getCdl_block_number(),
                                codeList[j].getCdl_sub_section().equals("")? null : codeList[j].getCdl_sub_section(),
                                codeList[j].getCdl_block_name(),
                                codeList[j].getCdl_code());
                        repo.save(newCodeList);
                    }
                } else {
                    CodeList newCodeList = new CodeList(codeList[j].getMnl_id(), codeList[j].getFlg_secundary(),
                            codeList[j].getCdl_section(), codeList[j].getCdl_block_number(),
                            codeList[j].getCdl_sub_section().equals("")? null : codeList[j].getCdl_sub_section(),
                            codeList[j].getCdl_block_name(),
                            codeList[j].getCdl_code());

                    repo.save(newCodeList);
                }
            }
    }

    public CodeList get(Integer id) {return repo.findById(id).get();}

    public void delete(Integer id) {repo.deleteById(id);}

    public void save(CodeList codeList) {repo.save(codeList);}

    public List<CodeList> filtroLep(final Integer mnlId, final String flgTag) {
        return this.repo.filtroLep(mnlId, flgTag);
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
