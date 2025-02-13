package com.FinanceBack.FinanceBack.Repository;
import com.FinanceBack.FinanceBack.Entites.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    Material findByName(String name);

}
