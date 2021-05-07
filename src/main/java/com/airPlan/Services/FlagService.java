package com.airPlan.Services;

import com.airPlan.Entities.Flag;
import com.airPlan.Repository.FlagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FlagService {

    @Autowired
    private FlagRepository repo;

    public void save(Flag flag) {repo.save(flag);}

}
