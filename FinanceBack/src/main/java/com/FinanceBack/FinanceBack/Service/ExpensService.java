package com.FinanceBack.FinanceBack.Service;

import com.FinanceBack.FinanceBack.DTO.ExpensDTO.CreateExpensDTO;
import com.FinanceBack.FinanceBack.DTO.ExpensDTO.ShortExpensInfoDTO;
import com.FinanceBack.FinanceBack.DTO.ExpensDTO.UpdateExpensDTO;
import com.FinanceBack.FinanceBack.Entites.Expens;
import com.FinanceBack.FinanceBack.Repository.ExpensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ExpensService {

    private final ExpensRepository expensRepository;

    @Autowired
    public ExpensService(ExpensRepository expensRepository) {
        this.expensRepository = expensRepository;
    }

    public List<ShortExpensInfoDTO> getExpenses() {
        List<Expens> expenses = expensRepository.findAll();

        return expenses.stream()
                .map(expens -> {
                    ShortExpensInfoDTO expensDTO = new ShortExpensInfoDTO();
                    expensDTO.setExpenses_id(expens.getId());
                    expensDTO.setDescription(expens.getDescription());
                    expensDTO.setAmount(expens.getAmount());
                    return expensDTO;
                }).toList();
    }

    public ShortExpensInfoDTO getExpensById(int id) {
        Expens expens = expensRepository.findById(id);

        ShortExpensInfoDTO expensDTO = new ShortExpensInfoDTO();
        expensDTO.setExpenses_id(expens.getId());
        expensDTO.setDescription(expens.getDescription());
        expensDTO.setAmount(expens.getAmount());

        return expensDTO;
    }

    public void createExpens(CreateExpensDTO createExpensDTO) {
        Expens expens = new Expens();
        expens.setDescription(createExpensDTO.getDescription());
        expens.setAmount(createExpensDTO.getAmount());

        expensRepository.save(expens);
    }

    public void updateExpens(int id, UpdateExpensDTO updateExpensDTO) {
        Expens expensToUpdate = expensRepository.findById(id);

        if (updateExpensDTO.getDescription() != null) {
            expensToUpdate.setDescription(updateExpensDTO.getDescription());
        }
        if (updateExpensDTO.getAmount() != null) {
            expensToUpdate.setAmount(updateExpensDTO.getAmount());
        }

        expensRepository.save(expensToUpdate);
    }

    public void deleteExpens(int id) {
        Expens expensToDelete = expensRepository.findById(id);

        expensRepository.delete(expensToDelete);
    }
}