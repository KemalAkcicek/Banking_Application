package com.kemalakcicek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kemalakcicek.model.Transaction;

import jakarta.transaction.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query(value = "Select * from transcation WHERE sender_account_id=:id", nativeQuery = true)
	List<Transaction> findSenderAccount(@Param("id") Long id);

	@Modifying // query update veya delete işlemleri yaparken modifiyng anatasyonu kullanırız
	@Transactional
	@Query(value = "DELETE  FROM transcation WHERE transcation_date < CURDATE()- INTERVAL 7 DAY", nativeQuery = true)
	void deleteTransactionFrom10();

}
