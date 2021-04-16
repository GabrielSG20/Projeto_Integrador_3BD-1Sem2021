package api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import api.model.ManualFlagModel;

import java.util.List;

@Repository
public interface ManualFlagRepository extends CrudRepository<ManualFlagModel, Long>{
    List<ManualFlagModel> findAll();
}
