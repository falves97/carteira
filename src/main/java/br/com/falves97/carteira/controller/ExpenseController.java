package br.com.falves97.carteira.controller;

import br.com.falves97.carteira.controller.dto.TransactionDto;
import br.com.falves97.carteira.controller.form.TransactionForm;
import br.com.falves97.carteira.controller.form.TransactionUpdateForm;
import br.com.falves97.carteira.model.entity.Expense;
import br.com.falves97.carteira.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<TransactionDto> list() {
        return TransactionDto.convertAll(repository.findAll());
    }

    @GetMapping("/{id}")
    public TransactionDto getEspense(@PathVariable Long id) {
        Optional<Expense> optionalExpense = repository.findById(id);

        return optionalExpense.map(TransactionDto::new).orElse(null);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<TransactionDto> insert(@RequestBody @Valid TransactionForm transactionForm, UriComponentsBuilder uriComponentsBuilder) {
        Expense expense = TransactionForm.valueOf(transactionForm);

        repository.save(expense);

        return getUriExpense(uriComponentsBuilder, expense);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TransactionDto> update(@RequestBody TransactionUpdateForm transactionUpdateForm, UriComponentsBuilder uriComponentsBuilder, @PathVariable Long id) {
        Optional<Expense> expense = repository.findById(id);

        if (expense.isPresent()) {
            TransactionUpdateForm.update(transactionUpdateForm, expense.get());

            return ResponseEntity.ok(new TransactionDto(expense.get()));
        }

        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<TransactionDto> delete(@PathVariable Long id) {
        Optional<Expense> expense = repository.findById(id);

        if (expense.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().body(null);
    }

    private ResponseEntity<TransactionDto> getUriExpense(UriComponentsBuilder uriComponentsBuilder, Expense expense) {
        URI uri = uriComponentsBuilder
                .path("despesas/{id}")
                .buildAndExpand(expense.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new TransactionDto(expense));
    }

}
