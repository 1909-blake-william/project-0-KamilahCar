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

	WizardStudent extractWizard(ResultSet rs) throws SQLException {
		int id = rs.getInt("wizard_id");
		String name = rs.getString("wizard_name");
		int year = rs.getInt("wizard_year");
		int housePoints = rs.getInt("house_points");
		String house = rs.getString("house_name");

		return new WizardStudent(id, name, year, housePoints, house, registerUser.getCurrentUser());
	}

	public List<WizardStudent> findAllDisabledCharacters() {
		List<WizardStudent> wizards = new ArrayList<WizardStudent>();
		try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {
			String selection = "SELECT * FROM DISABLED_CHARACTERS";
			PreparedStatement ps = hogwartsDatabase.prepareStatement(selection);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				WizardStudent wizardFindAll = extractWizard(rs);
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

	public List<WizardStudent> findAll() {
		WizardStudent wizardFindAll = null;
		List<WizardStudent> wizards = new ArrayList<WizardStudent>();
		try (Connection c = ConnectionUtil.getConnection()) {
			String selection = "SELECT * FROM HOGWARTS_CHARACTERS";
			PreparedStatement ps = c.prepareStatement(selection);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				wizardFindAll = extractWizard(rs);
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

		try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {
			String selection = "SELECT * FROM HOGWARTS_CHARACTERS " + "WHERE WIZARD_NAME = ?";
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

	public boolean doSave(WizardStudent wizard) {
		int year = wizard.getWizardYear();
		String name = wizard.getName();
		WizardStudent wiz = findByName(name);
		String houseName = wizard.getHouseName();
		if (wiz != null) {
			return false;
		} else if (!houseName.equalsIgnoreCase("Gryffindor") && !houseName.equalsIgnoreCase("Slytherin")
				&& !houseName.equalsIgnoreCase("Hufflepuff") && !houseName.equalsIgnoreCase("Ravenclaw")) {
			return false;

		} else if (year != 1 && year != 2 && year != 3 && year != 4) {
			return false;
		} else {
			return true;
		}

	}

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

//Cascading delete based on foreign key
//Move character over to a new table (inactive table) based on booleans
	public int remove(WizardStudent removeWizard) {
		try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {
			// "DELETE FROM hogwarts_users WHERE user_name = ?" +
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
