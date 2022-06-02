package br.com.falves97.carteira.controller.form;

import br.com.falves97.carteira.model.entity.Category;
import br.com.falves97.carteira.model.entity.Expense;
import br.com.falves97.carteira.model.entity.Transaction;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TransactionForm {
    @NotNull @NotEmpty
    private String description;
    @NotNull @NotEmpty
    private String value;
    @NotNull @NotEmpty
    private String date;

    private List<String> categories;

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

    public List<String> getCategories() {
        return categories;
    }

    public static Transaction valueOf(TransactionForm transactionForm) {
        LocalDate localDate = LocalDate.parse(transactionForm.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Double value = getaDoubleValue(transactionForm.getValue());
        ArrayList<Category> categories = new ArrayList<>();

        if (transactionForm.getCategories() != null) {
            transactionForm.getCategories().forEach(c -> {
                categories.add(Category.of(c));
            });
        }

        return new Transaction(transactionForm.getDescription(), value, localDate, categories);
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
