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
public class UserTransfer 
{
    @Id
    @SequenceGenerator(name = "transfer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "transfer_sequence")
    Long transferId;
    Long accountNumber;
    Long beneficiaryAccount;
    Double amount;
    String refrence;
    String userId;
    String localDateTime;
}
