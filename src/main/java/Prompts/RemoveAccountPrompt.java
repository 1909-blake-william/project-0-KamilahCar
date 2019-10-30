package Prompts;

import java.util.Scanner;

import org.apache.log4j.Logger;

import Daos.UserDao;
import Daos.WizardDao;
import Models.User;

public class RemoveAccountPrompt implements Prompt{
	//Instance Variables
	Scanner accountNameInput = new Scanner(System.in);
	Scanner removeAnotherAccountInput = new Scanner(System.in);
	UserDao userDao = UserDao.currentUserImplementation;
	WizardDao wizardDao = WizardDao.currentAccountImplementation;
	Logger log = Logger.getRootLogger();
	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
		System.out.println("Enter the name of the account you want to remove.");
		String accountName = accountNameInput.nextLine();
		log.debug("Removing User");
		User removeUser = userDao.findByUsername(accountName);
		if (removeUser != null) {
			userDao.remove(removeUser);
			log.debug("User should be removed.");
		//accountNameInput.close();
		} else {
			System.out.println("You do not have any accounts to remove");
		}
		System.out.println("Would you like to remove another account Y/N");
		String answer = removeAnotherAccountInput.nextLine();
		switch(answer) {
		case "Y":
			break;
		case "N":
			return new MainMenuPrompt();
		default:
			System.out.println("Invalid input. Please choose Y or N");
		}
		return this;
	}
	
}
