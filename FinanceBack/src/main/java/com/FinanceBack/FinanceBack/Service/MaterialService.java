package com.FinanceBack.FinanceBack.Service;

import com.FinanceBack.FinanceBack.DTO.MaterialDTO.CreateMaterialDTO;
import com.FinanceBack.FinanceBack.DTO.MaterialDTO.ShortMaterialInfoDTO;
import com.FinanceBack.FinanceBack.DTO.MaterialDTO.UpdateMaterialInfoDTO;
import com.FinanceBack.FinanceBack.Entites.Material;
import com.FinanceBack.FinanceBack.Repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class MaterialService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<ShortMaterialInfoDTO> getMaterials() {
        List<Material> materials = materialRepository.findAll();

        return materials.stream()
                .map(material -> {
                    ShortMaterialInfoDTO materialDTO = new ShortMaterialInfoDTO();
                    materialDTO.setMaterial_id(material.getId());
                    materialDTO.setName(material.getName());
                    materialDTO.setCostPerUnit(material.getCostPerUnit());
                    materialDTO.setUnit(material.getUnit());
                    return materialDTO;
                }).toList();
    }

    public ShortMaterialInfoDTO getMaterialById(int id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Material not found"));

        ShortMaterialInfoDTO materialDTO = new ShortMaterialInfoDTO();
        materialDTO.setMaterial_id(material.getId());
        materialDTO.setName(material.getName());
        materialDTO.setCostPerUnit(material.getCostPerUnit());
        materialDTO.setUnit(material.getUnit());

        return materialDTO;
    }

    public void createMaterial(CreateMaterialDTO createMaterialDTO) {
        Material material = new Material();
        material.setName(createMaterialDTO.getName());
        material.setCostPerUnit(createMaterialDTO.getCostPerUnit());
        material.setUnit(createMaterialDTO.getUnit());

        materialRepository.save(material);
    }

    public void updateMaterial(int id, UpdateMaterialInfoDTO updateMaterialDTO) {
        Material materialToUpdate = materialRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Material with ID " + id + " not found"));

        if (updateMaterialDTO.getName() != null) {
            materialToUpdate.setName(updateMaterialDTO.getName());
        }
        if (updateMaterialDTO.getCostPerUnit() != null) {
            materialToUpdate.setCostPerUnit(updateMaterialDTO.getCostPerUnit());
        }
        if (updateMaterialDTO.getUnit() != null) {
            materialToUpdate.setUnit(updateMaterialDTO.getUnit());
        }

        materialRepository.save(materialToUpdate);
    }

    public void deleteMaterial(int id) {
        Material materialToDelete = materialRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Material with ID " + id + " not found"));

        materialRepository.delete(materialToDelete);
    }
}