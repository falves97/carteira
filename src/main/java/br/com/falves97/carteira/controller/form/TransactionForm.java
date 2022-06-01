package br.com.falves97.carteira.controller.form;

import br.com.falves97.carteira.model.entity.Expense;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TransactionForm {
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

    public static Expense valueOf(TransactionForm transactionForm) {
        LocalDate localDate = LocalDate.parse(transactionForm.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Double value = getaDoubleValue(transactionForm.getValue());
        return new Expense(transactionForm.getDescription(), value, localDate);
    }

    public static Double getaDoubleValue(String valueStr) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        DecimalFormat format = new DecimalFormat("#,##0.0#", symbols);

        Number value = null;
        try {
            value = format.parse(valueStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return value.doubleValue();
    }
}
