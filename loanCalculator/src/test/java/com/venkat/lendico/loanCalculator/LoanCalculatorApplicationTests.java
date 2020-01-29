package com.venkat.lendico.loanCalculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoanCalculatorApplicationTests {

	public LoanCalculatorApplicationTests() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private PlanCalculatorUtil planCalculatorUtil;

	@Test
	public void contextLoads() {
	}

	@Test
	public void calculateAnnuityTest() {

		double actual = PlanCalculatorUtil.calculateAnnuity(5000, 0.05, 24);

		assertEquals(219, actual, 1);
	}
}
