package Daos;

import java.util.List;

import Models.WizardStudent;

public interface WizardDao {
	WizardDao currentAccountImplementation = new WizardDaoDatabase(); 
	WizardStudent findByName(String name);
	int save(WizardStudent addWizard);
	int remove(WizardStudent removeWizard);
	List<WizardStudent> findAll();
	boolean doSave(WizardStudent createdCharacter);
	int disableCharacter(WizardStudent wizard);
	
}
