package Prompts;

import java.util.Scanner;

import Daos.WizardDao;
import Daos.UserDao;
import Daos.UserDaoDatabase;
import Daos.WizardDaoDatabase;

public class AdminMainPrompt implements Prompt {
	private Scanner action = new Scanner(System.in);
	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
			
			System.out.println("What would you like to do next?");
			//banking options
			//Deposit money and withdraw money option may be combined in the
			//future using gui same with adding/removing bank account
			System.out.println("1. Deposit House points?");
			System.out.println("2. Use House points?");
			System.out.println("3. Add a user account?");
			System.out.println("4. Remove a user account");
			System.out.println("5. View your transaction history");
			
			
			
			String actionEntered = action.nextLine();
			switch (actionEntered) {
			case "1":
				new DepositPointsPrompt();
				break;
			case "2":
				new WithdrawPointsPrompt();
				break;
			case "3":
				//System.out.println("What is the name of the bank account?");
				new AddAccountPrompt();
				break;
			case"4":
				new RemoveAccountPrompt();
				//System.out.println("Checkings or savings?");
				break;
			case "5":
				new ViewAllTransactionHistoryPrompt();
				break;
			default:
				break;
			}
			action.close();
			return this;
		}
	}
