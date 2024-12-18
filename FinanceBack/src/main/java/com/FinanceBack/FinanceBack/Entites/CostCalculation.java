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
@Table(name = "cost_calculations")
public class CostCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cost_calculations_id_gen")
    @SequenceGenerator(name = "cost_calculations_id_gen", sequenceName = "cost_calculations_id_seq", allocationSize = 1)
    @Column(name = "costcalculation_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id")
    private com.FinanceBack.FinanceBack.Entites.Product product;

    @NotNull
    @Column(name = "total_material_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalMaterialCost;

    @NotNull
    @Column(name = "total_expenses", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalExpenses;

    @NotNull
    @Column(name = "total_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCost;

}