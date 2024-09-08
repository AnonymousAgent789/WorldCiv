package worldciv.ui.screens;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameScreen extends JFrame {
	
	public static final int START_MENU_SCREEN = 0;
	public static final int START_NEW_SCREEN = 1;
	public static final int LOAD_GAME_SCREEN = 2;
	public static final int OPTIONS_SCREEN = 3;
	public static final int INFO_SCREEN = 4;
	public static final int HELP_SCREEN = 5;
	public static Dimension size;
	public static StartPanel startPanel;
	public static StartNewPanel startNewPanel;
	public static OptionsPanel optionsPanel;
	public static InfoPanel infoPanel;
	public static HelpPanel helpPanel;
	public static GamePanel gamePanel;
	
	public GameScreen() {
		
		//Setup
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("WorldCiv");
		setResizable(false);
		setLayout(null);
		setVisible(true);
		size = getSize();
		
		//Pages
		startPanel = new StartPanel();
		startNewPanel = new StartNewPanel();
		optionsPanel = new OptionsPanel();
		infoPanel = new InfoPanel();
		helpPanel = new HelpPanel();
		
		//Adding
		add(startNewPanel);
		add(optionsPanel);
		add(infoPanel);
		add(helpPanel);
		add(startPanel);
		repaint();
		
	}
	
}