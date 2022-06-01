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
    private String value;
    @NotNull @NotEmpty
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

    public static Expense valueOf(ExpenseForm expenseForm) {
        LocalDate localDate = LocalDate.parse(expenseForm.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Double value = Double.valueOf(expenseForm.getValue());

        return new Expense(expenseForm.getDescription(), value, localDate);
    }
}
