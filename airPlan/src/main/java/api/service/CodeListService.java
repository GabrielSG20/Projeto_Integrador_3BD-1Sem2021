package api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.model.CodeListModel;
import api.repository.CodeListRepository;

import java.util.List;

@Service
public class CodeListService {

    @Autowired
    private CodeListRepository codeListRepository;

    public void createCodelist(CodeListModel codelistModel) {
        codeListRepository.save(codelistModel);
    }

    public List<CodeListModel> listar() {
        List<CodeListModel> lista = codeListRepository.findAll();
        return lista;
    }
}
