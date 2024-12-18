package com.FinanceBack.FinanceBack.Controller;

import com.FinanceBack.FinanceBack.Service.ExpensService;
import com.FinanceBack.FinanceBack.DTO.ExpensDTO.CreateExpensDTO;
import com.FinanceBack.FinanceBack.DTO.ExpensDTO.ShortExpensInfoDTO;
import com.FinanceBack.FinanceBack.DTO.ExpensDTO.UpdateExpensDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpensController {

    private final ExpensService expensService;

    @Autowired
    public ExpensController(ExpensService expensService) {
        this.expensService = expensService;
    }

    @GetMapping
    public List<ShortExpensInfoDTO> getAllExpenses() {
        return expensService.getExpenses();
    }

    @GetMapping("/{id}")
    public ShortExpensInfoDTO getExpensById(@PathVariable int id) {
        return expensService.getExpensById(id);
    }

    @PostMapping
    public void createExpens(@RequestBody CreateExpensDTO createExpensDTO) {
        expensService.createExpens(createExpensDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateExpens(@PathVariable("id") int id, @Valid @RequestBody UpdateExpensDTO updateExpensDTO) {
        try {
            expensService.updateExpens(id, updateExpensDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpens(@PathVariable("id") int id) {
        try {
            expensService.deleteExpens(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}