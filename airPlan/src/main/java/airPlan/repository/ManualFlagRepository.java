package airPlan.repository;

import org.springframework.data.repository.CrudRepository;
import airPlan.model.ManualFlag;
import airPlan.model.ManualFlagId;

public interface ManualFlagRepository extends CrudRepository<ManualFlag, ManualFlagId>{}
