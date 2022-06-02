package br.com.falves97.carteira.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(value = "R")
public class Revenue extends Transaction {
    public Revenue() {
        super();
    }

    public Revenue(String description, Double value, LocalDate date) {
        super(description, value, date);
    }

    public Revenue(Transaction transaction) {
        super(transaction);
    }
}
