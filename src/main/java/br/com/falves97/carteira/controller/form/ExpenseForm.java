package br.com.falves97.carteira.controller.form;

import br.com.falves97.carteira.model.entity.Expense;
import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExpenseForm {
    @NotNull @NotEmpty
    private String description;
    @NotNull @NotEmpty
    private Double value;
    @NotNull @NotEmpty
    private String date;

    public String getDescription() {
        return description;
    }

    public Double getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

    public static Expense valueOf(ExpenseForm expenseForm) {
        LocalDate localDate = LocalDate.parse(expenseForm.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return new Expense(expenseForm.getDescription(), expenseForm.getValue(), localDate);
    }
}
