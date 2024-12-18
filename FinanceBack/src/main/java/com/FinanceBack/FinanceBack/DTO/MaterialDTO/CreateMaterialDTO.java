package com.FinanceBack.FinanceBack.DTO.MaterialDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateMaterialDTO {

    private Integer material_id;
    private String name;
    private BigDecimal costPerUnit;
    private String unit;

}
