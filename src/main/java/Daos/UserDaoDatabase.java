package Daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

//import org.apache.log4j.Logger;
import Models.User;
import Util.ConnectionUtil;
public class UserDaoDatabase implements UserDao {

		private Logger log = Logger.getRootLogger();

		User extractUser(ResultSet rs) throws SQLException {
			int id = rs.getInt("user_id");
			String rsUsername = rs.getString("user_name");
			String rsPassword = rs.getString("user_password");
			String role = rs.getString("systemrole");
			return new User(id, rsUsername, rsPassword, role);
		}

		@Override
		//Order by later
		public int save(User u) {
			//log.debug("attempting to find user by credentials from DB");
			try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {

				String saveStatement = "INSERT INTO hogwarts_users (user_id, user_name, user_password) "
						+ " VALUES (hogwarts_users_id_seq.nextval,?,?)";

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
				String deleteStatement = "DELETE FROM hogwarts_users WHERE user_name = ?";
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
						"VALUES(hogwarts_user_id_seq,?,?)";
				String deleteStatement = "DELETE FROM hogwarts_users WHERE user_name = ?";
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
			try (Connection c = ConnectionUtil.getConnection()) {

				String selection = "SELECT * FROM hogwarts_users";

				PreparedStatement ps = c.prepareStatement(selection);

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

		@Override
		public User findById(int id) {
			log.debug("attempting to find user by credentials from DB");
			try (Connection hogwartsDatabase = ConnectionUtil.getConnection()) {

				String sql = "SELECT * FROM hogwarts_users " + "WHERE user_id = id";

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

				String sql = "SELECT * FROM hogwarts_users " + "WHERE user_name = ? AND user_password = ?";

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
				String selection = "SELECT * FROM hogwarts_users " + "WHERE user_name = ?";
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
