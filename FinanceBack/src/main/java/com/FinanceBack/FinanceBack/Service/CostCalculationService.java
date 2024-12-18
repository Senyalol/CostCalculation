package com.FinanceBack.FinanceBack.Service;

import com.FinanceBack.FinanceBack.DTO.CostCalculationDTO.CreateCostCalculationDTO;
import com.FinanceBack.FinanceBack.DTO.CostCalculationDTO.ShortCostCalculationInfoDTO;
import com.FinanceBack.FinanceBack.DTO.CostCalculationDTO.UpdateCostCalculationDTO;
import com.FinanceBack.FinanceBack.Entites.CostCalculation;
import com.FinanceBack.FinanceBack.Repository.CostCalulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CostCalculationService {

    private final CostCalulationRepository costCalculationRepository;

    @Autowired
    public CostCalculationService(CostCalulationRepository costCalculationRepository) {
        this.costCalculationRepository = costCalculationRepository;
    }

    public List<ShortCostCalculationInfoDTO> getCostCalculations() {
        List<CostCalculation> costCalculations = costCalculationRepository.findAll();

        return costCalculations.stream()
                .map(costCalculation -> {
                    ShortCostCalculationInfoDTO dto = new ShortCostCalculationInfoDTO();
                    dto.setCostcalculation_id(costCalculation.getId());
                    dto.setProduct_id(costCalculation.getProduct().getId());
                    dto.setTotalMaterialCost(costCalculation.getTotalMaterialCost());
                    dto.setTotalExpenses(costCalculation.getTotalExpenses());
                    dto.setTotalCost(costCalculation.getTotalCost());
                    return dto;
                }).toList();
    }

    public ShortCostCalculationInfoDTO getCostCalculationById(int id) {
        CostCalculation costCalculation = costCalculationRepository.findById(id);

        ShortCostCalculationInfoDTO dto = new ShortCostCalculationInfoDTO();
        dto.setCostcalculation_id(costCalculation.getId());
        dto.setProduct_id(costCalculation.getProduct().getId());
        dto.setTotalMaterialCost(costCalculation.getTotalMaterialCost());
        dto.setTotalExpenses(costCalculation.getTotalExpenses());
        dto.setTotalCost(costCalculation.getTotalCost());

        return dto;
    }

    public void createCostCalculation(CreateCostCalculationDTO createCostCalculationDTO) {
        CostCalculation costCalculation = new CostCalculation();

        costCalculation.setTotalMaterialCost(createCostCalculationDTO.getTotalMaterialCost());
        costCalculation.setTotalExpenses(createCostCalculationDTO.getTotalExpenses());
        costCalculation.setTotalCost(createCostCalculationDTO.getTotalCost());

        // Установить продукт (предполагается, что он уже существует)
        costCalculation.setProduct(new com.FinanceBack.FinanceBack.Entites.Product());
        costCalculation.getProduct().setId(createCostCalculationDTO.getProduct_id());

        costCalculationRepository.save(costCalculation);
    }

    public void updateCostCalculation(int id, UpdateCostCalculationDTO updateCostCalculationDTO) {
        CostCalculation costCalculationToUpdate = costCalculationRepository.findById(id);

        if (updateCostCalculationDTO.getTotalMaterialCost() != null) {
            costCalculationToUpdate.setTotalMaterialCost(updateCostCalculationDTO.getTotalMaterialCost());
        }
        if (updateCostCalculationDTO.getTotalExpenses() != null) {
            costCalculationToUpdate.setTotalExpenses(updateCostCalculationDTO.getTotalExpenses());
        }
        if (updateCostCalculationDTO.getTotalCost() != null) {
            costCalculationToUpdate.setTotalCost(updateCostCalculationDTO.getTotalCost());
        }
        if (updateCostCalculationDTO.getProduct_id() != null) {
            costCalculationToUpdate.setProduct(new com.FinanceBack.FinanceBack.Entites.Product());
            costCalculationToUpdate.getProduct().setId(updateCostCalculationDTO.getProduct_id());
        }

        costCalculationRepository.save(costCalculationToUpdate);
    }

    public void deleteCostCalculation(int id) {
        CostCalculation costCalculationToDelete = costCalculationRepository.findById(id);

        costCalculationRepository.delete(costCalculationToDelete);
    }
}