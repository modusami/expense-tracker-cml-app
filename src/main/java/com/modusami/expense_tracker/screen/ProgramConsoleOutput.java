package com.modusami.expense_tracker.screen;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.modusami.expense_tracker.expense.Expense;

/**
 * This class is responsible for all output related console experiences
 * @author Michael-Andre Odusami
 * @version 2024-06
 */
@Component
public class ProgramConsoleOutput {
	private static final String printFormat = "%-5s %-30s %-20s %-20s %10s %-12s %-20s%n";
	public static final HashMap<Integer, String> options = new HashMap<>();

	/**
	 * Generates Menu Options 
	 */
	public static void generateMenuOptions() {
		options.put(1, "Create Expense");
		options.put(2, "Find Expense By Id");
		options.put(3, "Find All Expenses");
		options.put(4, "Find Expense By Category");
		options.put(5, "Find Expense By Payment Method");
		options.put(6, "Update Expense By Id");
		options.put(7, "Delete Expense By Id");
		options.put(0, "Quit");
	}
	
	
	/**
     * Displays the menu options.
     */
	public static void displayMenu() {
		print("Welcome To The Expense Tracker.");
		for (Integer idInteger : options.keySet()) {
			String optionString = options.get(idInteger);
			print(idInteger + ". " + optionString);
		}
		print(" -> ");
	}
	
	/**
	 * Displays payment menu
	 */
	public static void printPaymentMenu() {
		print("Enter a payment method:");
	    print("1. Cash");
	    print("2. Credit Card");
	    print("3. Debit Card");
	    print("4. Bank Transfer");
	    print("5. PayPal");
	    print("6. Venmo");
	    print("7. Apple Pay");
	    print("8. Google Pay");
	    print("9. Other");
	    print("Enter your choice (1-9): ");
	}
	
	/**
	 * Displays category menu
	 */
	public static void printCategoryMenu() {
		print("Enter an expense category:");
        print("1. Food");
        print("2. Transportation");
        print("3. Utilities");
        print("4. Entertainment");
        print("5. Shopping/Groceries");
        print("6. Other");
        print("Enter your choice (1-6): ");
	}
	
	/**
     * Prints a value to the console with a new line.
     * 
     * @param val the value to print
     */
	public static <T> void print(T val) {
		System.out.println(val);
	}
	
	/**
     * Prints a value to the console with a specified end string.
     * 
     * @param val the value to print
     * @param end the end string to append
     */
	public static <T> void print(T val, String end) {
		System.out.print(val + end);
	}
	
	/**
     * Prints a value to the console without a new line.
     * 
     * @param val the value to print
     */
	public static <T> void printn(T val) {
		System.out.print(val);
	}
	
	/**
     * Prints the expense header.
     */
	public static void printExpenseHeader() {
	    System.out.printf(printFormat, "ID", "Title", "Category", "Payment Method", "Amount", "Date", "Created At");
	    System.out.println("--------------------------------------------------------------------------------------------------------");
	}
	
	 /**
     * Prints the details of an expense.
     * 
     * @param expense the expense to print
     */
	public static void printExpenseDetails(Expense expense) {
	    System.out.printf(printFormat, expense.getId().toString(), expense.getTitle(), expense.getCategory(),expense.getPaymentMethod(), expense.getAmount(), expense.getDate(), expense.getCreatedAt());
	}
	
	 /**
     * Prints a list of expenses.
     * 
     * @param expenses the list of expenses to print
     */
	public static void printExpenses(List<Expense> expenses) {
		printExpenseHeader();
		for (Expense expense : expenses) {
			printExpenseDetails(expense);
		}
	}
	
	
	
}
