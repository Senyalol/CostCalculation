package com.FinanceBack.FinanceBack.DTO.ExpensDTO;

import java.math.BigDecimal;
import lombok.Data;
@Data
public class UpdateExpensDTO {
    private Integer expenses_id;
    private String description;
    private BigDecimal amount;
}
