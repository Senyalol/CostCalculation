package com.FinanceBack.FinanceBack.DTO.MaterialDTO;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ShortMaterialInfoDTO {

    private Integer material_id;
    private String name;
    private BigDecimal costPerUnit;
    private String unit;

}
