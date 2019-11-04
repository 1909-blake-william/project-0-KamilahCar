package Prompts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import Daos.UserDao;
import Daos.WizardDao;
import Models.WizardStudent;
import Util.ConnectionUtil;

public class WithdrawPointsPrompt implements Prompt{
	private Scanner input = new Scanner(System.in);
	WizardDao wizardDao = WizardDao.currentAccountImplementation;
	UserDao userDao = UserDao.currentUserImplementation;
	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
		System.out.println("Which character would you like to withdraw from?");
		String name = input.nextLine();
		WizardStudent currentWizard = wizardDao.findByName(name);
		if(currentWizard != null) {
			System.out.println("How many points would you like to withdraw?");
			int pointsSubtracted = input.nextInt();
			int currentPoints = currentWizard.getHousePoints();
			int newHousePoints = currentPoints - pointsSubtracted;
			if (newHousePoints < 0) {
				newHousePoints = 0;
			}
			try (Connection hogwartsDatabase = ConnectionUtil.getConnection()){
				String changePoints = "UPDATE HOGWARTS_CHARACTERS SET HOUSE_POINTS = ? WHERE WIZARD_NAME = ?";
				PreparedStatement ps = hogwartsDatabase.prepareStatement(changePoints);
				ps.setInt(1, newHousePoints);
				ps.setString(2, name);
				
				ps.executeUpdate();
				
			} catch(SQLException e) {
				e.getSQLState();
				e.getCause();
				e.printStackTrace();
			}
			wizardDao.addTransaction("subtracted points: " + pointsSubtracted, currentWizard.getName());
			//AddAccountPrompt.addTransaction.add("Points added: " + pointsSubtracted);
		} else {
			System.out.println("This character doesn't exist, please enter a valid character.");
			return this;
		}
		return new MainMenuPrompt();
	}
	
}
