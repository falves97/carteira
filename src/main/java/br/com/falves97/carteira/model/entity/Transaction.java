package br.com.falves97.carteira.model.entity;

import br.com.falves97.carteira.model.converter.TransactionConverter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "transactions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 1, discriminatorType = DiscriminatorType.STRING)
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(unique = true)
    protected String description;

    @Column(name = "value_transaction")
    protected Double value;

    protected LocalDate date;

    public Transaction() {
        setCategories(null);
    }

    @Convert(converter = TransactionConverter.class)
    protected List<Category> categories;

    public Transaction(String description, Double value, LocalDate date) {
        this.description = description;
        this.value = value;
        this.date = date;
        setCategories(null);
    }

    public Transaction(Transaction transaction) {
        id = transaction.getId();
        this.description = transaction.getDescription();
        this.value = transaction.getValue();
        this.date = transaction.getDate();
        setCategories(transaction.getCategories());
    }

    public Transaction(String description, Double value, LocalDate date, ArrayList<Category> categories) {
        this.description = description;
        this.value = value;
        this.date = date;
        setCategories(categories);
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        if (categories != null) {
            if (!categories.isEmpty()) {
                this.categories = categories;
            } else {
                categories = new ArrayList<>();
                categories.add(Category.OTHERS);
                this.categories = categories;
            }
        }
    }
}
