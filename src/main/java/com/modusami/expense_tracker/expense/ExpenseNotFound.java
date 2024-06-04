package com.modusami.expense_tracker.expense;

public class ExpenseNotFound extends RuntimeException {
	
	public ExpenseNotFound() {
		super("Expense not found");
	}
	
	public ExpenseNotFound(String str) {
		super(str);
	}
}
