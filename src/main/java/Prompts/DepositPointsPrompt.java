package Prompts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import Daos.UserDao;
import Daos.WizardDao;
import Models.WizardStudent;
import Util.ConnectionUtil;

public class DepositPointsPrompt implements Prompt{
	private Scanner input = new Scanner(System.in);
	WizardDao wizardDao = WizardDao.currentAccountImplementation;
	UserDao userDao = UserDao.currentUserImplementation;
	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
		System.out.println("Which character would you like to upgrade?");
		String name = input.nextLine();
		WizardStudent currentWizard = wizardDao.findByName(name);
		if(currentWizard != null) {
			System.out.println("How many points would you like to deposit?");
			int pointsAdded = input.nextInt();
			int currentPoints = currentWizard.getHousePoints();
			int newHousePoints = currentPoints + pointsAdded;
			
			try (Connection hogwartsDatabase = ConnectionUtil.getConnection()){
				String changePoints = "UPDATE hogwarts_characters SET house_points = ? WHERE wizard_name = ?";
				PreparedStatement ps = hogwartsDatabase.prepareStatement(changePoints);
				ps.setInt(1, newHousePoints);
				ps.setString(2, name);
				ps.executeUpdate();
				
			} catch(SQLException e) {
				e.getSQLState();
				e.getCause();
				e.printStackTrace();
			}
			System.out.println("Points added: " + pointsAdded);
			AddAccountPrompt.addTransaction.add("Points added: " + pointsAdded);
		} else {
			System.out.println("Invalid character, please enter a valid character.");
			return this;
		}
		

		return new MainMenuPrompt();
	}
	
}
