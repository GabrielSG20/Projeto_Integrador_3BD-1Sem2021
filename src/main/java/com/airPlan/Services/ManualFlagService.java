package com.airPlan.Services;

import com.airPlan.Entities.ManualFlag;
import com.airPlan.Entities.ManualFlagId;
import com.airPlan.Repository.ManualFlagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ManualFlagService {

    @Autowired
    private ManualFlagRepository repo;

    public void save(ManualFlag manualFlag) {repo.save(manualFlag);}
}
