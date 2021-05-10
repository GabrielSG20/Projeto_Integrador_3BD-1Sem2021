package com.airPlan.services;

import com.airPlan.entities.Flag;
import com.airPlan.repository.FlagRepository;
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
