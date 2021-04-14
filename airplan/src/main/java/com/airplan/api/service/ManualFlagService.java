package com.airplan.api.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.airplan.api.model.ManualFlagModel;
import com.airplan.api.repository.ManualFlagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManualFlagService {
	
	@Autowired
	private ManualFlagRepository manualflagRepository;

	public void createManualFlag(ManualFlagModel manualflagModel) {
	   	manualflagRepository.save(manualflagModel);
	    }

	public List<ManualFlagModel> lista(ManualFlagModel manualFlagModel) {
		List<ManualFlagModel> listaFlag = manualflagRepository.findAll();
		return listaFlag;
	}
}
