package com.FinanceBack.FinanceBack.DTO.CostCalculationDTO;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class UpdateCostCalculationDTO {

    private Integer costcalculation_id;
    private Integer product_id;
    private BigDecimal totalMaterialCost;
    private BigDecimal totalExpenses;
    private BigDecimal totalCost;

}
