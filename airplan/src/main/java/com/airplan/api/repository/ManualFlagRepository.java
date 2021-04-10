package com.airplan.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.airplan.api.model.ManualFlagModel;

public interface ManualFlagRepository extends CrudRepository<ManualFlagModel, Long> {

}
