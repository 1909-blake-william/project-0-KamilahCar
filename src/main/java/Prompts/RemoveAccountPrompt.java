package Prompts;

import java.util.Scanner;

/*import org.apache.log4j.Logger;
import Models.User;
import Daos.UserDao;*/
import Daos.WizardDao;

import Models.WizardStudent;
import Util.UserRegistryUtil;

public class RemoveAccountPrompt implements Prompt{
	//Instance Variables
	private UserRegistryUtil registerUser = UserRegistryUtil.instance;
	private Scanner accountNameInput = new Scanner(System.in);
	//private Scanner removeAnotherAccountInput = new Scanner(System.in);
	//private UserDao userDao = UserDao.currentUserImplementation;
	private WizardDao wizardDao = WizardDao.currentAccountImplementation;
	//Logger log = Logger.getRootLogger();
	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
		//Getting Account info
		System.out.println("Enter the name of the account you want to remove.");
		String accountName = accountNameInput.nextLine();
		//log.debug("Removing User if the user is an admin"); 
		WizardStudent wizardToBeRemoved = wizardDao.findByName(accountName);
		String userRole = registerUser.getCurrentUser().getRole();
		//log.debug("User should be removed if the user is an admin.");
		//Checking if account exists in database
		if (wizardToBeRemoved != null && userRole.equalsIgnoreCase("admin")) {
				wizardDao.remove(wizardToBeRemoved);
			//Accessing list of transactions created in AddAccountPrompt class
				//Adding this transaction to that list
				AddAccountPrompt.addTransaction.add("Wizard removed " + accountName);
				//Return to Admin Main Menu
				return new AdminMainPrompt();
		} else if (wizardToBeRemoved != null && !userRole.equalsIgnoreCase("admin")){
				wizardDao.disableCharacter(wizardToBeRemoved);
				AddAccountPrompt.addTransaction.add("Wizard removed " + accountName);
				//Return to main menu
				return new MainMenuPrompt();
				//accountNameInput.close();
		//Username already exists in database and cannot be duplicated
		} else {
			System.out.println("Sorry, that Wizard doesn't exist.");
			return new MainMenuPrompt();
		}
		
	}
	
}
