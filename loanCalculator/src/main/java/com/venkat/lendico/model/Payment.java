package com.venkat.lendico.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Payment {

	private double borrowerPaymentAmount;
	private LocalDateTime date;
	private double initialOutstandingPrincipal;
	private double interest;
	private double principal;
	private double remainingOutstandingPrincipal;

}