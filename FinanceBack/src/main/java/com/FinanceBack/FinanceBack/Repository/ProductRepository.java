package com.FinanceBack.FinanceBack.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.FinanceBack.FinanceBack.Entites.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByName(String name);

}
