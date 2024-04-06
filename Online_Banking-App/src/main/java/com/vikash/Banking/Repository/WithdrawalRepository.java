package com.vikash.Banking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikash.Banking.Entity.UserTransaction;

public interface WithdrawalRepository extends JpaRepository<UserTransaction,Long>
{
	List<UserTransaction> getWithdrawalListByUserId(String name);

}
