package br.com.falves97.carteira.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(value = "E")
public class Expense extends Transaction {
    public Expense() {
        super();
    }

    public Expense(String description, Double value, LocalDate date) {
        super(description, value, date);
    }

    public Expense(Transaction transaction) {
       super(transaction);
    }
}
