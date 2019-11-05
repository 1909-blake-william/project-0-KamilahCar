package Prompts;

import Daos.UserDao;
//import Util.UserRegistryUtil;

public class ViewAllTransactionHistoryPrompt implements Prompt{
	//private UserRegistryUtil registerUser = UserRegistryUtil.instance;
	UserDao userDao = UserDao.currentUserImplementation;
	
	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
		System.out.println("User transactions: ");
		System.out.println(userDao.findAllTransactions());
		//always return to main menu
		return new AdminMainPrompt();
	}

}
