package com.hoolah.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "tranaction")
@Component
public class TransactionRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tranaction_id")
	private String tranactionId;

	@Column(name = "tranaction_date")
	private Date date;

	@Column(name = "amount")
	private double amount;

	@Column(name = "merchant")
	private String merchant;

	@Column(name = "trans_type")
	private String type;

	@Column(name = "related_transaction")
	private String relatedTransaction;

	public String getTranactionId() {
		return tranactionId;
	}

	public void setTranactionId(String tranactionId) {
		this.tranactionId = tranactionId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRelatedTransaction() {
		return relatedTransaction;
	}

	public void setRelatedTransaction(String relatedTransaction) {
		this.relatedTransaction = relatedTransaction;
	}

}
