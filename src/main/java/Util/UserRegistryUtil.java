package Util;
import java.util.List;

import Daos.UserDao;
import Models.User;
import Prompts.AddAccountPrompt;

public class UserRegistryUtil {
	public static final UserRegistryUtil instance = new UserRegistryUtil();

	private UserDao userDao = UserDao.currentUserImplementation;
	private User currentUser = null;

	private UserRegistryUtil() {
		super();
	}

	public User login(String username, String password) {
		User u = userDao.findByUsernameAndPassword(username, password);
		currentUser = u;
		return u;
	}

	public User getCurrentUser() {
		return currentUser;
	}
	
	public List<String> getTransactions(){
		return AddAccountPrompt.addTransaction;
	}

}