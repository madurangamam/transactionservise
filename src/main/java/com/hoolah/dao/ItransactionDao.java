package com.hoolah.dao;
import java.util.List;

import com.hoolah.entity.TransactionRecord;

public interface ItransactionDao {
	
	void addTransRecord(TransactionRecord transRecord);
	List<TransactionRecord> getTransactionList(String Merchant);	

}