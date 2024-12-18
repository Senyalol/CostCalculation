package com.FinanceBack.FinanceBack.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.FinanceBack.FinanceBack.Entites.ProductMaterial;

@Repository
public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, Integer> {
    ProductMaterial findById(int id);
}
