package com.FinanceBack.FinanceBack.DTO.ProductMaterialDTO;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateProductMaterialDTO {

    private Integer product_material_id;
    private Integer product_id;
    private Integer material_id;
    private BigDecimal quantity;

}
