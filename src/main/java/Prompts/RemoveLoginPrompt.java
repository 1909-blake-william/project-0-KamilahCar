package Prompts;

import java.util.Scanner;

import Daos.UserDao;
import Models.User;
import Util.UserRegistryUtil;

public class RemoveLoginPrompt implements Prompt {
	private UserRegistryUtil registerUser = UserRegistryUtil.instance;
	private Scanner accountNameInput = new Scanner(System.in);
	private UserDao userDao = UserDao.currentUserImplementation;

	@Override
	public Prompt run() {
		System.out.println("Are you sure you want to shut down your account?");
		String userName = accountNameInput.nextLine();
		// log.debug("Removing User if the user is an admin");
		User loginDelete = registerUser.getCurrentUser();
		String userRole = registerUser.getCurrentUser().getRole();
		// log.debug("User should be removed if the user is an admin.");
		// Checking if account exists in database
		if (userRole.equalsIgnoreCase("admin")) {
			userDao.remove(loginDelete);
			// Accessing list of transactions created in AddAccountPrompt class
			// Adding this transaction to that list
			AddAccountPrompt.addTransaction.add("Login removed " + userName);
			// Return to Admin Main Menu
			return new AdminMainPrompt();
		} else if (!userRole.equalsIgnoreCase("admin")) {
			userDao.disableLogin(loginDelete);
			// Return to main menu
			return new MainMenuPrompt();
			// accountNameInput.close();
			// Username already exists in database and cannot be duplicated
		} else {
			System.out.println("Invalid username and password.");
			return this;
		}

	}
}
