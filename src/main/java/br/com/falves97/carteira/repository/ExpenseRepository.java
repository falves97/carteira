package br.com.falves97.carteira.repository;

import br.com.falves97.carteira.model.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    public List<Expense> findByDescriptionContainsIgnoreCase(String description);

    public List<Expense> findByDateBetween(LocalDate dateStart, LocalDate dateEnd);

}
