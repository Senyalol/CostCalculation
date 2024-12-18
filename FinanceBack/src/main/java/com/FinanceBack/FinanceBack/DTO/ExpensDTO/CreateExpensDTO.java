package com.FinanceBack.FinanceBack.DTO.ExpensDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateExpensDTO {

    private Integer expenses_id;
    private String description;
    private BigDecimal amount;

}
