package api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.model.ManualModel;
import api.repository.ManualRepository;

import java.util.List;

@Service
public class ManualService {
	@Autowired
    private ManualRepository manualRepository;


    public void createManual(ManualModel manualModel) {
        manualRepository.save(manualModel);
    }

    public List<ManualModel> lista(ManualModel manualModel) {
        List<ManualModel> listManual = manualRepository.findAll();
        return listManual;
    }
}
