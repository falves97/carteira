package br.com.falves97.carteira.controller;

import br.com.falves97.carteira.controller.dto.ExpenseDto;
import br.com.falves97.carteira.controller.form.ExpenseForm;
import br.com.falves97.carteira.model.entity.Expense;
import br.com.falves97.carteira.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("despesas")
public class ExpenseController {

    @Autowired
    private ExpenseRepository repository;

    @GetMapping
    public List<ExpenseDto> list() {
        return ExpenseDto.convertAll(repository.findAll());
    }

    @GetMapping("/{id}")
    public ExpenseDto getEspense(@PathVariable Long id) {
        Optional<Expense> optionalExpense = repository.findById(id);

        return optionalExpense.map(ExpenseDto::new).orElse(null);

    }

    @PostMapping
    public ResponseEntity<ExpenseDto> insert(@RequestBody ExpenseForm expenseForm, UriComponentsBuilder uriComponentsBuilder) {
        Expense expense = ExpenseForm.valueOf(expenseForm);
        repository.save(expense);

        URI uri = uriComponentsBuilder
                .path("despesas/{id}")
                .buildAndExpand(expense.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new ExpenseDto(expense));
    }

}
