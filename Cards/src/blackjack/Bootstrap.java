package blackjack;

import blackjack.Controller;
import blackjack.Model;

public class Bootstrap {
	public Bootstrap() throws Exception {
		Model model = new Model();
		new Controller(model);
		
	}
	
	public static void main(String[] args) throws Exception {
		new Bootstrap();
	}
}