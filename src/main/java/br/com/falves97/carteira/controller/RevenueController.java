package br.com.falves97.carteira.controller;

import br.com.falves97.carteira.controller.dto.TransactionDto;
import br.com.falves97.carteira.controller.form.TransactionForm;
import br.com.falves97.carteira.controller.form.TransactionUpdateForm;
import br.com.falves97.carteira.model.entity.Revenue;
import br.com.falves97.carteira.model.entity.Transaction;
import br.com.falves97.carteira.repository.RevenueRepository;
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
@RequestMapping("receitas")
public class RevenueController {

    @Autowired
    private RevenueRepository repository;

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
        Optional<Revenue> optionalRevenue = repository.findById(id);

        if (optionalRevenue.isPresent()) {
            Transaction transaction = optionalRevenue.get();
            return ResponseEntity.ok(new TransactionDto(transaction));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TransactionDto> insert(@RequestBody @Valid TransactionForm transactionForm, UriComponentsBuilder uriComponentsBuilder) {

        try {
            Transaction transaction = TransactionForm.valueOf(transactionForm);
            Revenue revenue = new Revenue(transaction);

            repository.save(revenue);

            return getUriExpense(uriComponentsBuilder, revenue);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TransactionDto> update(@RequestBody TransactionUpdateForm transactionUpdateForm, @PathVariable Long id) {
        Optional<Revenue> revenue = repository.findById(id);

        if (revenue.isPresent()) {
            TransactionUpdateForm.update(transactionUpdateForm, revenue.get());

            return ResponseEntity.ok(new TransactionDto(revenue.get()));
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<TransactionDto> delete(@PathVariable Long id) {
        Optional<Revenue> revenue = repository.findById(id);

        if (revenue.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }

    private ResponseEntity<TransactionDto> getUriExpense(UriComponentsBuilder uriComponentsBuilder, Revenue expense) {
        URI uri = uriComponentsBuilder
                .path("despesas/{id}")
                .buildAndExpand(expense.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new TransactionDto(expense));
    }

}
