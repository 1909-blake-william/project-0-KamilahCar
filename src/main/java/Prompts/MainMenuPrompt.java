package Prompts;

import java.util.Scanner;

public class MainMenuPrompt implements Prompt{
	
	Scanner input = new Scanner(System.in);
	public Prompt run() {
		// TODO Auto-generated method stub
	//Saving username and password	
	System.out.println("Please create your user name");
	String firstTimeUserName = input.nextLine();
	System.out.println("Please create your password.");
	String firstTimePassword = input.nextLine();
	
	
	/*//Getting username and password
	System.out.println("Please enter your user name");
	String username = input.nextLine();
	System.out.println("Please enter your password");
	String password = input.nextLine();*/
	
	//close username and password scanner
	input.close();
	
	System.out.println("What would you like to do next?");
	//banking options
	//Deposit money and withdraw money option may be combined in the
	//future using gui same with adding/removing bank account
	System.out.println("1. Deposit money");
	System.out.println("2. Withdraw money");
	System.out.println("3. Add a banking account");
	System.out.println("4. Remove a bank account");
	System.out.println("5. View transaction history");
	
	
	Scanner action = new Scanner(System.in);
	String actionEntered = action.nextLine();
	switch (actionEntered) {
	case "1":
		new DepositMoneyPrompt();
		break;
	case "2":
		new WithdrawMoneyPrompt();
		break;
	case "3":
		//System.out.println("What is the name of the bank account?");
		new AddBankAccountPrompt();
		break;
	case"4":
		new RemoveBankAccountPrompt();
		//System.out.println("Checkings or savings?");
		break;
	case "5":
		new ViewTransactionHistoryPrompt();
		break;
	default:
		break;
	}
	action.close();
	return this;
	}
	

}
