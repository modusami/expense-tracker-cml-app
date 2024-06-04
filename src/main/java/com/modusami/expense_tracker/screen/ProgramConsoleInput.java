package com.modusami.expense_tracker.screen;


import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.modusami.expense_tracker.expense.ExpenseCategory;
import com.modusami.expense_tracker.expense.ExpensePaymentMethod;

/**
 * This class is responsible for all input related console experiences
 * @author Michael-Andre Odusami
 * @version 2024-06
 */
@Component
public class ProgramConsoleInput {

	
	private final static Scanner inScanner = new Scanner(System.in);
	
	

	/**
     * Gets a string input from the user with a prompt.
     * 
     * @param promptString the prompt to display to the user
     * @return the user's input as a string
     */
	public static String getStringInput(String promptString) {
		ProgramConsoleOutput.printn(promptString);
		String responseString = inScanner.nextLine().trim();
		return responseString;
	}
	
	 /**
     * Gets a double input from the user with a prompt.
     * 
     * @param promptString the prompt to display to the user
     * @return the user's input as a double
     */
	public static Double getDoubleInput(String promptString) {
		ProgramConsoleOutput.printn(promptString);
		Double response = -1.0;
		try {
			response = inScanner.nextDouble();
			inScanner.nextLine();
		}
		catch(InputMismatchException e) {
			ProgramConsoleOutput.print("Invalid Input");
			inScanner.next();
		}
		return response;
	}
	
	/**
     * Gets an integer input from the user with a prompt.
     * 
     * @param promptString the prompt to display to the user
     * @return the user's input as an integer
     */
	public static Integer getIntegerInput(String promptString) {
		ProgramConsoleOutput.printn(promptString);
		Integer response = -1;
		try {
			response = inScanner.nextInt();
			inScanner.nextLine();
		}
		catch(InputMismatchException e) {
			ProgramConsoleOutput.print("Invalid Input");
			inScanner.next();
		}
		return response;
	}
	
	/**
     * Gets the expense category input from the user.
     * 
     * @return the selected ExpenseCategory
     */
	public static ExpenseCategory getCategoryInput() {
		ProgramConsoleOutput.printCategoryMenu();
        int choice = getIntegerInput("");
        return determineExpenseCategory(choice);
        
	}
	
	 /**
     * Determines the ExpenseCategory based on the user's choice.
     * 
     * @param choice the user's choice as an integer
     * @return the corresponding ExpenseCategory
     */
	private static ExpenseCategory determineExpenseCategory(int choice) {
		ExpenseCategory category;
		switch (choice) {
	        case 1:
	            category = ExpenseCategory.FOOD;
	            break;
	        case 2:
	            category = ExpenseCategory.TRANSPORTATION;
	            break;
	        case 3:
	            category = ExpenseCategory.UTILITIES;
	            break;
	        case 4:
	            category = ExpenseCategory.ENTERTAINMENT;
	            break;
	        case 5:
	            category = ExpenseCategory.GROCERIES;
	            break;
	        case 6:
	        	category = ExpenseCategory.OTHER;
	        	break;
	        default:
	        	ProgramConsoleOutput.print("Invalid choice. Defaulting to OTHER.");
	            category = ExpenseCategory.OTHER;
	    }
		return category;
	}
	
	/**
     * Gets the expense payment method input from the user.
     * 
     * @return the selected ExpensePaymentMethod
     */
	public static ExpensePaymentMethod getPaymentMethodInput() {
	    ProgramConsoleOutput.printPaymentMenu();
	    int choice = getIntegerInput("");
	    return determineExpensePaymentMethod(choice);
	}
	
	/**
     * Determines the ExpensePaymentMethod based on the user's choice.
     * 
     * @param choice the user's choice as an integer
     * @return the corresponding ExpensePaymentMethod
     */
	private static ExpensePaymentMethod determineExpensePaymentMethod(int choice) {
	    ExpensePaymentMethod paymentMethod;
	    switch (choice) {
	        case 1:
	            paymentMethod = ExpensePaymentMethod.CASH;
	            break;
	        case 2:
	            paymentMethod = ExpensePaymentMethod.CREDIT_CARD;
	            break;
	        case 3:
	            paymentMethod = ExpensePaymentMethod.DEBIT_CARD;
	            break;
	        case 4:
	            paymentMethod = ExpensePaymentMethod.BANK_TRANSFER;
	            break;
	        case 5:
	            paymentMethod = ExpensePaymentMethod.PAYPAL;
	            break;
	        case 6:
	            paymentMethod = ExpensePaymentMethod.VENMO;
	            break;
	        case 7:
	            paymentMethod = ExpensePaymentMethod.APPLE_PAY;
	            break;
	        case 8:
	            paymentMethod = ExpensePaymentMethod.GOOGLE_PAY;
	            break;
	        case 9:
	            paymentMethod = ExpensePaymentMethod.OTHER;
	            break;
	        default:
	            ProgramConsoleOutput.print("Invalid choice. Defaulting to OTHER.");
	            paymentMethod = ExpensePaymentMethod.OTHER;
	    }
	    return paymentMethod;
	}
	
	
	// destructors
	/**
     * Closes the input scanner.
     */
	public static void closeScanner() {
		inScanner.close();
	}

}
