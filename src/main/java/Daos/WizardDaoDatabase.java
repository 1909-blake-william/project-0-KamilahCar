package Daos;

//import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
//import org.apache.log4j.Logger;
//import Models.User;
import Models.WizardStudent;
import Util.ConnectionUtil;
import Util.UserRegistryUtil;

public class WizardDaoDatabase implements WizardDao {
	// private Logger log = Logger.getRootLogger();
	private UserRegistryUtil registerUser = UserRegistryUtil.instance;
	
	//Get a single Wizard object from one row in database
	WizardStudent extractWizard(ResultSet rs) throws SQLException {
		//Setting all arguments for new WizardStudent object
		int id = rs.getInt("wizard_id");
		String name = rs.getString("wizard_name");
		int year = rs.getInt("wizard_year");
		int housePoints = rs.getInt("house_points");
		String house = rs.getString("house_name");
		//Creating new wizardstudent based on these arguments
		//and returning
		return new WizardStudent(id, name, year, housePoints, house, registerUser.getCurrentUser());
	}
	//Extract all disabled characters (wizards students) from disabled characters table in database
	public List<WizardStudent> findAllDisabledCharacters() {
		//Variables
		List<WizardStudent> wizards = new ArrayList<WizardStudent>();
		//establishing connection to database
		try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {
			//SQL Query
			//Selecting all columns from disabled characters
			String selection = "SELECT * FROM DISABLED_CHARACTERS";
			PreparedStatement ps = hogwartsDatabase.prepareStatement(selection);
			//Executing SQL Query on all rows in table
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				//Getting wizard from row
				WizardStudent wizardFindAll = extractWizard(rs);
				//adding row to list of wizards
				wizards.add(wizardFindAll);
			}
			return wizards;
		} catch (SQLException anyException) {
			anyException.getCause();
			anyException.getSQLState();
			anyException.printStackTrace();
			return null;
		}
	}
	//Extract all characters (wizards students) from characters table in database
	public List<WizardStudent> findAll() {
		//Variables
		List<WizardStudent> wizards = new ArrayList<WizardStudent>();
		//establishing connection to database
		try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {
			//SQL Query
			//Selecting all columns from hogwarts characters
			String selection = "SELECT * FROM HOGWARTS_CHARACTERS";
			PreparedStatement ps = hogwartsDatabase.prepareStatement(selection);
			//Executing SQL Query on all rows in table
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				//Getting wizard from row
				WizardStudent wizardFindAll = extractWizard(rs);
				//adding row to list of wizards
				wizards.add(wizardFindAll);
			}
			return wizards;
		} catch (SQLException anyException) {
			anyException.getCause();
			anyException.getSQLState();
			anyException.printStackTrace();
			return null;
		}
	}

	@Override
	public WizardStudent findByName(String name) {
		// TODO Auto-generated method stub
		//establishing connection to database
		try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {
			//SQL Query
			//Selecting all columns from hogwarts characters that match the name input
			String selection = "SELECT * FROM HOGWARTS_CHARACTERS " + "WHERE WIZARD_NAME = ?";
			PreparedStatement ps = hogwartsDatabase.prepareStatement(selection);
			//Setting name input parameter
			ps.setString(1, name);
			//Executing query on row where input name matches character name in database
			ResultSet rs = ps.executeQuery();
			//Searching rows
			if (rs.next()) {
				//return the wizard that matches the criteria
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
	//Checks if a newly created character (Wizard student) should
	//be saved
	public boolean doSave(WizardStudent wizard) {
		//Variables
		int year = wizard.getWizardYear();
		String name = wizard.getName();
		WizardStudent wiz = findByName(name);
		String houseName = wizard.getHouseName();
		//Wizard must not already exist in database
		if (wiz != null) {
			return false;
		//house name must equal Gryffindor, Slytherin, Hufflepuff,
		//or Ravenclaw
		} else if (!houseName.equalsIgnoreCase("Gryffindor") && !houseName.equalsIgnoreCase("Slytherin")
				&& !houseName.equalsIgnoreCase("Hufflepuff") && !houseName.equalsIgnoreCase("Ravenclaw")) {
			return false;
		//year must be within range 1-4
		} else if (year != 1 && year != 2 && year != 3 && year != 4) {
			return false;
		} else {
			return true;
		}

	}
	//
	@Override
	public int save(WizardStudent wizard) {
		try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {

			// Database Function
			String addWizard = "INSERT INTO HOGWARTS_CHARACTERS(WIZARD_ID, WIZARD_NAME, "
					+ "WIZARD_YEAR, HOUSE_POINTS, HOUSE_NAME) " + "VALUES(hogwarts_wizard_id_seq.nextval,?,?,?,?)";

			// Adding values
			PreparedStatement ps = hogwartsDatabase.prepareStatement(addWizard);
			ps.setString(1, wizard.getName());
			ps.setInt(2, wizard.getWizardYear());
			ps.setInt(3, wizard.getHousePoints());
			ps.setString(4, wizard.getHouseName());

			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getCause();
			e.getSQLState();
			e.printStackTrace();
			return 0;
		}
	}


//Move character over to a new table (inactive table) based on booleans
	public int remove(WizardStudent removeWizard) {
		try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {
			
			String deleteStatement = "DELETE FROM HOGWARTS_CHARACTERS WHERE WIZARD_NAME = ?";
			PreparedStatement ps = hogwartsDatabase.prepareStatement(deleteStatement);
			ps.setString(1, removeWizard.getName());

			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getCause());
			System.out.println(e.getSQLState());
			// e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void disableCharacter(WizardStudent removeWizard) {
		// TODO Auto-generated method stub
		try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {
			// "DELETE FROM hogwarts_users WHERE user_name = ?" +
			String addStatement = "INSERT INTO DISABLED_CHARACTERS(WIZARD_ID, WIZARD_NAME, WIZARD_YEAR, HOUSE_POINTS, HOUSE_NAME) "
					+ "VALUES(hogwarts_wizard_id_seq.nextval,?,?,?,?)";
			String deleteStatement = "DELETE FROM HOGWARTS_CHARACTERS WHERE WIZARD_NAME = ?";

			PreparedStatement psAdd = hogwartsDatabase.prepareStatement(addStatement);
			psAdd.setString(1, removeWizard.getName());
			psAdd.setInt(2, removeWizard.getWizardYear());
			psAdd.setInt(3, removeWizard.getHousePoints());
			psAdd.setString(4, removeWizard.getHouseName());
			psAdd.executeUpdate();

			PreparedStatement ps = hogwartsDatabase.prepareStatement(deleteStatement);
			ps.setString(1, removeWizard.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addTransaction(String actionName, String accountName) {
		// Getting WizardStudent
		// SQL Statement
		// Adding transaction to transaction table in Database
		String addTransaction = "INSERT INTO HOGWARTS_USERTRANSACTIONS"
				+ "(transaction_id, transaction_action, transaction_name, user_id) "
				+ "VALUES(transaction_id_seq.nextval,?,?,?)";
		// Connecting to database
		try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {
			// Performing SQL Statement, Adding values
			PreparedStatement ps = hogwartsDatabase.prepareStatement(addTransaction);
			ps.setString(1, actionName);
			ps.setString(2, accountName);
			ps.setInt(3, registerUser.getCurrentUser().getId());
			// Performing the SQL Statement on the specified row
			return ps.executeUpdate();
			// If there is an SQL exception, no updates will occur
			// within the database
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getCause();
			e.getSQLState();
			e.printStackTrace();
			return 0;
		}
	}
}
