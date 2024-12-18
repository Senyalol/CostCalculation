package com.FinanceBack.FinanceBack.DTO.ProductDTO;
import lombok.Data;

@Data
public class ShortProductInfoDTO {

    private Integer product_id;
    private String name;
    private String description;
    private String price;
    private String count;
}
