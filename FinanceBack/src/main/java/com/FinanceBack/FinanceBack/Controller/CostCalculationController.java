package com.FinanceBack.FinanceBack.Controller;

import com.FinanceBack.FinanceBack.Service.CostCalculationService;
import com.FinanceBack.FinanceBack.DTO.CostCalculationDTO.CreateCostCalculationDTO;
import com.FinanceBack.FinanceBack.DTO.CostCalculationDTO.ShortCostCalculationInfoDTO;
import com.FinanceBack.FinanceBack.DTO.CostCalculationDTO.UpdateCostCalculationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;
import java.util.List;

@RestController
@RequestMapping("/api/cost-calculations")
public class CostCalculationController {

    private final CostCalculationService costCalculationService;

    @Autowired
    public CostCalculationController(CostCalculationService costCalculationService) {
        this.costCalculationService = costCalculationService;
    }

    @GetMapping
    public List<ShortCostCalculationInfoDTO> getAllCostCalculations() {
        return costCalculationService.getCostCalculations();
    }

    @GetMapping("/{id}")
    public ShortCostCalculationInfoDTO getCostCalculationById(@PathVariable int id) {
        return costCalculationService.getCostCalculationById(id);
    }

    @PostMapping
    public void createCostCalculation(@RequestBody CreateCostCalculationDTO createCostCalculationDTO) {
        costCalculationService.createCostCalculation(createCostCalculationDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateCostCalculation(@PathVariable("id") int id, @Valid @RequestBody UpdateCostCalculationDTO updateCostCalculationDTO) {
        try {
            costCalculationService.updateCostCalculation(id, updateCostCalculationDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCostCalculation(@PathVariable("id") int id) {
        try {
            costCalculationService.deleteCostCalculation(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}