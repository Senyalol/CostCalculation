package com.FinanceBack.FinanceBack.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "materials_id_gen")
    @SequenceGenerator(name = "materials_id_gen", sequenceName = "materials_id_seq", allocationSize = 1)
    @Column(name = "material_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "cost_per_unit", nullable = false, precision = 10, scale = 2)
    private BigDecimal costPerUnit;

    @Size(max = 50)
    @NotNull
    @Column(name = "unit", nullable = false, length = 50)
    private String unit;

}