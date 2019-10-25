import Prompts.MainMenuPrompt;
import Prompts.Prompt;

//console line application
//user can login
//user can add/remove bank accounts
//user can deposit/withdraw
//user can view transaction history
//user can logout
//admin can view all users
//admin can view all accounts
public class BankingApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Prompt p = new MainMenuPrompt();
		p.run();

	}

}
