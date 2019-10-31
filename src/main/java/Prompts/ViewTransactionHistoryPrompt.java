package Prompts;

//import Daos.UserDao;
//import Models.User;
import Util.UserRegistryUtil;

public class ViewTransactionHistoryPrompt implements Prompt{
	private UserRegistryUtil registerUser = UserRegistryUtil.instance;
	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
		//Printing out list of transactions shared among all main menu prompt options
		if (registerUser.getTransactions().size() > 0) {
			registerUser.getCurrentUser();
			System.out.println("Your recent transactions");
			System.out.println(registerUser.getTransactions());
		} else {
			System.out.println("Sorry You have no recent transactions.");
		}
		
		return new MainMenuPrompt();
	}
	//System.out.println("No recent transaction history to report.");
	

}
