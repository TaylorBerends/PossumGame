package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame window = new JFrame(); //makes window
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //lets window properly close when user presses the close ('X') button
		window.setResizable(false); //prevents the window from being able to be resizeable
		window.setTitle("2D Adventure Game"); //sets the title of the window
		
		GamePanel gamePanel = new GamePanel(); //creates the game panel
		window.add(gamePanel); //adds game panel to JFrame window
		
		window.pack(); //causes this window to be sized to fit the preferred size and layouts of its subcomponents
		
		window.setLocationRelativeTo(null); //doesn't specify window location of window and will be displayed at center of screen
		window.setVisible(true); //makes window visible
		
		gamePanel.startGameThread();
	}

}
