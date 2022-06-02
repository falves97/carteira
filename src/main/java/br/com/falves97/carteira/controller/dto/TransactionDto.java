package br.com.falves97.carteira.controller.dto;

import br.com.falves97.carteira.model.entity.Expense;
import br.com.falves97.carteira.model.entity.Transaction;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class TransactionDto {
    private Long id;
    private String description;
    private String value;
    private String date;

    public TransactionDto(Transaction transaction) {
        this.id = transaction.getId();
        this.description = transaction.getDescription();
        this.date = transaction.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        setValue(transaction.getValue());
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

    public static List<TransactionDto> convertAll(List<Transaction> transactions) {

        return transactions
                .stream()
                .map(TransactionDto::new)
                .collect(Collectors.toList());
    }
}
