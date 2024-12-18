package com.FinanceBack.FinanceBack.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.FinanceBack.FinanceBack.Entites.Expens;

@Repository
public interface ExpensRepository extends JpaRepository<Expens, Integer> {
    Expens findById(int id);
}
