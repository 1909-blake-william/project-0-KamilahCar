package Prompts;

import java.util.Scanner;
//import org.apache.log4j.Logger;

import Daos.UserDao;
import Models.User;
import Util.UserRegistryUtil;

public class LogInPrompt implements Prompt{
	private Scanner input = new Scanner(System.in);
	private UserRegistryUtil registerUser = UserRegistryUtil.instance;
	private UserDao userDao = UserDao.currentUserImplementation;
	//Logger log = Logger.getRootLogger();

	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
		

		//Saving username and password	
		System.out.println("Welcome to Hogwarts. All new students must create a new account. "
				+ "What would you like to do?");
		System.out.println("1 Create a new account?");
		System.out.println("2 Sign In?");
		String answer = input.nextLine();
		switch(answer) {
		case "1":
			System.out.println("Please create your username");
			String firstTimeUsername = input.nextLine();
			
			User currentUser = userDao.findByUsername(firstTimeUsername);
			
			if (currentUser != null) {
				//log.debug("attempting to store username");
				System.out.println("This user already exists. Please choose a different name");
				break;
			} else {
				System.out.println("Please create your password.");
				String firstTimePassword = input.nextLine();
				
				//log.debug("attempting to store username and password");
				User newUser = new User(1, firstTimeUsername, firstTimePassword, "student");
				userDao.save(newUser);
				registerUser.login(firstTimeUsername, firstTimePassword);
			}
		case "2":
				System.out.println("Please enter your username");
				String username = input.nextLine();
				System.out.println("Please enter your password");
				String password = input.nextLine();
				User anotherUser = registerUser.login(username, password);
				if (anotherUser == null) {
					System.out.println("Invalid Credentials. Check your username or password.");
					return this;
				} else if (anotherUser.getRole().equalsIgnoreCase("admin")){
						System.out.println("Welcome Admin");
						return new AdminMainPrompt();
				} else {
					return new MainMenuPrompt();
				}
		default:
			System.out.println("invalid option");
			break;
		}
		return this;
	}

}

