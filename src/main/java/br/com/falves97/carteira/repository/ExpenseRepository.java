package br.com.falves97.carteira.repository;

import br.com.falves97.carteira.model.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
