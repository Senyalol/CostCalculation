package com.FinanceBack.FinanceBack.Controller;

import com.FinanceBack.FinanceBack.Service.ProductMaterialService;
import com.FinanceBack.FinanceBack.DTO.ProductMaterialDTO.CreateProductMaterialDTO;
import com.FinanceBack.FinanceBack.DTO.ProductMaterialDTO.ShortProductMaterialInfoDTO;
import com.FinanceBack.FinanceBack.DTO.ProductMaterialDTO.UpdateProductMaterialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;
import java.util.List;

@RestController
@RequestMapping("/api/product-materials")
public class ProductMaterialController {

    private final ProductMaterialService productMaterialService;

    @Autowired
    public ProductMaterialController(ProductMaterialService productMaterialService) {
        this.productMaterialService = productMaterialService;
    }

    @GetMapping
    public List<ShortProductMaterialInfoDTO> getAllProductMaterials() {
        return productMaterialService.getProductMaterials();
    }

    @GetMapping("/{id}")
    public ShortProductMaterialInfoDTO getProductMaterialById(@PathVariable int id) {
        return productMaterialService.getProductMaterialById(id);
    }

    @PostMapping
    public void createProductMaterial(@RequestBody CreateProductMaterialDTO createProductMaterialDTO) {
        productMaterialService.createProductMaterial(createProductMaterialDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateProductMaterial(@PathVariable("id") int id, @Valid @RequestBody UpdateProductMaterialDTO updateProductMaterialDTO) {
        try {
            productMaterialService.updateProductMaterial(id, updateProductMaterialDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductMaterial(@PathVariable("id") int id) {
        try {
            productMaterialService.deleteProductMaterial(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}