package blackjack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
	// packages
	private Model model;
	private View view;
	
	// card/suit vars
	private String[] cards;
	private String[] suits;
	private int cardNum;
	private int suitNum;
	
	// user info
	private ArrayList<String> currentHand = new ArrayList<String>();
	private int currentScore = 0;
	
	// dealer info
	private ArrayList<String> dealerHand = new ArrayList<String>();
	private int dealerScore = 0;
	
	public Controller(Model model) {
		this.model = model;
		this.cards = model.getCards();
		this.suits = model.getSuits();
		
		this.cardNum = cards.length - 1;
		this.suitNum = suits.length - 1;
		
		resetHand();
		this.view = new View(currentHand);
		addListeners();
	}
	
	private void resetHand() {
		addCard();
		addCard();		
	}
	
	private void addListeners() {
		view.addHitListener(new HitListener());
		view.addStandListener(new StandListener());
	}
	
	private void addCard() {
		Random rand = new Random();
		int card = rand.nextInt(cardNum);
		int suit = rand.nextInt(suitNum);
		boolean inUse = checkDeck(card, suit);
		while(inUse == true) {
			card = rand.nextInt(cardNum);
			suit = rand.nextInt(suitNum);
			inUse = checkDeck(card, suit);
		}
		currentHand.add(cards[card] + suits[suit]);
		currentScore += model.getCardValue(card);
	}
	
	private void addDealerCard(){
		Random rand = new Random();
		int card = rand.nextInt(cardNum);
		int suit = rand.nextInt(suitNum);
		
		boolean inUse = checkDeck(card, suit);
		while(inUse == true) {
			card = rand.nextInt(cardNum);
			suit = rand.nextInt(suitNum);
			inUse = checkDeck(card, suit);
		}
		dealerHand.add(cards[card] + suits[suit]);
		dealerScore += model.getCardValue(card);
	}
	
	private boolean checkDeck(int card, int suit) {
		String suitValue = suits[suit];
		String cardValue = cards[card];
		String cardAndSuit = cardValue + suitValue;
		boolean flag = currentHand.contains(cardAndSuit);
		if (flag == false) {
			flag = dealerHand.contains(cardAndSuit);
		}
		return flag;
	}
	
	private class HitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// if they hit, need to add a new card
			view.reset();
			addCard();
			if (dealerScore < 15 || ((currentScore <= 21 && currentScore >= 15) && dealerScore < currentScore)) {
				addDealerCard();
			}
			view.setHand(currentHand);
			view.start();
			addListeners();
		}

	}		

	
	public class StandListener implements ActionListener {
		// if Stand is clicked, the game is over. Calculate the dealer's moves,
		// figure out who won, and then output the result.
		@Override
		public void actionPerformed(ActionEvent e) {
			while(dealerScore < 15) {
				addDealerCard();
			}
			while (currentScore <= 21 && currentScore >= 15 && dealerScore < currentScore) {
				addDealerCard();
			}
		
			// setup view
			view.reset();
			view.setDealerHand(dealerHand);
			view.renderFinal(dealerScore, currentScore);
			view.start();
		}
	}
}
