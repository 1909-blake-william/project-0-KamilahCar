package Prompts;


import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

//import Daos.UserDao;
import Daos.WizardDao;
//import Models.User;
import Models.WizardStudent;
//import Util.UserRegistryUtil;

public class AddAccountPrompt implements Prompt{
private Scanner characterInput = new Scanner(System.in);
public static List <String>addTransaction = new ArrayList<String>();
//private UserRegistryUtil registerUser = UserRegistryUtil.instance;
private WizardDao wizardDao = WizardDao.currentAccountImplementation;
	@Override
	public Prompt run() {
		
		System.out.println("Create your character");
			
			//Getting character info
			System.out.println("What is the name?");
			String wizardName = characterInput.nextLine();
			
			System.out.println("What house do they belong to?");
			System.out.print("Gryffindor, Slytherin, Hufflepuff, or Ravenclaw");
			String houseName = characterInput.nextLine();
			
			System.out.println("What year are they in (1-4)?");
			int year = characterInput.nextInt();
			characterInput.nextLine();
			
			//Create a new Wizard with data entered for name, year, and house name
			//the rest is dummy data
			WizardStudent createdCharacter = new WizardStudent(1, wizardName, year, 10, houseName);
			//Saving wizard if there are no problems with the input
			if (wizardDao.doSave(createdCharacter) == true) {
				wizardDao.save(createdCharacter);
				//Accessing list created in AddAccountPrompt class and adding
				//this transaction to that list
				//addTransaction.add("Wizard added " + wizardName);
				wizardDao.addTransaction("added", wizardName);
			//User didn't enter a valid year (1-4) for character or
			//User didn't enter valid house for character 
			} else {
				System.out.println("Character didn't save. Please enter a different name, "
						+ "makes sure you enter a year between 1 and 4, and"
						+ "make sure your house is a valid house.");
				//Start this prompt over if character save is unsuccessful
				//return this;
			}
				//Go to main menu if a character is successfully created
			return new MainMenuPrompt();
	}

}
