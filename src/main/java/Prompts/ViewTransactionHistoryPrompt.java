package Prompts;

import java.util.List;

import Daos.UserDao;
import Models.Transaction;
//import Models.User;
import Util.UserRegistryUtil;

public class ViewTransactionHistoryPrompt implements Prompt {
	private UserRegistryUtil registerUser = UserRegistryUtil.instance;
	UserDao userDao = UserDao.currentUserImplementation;

	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
		
		//Getting all transactions made by user who is logged in
		List<Transaction> userTransactions = userDao.
				findTransactionByUserId(registerUser.
				getCurrentUser().getId());
		
		if (userTransactions.size() > 0) {
			System.out.println("Recent transactions for " 
					+ registerUser.getCurrentUser().getName());
			System.out.println(userTransactions);
		//If there are no transactions to report this print statement
		//is issued
		} else {
			System.out.println("Sorry You have no recent "
					+ "transactions to report.");
		}
		
		//always return to main menu
		return new MainMenuPrompt();
	}

}
