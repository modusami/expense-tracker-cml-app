package com.modusami.expense_tracker.screen;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.modusami.expense_tracker.expense.Expense;
import com.modusami.expense_tracker.expense.ExpenseCategory;
import com.modusami.expense_tracker.expense.ExpenseDAO;
import com.modusami.expense_tracker.expense.ExpenseNotFound;
import com.modusami.expense_tracker.expense.ExpensePaymentMethod;


/**
 * Runs The Program
 * @author Michael-Andre Odusami
 * @version 2024-06
 */
@Component
public class ProgramRunner implements CommandLineRunner {
	
	private ExpenseDAO expenseDAO;
	
	/**
     * Constructs a new ProgramRunner with the specified ExpenseDAO and ProgramRunnerHelper.
     *
     * @param expenseDAO         the ExpenseDAO instance used for data access
     */
	public ProgramRunner(ExpenseDAO expenseDAO) {
		this.expenseDAO = expenseDAO;
		
	}

	 /**
     * Runs the expense tracker program.
     * Displays the menu, gets user choice, and executes the corresponding action.
     *
     * @param args the command line arguments
     * @throws Exception if an exception occurs during program execution
     */
	@Override
	public void run(String... args) throws Exception {
		ProgramConsoleOutput.generateMenuOptions();
		ProgramConsoleOutput.displayMenu();
		while (true) {
			int userChoice = ProgramConsoleInput.getIntegerInput("Your Choice -> ");
			if (userChoice == 0) {
				break;
			}
			executeChoice(userChoice);
		}
		ProgramConsoleInput.closeScanner();
	}
	
	/**
     * Executes the user's choice based on the provided choice number.
     *
     * @param userChoice the user's choice number
     */
	private void executeChoice(int userChoice) {
		switch (userChoice) {
		case 1:
			createExpense();
			break;
		
		case 2:
			Integer idInteger = ProgramConsoleInput.getIntegerInput("Enter id of expense: ");
			findExpenseById(idInteger);
			break;
		
		case 3:
			findAll();
			break;
		case 4:
			ExpenseCategory category = ProgramConsoleInput.getCategoryInput();
			findByCategory(category);
			break;
		case 5:
			ExpensePaymentMethod paymentMethod = ProgramConsoleInput.getPaymentMethodInput();
			findByPaymentMethod(paymentMethod);
			break;
		case 6:
			// ask user for the id
			Integer idInteger2 = ProgramConsoleInput.getIntegerInput("Enter id of expense to update: ");
			updateExpense(idInteger2);
			break;
		case 7:
			Integer idInteger3 = ProgramConsoleInput.getIntegerInput("Enter id of expense to delete: ");
			deleteExpense(idInteger3);
			break;
		default:
			ProgramConsoleOutput.print("Invalid choice");
		}
	}
	
	/**
	 * Deletes an expense from the integer id
	 * @param id
	 */
	private void deleteExpense(Integer id) {
		// find expense
		Expense expense = expenseDAO.findById(id);
		if(expense == null) {
			throw new ExpenseNotFound("Expense with id of " + id);
		}
		else {
			// print expense
			ProgramConsoleOutput.print("Expense Found. Printing The Expense");
			ProgramConsoleOutput.printExpenseHeader();
			ProgramConsoleOutput.printExpenseDetails(expense);
			// print confirmation
			String inputString = ProgramConsoleInput.getStringInput("Confirm Deletion y/n: ");
			// delete
			if (inputString.matches("yes|y|")) {
				ProgramConsoleOutput.print("Deleting Expense...");
				expenseDAO.deleteByid(id);
			}
			
		}	
		
		
	}

	/**
	 * Updates an expense in the database
	 * @param id of expense to get and update
	 */
	private void updateExpense(Integer id) {
		// get and display the expense
		Expense expense = expenseDAO.findById(id);
		if(expense == null) {
			throw new ExpenseNotFound("Expense with id of " + id);
		}
		else {
			ProgramConsoleOutput.print("Expense Found. Printing The Expense");
			ProgramConsoleOutput.printExpenseHeader();
			ProgramConsoleOutput.printExpenseDetails(expense);
			ProgramConsoleOutput.print("Enter new information about the expense: ");
			// re-prompt user for information
			String titleString = ProgramConsoleInput.getStringInput("Title Of Expense: ");
			Double amountDouble = ProgramConsoleInput.getDoubleInput("Amount Expended: ");
			ExpenseCategory category = ProgramConsoleInput.getCategoryInput();
			ExpensePaymentMethod paymentMethod = ProgramConsoleInput.getPaymentMethodInput();
			// update the expense
			expense.setTitle(titleString);
			expense.setAmount(amountDouble);
			expense.setCategory(category);
			expense.setPaymentMethod(paymentMethod);
			expenseDAO.update(expense);
		}		
					
		
	}

	/**
     * Finds expenses by payment method.
     *
     * @param paymentMethod the payment method to search for
     * @throws ExpenseNotFound if no expenses are found for the given payment method
     */
	private void findByPaymentMethod(ExpensePaymentMethod paymentMethod){
		List<Expense> expenses = expenseDAO.findByPaymentMethod(paymentMethod);
		if (expenses == null) {
			throw new ExpenseNotFound("No Expenses Found");
		}
		else {
			ProgramConsoleOutput.printExpenses(expenses);
		}
	}
	
	 /**
     * Finds expenses by category.
     *
     * @param category the category to search for
     * @throws ExpenseNotFound if no expenses are found for the given category
     */
	private void findByCategory(ExpenseCategory category){
		List<Expense> expenses = expenseDAO.findByCategory(category);
		if (expenses == null) {
			throw new ExpenseNotFound("No Expenses Found");
		}
		else {
			ProgramConsoleOutput.printExpenses(expenses);
		}
	}
	
	/**
     * Finds all expenses.
     *
     * @throws ExpenseNotFound if no expenses are found
     */
	private void findAll() {
		List<Expense> expenses = expenseDAO.findAll();
		if (expenses == null) {
			throw new ExpenseNotFound("No Expenses Found");
		}
		// print expenses
		else {
			ProgramConsoleOutput.printExpenses(expenses);
		}
		
	}

	/**
     * Finds an expense by its ID.
     *
     * @param id the ID of the expense to find
     * @throws ExpenseNotFound if no expense is found with the given ID
     */
	private void findExpenseById(Integer id) {
		Expense expense = expenseDAO.findById(id);
		if(expense == null) {
			throw new ExpenseNotFound("Expense with id of " + id);
		}
		else {
			ProgramConsoleOutput.print("Expense Found. Printing The Expense");
			ProgramConsoleOutput.printExpenseHeader();
			ProgramConsoleOutput.printExpenseDetails(expense);
		}
	}

	/**
     * Creates a new expense based on user input.
     * Prompts the user for expense details, creates an Expense object, and saves it using the ExpenseDAO.
     */
	private void createExpense() {
		// get info
		String titleString = ProgramConsoleInput.getStringInput("Title Of Expense: ");
		Double amountDouble = ProgramConsoleInput.getDoubleInput("Amount Expended: ");
		ExpenseCategory category = ProgramConsoleInput.getCategoryInput();
		ExpensePaymentMethod paymentMethod = ProgramConsoleInput.getPaymentMethodInput();
		
		// create expense
		ProgramConsoleOutput.print("\nCreating Expense...");
		Expense expense = new Expense(titleString, category, paymentMethod, amountDouble);
		
		// save expense
		ProgramConsoleOutput.print("Saving Expense...");
		expenseDAO.save(expense);
		
		
		// display id of expense
		ProgramConsoleOutput.print("Expense Saved...");
		
	}

}
