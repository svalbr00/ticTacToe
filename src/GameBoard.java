package es.unileon.prg1.ticTacToe;
import java.awt.Window;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GameBoard implements ActionListener{

	private int[][] winCombinations = 
			new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // horizontal wins
			{ 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // vertical wins
			{ 0, 4, 8 }, { 2, 4, 6 } // diagonal wins
	};
	
	private Window window;

	private JButton[] buttons;
	
	private Player player1;

	private Player player2;

	private int count;

	public GameBoard(Window window, TicTacToe ticTacToe){
		// Assign the window where the GameBoard will be included
		this.window = window;
		// Creates the array of buttons of the GameBoard
		this.buttons = new JButton[9];
		
		// Creates the nine buttons
		for (int i = 0; i <= 8; i++) {
			// Stores the buttons in the array of buttons
			buttons[i] = new JButton();
			// 
			this.window.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		
		this.count = 0;

	}

	public void setPlayers(Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		Player winner;
		Mark mark;
		
		Object source = actionEvent.getSource();

		count++;

		/* Calculate whose turn it is */
		if (count % 2 == 0) {
			mark = this.player2.getMark();
		} else {
			mark = this.player1.getMark();
		}

		/* Write the mark to the button and deactivate it */
		JButton pressedButton = (JButton) source;
		// Write the mark in both the enabled and disabled modes
		pressedButton.setIcon(mark.getIcon());
		pressedButton.setDisabledIcon(mark.getIcon());
		pressedButton.setEnabled(false);

		/* Show a dialog when game is over */
		if ( isThereAWinner() == true) {
			/* Determine who won */
			if (mark.equals(player1.getMark()))
				winner = player1;
			else
				winner = player2;
			JOptionPane.showMessageDialog(null, winner + " wins the game!");
			int answer = JOptionPane.showConfirmDialog(null, "Start",
					"Do you want to start a new game",
					JOptionPane.YES_NO_OPTION);

			if (answer == JOptionPane.YES_OPTION) {
				clearIt();
			} else {
				JOptionPane.showMessageDialog(null, "Thank you " + this.player1
						+ " and " + this.player2 + " for playing");
				System.exit(0);
			}

		} else if (count == 9) {
			JOptionPane.showMessageDialog(null, "The game was tie!");
			int answer = JOptionPane.showConfirmDialog(null, "Start",
					"Do you want to start a new game",
					JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				clearIt();
			} else {
				JOptionPane.showMessageDialog(null, "Thank you " + this.player1
						+ " and " + this.player2 + " for playing");
				System.exit(0);
			}

		}
		
	}
	
	private boolean isThereAWinner(){
		boolean winner;
		
		winner = false;
		
		for (int i = 0; i <= 7; i++) {
			if ( ( this.buttons[winCombinations[i][0]].getIcon() ==  this.buttons[winCombinations[i][1]].getIcon() ) 
					&&
					( this.buttons[winCombinations[i][1]].getIcon() == this.buttons[winCombinations[i][2]].getIcon() )
					&&
					( this.buttons[winCombinations[i][0]].getIcon() != null) ) {
				winner = true;
			}
		}
	
		return winner;
	}
	
	public void clearIt(){
		// Resets the nine buttons
		for (int i = 0; i <= 8; i++) {
			buttons[i].setIcon(null);
			buttons[i].setEnabled(true);
		}
		this.count = 0;
	}
	
}
