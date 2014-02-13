package blackjack;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame {
	
	private static final long serialVersionUID = 1L;
	// vars
	int points = 0;
	
	private Container panel;
	// Buttons
	private JPanel buttons = new JPanel();
	private JButton hitBtn = new JButton("Hit");
	private JButton standBtn = new JButton("Stand");
	private JButton doubleBtn = new JButton("Double");
	private JButton surrenderBtn = new JButton("Surrender");
	
	// Hand
	private JPanel handPnl = new JPanel();
	private ArrayList<String> hand;
	
	// dealer
	private JPanel dealerPnl = new JPanel();
	private ArrayList<String> dealerHand;
	
	private JPanel topPnl = new JPanel();
	
	public void reset() {
		handPnl.removeAll();
		panel.removeAll();

		for (ActionListener al : hitBtn.getActionListeners()) {
			hitBtn.removeActionListener(al);
		}
		for (ActionListener al : standBtn.getActionListeners()) {
			standBtn.removeActionListener(al);
		}
	}
	
	public void renderButtons() {
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttons.add(hitBtn);
		buttons.add(standBtn);
		buttons.add(doubleBtn);
		buttons.add(surrenderBtn);
		panel.add(buttons, BorderLayout.CENTER);
	}
	
	public void renderHand() {
		handPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
		for(int i = 0; i < hand.size(); i++) {
			JLabel cardLbl = new JLabel();
			cardLbl.setIcon(new ImageIcon("res/" + hand.get(i) + ".png"));
			handPnl.add(cardLbl);
		}
		panel.add(handPnl, BorderLayout.SOUTH);
	}
	
	public void renderFinal(int dealerScore, int userScore) {
		topPnl.setLayout(new GridLayout(2,1));
		JPanel scorePnl = new JPanel();
		scorePnl.setLayout(new FlowLayout(FlowLayout.CENTER));
		dealerPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		for(int i = 0; i < dealerHand.size(); i++) {
			JLabel cardLbl = new JLabel();
			cardLbl.setIcon(new ImageIcon("res/" + dealerHand.get(i) + ".png"));
			dealerPnl.add(cardLbl);
		}
		scorePnl.add(new JLabel("Your Score: " + userScore + " vs Dealer Score: " + dealerScore));
		String msg = new String();
		if ((userScore > dealerScore && userScore <= 21) || (dealerScore > 21 && userScore < dealerScore)) {
			msg = "You win!";
		} else {
			msg = "You lose.";
		}
		scorePnl.add(new JLabel(msg));
		topPnl.add(scorePnl, BorderLayout.NORTH);
		topPnl.add(dealerPnl, BorderLayout.SOUTH);
		panel.add(topPnl, BorderLayout.NORTH);
	}
	
	public void setHand(ArrayList<String> newHand) {
		hand = new ArrayList<String>();
		hand = newHand;
	}
	public void setDealerHand(ArrayList<String> hand) {
		dealerHand = new ArrayList<String>();
		dealerHand = hand;
	}

	public void setPoints(int points) { this.points = points; }
	
	public void start() {
		renderButtons();
		renderHand();

		pack();
		setVisible(true);
	}
	public View(ArrayList<String> currentHand) {
		setHand(currentHand);
		panel = this.getContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Black Jack");

		start();
	}
	
	public void addHitListener(ActionListener hal) {
		hitBtn.addActionListener(hal);
	}
	public void addStandListener(ActionListener sal) {
		standBtn.addActionListener(sal);
	}
}
