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
			//Gathering user input for log in
			String firstTimeUsername = input.nextLine();
			
			//Checking if username entered is availabe i.e. noy already
			//in database
			User currentUser = userDao.findByUsername(firstTimeUsername);
			if (currentUser != null) {
				//log.debug("attempting to store username");
				System.out.println("This user already exists. Please choose a different name");
				break;
			} else {
				//password input
				System.out.println("Please create your password.");
				String firstTimePassword = input.nextLine();
				
				//log.debug("attempting to store username and password");
				//Create new user
				User newUser = new User(1, firstTimeUsername, firstTimePassword, "user");
				//Save this user in database
				userDao.save(newUser);
				//Log in
				registerUser.login(firstTimeUsername, firstTimePassword);
				//Enter user main menu since admin role is not available
				//for new users
				return new MainMenuPrompt();
			}
		case "2":
				//Gathering user input for log in
				System.out.println("Please enter your username");
				String username = input.nextLine();
				System.out.println("Please enter your password");
				String password = input.nextLine();
				
				//finding user and checking credentials
				User anotherUser = registerUser.login(username, password);
				//Checking if user name and password can be found in database
				if (anotherUser == null) {
					System.out.println("Invalid Credentials. Check your username or password.");
					return this;
				} else if (anotherUser.getRole().equalsIgnoreCase("admin")){
						System.out.println("Welcome Admin");
						//open admin main menu upon successful admin log in
						return new AdminMainPrompt();
				} else {
					//open user main menu upon successful user log in
					return new MainMenuPrompt();
				}
		default:
			System.out.println("invalid option");
			break;
		}
		//always return to the login prompt unless there is a successful log in
		return this;
	}

}

