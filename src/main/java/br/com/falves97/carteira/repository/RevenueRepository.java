package br.com.falves97.carteira.repository;

import br.com.falves97.carteira.model.entity.Expense;
import br.com.falves97.carteira.model.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {

    public List<Revenue> findByDescriptionContainsIgnoreCase(String description);

    public List<Revenue> findByDateBetween(LocalDate dateStart, LocalDate dateEnd);
}
