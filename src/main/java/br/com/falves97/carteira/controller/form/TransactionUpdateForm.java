package br.com.falves97.carteira.controller.form;

import br.com.falves97.carteira.controller.dto.TransactionDto;
import br.com.falves97.carteira.model.entity.Category;
import br.com.falves97.carteira.model.entity.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionUpdateForm {
    private String description;
    private String value;
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

    public static void update(TransactionUpdateForm transactionUpdateForm, Transaction transaction) {
        if (transactionUpdateForm.getDate() != null) {
            LocalDate localDate = LocalDate.parse(transactionUpdateForm.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            transaction.setDate(localDate);
        }

        if (transactionUpdateForm.getDescription() != null) {
            transaction.setDescription(transactionUpdateForm.getDescription());
        }

        if (transactionUpdateForm.getValue() != null) {
            transaction.setValue(TransactionForm.getaDoubleValue(transactionUpdateForm.getValue()));
        }

        if (transactionUpdateForm.getCategories() != null) {
            List<Category> list = transactionUpdateForm.getCategories()
                    .stream()
                    .map(Category::of).toList();

            transaction.setCategories(list);
        }
    }
}
