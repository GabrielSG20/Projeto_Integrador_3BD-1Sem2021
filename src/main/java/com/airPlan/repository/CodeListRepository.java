package com.airPlan.repository;

import com.airPlan.entities.CodeList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeListRepository extends JpaRepository<CodeList, Integer> {
}
