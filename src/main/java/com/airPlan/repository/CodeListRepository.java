package com.airPlan.repository;

import com.airPlan.entities.CodeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CodeListRepository extends JpaRepository<CodeList, Integer> {

    @Query(" select c from CodeList c where c.mnl_id = :manualId ")
    List<CodeList> filtroManual(@Param("manualId") Integer manualId);

    @Query(" select c from CodeList c where c.mnl_id = :manualId and c.flg_secundary = :flgSecundary ")
    List<CodeList> filtroSecundary(@Param("manualId") Integer manualId, @Param("flgSecundary") String flgSecundary);

    @Query(" select c from CodeList c where c.mnl_id = :manualId and c.cdl_block_number = :cdlBlock ")
    List<CodeList> filtroBloco(@Param("manualId") Integer manualId, @Param("cdlBlock") Integer cdlBlock);

    @Query(" select c from CodeList c where c.mnl_id = :manualId and c.flg_secundary = :flgSecundary and c.cdl_block_number = :cdlBlock ")
    List<CodeList> filtroAll(@Param("manualId") Integer manualId, @Param("flgSecundary") String flgSecundary, @Param("cdlBlock") Integer cdlBlock);
}
