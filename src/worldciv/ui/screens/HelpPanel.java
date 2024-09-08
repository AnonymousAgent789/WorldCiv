package worldciv.ui.screens;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import worldciv.ui.components.Button;

@SuppressWarnings("serial")
public class HelpPanel extends JPanel {
	
	Button closeButton = new Button(Button.HELP_MENU, "Close", "Close-Button-On-Help-Screen");
	JLabel titleLabel = new JLabel("Help");
	JLabel contentLabel = new JLabel("<html>Civilisation spin-off with 0 features made for fun.</html>");
	
	public HelpPanel() {
		
		//Screen
		setBackground(new Color(40, 65, 159));
		setBounds(GameScreen.size.width / 3, GameScreen.size.height / 8, GameScreen.size.width / 3, (int) (GameScreen.size.height * 0.75));
		setEnabled(true);
		setLayout(null);
		setVisible(false);
		
		//Title
		add(titleLabel);
		titleLabel.setFont(new Font("sans-serif", Font.BOLD, 40));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(getWidth() / 2 - 40, -(getHeight() / 2) + 50, getWidth(), getHeight());
		
		//Text
		add(contentLabel);
		contentLabel.setFont(new Font("sans-serif", Font.BOLD, 20));
		contentLabel.setForeground(Color.WHITE);
		contentLabel.setBounds(10, -(getHeight() / 4), getWidth(), getHeight());
		
		//Button
		add(closeButton);
		closeButton.setLocation(getWidth() / 2 - 50, getHeight() - 75);
		
	}
	
}