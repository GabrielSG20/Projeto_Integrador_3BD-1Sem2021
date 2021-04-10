package com.airplan.api.service;

import com.airplan.api.model.FlagModel;
import com.airplan.api.repository.CreateCodeRepository;
import com.airplan.api.repository.FlagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlagService {

    @Autowired
    private FlagRepository flagRepository;

    public void createFlag(FlagModel flagModel) {
        flagRepository.save(flagModel);

    }

}
