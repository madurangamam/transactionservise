package com.hoolah.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hoolah.entity.TransactionRecord;

@Transactional
@Repository
public class TransactionDaoImpl implements ItransactionDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addTransRecord(TransactionRecord transRecord) {
		entityManager.persist(transRecord);

	}

	@Override
	public List<TransactionRecord> getTransactionList(String merchant) {

		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date stDate = null;
		Date edDate = null;
		try {
			stDate = format.parse("20/08/2018 12:00:00");
			edDate = format.parse("20/08/2018 13:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "Select * FROM tranaction as ct WHERE ct.merchant=:merchant AND (ct.tranaction_date BETWEEN :stDate AND :edDate) OR (ct.trans_type=:type)";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("merchant", merchant);
		query.setParameter("stDate", stDate);
		query.setParameter("edDate", edDate);
		query.setParameter("type", "REVERSAL");

		int count = query.getResultList().size();

		System.out.println("Count---->>>" + count);

		List<Object[]> rows = query.getResultList();
		List<TransactionRecord> resut = new ArrayList<>();

		for (Object[] row : rows) {

			TransactionRecord trecord = new TransactionRecord();

			trecord.setTranactionId((String) row[0]);
			trecord.setDate((Date) row[1]);
			trecord.setAmount((Double) row[2]);
			trecord.setMerchant((String) row[3]);
			trecord.setType((String) row[4]);
			trecord.setRelatedTransaction((String) row[5]);
			resut.add(trecord);

		}

		return resut;

	}

}
