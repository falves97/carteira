package br.com.falves97.carteira.controller.dto;

import br.com.falves97.carteira.model.entity.Expense;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class TransactionDto {
    private Long id;
    private String description;
    private String value;
    private String date;

    public TransactionDto(Expense expense) {
        this.id = expense.getId();
        this.description = expense.getDescription();
        this.date = expense.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        setValue(expense.getValue());
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(Double value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        DecimalFormat format = new DecimalFormat("#,##0.00", symbols);

        this.value = format.format(value);
    }

    public String getDate() {
        return date;
    }

    public static List<TransactionDto> convertAll(List<Expense> expenses) {
        return expenses
                .stream()
                .map(TransactionDto::new)
                .collect(Collectors.toList());
    }
}
