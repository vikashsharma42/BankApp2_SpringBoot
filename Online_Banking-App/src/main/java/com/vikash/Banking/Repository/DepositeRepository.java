package com.vikash.Banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikash.Banking.Entity.UserTransaction;


public interface DepositeRepository extends JpaRepository<UserTransaction,Long>
{

}
