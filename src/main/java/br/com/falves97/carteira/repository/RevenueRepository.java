package br.com.falves97.carteira.repository;

import br.com.falves97.carteira.model.entity.Expense;
import br.com.falves97.carteira.model.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {

    public List<Revenue> findByDescriptionContainsIgnoreCase(String description);

}
