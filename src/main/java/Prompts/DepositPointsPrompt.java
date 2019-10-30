package Prompts;

public class DepositPointsPrompt implements Prompt{
	public DepositPointsPrompt() {
		System.out.println("How much money would you like to deposit?");
	}

	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
		return new MainMenuPrompt();
	}
	
}
