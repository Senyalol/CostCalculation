package com.FinanceBack.FinanceBack.Controller;

import com.FinanceBack.FinanceBack.Service.ProductService;
import com.FinanceBack.FinanceBack.DTO.ProductDTO.CreateProductDTO;
import com.FinanceBack.FinanceBack.DTO.ProductDTO.ShortProductInfoDTO;
import com.FinanceBack.FinanceBack.DTO.ProductDTO.UpdateProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ShortProductInfoDTO> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ShortProductInfoDTO getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public void createProduct(@RequestBody CreateProductDTO createProductDTO) {
        productService.createProduct(createProductDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") int id, @Valid @RequestBody UpdateProductDTO updateProductDTO) {
        try {
            productService.updateProduct(id, updateProductDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}