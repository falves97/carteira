package br.com.falves97.carteira.controller.dto;

import br.com.falves97.carteira.model.entity.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseDto {
    private Long id;
    private String description;
    private Double value;
    private LocalDate date;

    public ExpenseDto(Expense expense) {
        this.id = expense.getId();
        this.description = expense.getDescription();
        this.value = expense.getValue();
        this.date = expense.getDate();
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public static List<ExpenseDto> convertAll(List<Expense> expenses) {
        return expenses
                .stream()
                .map(ExpenseDto::new)
                .collect(Collectors.toList());
    }
}
