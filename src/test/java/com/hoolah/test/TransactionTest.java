package com.hoolah.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hoolah.dao.TransactionDaoImpl;
import com.hoolah.entity.TransactionRecord;
import com.hoolah.services.TransactionServiceImpl;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTest {
	
	@InjectMocks
	TransactionServiceImpl transactionServiceMock;
	
	
	@Mock
	TransactionDaoImpl transactionDao;
	
	List<TransactionRecord> transactionRecordList = new ArrayList<>();
	@Before
    public void setup(){
		
		TransactionRecord trRecord = new TransactionRecord();

		trRecord.setMerchant("Kwik-E-Mart");
		trRecord.setAmount(60.00);
		trRecord.setType("PAYMENT");
		trRecord.setTranactionId("WLMFRDDD");
		trRecord.setRelatedTransaction("NON");
		
		transactionRecordList.add(trRecord);
    }

	
	@Test
	public void testFindTheGreatestFromAllData() {
		
		when(transactionDao.getTransactionList("Kwik-E-Mart")).thenReturn(transactionRecordList);
		assertEquals(60.00, transactionServiceMock.calulateAverageTransaction("Kwik-E-Mart",1),0.00);
		
	}
}

