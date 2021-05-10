package com.airPlan.repository;

import com.airPlan.entities.CodeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CodeListRepository extends JpaRepository<CodeList, Integer> {
    @Query(" from CodeList where mnl_id = ?1 ")
    public List<CodeList> filtroManual(Integer manualId);

    @Query(" from CodeList where mnl_id = ?1 and flg_secundary = ?2 ")
    public List<CodeList> filtroSecundary(Integer manualId, String flgSecundary);

    @Query(" from CodeList where mnl_id = ?1 and cdl_block_number = ?2 ")
    public List<CodeList> filtroBloco(Integer manualId, Integer cdlBlock);

    @Query(" from CodeList where mnl_id = ?1 and flg_secundary = ?2 and cdl_block_number = ?3 ")
    public List<CodeList> filtroAll(Integer manualId, String flgSecundary, Integer cdlBlock);
}
