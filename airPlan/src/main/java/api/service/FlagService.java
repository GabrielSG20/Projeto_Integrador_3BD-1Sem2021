package api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.model.FlagModel;
import api.repository.FlagRepository;

import java.util.List;

@Service
public class FlagService {

    @Autowired
    private FlagRepository flagRepository;

    public void createFlag(FlagModel flagModel) {
        flagRepository.save(flagModel);

    }

    public List<FlagModel> listar() {
        List<FlagModel> lista = flagRepository.findAll();
        return lista;
    }
}
