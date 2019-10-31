package Prompts;

import Daos.UserDao;
import Util.UserRegistryUtil;

public class ViewAllTransactionHistoryPrompt implements Prompt{
	private UserRegistryUtil registerUser = UserRegistryUtil.instance;
	UserDao userDao = UserDao.currentUserImplementation;
	
	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
		System.out.println("Users");
		System.out.println(userDao.findAll());
		System.out.println("User Transactions");
		//for (int i = 0; i < userDao.findAll().size(); i++) {}
		System.out.println(registerUser.getTransactions());
		return new AdminMainPrompt();
	}

}
