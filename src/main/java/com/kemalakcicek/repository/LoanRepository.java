package com.kemalakcicek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kemalakcicek.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

}
