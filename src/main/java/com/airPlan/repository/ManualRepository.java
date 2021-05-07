package com.airPlan.repository;

import com.airPlan.entities.Manual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ManualRepository extends JpaRepository<Manual, Integer> {
    @Query(" select mnl_id from Manual where mnl_name = ?1 ")
    Integer findManualByName(String nomeManual);
}
