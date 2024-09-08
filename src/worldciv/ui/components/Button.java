package worldciv.ui.components;

import worldciv.main.Game;
import worldciv.ui.screens.GameScreen;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {
	
	public static final int START_MENU = 0;
	public static final int NEW_GAME = 1;
	public static final int LOAD_GAME = 2;
	public static final int OPTIONS_MENU = 3;
	public static final int INFO_MENU = 4;
	public static final int HELP_MENU = 5;
	
	public Button(int type, String text, String name) {
		
		setText(text);
		setName(name);
		setFocusable(false);
		setFocusPainted(false);
		setBorderPainted(false);
		setBackground(new Color(0, 72, 186, 200));
		setForeground(Color.WHITE);
		addMouseListener(new ScreenChange());
		
		switch(type) {
			
			//Start menu
			case START_MENU:
				setSize(300, 100);
				setFont(new Font("sans-serif", Font.BOLD, 40));
				break;
			
			//New game menu
			case NEW_GAME:
				setSize(100, 50);
				setFont(new Font("sans-serif", Font.BOLD, 20));
				break;
				
			//Info menu
			case INFO_MENU:
				setSize(100, 50);
				setFont(new Font("sans-serif", Font.BOLD, 20));
				break;
				
			//Help menu
			case HELP_MENU:
				setSize(100, 50);
				setFont(new Font("sans-serif", Font.BOLD, 20));
				break;
		
		}
			
	}

	//Mouse interaction
	public class ScreenChange extends java.awt.event.MouseAdapter {
		//Hover in
		public void mouseEntered(java.awt.event.MouseEvent evt) {
			new Thread() {
				@Override
				public void run() {
					for (int i = 200; i < 256; i++) {
						setVisible(false); //To stop transparency issues
						setBackground(new Color(0, 72 - ((i - 200) / 2), 186 - ((i - 200) / 2), i)); //Get darker
						setVisible(true); //To stop transparency issues
						try {
							Thread.sleep(3);
						} catch (InterruptedException e) {}
					}
				}
			}.start();
		}
		
		//Hover out
		public void mouseExited(java.awt.event.MouseEvent evt) {
			new Thread() {
				@Override
				public void run() {
					for (int i = 255; i > 199; i--) {
						setVisible(false); //To stop transparency issues
						setBackground(new Color(0, 72 - ((i - 200) / 2), 186 - ((i - 200) / 2), i)); //Get lighter
						setVisible(true); //To stop transparency issues
						try {
							Thread.sleep(3);
						} catch (InterruptedException e) {}
					}
				}
			}.start();
		}
		
		//Mouse down
		public void mousePressed(java.awt.event.MouseEvent evt) {
			setContentAreaFilled(false); //Stops button highlighting
			setOpaque(true);
			setBackground(getBackground().darker());
		}
		
		//Mouse up
		public void mouseReleased(java.awt.event.MouseEvent evt) {
			setBackground(new Color(0, 45, 159));
		}
		
		//Mouse click
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			setBackground(new Color(0, 72, 186, 200));
			
			switch(getName()) {
				//Resuming game
				case "Resume-Button-On-Start-Screen":
					//Load world
					break;
			
				//Opening new game screen
				case "Start-New-Button-On-Start-Screen":
					Game.changeScreen(GameScreen.START_MENU_SCREEN, GameScreen.START_NEW_SCREEN);
					break;
				
				//Generating new game
				case "Generate-Button-On-New-Game-Screen":
					Game.generateNewGame();
					break;	
				
				//Closing new game screen
				case "Cancel-Button-On-New-Game-Screen":
					Game.changeScreen(GameScreen.START_NEW_SCREEN, GameScreen.START_MENU_SCREEN);
					break;
			
				//Opening load screen
				case "Load-Button-On-Start-Screen":
					Game.changeScreen(GameScreen.START_MENU_SCREEN, GameScreen.LOAD_GAME_SCREEN);
					break;
				
				//Closing load screen
				case "Cancel-Button-On-Load-Game-Screen":
					Game.changeScreen(GameScreen.LOAD_GAME_SCREEN, GameScreen.START_MENU_SCREEN);
					break;
			
				//Opening options screen
				case "Options-Button-On-Start-Screen":
					Game.changeScreen(GameScreen.START_MENU_SCREEN, GameScreen.OPTIONS_SCREEN);
					break;
				
				//Closing options screen
				case "Close-Button-On-Options-Screen":
					Game.changeScreen(GameScreen.OPTIONS_SCREEN, GameScreen.START_MENU_SCREEN);
					break;
			
				//Opening info screen
				case "Info-Button-On-Start-Screen":
					Game.changeScreen(GameScreen.START_MENU_SCREEN, GameScreen.INFO_SCREEN);
					break;
				
				//Closing info screen
				case "Close-Button-On-Info-Screen":
					Game.changeScreen(GameScreen.INFO_SCREEN, GameScreen.START_MENU_SCREEN);
					break;
			
				//Opening help screen
				case "Help-Button-On-Start-Screen":
					Game.changeScreen(GameScreen.START_MENU_SCREEN, GameScreen.HELP_SCREEN);
					break;
				
				//Closing help screen
				case "Close-Button-On-Help-Screen":
					Game.changeScreen(GameScreen.HELP_SCREEN, GameScreen.START_MENU_SCREEN);
					break;
			
			}
		}
	}

}