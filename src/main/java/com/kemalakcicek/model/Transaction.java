package com.kemalakcicek.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transcation")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int amount;

	private String description;

	@CreationTimestamp
	private LocalDate transcationDate;

	@ManyToOne
	@JoinColumn(name = "sender_account_id")
	private Account senderAccount;

	@ManyToOne
	@JoinColumn(name = "receive_account_id")
	private Account receiveAccount;

}
