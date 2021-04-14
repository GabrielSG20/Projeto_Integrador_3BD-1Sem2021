package com.airplan.api.repository;

import com.airplan.api.model.CodeListModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CodeListRepository extends CrudRepository<CodeListModel, Long> {
    List<CodeListModel> findAll();
}
