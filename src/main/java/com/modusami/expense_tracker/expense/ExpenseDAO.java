package com.modusami.expense_tracker.expense;

import java.util.List;

/**
 * Represents an interface for an Expense DAO
 * @author Michael-Andre Odusami
 * @version 2024-05
 */
public interface ExpenseDAO {
	
	/**
	 * Saves an expense to the database
	 * @param expense to save
	 */
	public void save(Expense expense);
	
	/**
	 * Finds an expense by id
	 * @param id of expense to find
	 * @return the expense found or null if id is not found
	 */
	public Expense findById(Integer id);
	
	/**
	 * Finds all expenses from database
	 * @return list of all expenses
	 */
	public List<Expense> findAll();
	
	/**
	 * Finds and returns expenses with this category
	 * @param thExpenseCategory to query by
	 * @return list of expenses with that category
	 */
	public List<Expense> findByCategory(ExpenseCategory theExpenseCategory);
	
	/**
	 * Finds and returns expenses with this payment method
	 * @param thExpenseCategory to query by
	 * @return list of expenses with that payment method
	 */
	public List<Expense> findByPaymentMethod(ExpensePaymentMethod thePaymentMethod);
	
	/**
	 * Edits an expense in the database
	 * @param expense to update
	 */
	public void update(Expense expense);
	
	
	/**
	 * Deletes an expense by id
	 * @param id of expense
	 */
	public void deleteByid(Integer id);
	
	
}
