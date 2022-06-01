package br.com.falves97.carteira.controller.form;

import br.com.falves97.carteira.model.entity.Expense;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ExpenseUpdateForm {
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

    public static void update(ExpenseUpdateForm expenseUpdateForm, Expense expense) {
        if (expenseUpdateForm.getDate() != null) {
            LocalDate localDate = LocalDate.parse(expenseUpdateForm.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            expense.setDate(localDate);
        }

        if (expenseUpdateForm.getDescription() != null) {
            expense.setDescription(expenseUpdateForm.getDescription());
        }

        if (expenseUpdateForm.getValue() != null) {
            expense.setValue(Double.valueOf(expenseUpdateForm.getValue()));
        }
    }
}
