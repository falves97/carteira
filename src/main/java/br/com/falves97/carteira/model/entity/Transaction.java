package br.com.falves97.carteira.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "transactions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 1, discriminatorType = DiscriminatorType.STRING)
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String description;

    @Column(name = "value_transaction")
    protected Double value;

    protected LocalDate date;

    public Transaction() {
    }

    public Transaction(String description, Double value, LocalDate date) {
        this.description = description;
        this.value = value;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double valueTransaction) {
        this.value = valueTransaction;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate dateTransaction) {
        this.date = dateTransaction;
    }
}
