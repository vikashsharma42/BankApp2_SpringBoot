package com.vikash.Banking.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserTransaction
{
	@Id
	@SequenceGenerator(name = "transaction_sequence", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "transaction_sequence")
	Long transactionId;
	Long accountNumber;
	String localDateTime;
	Double amount;
	String userId;
	String reference;
}
