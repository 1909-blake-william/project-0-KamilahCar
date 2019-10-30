import Daos.WizardDao;
import Models.WizardStudent;
import Prompts.MainMenuPrompt;
import Prompts.Prompt;

//console line application
//user can login
//user can add/remove bank accounts
//user can deposit/withdraw
//user can view transaction history
//user can logout
//admin can view all users
//admin can view all accounts
public class HogwartsApp {
	WizardDao wizardDao = WizardDao.currentAccountImplementation;
	public boolean doSave(WizardStudent wizard) {
		boolean saveWizard = false;
		int year = wizard.getWizardYear();
		//String name = wizard.getName();
		String houseName = wizard.getHouseName();
		//if (wizardDao.findByName(name) != null) {
			if (houseName.equals("Gryffindor")|| houseName.equals("Slytherin")||
					houseName.equals("Hufflepuff")|| houseName.equals("Ravenclaw")) {
					if (year == 1 || year == 2 || year == 3 || year == 4) {
						saveWizard = true;
					}
			}
		//}
		return saveWizard;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Prompt p = new MainMenuPrompt();
		p.run();*/
		HogwartsApp test = new HogwartsApp();
		WizardStudent sample = new WizardStudent(1, "Harry", 5, 0, "Slytherin");
		test.doSave(sample);
		

	}

}
