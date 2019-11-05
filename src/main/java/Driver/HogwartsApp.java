package Driver;



import Prompts.Prompt;
import Prompts.LogInPrompt;

//console line application
//user can login
//user can add/remove wizard accounts
//user can deposit/withdraw
//user can view transaction history
//user can logout
//admin can view all users
//admin can view all accounts
public class HogwartsApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Prompt p = new LogInPrompt();
		while(true) {
			p = p.run();
		}
		

	}

}
