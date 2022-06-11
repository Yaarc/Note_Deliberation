package com.gsnotes.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.boudaa.tools.ExcelHandlerException;
import com.gsnotes.services.IPersonService;
import com.helpers.VerificationHelper;
import com.helpers.InvalidFormat;
import com.helpers.InvalidNoteException;

@Repository
@Controller
public class MyController {
	@Autowired
	private IPersonService personService;

	//Et autres services

	@GetMapping("/deliberation")
	public String fileVerification() throws InvalidNoteException, InvalidFormat, ExcelHandlerException {
		VerificationHelper.verify("C:/Users/Ayoub/Desktop/noteDelib.xlsx");

		//Add to database
		//With Services Implemented in previous tasks

		return "admin/adminHome";
	}
}
