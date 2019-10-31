package Prompts;

import java.util.Scanner;

public class AdminMainPrompt implements Prompt {
	private Scanner action = new Scanner(System.in);
	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
			
			System.out.println("What would you like to do next?");
			//banking options
			//Deposit money and withdraw money option may be combined in the
			//future using gui same with adding/removing bank account
			System.out.println("1. Remove a user account?");
			System.out.println("2. View all transaction history?");
			
			
			
			String actionEntered = action.nextLine();
			switch (actionEntered) {
			case"1":
				new RemoveAccountPrompt();
				//System.out.println("Checkings or savings?");
				break;
			case "2":
				new ViewAllTransactionHistoryPrompt();
				break;
			default:
				break;
			}
			action.close();
			return this;
		}
	}
