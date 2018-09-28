package com.hoolah.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hoolah.entity.TransactionRecord;

@Component
public class TransactionRecordDto {

	@Autowired
	TransactionRecord transrecord;

	private List<TransactionRecord> getTransActionRecords;

	public List<TransactionRecord> getGetTransActionRecords() {
		return getTransActionRecords;
	}

	public void setGetTransActionRecords(List<TransactionRecord> getTransActionRecords) {
		this.getTransActionRecords = getTransActionRecords;
	}

}
