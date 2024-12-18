package com.FinanceBack.FinanceBack.DTO.ProductMaterialDTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductMaterialDTO {

    private Integer product_material_id;
    private Integer product_id;
    private Integer material_id;
    private BigDecimal quantity;

}
