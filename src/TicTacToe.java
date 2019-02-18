package es.unileon.prg1.ticTacToe;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TicTacToe implements ActionListener {
	
	private JFrame window;
	
	private GameBoard gameBoard;
	
	private Player player1;

	private Player player2;
	
	private JMenuBar menu;

	private JMenuItem newGame;

	private JMenuItem name;

	private JMenuItem instr;

	private JMenuItem exit;

	public TicTacToe() {
		// Create Window
		this.window = new JFrame("Tic-Tac-Toe");
		this.window.setSize(370, 370);
		this.window.setLocationRelativeTo(null);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setLayout(new GridLayout(3, 3));

		// Add Buttons To The Window
		this.gameBoard = new GameBoard(this.window, this);

		this.newGame = new JMenuItem("New Game");
		this.name = new JMenuItem("Change Name");
		this.instr = new JMenuItem("Instructions");
		this.exit = new JMenuItem("Exit");
		
		this.menu = new JMenuBar();
		
		this.menu.add(this.newGame);
		this.menu.add(this.name);
		this.menu.add(this.instr);
		this.menu.add(this.exit);

		this.newGame.addActionListener(this);
		this.name.addActionListener(this);
		this.instr.addActionListener(this);
		this.exit.addActionListener(this);
		
		this.window.setJMenuBar(this.menu);
	}
	
	public void play(){
		// Make The Window Visible
		this.window.setVisible(true);
		// Create the players only the first time
		if ( (this.player1 == null) && (this.player2 == null) ) {
			this.setPlayers();
		} else {
			this.gameBoard.setPlayers(player1, player2);
		}
	}
	
	public void setPlayers() {
		String player1Name, player2Name;
		
		// JOptionPane belongs to javax.swing
		player1Name = JOptionPane.
				showInputDialog(null, "Enter Name of player 1: ", "", 1);
		player2Name = JOptionPane.
				showInputDialog(null, "Enter Name of player 2: ", "", 1);
		// If the name introduced for the first player is null or empty
		// then the name "1" is assigned to this player
		if ( (player1Name == null) || (player1Name.length() == 0) ) {
			player1Name = "1";
		}
		// If the name introduced for the second player is null or empty
		// then the name "2" is assigned to this player
		if ( (player2Name == null) || (player2Name.length() == 0) ) {
			player2Name = "2";
		}
		JOptionPane
				.showMessageDialog(
						null,
						"Your names have been set\nTo change your names click on the Change name button in the menu bar",
						"Name Changed!!!", JOptionPane.INFORMATION_MESSAGE);

		this.player1 = new Player(
				player1Name, 
				new Mark(
						new ImageIcon("etc/images/chrome_100x100.jpg")
						) 
				);
		this.player2 = new Player(
				player2Name, 
				new Mark(
						new ImageIcon("etc/images/firefox_100x100.jpg")
						)
				);
		
		this.gameBoard.setPlayers(player1, player2);
	}

	public void actionPerformed(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();

		if (source == this.newGame ) {
			int answer = JOptionPane
					.showConfirmDialog(
							null,
							"Your current game will not be saved...\nContinue Anyways??",
							"Do you want to start a new game?",
							JOptionPane.YES_NO_OPTION);

			if (answer == JOptionPane.YES_OPTION) {
				this.gameBoard.clearIt();
			}

		} else if (source == this.name) {
			this.setPlayers();

		} else if (source == this.instr) {
			JOptionPane
					.showMessageDialog(
							null,
							"Your goal is to be the first player to get 3 X's or O's in a row. (horizontally, diagonally, or vertically)",
							"Instructions", JOptionPane.INFORMATION_MESSAGE);
		} else if (source == this.exit) {
			int answer = JOptionPane.showConfirmDialog(null, "EXIT",
					"Are You sure you want to exit??",
					JOptionPane.YES_NO_OPTION);

			if (answer == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "Thank you " + this.player1 + " and "
						+ this.player2 + " for playing");
				System.exit(0);
			}
		}
	}

}
