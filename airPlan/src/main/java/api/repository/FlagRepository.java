package api.repository;

import api.model.FlagModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlagRepository extends CrudRepository<FlagModel, Long> {
    List<FlagModel> findAll();
}
