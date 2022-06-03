package br.com.falves97.carteira.controller;

import br.com.falves97.carteira.controller.dto.TransactionDto;
import br.com.falves97.carteira.controller.form.TransactionForm;
import br.com.falves97.carteira.controller.form.TransactionUpdateForm;
import br.com.falves97.carteira.model.entity.Expense;
import br.com.falves97.carteira.model.entity.Transaction;
import br.com.falves97.carteira.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("despesas")
public class ExpenseController {

    @Autowired
    private ExpenseRepository repository;

    @GetMapping
    public List<TransactionDto> list(@RequestParam(required = false) String descricao) {
        List<Transaction> transactions;

        if (descricao != null && !descricao.isEmpty()){
            transactions = new ArrayList<>(repository.findByDescriptionContainsIgnoreCase(descricao));
        }
        else {
            transactions = new ArrayList<>(repository.findAll());
        }

        return TransactionDto.convertAll(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getEspense(@PathVariable Long id) {
        Optional<Expense> optionalExpense = repository.findById(id);

        if (optionalExpense.isPresent()) {
            Transaction transaction = optionalExpense.get();
            return ResponseEntity.ok(new TransactionDto(transaction));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TransactionDto> insert(@RequestBody @Valid TransactionForm transactionForm, UriComponentsBuilder uriComponentsBuilder) {
        try {
            Transaction transaction = TransactionForm.valueOf(transactionForm);
            Expense expense = new Expense(transaction);

            repository.save(expense);

            return getUriExpense(uriComponentsBuilder, expense);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TransactionDto> update(@RequestBody TransactionUpdateForm transactionUpdateForm, @PathVariable Long id) {
        Optional<Expense> expense = repository.findById(id);

        if (expense.isPresent()) {
            TransactionUpdateForm.update(transactionUpdateForm, expense.get());

            return ResponseEntity.ok(new TransactionDto(expense.get()));
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<TransactionDto> delete(@PathVariable Long id) {
        Optional<Expense> expense = repository.findById(id);

        if (expense.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }

    private ResponseEntity<TransactionDto> getUriExpense(UriComponentsBuilder uriComponentsBuilder, Expense expense) {
        URI uri = uriComponentsBuilder
                .path("despesas/{id}")
                .buildAndExpand(expense.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new TransactionDto(expense));
    }

}
