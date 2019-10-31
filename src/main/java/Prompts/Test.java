package Prompts;

import Daos.WizardDao;
import Models.WizardStudent;

public class Test {
	private WizardDao wizardDao = WizardDao.currentAccountImplementation;

	public boolean doSave(WizardStudent wizard) {
		int year = wizard.getWizardYear();
		String name = wizard.getName();
		String houseName = wizard.getHouseName();
		if (wizardDao.findByName(name) == null) {
			return false;
		} else if (!houseName.equals("Gryffindor")|| !houseName.equals("Slytherin")||
					!houseName.equals("Hufflepuff")|| !houseName.equals("Ravenclaw")) {
			return false;
			
		} else if (year == 1 || year == 2 || year == 3 || year == 4) {
					return false;
		} else {
			return true;
		}
		
	}
	public static void main (String args[]) {
		Test sample = new Test();
		WizardStudent wizard = new WizardStudent(1, "Harry", 2, 10, "Gryffindor");
		WizardStudent otherWizard = new WizardStudent(2, "Neville", 2, 10, "Gryffindor");
		sample.doSave(wizard);
		sample.doSave(otherWizard);
	}
}
