package com.airPlan.Services;


import com.airPlan.Entities.Manual;
import com.airPlan.Repository.ManualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ManualService {

    @Autowired
    private ManualRepository repo;

    public List<Manual> listAll() {
        return repo.findAll();
    }

    public void save(Manual manual) {
        repo.save(manual);
    }

    public Manual get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
