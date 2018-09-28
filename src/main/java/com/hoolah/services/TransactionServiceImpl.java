package com.hoolah.services;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoolah.dao.ItransactionDao;
import com.hoolah.dto.TransactionRecordDto;
import com.hoolah.entity.TransactionRecord;
import com.hoolah.exception.TransactionException;

@Service
public class TransactionServiceImpl implements ItransactionService {

	@Autowired
	private ItransactionDao transactionDao;
	@Autowired
	private TransactionRecordDto transActionRecordDto;

	/**
	 * Saved transactions to DB
	 */
	@Override
	public synchronized String addTransActionData(TransactionRecord transRecord) throws TransactionException {

		List<TransactionRecord> recordList = transActionRecordDto.getGetTransActionRecords();

		String message = null;
		try {
			for (TransactionRecord record : recordList) {
				transactionDao.addTransRecord(record);

			}
			message = "Sucessfully saved the record in to tranaction Table";
		} catch (Exception e) {
			message = "Exception occurs inserting Data into DB";
			throw new TransactionException(e.getMessage(), message);
		}

		return message;
	}

	/**
	 * Create String to dispaly Number of transactions and Average Transaction Value
	 */
	@Override
	public String getTransactionDetails(String Merchant) {

		List<TransactionRecord> recordListWithoutReverelTransaction = ReversalTransactionRemovedList(
				transactionDao.getTransactionList(Merchant));

		int count = recordListWithoutReverelTransaction.size();

		double averageTransAction = calulateAverageTransaction(Merchant, count);

		StringBuilder builder = new StringBuilder();

		builder.append("Number of transactions = ").append(count);
		builder.append(System.getProperty("line.separator"));
		builder.append("Average Transaction Value = ").append(averageTransAction);
		return builder.toString();
	}

	/**
	 * Calculate the average transaction
	 * 
	 * @param Merchant
	 * @param count
	 * @return
	 */
	public double calulateAverageTransaction(String Merchant, int count) {
		List<TransactionRecord> recordListWithoutReverelTransaction = ReversalTransactionRemovedList(
				transactionDao.getTransactionList(Merchant));

		double transActionAmount = 0d;

		for (TransactionRecord transRecord : recordListWithoutReverelTransaction) {
			transActionAmount += transRecord.getAmount();
		}

		double averageTransAction = transActionAmount / count;
		return averageTransAction;
	}

	/**
	 * Get the list of transaction and pass to remove reversal transactions.
	 * 
	 * @param recordList
	 * @return
	 */
	private List<TransactionRecord> ReversalTransactionRemovedList(List<TransactionRecord> recordList) {

		List<TransactionRecord> copyOnWritetranactionRecordList = new CopyOnWriteArrayList<TransactionRecord>();
		copyOnWritetranactionRecordList.addAll(recordList);

		System.out.println("copyOnWritetranactionRecordList Size----->>>>" + copyOnWritetranactionRecordList.size());

		return removeReversalTransaction(copyOnWritetranactionRecordList, "REVERSAL");
	}

	/**
	 * This method will remove the reversal transactions.
	 * 
	 * @param recordList
	 * @param entityName
	 * @return
	 */
	private List<TransactionRecord> removeReversalTransaction(List<TransactionRecord> recordList, String entityName) {

		List<TransactionRecord> trecordList = new CopyOnWriteArrayList<>();
		trecordList.addAll(recordList);
		for (TransactionRecord trecord : trecordList) {
			if (trecord.getType().equalsIgnoreCase(entityName)
					|| trecord.getTranactionId().equalsIgnoreCase(entityName)) {
				entityName = trecord.getRelatedTransaction();
				trecordList.remove(trecord);
				/* Use recursion */
				removeReversalTransaction(trecordList, entityName);

			}

		}
		return trecordList;
	}

}
