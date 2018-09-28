package com.hoolah.services;
import com.hoolah.entity.TransactionRecord;
import com.hoolah.exception.TransactionException;

public interface ItransactionService {
	
	String addTransActionData(TransactionRecord transRecord)throws TransactionException;
	String getTransactionDetails(String Merchant);

}
