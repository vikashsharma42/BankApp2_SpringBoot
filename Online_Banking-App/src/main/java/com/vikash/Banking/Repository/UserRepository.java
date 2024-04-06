package com.vikash.Banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vikash.Banking.Entity.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User,Long> 
{
   User findByUserName(String username);

   User findUserByUserName(String name);

   @Modifying
   @Transactional
   @Query(nativeQuery = true, value="update user set balance=:amount where account=:accountNo")
   void updateWithdrawalBalance(@Param("amount")Double userBalance, @Param("accountNo") Long accountNumber);

   User findUserByAccount(Long transferedAccount);

   @Modifying
   @Transactional
   @Query(nativeQuery = true, value="update user set balance=:amount where account=:accountNo")
   void updateDepositedBalance(@Param("amount") Double userBalance, @Param("accountNo") Long accountNumber);
   
   @Modifying
   @Transactional
   @Query(nativeQuery = true, value="update user set balance=:amount where account=:accountNo")
   void updateTransferBalance(@Param("amount") Double userBalance, @Param("accountNo") Long account);


}
