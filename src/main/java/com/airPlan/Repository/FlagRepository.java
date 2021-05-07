package com.airPlan.Repository;

import com.airPlan.Entities.Flag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlagRepository extends JpaRepository<Flag, String> {
}
