package com.airplan.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.airplan.api.model.ManualModel;

@Repository
public interface ManualRepository extends CrudRepository<ManualModel, Long>{

}
