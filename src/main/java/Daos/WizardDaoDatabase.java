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

public class WizardDaoDatabase implements WizardDao {
	//private Logger log = Logger.getRootLogger();
		WizardStudent extractWizard(ResultSet rs) throws SQLException {
			int id = rs.getInt("wizard_id");
			String name = rs.getString("wizard_name");
			int year = rs.getInt("wizard_year");
			int housePoints = rs.getInt("house_points");
			String house = rs.getString("house_name");
			//int userId = rs.getInt("user_id");
			//String userPassword = rs.getString("user_password");
			//String userName = rs.getString("user_name");
			//String userRole = rs.getString("systemRole");
			return new WizardStudent(id, name, year, housePoints, house/*, userName*/);
		}
 public List<WizardStudent> findAll() {
	 WizardStudent wizardFindAll = null;
	List<WizardStudent> wizards = new ArrayList<WizardStudent>();
	 try(Connection c = ConnectionUtil.getConnection()){
		 String selection = "SELECT * FROM HOGWARTS_CHARACTERS";
		 PreparedStatement ps = c.prepareStatement(selection);
		 ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			wizardFindAll = extractWizard(rs);
			wizards.add(wizardFindAll);
		}
		return wizards;
	 } catch(SQLException anyException) {
		 anyException.getCause();
		 anyException.getSQLState();
		 anyException.printStackTrace();
		 return null;
	 }
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
public boolean doSave(WizardStudent wizard) {
	boolean saveWizard = false;
	int year = wizard.getWizardYear();
	String name = wizard.getName();
	String houseName = wizard.getHouseName();
	if (findByName(name) == null) {
		if (houseName.equals("Gryffindor")|| houseName.equals("Slytherin")||
				houseName.equals("Hufflepuff")|| houseName.equals("Ravenclaw")) {
			if (year == 1 || year == 2 || year == 3 || year == 4) {
				saveWizard = true;
			}
		}
	}
	return saveWizard;
}
@Override
public int save(WizardStudent wizard) {
	try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {
		
		//Database Function 
		String addWizard = "INSERT INTO HOGWARTS_CHARACTERS(?,?,?,?,?)" + 
		"VALUES(hogwarts_wizard_id_seq,?,?,?,?)";
		
		//Adding values
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
public int remove(WizardStudent removeWizard) {
	try(Connection hogwartsDatabase = ConnectionUtil.getConnection()){
		//"DELETE FROM hogwarts_users WHERE user_name = ?" +
		String deleteStatement = "DELETE FROM hogwarts_characters WHERE wizard_name = ?";
		PreparedStatement ps = hogwartsDatabase.prepareStatement(deleteStatement);
		ps.setString(1, removeWizard.getName());	
		
		return ps.executeUpdate();
	} catch(SQLException e) {
		System.out.println(e.getCause());
		System.out.println(e.getSQLState());
		//e.printStackTrace();
		return 0;
	}
}
}
