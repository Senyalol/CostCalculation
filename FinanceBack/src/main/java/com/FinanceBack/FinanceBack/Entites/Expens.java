package com.FinanceBack.FinanceBack.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "expenses")
public class Expens {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expenses_id_gen")
    @SequenceGenerator(name = "expenses_id_gen", sequenceName = "expenses_id_seq", allocationSize = 1)
    @Column(name = "expenses_id", nullable = false)
    private Integer id;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @NotNull
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

}