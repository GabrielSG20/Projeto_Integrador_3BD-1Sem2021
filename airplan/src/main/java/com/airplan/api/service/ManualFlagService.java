package com.airplan.api.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.airplan.api.model.ManualFlagModel;
import com.airplan.api.repository.ManualFlagRepository;

public class ManualFlagService {
	
	  @Autowired
	    private ManualFlagRepository manualflagRepository;

	    public void createManualFlag(ManualFlagModel manualflagModel) {
	    	manualflagRepository.save(manualflagModel);

	    }

}
