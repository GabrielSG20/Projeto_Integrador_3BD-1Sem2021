package api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import api.model.ManualModel;

import java.util.List;

@Repository
public interface ManualRepository extends CrudRepository<ManualModel, Long>{
    List<ManualModel> findAll();
}
