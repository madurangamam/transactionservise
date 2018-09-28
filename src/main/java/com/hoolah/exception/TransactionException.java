package com.hoolah.exception;

public class TransactionException extends Exception{
	
	private static final long serialVersionUID = 5128131391100915067L;
	private String errorCode="Unknown_Exception";

	public TransactionException(String message, String errorCode){
		super(message);
		this.errorCode=errorCode;
	}

	public String getErrorCode(){
		return this.errorCode;
	}

}
