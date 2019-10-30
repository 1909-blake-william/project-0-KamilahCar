package Prompts;

import java.util.Scanner;
import Daos.UserDao;
import Daos.UserDaoDatabase;
import Daos.WizardDao;
import Daos.WizardDaoDatabase;
import Models.User;

public class MainMenuPrompt implements Prompt{
	
	//Scanner input = new Scanner(System.in);
	WizardDao wizard = WizardDaoDatabase.currentAccountImplementation;
	UserDao user = UserDaoDatabase.currentUserImplementation;
	private Scanner action = new Scanner(System.in);
	public Prompt run() {
		// TODO Auto-generated method stub
	/*System.out.println("All users");
		System.out.println(user.findAll());	
	
	System.out.println("All wizards");
		System.out.println(savedAccount.findAll());*/
	
	System.out.println("Save a user");
	User saveUser = new User("BobSample", "pass9");
	System.out.println(user.save(saveUser));
	System.out.println(user.findAll());	
	System.out.println("Delete a user");
	//System.out.println(user.remove(saveUser));
	System.out.println(user.findAll());
	
	
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
		new AddAccountPrompt();
		break;
	case"4":
		new RemoveAccountPrompt();
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
