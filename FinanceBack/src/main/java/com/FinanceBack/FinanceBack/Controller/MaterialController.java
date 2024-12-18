package com.FinanceBack.FinanceBack.Controller;

import com.FinanceBack.FinanceBack.Service.MaterialService;
import com.FinanceBack.FinanceBack.DTO.MaterialDTO.CreateMaterialDTO;
import com.FinanceBack.FinanceBack.DTO.MaterialDTO.ShortMaterialInfoDTO;
import com.FinanceBack.FinanceBack.DTO.MaterialDTO.UpdateMaterialInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;
import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public List<ShortMaterialInfoDTO> getAllMaterials() {
        return materialService.getMaterials();
    }

    @GetMapping("/{id}")
    public ShortMaterialInfoDTO getMaterialById(@PathVariable int id) {
        return materialService.getMaterialById(id);
    }

    @PostMapping
    public void createMaterial(@RequestBody CreateMaterialDTO createMaterialDTO) {
        materialService.createMaterial(createMaterialDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateMaterial(@PathVariable("id") int id, @Valid @RequestBody UpdateMaterialInfoDTO updateMaterialDTO) {
        try {
            materialService.updateMaterial(id, updateMaterialDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable("id") int id) {
        try {
            materialService.deleteMaterial(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}