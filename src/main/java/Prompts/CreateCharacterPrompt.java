package Prompts;
import java.util.Scanner;
import Daos.WizardDao;
import Models.WizardStudent;

public class CreateCharacterPrompt implements Prompt {
	//instance variables
	Scanner characterInput = new Scanner(System.in);
	WizardDao wizardDao = WizardDao.currentAccountImplementation;
	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
		System.out.println("Create your character");
		System.out.println("What is the name?");
		String wizardName = characterInput.nextLine();
		//System.out.println("Please enter a valid name.");
		
		System.out.println("What year are they in (1-4)?");
		int year = characterInput.nextInt();
		//System.out.println("Please enter a valid year.");	
		
		System.out.println("What house do they belong to?");
		System.out.print("Gryffindor, Slytherin, Hufflepuff, or Ravenclaw");
		String houseName = characterInput.nextLine();
		//System.out.println("Please enter a valid house name.");
		
		//Create a new Wizard with dummy data
		WizardStudent createdCharacter = new WizardStudent(1, wizardName, year, 0, houseName);
		//Saving wizard if there are no problems with the input
		if (wizardDao.doSave(createdCharacter) == true) {
			wizardDao.save(createdCharacter);
		} else {
			System.out.println("Character didn't save. Please enter valid input.");
			return this;
		}
			return new MainMenuPrompt();
	}

}
