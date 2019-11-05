package Prompts;

import java.util.Scanner;

import Daos.UserDao;
import Daos.WizardDao;

public class AdminMainPrompt implements Prompt {
	private Scanner action = new Scanner(System.in);
	private UserDao userDao = UserDao.currentUserImplementation;
	private WizardDao wizardDao = WizardDao.currentAccountImplementation;
	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
			
			System.out.println("What would you like to do next?");
			//banking options
			//Deposit money and withdraw money option may be combined in the
			//future using gui same with adding/removing bank account
			System.out.println("1. Remove a user account?");
			System.out.println("2. View all users?");
			System.out.println("3. View all transaction history?");
			System.out.println("4. Logout?");
			System.out.println("5. View disabled wizard accounts?");
			
			
			
			String actionEntered = action.nextLine();
			switch (actionEntered) {
			case"1":
				return new RemoveAccountPrompt();
			case"2":
				System.out.println(userDao.findAll());
			case "3":
				return new ViewAllTransactionHistoryPrompt();
			case "4":
				return new LogInPrompt();
			case "5":
				System.out.println(wizardDao.findAllDisabledCharacters());
			default:
				break;
			}
	
			return this;
		}
	}
