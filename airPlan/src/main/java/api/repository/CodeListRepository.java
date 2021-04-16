package api.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import api.model.CodeListModel;

import java.util.List;

@Repository
public interface CodeListRepository extends CrudRepository<CodeListModel, Long> {
    List<CodeListModel> findAll();
}
