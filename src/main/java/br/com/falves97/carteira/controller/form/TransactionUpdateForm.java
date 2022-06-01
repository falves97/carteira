package br.com.falves97.carteira.controller.form;

import br.com.falves97.carteira.model.entity.Expense;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionUpdateForm {
    private String description;
    private String value;
    private String date;

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

    public static void update(TransactionUpdateForm transactionUpdateForm, Expense expense) {
        if (transactionUpdateForm.getDate() != null) {
            LocalDate localDate = LocalDate.parse(transactionUpdateForm.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            expense.setDate(localDate);
        }

        if (transactionUpdateForm.getDescription() != null) {
            expense.setDescription(transactionUpdateForm.getDescription());
        }

        if (transactionUpdateForm.getValue() != null) {
            expense.setValue(TransactionForm.getaDoubleValue(transactionUpdateForm.getValue()));
        }
    }
}
