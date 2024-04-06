package com.vikash.Banking.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class User 
{
	@Id
	@SequenceGenerator(name = "user_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "user_sequence")
	Long SIno;
    private Long account;
	private String password;
	private String userName;
	private String firstName;
	private String lastName;
	private String city;
	private String age;
	private String phone;
	private String email;
	private double balance;
}
