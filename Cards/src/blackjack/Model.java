package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Model {
	List<Integer> cardVals = new ArrayList<Integer>();
	String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	String[] suits = {"H", "D", "C", "S"};
	public Model() {
		int cValue = 2;
		for(String card : cards) {
			if (card == "J" || card == "Q" || card == "K") {
				cValue = 10;
			} else if (card == "A") {
				cValue = 11;
			} else {
				cValue = Integer.parseInt(card);
			}
			
			cardVals.add(cValue);
		}
	}
	
	public String[] getCards() { return cards; }
	public String getCard(int key) { return cards[key]; }
	
	public String getSuit(int key) { return suits[key]; }
	public String[] getSuits() { return suits; }
	
	public Integer getCardValue(int key) {
		return cardVals.get(key);
	}
}
