package airPlan.repository;

import org.springframework.data.repository.CrudRepository;
import airPlan.model.CodeList;
import airPlan.model.CodeListId;

public interface CodeListRepository extends CrudRepository<CodeList, CodeListId>{}
