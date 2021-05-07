package com.airPlan.Repository;

import com.airPlan.Entities.Manual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManualRepository extends JpaRepository<Manual, Integer> {
}
