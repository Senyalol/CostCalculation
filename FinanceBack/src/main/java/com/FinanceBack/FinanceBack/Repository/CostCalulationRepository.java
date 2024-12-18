package com.FinanceBack.FinanceBack.Repository;

import com.FinanceBack.FinanceBack.Entites.CostCalculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostCalulationRepository extends JpaRepository<CostCalculation, Long> {
    CostCalculation findById(int id);
}
