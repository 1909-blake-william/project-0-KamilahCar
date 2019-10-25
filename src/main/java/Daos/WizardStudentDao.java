package Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.util.ConnectionUtil;

//import Models.User;
import Models.WizardStudent;

public class WizardStudentDao implements HogwartsSavedAccountDao {
		WizardStudent extractWizard(ResultSet rs) throws SQLException {
			int id = rs.getInt("wizard_id");
			String name = rs.getString("wizard_name");
			int year = rs.getInt("wizard_year");
			int housePoints = rs.getInt("house_points");
			String house = rs.getString("house_name");
			/*int userId = rs.getInt("user");
			String userName = rs.getString("username");
			String userRole = rs.getString("systemRole");*/
			return new WizardStudent(id, name, year, housePoints, house);
		}
 public WizardStudent findAll() {
	 ResultSet rs = null;
	 WizardStudent wizardFindAll = null;
	List<WizardStudent> wizards = new ArrayList<WizardStudent>();
	 try(Connection c = ConnectionUtil.getConnection()){
		 String selection = "SELECT * FROM HOGWARTS_CHARACTERS";
		 PreparedStatement ps = c.prepareStatement(selection);
		 rs = ps.executeQuery();
		while (rs.next()) {
			wizardFindAll = extractWizard(rs);
			wizards.add(wizardFindAll);
		}
	 } catch(SQLException anyException) {
		 anyException.getCause();
		 anyException.getSQLState();
		 anyException.printStackTrace();
	 }
	return null;
 }

@Override
public WizardStudent findByName(String name) {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	
	WizardStudent wizardfindByName = null;
	try (Connection hogwartsDatabase = ConnectionUtil.getConnection()){
		String selection = "SELECT * FROM HOGWARTS_CHARACTERS hc" + "";
		PreparedStatement ps = hogwartsDatabase.prepareStatement(selection);
		rs = ps.executeQuery();
		while(rs.next()) {
			wizardfindByName = extractWizard(rs);
		}
	} catch(SQLException e) {
		e.getCause();
		e.getSQLState();
		e.printStackTrace();
	}
	return wizardfindByName;
}

@Override
public int save(WizardStudent wizard) {
	ResultSet rs = null;
	try (Connection hogwartsDatabase = ConnectionUtil.getConnection()){
		"UPDATE HOGWARTS_CHARACTERS SET"
		if(rs.next() == false) {
			WizardStudent saveWizard = new WizardStudent();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
}
}
