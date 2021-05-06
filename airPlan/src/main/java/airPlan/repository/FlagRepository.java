package airPlan.repository;

import org.springframework.data.repository.CrudRepository;
import airPlan.model.Flag;

public interface FlagRepository extends CrudRepository<Flag, String>{}
