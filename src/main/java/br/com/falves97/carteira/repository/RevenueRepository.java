package br.com.falves97.carteira.repository;

import br.com.falves97.carteira.model.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {
}
