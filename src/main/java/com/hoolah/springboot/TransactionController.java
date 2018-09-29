package com.hoolah.springboot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hoolah.commons.FileUtil;
import com.hoolah.services.TransactionServiceImpl;

@RestController
public class TransactionController {

	@Autowired
	FileUtil common;
	@Autowired
	TransactionServiceImpl transactionServise;

	
	@RequestMapping("/getrecords/{merchant}")
	public String getAverageTransaction(@PathVariable("merchant") String merchant) {

		return transactionServise.getTransactionDetails(merchant);
	}

}
