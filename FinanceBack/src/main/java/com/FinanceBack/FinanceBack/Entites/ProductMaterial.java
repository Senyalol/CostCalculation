package com.FinanceBack.FinanceBack.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "product_materials")
public class ProductMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_materials_id_gen")
    @SequenceGenerator(name = "product_materials_id_gen", sequenceName = "product_materials_id_seq", allocationSize = 1)
    @Column(name = "product_material_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id")
    private com.FinanceBack.FinanceBack.Entites.Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "material_id")
    private com.FinanceBack.FinanceBack.Entites.Material material;

    @NotNull
    @Column(name = "quantity", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;

}