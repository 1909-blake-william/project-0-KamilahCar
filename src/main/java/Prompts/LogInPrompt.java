package Prompts;

import java.util.Scanner;

public class LogInPrompt implements Prompt{

	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);

		//Saving username and password	
		System.out.println("Is this your first time at this bank?");
		String answer = input.nextLine();
		String firstTimeUserName = input.nextLine();
		System.out.println("Please create your password.");
		String firstTimePassword = input.nextLine();
		
		return new MainMenuPrompt();
	}

}
