package com.airplan.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airplan.api.model.ManualModel;
import com.airplan.api.repository.ManualRepository;

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
