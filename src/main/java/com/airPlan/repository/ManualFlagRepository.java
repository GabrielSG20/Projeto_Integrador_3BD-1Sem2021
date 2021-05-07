package com.airPlan.repository;


import com.airPlan.entities.ManualFlag;
import com.airPlan.entities.ManualFlagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManualFlagRepository extends JpaRepository<ManualFlag, ManualFlagId> {
}
