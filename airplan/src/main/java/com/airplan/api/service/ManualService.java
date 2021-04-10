package com.airplan.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airplan.api.model.ManualModel;
import com.airplan.api.repository.ManualRepository;

@Service
public class ManualService {
	@Autowired
    private ManualRepository manualRepository;


    public void create(ManualModel manualModel) {
        manualRepository.save(manualModel);
    }
}
