package worldciv.ui.screens;

import java.awt.Color;
import javax.swing.JPanel;
import worldciv.ui.components.Button;

@SuppressWarnings("serial")
public class StartPanel extends JPanel {
	
	public static Button resumeButton = new Button(Button.START_MENU, "↻ Resume", "Resume-Button-On-Start-Screen");
	public static Button startNewButton = new Button(Button.START_MENU, "㊉ Start new", "Start-New-Button-On-Start-Screen");
	public static Button loadGameButton = new Button(Button.START_MENU, "☖ Load game", "Load-Game-Button-On-Start-Screen");
	public static Button optionsButton = new Button(Button.START_MENU, "⚙ Options", "Options-Button-On-Start-Screen");
	public static Button infoButton = new Button(Button.START_MENU, "ⓘ Info", "Info-Button-On-Start-Screen");
	public static Button helpButton = new Button(Button.START_MENU, "❓ Help", "Help-Button-On-Start-Screen");
	
	public StartPanel() {
		
		//Screen
		setBackground(new Color(200, 255, 200));
		setBounds(0, 0, GameScreen.size.width, GameScreen.size.height);
		setEnabled(true);
		setLayout(null);
		setVisible(true);
		
		//Buttons
		buttonSetup();
	}

	private void buttonSetup() {
		
		//Locations
		resumeButton.setLocation(getSize().width / 2 - 320, getSize().height / 2 - 170);
		startNewButton.setLocation(getSize().width / 2 - 320, getSize().height / 2 - 50);
		loadGameButton.setLocation(getSize().width / 2 - 320, getSize().height / 2 + 70);
		optionsButton.setLocation(getSize().width / 2 + 20, getSize().height / 2 - 170);
		infoButton.setLocation(getSize().width / 2 + 20, getSize().height / 2 - 50);
		helpButton.setLocation(getSize().width / 2 + 20, getSize().height / 2 + 70);
		
		//Adding
		buttonEnable();
		
	}
	
	public void buttonEnable() {
		repaint();
		add(resumeButton);
		add(startNewButton);
		add(loadGameButton);
		add(optionsButton);
		add(infoButton);
		add(helpButton);
	}
	
	public void buttonDisable() {
		remove(resumeButton);
		remove(startNewButton);
		remove(loadGameButton);
		remove(optionsButton);
		remove(infoButton);
		remove(helpButton);
	}
	
}