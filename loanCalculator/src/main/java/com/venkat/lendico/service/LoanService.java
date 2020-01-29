package com.venkat.lendico.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.venkat.lendico.exceptions.BadRequestException;
import com.venkat.lendico.loanCalculator.PlanCalculatorUtil;
import com.venkat.lendico.model.Loan;
import com.venkat.lendico.model.Payment;

@Service
public class LoanService {

	public List<Payment> generatePlan(Loan loan) {

		validateLoanData(loan);

		List<Payment> plan = new ArrayList<Payment>();
		int loanAmount = loan.getLoanAmount();
		double nominalRate = loan.getNominalRate() / 100;
		int duration = loan.getDuration();
		LocalDateTime startdDate = loan.getStartDate();

		double annuity = PlanCalculatorUtil.calculateAnnuity(loanAmount, nominalRate, duration);

		Payment payment = new Payment();
		payment.setDate(startdDate);
		payment.setInitialOutstandingPrincipal(loanAmount);

		double interest = PlanCalculatorUtil.calculateInterest(loanAmount, nominalRate * 100);
		payment.setInterest(interest);

		double principal = (annuity - interest);
		if (interest > payment.getInitialOutstandingPrincipal()) {
			principal = payment.getInitialOutstandingPrincipal();
		}
		payment.setPrincipal(principal);

		payment.setBorrowerPaymentAmount(interest + principal);
		payment.setRemainingOutstandingPrincipal(loanAmount - principal);

		plan.add(payment);

		PlanCalculatorUtil.generatePlan(plan, payment, nominalRate, annuity);

		return plan.stream().map(p -> PlanCalculatorUtil.formatCurrency(p)).collect(Collectors.toList());

	}

	protected void validateLoanData(Loan loan) {
		List<String> errors = new ArrayList<>();

		if (loan.getDuration() < 1) {
			errors.add("Duration needs to be positive value");
		}
		if (loan.getLoanAmount() < 1) {
			errors.add("Loan Amount needs to be positive value");
		}
		if (loan.getNominalRate() < 1 || loan.getNominalRate() > 99) {
			errors.add("Nomimal Rate needs to be a value between 0 and 100%");
		}
		if (loan.getStartDate() == null) {
			errors.add("Start date is mandatory");
		}

		throwInvalidLoanData(errors);
	}

	protected void throwInvalidLoanData(List<String> invalidLoan) {
		if (invalidLoan != null && !invalidLoan.isEmpty()) {
			throw new BadRequestException(invalidLoan, "INVALID_DATA");
		}
	}
}