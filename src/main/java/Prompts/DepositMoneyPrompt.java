package Prompts;

public class DepositMoneyPrompt implements Prompt{
	public DepositMoneyPrompt() {
		System.out.println("How much money would you like to deposit?");
	}

	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
		return new MainMenuPrompt();
	}
	
}
