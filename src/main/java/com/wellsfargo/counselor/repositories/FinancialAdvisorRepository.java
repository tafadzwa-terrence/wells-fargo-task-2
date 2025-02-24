package com.wellsfargo.counselor.repositories;

import com.wellsfargo.counselor.entity.FinancialAdvisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialAdvisorRepository extends JpaRepository<FinancialAdvisor, Long> {
}
