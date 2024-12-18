package com.FinanceBack.FinanceBack.DTO.CostCalculationDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateCostCalculationDTO {

    private Integer costcalculation_id;
    private Integer product_id;
    private BigDecimal totalMaterialCost;
    private BigDecimal totalExpenses;
    private BigDecimal totalCost;

}
