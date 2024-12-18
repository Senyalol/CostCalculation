package com.FinanceBack.FinanceBack.Service;

import com.FinanceBack.FinanceBack.DTO.ProductMaterialDTO.CreateProductMaterialDTO;
import com.FinanceBack.FinanceBack.DTO.ProductMaterialDTO.ShortProductMaterialInfoDTO;
import com.FinanceBack.FinanceBack.DTO.ProductMaterialDTO.UpdateProductMaterialDTO;
import com.FinanceBack.FinanceBack.Entites.ProductMaterial;
import com.FinanceBack.FinanceBack.Repository.ProductMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductMaterialService {

    private final ProductMaterialRepository productMaterialRepository;

    @Autowired
    public ProductMaterialService(ProductMaterialRepository productMaterialRepository) {
        this.productMaterialRepository = productMaterialRepository;
    }

    public List<ShortProductMaterialInfoDTO> getProductMaterials() {
        List<ProductMaterial> productMaterials = productMaterialRepository.findAll();

        return productMaterials.stream()
                .map(productMaterial -> {
                    ShortProductMaterialInfoDTO dto = new ShortProductMaterialInfoDTO();
                    dto.setProduct_material_id(productMaterial.getId());
                    dto.setProduct_id(productMaterial.getProduct().getId());
                    dto.setMaterial_id(productMaterial.getMaterial().getId());
                    dto.setQuantity(productMaterial.getQuantity());
                    return dto;
                }).toList();
    }

    public ShortProductMaterialInfoDTO getProductMaterialById(int id) {
        ProductMaterial productMaterial = productMaterialRepository.findById(id);

        ShortProductMaterialInfoDTO dto = new ShortProductMaterialInfoDTO();
        dto.setProduct_material_id(productMaterial.getId());
        dto.setProduct_id(productMaterial.getProduct().getId());
        dto.setMaterial_id(productMaterial.getMaterial().getId());
        dto.setQuantity(productMaterial.getQuantity());

        return dto;
    }

    public void createProductMaterial(CreateProductMaterialDTO createProductMaterialDTO) {
        ProductMaterial productMaterial = new ProductMaterial();
        productMaterial.setQuantity(createProductMaterialDTO.getQuantity());

        // Установить продукты и материалы (предполагается, что они уже существуют)
        productMaterial.setProduct(new com.FinanceBack.FinanceBack.Entites.Product());
        productMaterial.getProduct().setId(createProductMaterialDTO.getProduct_id());

        productMaterial.setMaterial(new com.FinanceBack.FinanceBack.Entites.Material());
        productMaterial.getMaterial().setId(createProductMaterialDTO.getMaterial_id());

        productMaterialRepository.save(productMaterial);
    }

    public void updateProductMaterial(int id, UpdateProductMaterialDTO updateProductMaterialDTO) {
        ProductMaterial productMaterialToUpdate = productMaterialRepository.findById(id);

        if (updateProductMaterialDTO.getQuantity() != null) {
            productMaterialToUpdate.setQuantity(updateProductMaterialDTO.getQuantity());
        }
        if (updateProductMaterialDTO.getProduct_id() != null) {
            productMaterialToUpdate.setProduct(new com.FinanceBack.FinanceBack.Entites.Product());
            productMaterialToUpdate.getProduct().setId(updateProductMaterialDTO.getProduct_id());
        }
        if (updateProductMaterialDTO.getMaterial_id() != null) {
            productMaterialToUpdate.setMaterial(new com.FinanceBack.FinanceBack.Entites.Material());
            productMaterialToUpdate.getMaterial().setId(updateProductMaterialDTO.getMaterial_id());
        }

        productMaterialRepository.save(productMaterialToUpdate);
    }

    public void deleteProductMaterial(int id) {
        ProductMaterial productMaterialToDelete = productMaterialRepository.findById(id);

        productMaterialRepository.delete(productMaterialToDelete);
    }
}