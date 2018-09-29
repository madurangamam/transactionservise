package com.hoolah.commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hoolah.dto.TransactionRecordDto;
import com.hoolah.entity.TransactionRecord;
import com.hoolah.exception.TransactionException;
import com.hoolah.services.TransactionServiceImpl;

@Component
public class FileUtil {

	@Autowired
	TransactionRecordDto recordDto;
	
	@Autowired
	TransactionServiceImpl transactionServise;
	
	@Autowired
	TransactionRecord tranrecord;

	@PostConstruct
	public void init(){

		try {
			recordDto.setGetTransActionRecords(readFile());
			transactionServise.addTransActionData(tranrecord);
		} catch (TransactionException e) {
			System.out.println(e.getErrorCode());
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @return Transaction record list
	 * @throws ParseException,TransactionException
	 */
	public List<TransactionRecord> readFile() throws TransactionException, ParseException {

		String line = "";
		String cvsSplitBy = ",";
		List<TransactionRecord> recordList = new ArrayList<>();
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("transactionRecords.csv").getFile());

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			while ((line = br.readLine()) != null) {

				TransactionRecord trecord = new TransactionRecord();

				String[] transactionData = line.split(cvsSplitBy);

				trecord.setTranactionId(transactionData[0]);

				trecord.setDate(sdf.parse(transactionData[1]));

				trecord.setAmount(Double.parseDouble(transactionData[2]));
				trecord.setMerchant(transactionData[3]);
				trecord.setType(transactionData[4]);
				trecord.setRelatedTransaction(transactionData[5]);
				recordList.add(trecord);
			}

		} catch (IOException e) {
			throw new TransactionException(e.getMessage(),"IOException");
		}
		System.out.println("recordList size---->>" + recordList.size());
		return recordList;
	}

}
