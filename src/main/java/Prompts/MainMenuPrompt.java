package Prompts;

import java.util.Scanner;
import Util.UserRegistryUtil;

public class MainMenuPrompt implements Prompt {

	private UserRegistryUtil registerUser = UserRegistryUtil.instance;
	private Scanner action = new Scanner(System.in);
	@Override
	public Prompt run() {
		// TODO Auto-generated method stub

		System.out.println("Welcome " + registerUser.getCurrentUser().getName() + " what would you like to do next?");
		// application options
		System.out.println("1. Deposit House points?");
		System.out.println("2. Use House points?");
		System.out.println("3. Add a character?");
		System.out.println("4. Remove a character?");
		System.out.println("5. Log out?");
		System.out.println("6. View your transaction history");

		String actionEntered = action.nextLine();
		switch (actionEntered) {
		case "1":
			return new DepositPointsPrompt();
		case "2":
			return new WithdrawPointsPrompt();
		case "3":
			return new AddAccountPrompt();
		case "4":
			return new RemoveAccountPrompt();
		case "5":
			return new LogInPrompt();
		case "6":
			return new ViewTransactionHistoryPrompt();
		default:
			break;
		}
		return this;
	}
	
	
}
