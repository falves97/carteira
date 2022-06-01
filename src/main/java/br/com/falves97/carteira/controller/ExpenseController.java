package br.com.falves97.carteira.controller;

import br.com.falves97.carteira.controller.dto.ExpenseDto;
import br.com.falves97.carteira.controller.form.ExpenseForm;
import br.com.falves97.carteira.controller.form.ExpenseUpdateForm;
import br.com.falves97.carteira.model.entity.Expense;
import br.com.falves97.carteira.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
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
    public ResponseEntity<ExpenseDto> insert(@RequestBody @Valid ExpenseForm expenseForm, UriComponentsBuilder uriComponentsBuilder) {
        Expense expense = ExpenseForm.valueOf(expenseForm);
        repository.save(expense);

        return getUriExpense(uriComponentsBuilder, expense);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ExpenseDto> update(@RequestBody ExpenseUpdateForm expenseUpdateForm, UriComponentsBuilder uriComponentsBuilder, @PathVariable Long id) {
        Optional<Expense> expense = repository.findById(id);

        if (expense.isPresent()) {
            ExpenseUpdateForm.update(expenseUpdateForm, expense.get());

            return getUriExpense(uriComponentsBuilder, expense.get());
        }

        return ResponseEntity.badRequest().body(null);
    }

    private ResponseEntity<ExpenseDto> getUriExpense(UriComponentsBuilder uriComponentsBuilder, Expense expense) {
        URI uri = uriComponentsBuilder
                .path("despesas/{id}")
                .buildAndExpand(expense.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new ExpenseDto(expense));
    }

}
