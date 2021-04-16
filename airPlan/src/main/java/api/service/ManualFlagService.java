package api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.model.ManualFlagModel;
import api.repository.ManualFlagRepository;

@Service
public class ManualFlagService {

      @Autowired
        private ManualFlagRepository manualflagRepository;

        public void createManualFlag(ManualFlagModel manualflagModel) {
            manualflagRepository.save(manualflagModel);

        }


}