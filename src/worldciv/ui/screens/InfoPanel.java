package worldciv.ui.screens;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import worldciv.main.WorldCiv;
import worldciv.ui.components.Button;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel {
	
	Button closeButton = new Button(Button.INFO_MENU, "Close", "Close-Button-On-Info-Screen");
	JLabel titleLabel = new JLabel("Info");
	JLabel contentLabel = new JLabel("<html>Game Version: " + WorldCiv.GAME_VERSION + " (Build: " + WorldCiv.BUILD_NUMBER + ")<br><br><br>All credits belong to Anonymous Agent</html>");
	
	public InfoPanel() {
		
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