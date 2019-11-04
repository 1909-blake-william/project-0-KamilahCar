package Driver;

/*import Daos.WizardDao;
import Models.WizardStudent;
import Prompts.MainMenuPrompt;*/

import Prompts.Prompt;
import Prompts.LogInPrompt;
/*import Daos.WizardDao;
import Models.WizardStudent;*/
//import Daos.UserDao;
/*import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import Util.ConnectionUtil;*/

//console line application
//user can login
//user can add/remove bank accounts
//user can deposit/withdraw
//user can view transaction history
//user can logout
//admin can view all users
//admin can view all accounts
public class HogwartsApp {
	//private static UserDao userDao = UserDao.currentUserImplementation;
	//private static WizardDao wizardDao = WizardDao.currentAccountImplementation;
	/*WizardStudent extractWizard(ResultSet rs) throws SQLException {
		int id = rs.getInt("wizard_id");
		String name = rs.getString("wizard_name");
		int year = rs.getInt("wizard_year");
		int housePoints = rs.getInt("house_points");
		String house = rs.getString("house_name");

		return new WizardStudent(id, name, year, housePoints, house);
	}
	public WizardStudent findByName(String name) {
		// TODO Auto-generated method stub

		try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {
			String selection = "SELECT * FROM DISABLED_CHARACTERS " + "WHERE WIZARD_NAME = ?";
			PreparedStatement ps = hogwartsDatabase.prepareStatement(selection);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractWizard(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.getCause();
			e.getSQLState();
			e.printStackTrace();
			return null;
		}
	}
	public int remove(WizardStudent removeWizard) {
		try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {
			// "DELETE FROM hogwarts_users WHERE user_name = ?" +
			String deleteStatement = "DELETE FROM DISABLED_CHARACTERS WHERE WIZARD_NAME = ?";
			PreparedStatement ps = hogwartsDatabase.prepareStatement(deleteStatement);
			ps.setString(1, removeWizard.getName());

			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getCause());
			System.out.println(e.getSQLState());
			// e.printStackTrace();
			return 0;
		}
	}*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Prompt p = new LogInPrompt();
		while(true) {
			p = p.run();
		}
		/*System.out.println(userDao.findByUsername("Kamilah"));
		
		
		System.out.println(wizardDao.findByName("Harry"));
		WizardStudent w = new WizardStudent(2, "Neville", 2, 70, "Gryffindor");
		
		System.out.println(wizardDao.doSave(w));
		/*== true) {
			System.out.println(wizardDao.save(w));
		}*/
		
		
		
		
		
		
		/*HogwartsApp test = new HogwartsApp();
		WizardStudent sample = test.findByName("Neville");
		test.remove(sample);*/
		/*WizardStudent sample = new WizardStudent(1, "Neville", 2, 10, "Slytherin");
		if (wizardDao.doSave(sample) == true) {
			wizardDao.save(sample);
		}
		
		System.out.println(wizardDao.findAll());
		//wizardDao.disableCharacter(sample);
		//WizardStudent sample = wizardDao.findByName("Neville");
		wizardDao.remove(sample);
		System.out.println(wizardDao.findAll());*/
		//System.out.println(wizardDao.findAllDisabledCharacters());
		

	}

}
