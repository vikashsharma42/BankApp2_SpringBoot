package com.vikash.Banking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikash.Banking.Entity.UserTransfer;

public interface TransferRepository extends JpaRepository<UserTransfer,Long> 
{
	List<UserTransfer> getTransferListByUserId(String name);
}
