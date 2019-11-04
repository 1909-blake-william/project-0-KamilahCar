package Daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import Util.UserRegistryUtil;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import Models.Transaction;
//import org.apache.log4j.Logger;
import Models.User;
import Util.ConnectionUtil;
public class UserDaoDatabase implements UserDao {

		private Logger log = Logger.getRootLogger();
		//private UserRegistryUtil registerUser = UserRegistryUtil.instance;

		User extractUser(ResultSet rs) throws SQLException {
			int id = rs.getInt("user_id");
			String rsUsername = rs.getString("user_name");
			String rsPassword = rs.getString("user_password");
			String role = rs.getString("systemrole");
			return new User(id, rsUsername, rsPassword, role);
		}
		Transaction extractTransaction(ResultSet rs) throws SQLException{
			int id = rs.getInt("transaction_id");
			String rsAccountName = rs.getString("transaction_name");
			String rsActionPerformed = rs.getString("transaction_action");
			return new Transaction(id, rsActionPerformed, rsAccountName);
			
		}
		@Override
		//Order by later
		public int save(User u) {
			//log.debug("attempting to find user by credentials from DB");
			try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {

				String saveStatement = "INSERT INTO HOGWARTS_USERS (USER_ID, USER_NAME, USER_PASSWORD) "
						+ "VALUES(HOGWARTS_USERS_ID_SEQ.nextval,?,?)";

				PreparedStatement ps = hogwartsDatabase.prepareStatement(saveStatement);
				ps.setString(1, u.getName());
				ps.setString(2, u.getPassword());
				return ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getCause());
				System.out.println(e.getSQLState());
				//e.printStackTrace();
				return 0;
			}
		}
		public int remove (User thisUser) {
			try(Connection hogwartsDatabase = ConnectionUtil.getConnection()){
				String deleteStatement = "DELETE FROM HOGWARTS_USERS " + "WHERE USER_NAME = ?";
				PreparedStatement ps = hogwartsDatabase.prepareStatement(deleteStatement);
				ps.setString(1, thisUser.getName());	
				
				return ps.executeUpdate();
			} catch(SQLException e) {
				System.out.println(e.getCause());
				System.out.println(e.getSQLState());
				//e.printStackTrace();
				return 0;
			}
		}
		public int disableLogin(User deleteLogin) {
			try(Connection hogwartsDatabase = ConnectionUtil.getConnection()){
				String addStatement = "INSERT INTO HOGWARTS_DISABLEDUSERS(?,?,?) " + 
						"VALUES(HOGWARTS_USER_ID_SEQ.nextval,?,?)";
				String deleteStatement = "DELETE FROM HOGWARTS_USERS " + "WHERE USER_NAME = ?";
				PreparedStatement psAdd = hogwartsDatabase.prepareStatement(addStatement);
				psAdd.setString(1, deleteLogin.getName());	
				psAdd.setString(2, deleteLogin.getPassword());
				
				psAdd.executeUpdate();
			
				PreparedStatement ps = hogwartsDatabase.prepareStatement(deleteStatement);
				ps.setString(1, deleteLogin.getName());	
				
				return ps.executeUpdate();
			} catch(SQLException e) {
				System.out.println(e.getCause());
				System.out.println(e.getSQLState());
				//e.printStackTrace();
				return 0;
			}
			
			
		}

		@Override
		public List<User> findAll() {
			//log.debug("attempting to find all users from DB");
			try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {

				String selection = "SELECT * FROM HOGWARTS_USERS";

				PreparedStatement ps = hogwartsDatabase.prepareStatement(selection);

				ResultSet rs = ps.executeQuery();
				List<User> users = new ArrayList<User>();
				while (rs.next()) {
					users.add(extractUser(rs));
				}

				return users;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.debug("SQL isn't working");
				System.out.println(e.getCause());
				System.out.println(e.getSQLState());
				//e.printStackTrace();
				return null;
			}
		}
		public List<Transaction> findTransactionByUserId(int id){
			List<Transaction> transactions = new ArrayList<Transaction>();
			try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {
				// Adding values
				String addTransaction = "SELECT * FROM HOGWARTS_USERTRANSACTIONS WHERE USER_ID = ?";
				PreparedStatement ps = hogwartsDatabase.prepareStatement(addTransaction);
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					transactions.add(extractTransaction(rs));
				}
				return transactions;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.getCause();
				e.getSQLState();
				e.printStackTrace();
				return null;
			}
		}
		public List<Transaction> findAllTransactions(){
			
			List<Transaction> transactions = new ArrayList<Transaction>();
			try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {
				String selection = "SELECT * FROM HOGWARTS_USERTRANSACTIONS";
				PreparedStatement ps = hogwartsDatabase.prepareStatement(selection);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Transaction currentTransaction = extractTransaction(rs);
					transactions.add(currentTransaction);
				}
				return transactions;
			} catch (SQLException anyException) {
				anyException.getCause();
				anyException.getSQLState();
				anyException.printStackTrace();
				return null;
			}
			
		}
		@Override
		public User findById(int id) {
			log.debug("attempting to find user by credentials from DB");
			try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {

				String sql = "SELECT * FROM HOGWARTS_USERS " + "WHERE USER_ID = id";

				PreparedStatement ps = hogwartsDatabase.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return extractUser(rs);
				} else {
					return null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getCause());
				System.out.println(e.getSQLState());
				//e.printStackTrace();
				return null;
			}

		}

		@Override
		public User findByUsernameAndPassword(String username, String password) {
			log.debug("attempting to find user by credentials from DB");
			try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {

				String sql = "SELECT * FROM HOGWARTS_USERS " + 
				"WHERE USER_NAME = ? AND USER_PASSWORD = ?";

				PreparedStatement ps = hogwartsDatabase.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);

				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return extractUser(rs);
				} else {
					return null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getCause());
				System.out.println(e.getSQLState());
				//e.printStackTrace();
				return null;
			}
		}
		@Override
		public User findByUsername(String username) {
			// TODO Auto-generated method stub
			
			try (Connection hogwartsDatabase = ConnectionUtil.getConnection()){
				String selection = "SELECT * FROM HOGWARTS_USERS " + "WHERE USER_NAME = ?";
				PreparedStatement ps = hogwartsDatabase.prepareStatement(selection);
				ps.setString(1, username);
				
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					return extractUser(rs);
				} else {
					return null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getCause());
				System.out.println(e.getSQLState());
				e.printStackTrace();
				return null;
			}
			
		}

		/*@Override
		public int partiallyRemove(User u) {
			// TODO Auto-generated method stub
			
			return 0;
		}*/
}
