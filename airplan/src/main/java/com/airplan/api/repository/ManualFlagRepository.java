package com.airplan.api.repository;

import com.airplan.api.model.ManualModel;
import org.springframework.data.repository.CrudRepository;

import com.airplan.api.model.ManualFlagModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManualFlagRepository extends CrudRepository<ManualFlagModel, Long> {
    List<ManualFlagModel> findAll();
}
