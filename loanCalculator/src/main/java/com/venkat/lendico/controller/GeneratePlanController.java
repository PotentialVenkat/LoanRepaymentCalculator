package com.venkat.lendico.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.venkat.lendico.model.Loan;
import com.venkat.lendico.model.Payment;
import com.venkat.lendico.service.LoanService;

@RestController
@RequestMapping("/generate-plan")
public class GeneratePlanController {

	private final LoanService service;

	public GeneratePlanController(LoanService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<List<Payment>> generate(@Valid @RequestBody Loan loan) {

		List<Payment> plan = service.generatePlan(loan);

		return new ResponseEntity<List<Payment>>(plan, HttpStatus.OK);
	}
}