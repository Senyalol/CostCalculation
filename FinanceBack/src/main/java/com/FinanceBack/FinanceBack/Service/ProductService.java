package com.FinanceBack.FinanceBack.Service;

import com.FinanceBack.FinanceBack.DTO.ProductDTO.CreateProductDTO;
import com.FinanceBack.FinanceBack.DTO.ProductDTO.ShortProductInfoDTO;
import com.FinanceBack.FinanceBack.DTO.ProductDTO.UpdateProductDTO;
import com.FinanceBack.FinanceBack.Entites.Product;
import com.FinanceBack.FinanceBack.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ShortProductInfoDTO> getProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> {
                    ShortProductInfoDTO productDTO = new ShortProductInfoDTO();
                    productDTO.setProduct_id(product.getId());
                    productDTO.setName(product.getName());
                    productDTO.setDescription(product.getDescription());
                    productDTO.setPrice(product.getPrice());
                    productDTO.setCount(product.getCount());
                    return productDTO;
                }).toList();
    }

    public ShortProductInfoDTO getProductById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        ShortProductInfoDTO productDTO = new ShortProductInfoDTO();
        productDTO.setProduct_id(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCount(product.getCount());

        return productDTO;
    }

    public void createProduct(CreateProductDTO createProductDTO) {
        Product product = new Product();
        product.setName(createProductDTO.getName());
        product.setDescription(createProductDTO.getDescription());
        product.setPrice(createProductDTO.getPrice());
        product.setCount(createProductDTO.getCount());

        productRepository.save(product);
    }

    public void updateProduct(int id, UpdateProductDTO updateProductDTO) {
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product with ID " + id + " not found"));

        if (updateProductDTO.getName() != null) {
            productToUpdate.setName(updateProductDTO.getName());
        }
        if (updateProductDTO.getDescription() != null) {
            productToUpdate.setDescription(updateProductDTO.getDescription());
        }
        if (updateProductDTO.getPrice() != null) {
            productToUpdate.setPrice(updateProductDTO.getPrice());
        }
        if (updateProductDTO.getCount() != null) {
            productToUpdate.setCount(updateProductDTO.getCount());
        }

        productRepository.save(productToUpdate);
    }

    public void deleteProduct(int id) {
        Product productToDelete = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product with ID " + id + " not found"));

        productRepository.delete(productToDelete);
    }
}