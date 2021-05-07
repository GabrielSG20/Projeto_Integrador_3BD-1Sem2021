package com.airPlan.Repository;


import com.airPlan.Entities.ManualFlag;
import com.airPlan.Entities.ManualFlagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManualFlagRepository extends JpaRepository<ManualFlag, ManualFlagId> {
}
