package Daos;

import Models.WizardStudent;

public interface HogwartsSavedAccountDao {
	WizardStudent findByName(String name);
	int save(WizardStudent wizard);
	WizardStudent findAll();
	
}
