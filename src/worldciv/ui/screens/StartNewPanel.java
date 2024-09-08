package worldciv.ui.screens;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import worldciv.main.WorldCiv;
import worldciv.ui.components.Button;

@SuppressWarnings("serial")
public class StartNewPanel extends JPanel {
	
	Random random = new Random();
	JLabel title = new JLabel("Start New");
	
	JLabel worldNameLabel = new JLabel("World name:");
	public JTextField worldName;
	
	JLabel seedLabel = new JLabel("Seed:");
	public JTextField seed;
	
	JLabel sizePickerLabel = new JLabel("Size:");
	public JComboBox<Object> sizePicker;
	String[] sizes = {"Tiny", "Small", "Medium", "Large", "Huge"};
	
	JLabel waterLevelPickerLabel = new JLabel("Water level:");
	public JComboBox<Object> waterLevelPicker;
	String[] waterLevels = {"Low", "Normal", "High"};
	
	JLabel elevationPickerLabel = new JLabel("Elevation:");
	public JComboBox<Object> elevationPicker;
	String[] elevations = {"Low", "Normal", "High"};
	
	JLabel mapTypePickerLabel = new JLabel("Map type:");
	public JComboBox<Object> mapTypePicker;
	String[] mapTypes = {"Pangaea", "Continent and islands", "Archipelago"};
	
	Button generateButton = new Button(Button.NEW_GAME, "Start", "Generate-Button-On-New-Game-Screen");
	Button cancelButton = new Button(Button.NEW_GAME, "Cancel", "Cancel-Button-On-New-Game-Screen");
	
	StartNewPanel() {
		
		setBackground(new Color(40, 65, 159));
		setBounds(0, 0, GameScreen.size.width, GameScreen.size.height);
		setEnabled(true);
		setLayout(null);
		setVisible(false);
		
		//Title
		title.setBounds(GameScreen.size.width / 2 - 140, 100, 500, 100);
		title.setFont(new Font("sans-serif", Font.BOLD, 60));
		title.setForeground(Color.WHITE);
		
		//World name
		worldName = new JTextField(Math.abs(random.nextInt()) + " - v" + WorldCiv.GAME_VERSION);
		worldName.setBounds(GameScreen.size.width / 2 - 250, GameScreen.size.height / 2 - 100, 200, 40);
		worldName.setBackground(new Color(15, 87, 201));
		worldName.setForeground(Color.WHITE);
		worldName.setFont(new Font("sans-serif", Font.BOLD, getFont().getSize() + 2));
		worldName.setBorder(BorderFactory.createLineBorder(new Color(0, 72, 186), 3, true));
		worldName.setCaretColor(Color.WHITE);
		worldNameLabel.setBounds(GameScreen.size.width / 2 - 250, GameScreen.size.height / 2 - 140, 200, 40);
		worldNameLabel.setFont(new Font("sans-serif", Font.PLAIN, 20));
		worldNameLabel.setForeground(Color.WHITE);
		
		//Seed
		seed = new JTextField(Math.abs(random.nextLong()) + "");
		seed.setBounds(GameScreen.size.width / 2 - 250, GameScreen.size.height / 2, 200, 40);
		seed.setBackground(new Color(15, 87, 201));
		seed.setForeground(Color.WHITE);
		seed.setFont(new Font("sans-serif", Font.BOLD, getFont().getSize() + 2));
		seed.setBorder(BorderFactory.createLineBorder(new Color(0, 72, 186), 3, true));
		seed.setCaretColor(Color.WHITE);
		seed.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent e) {
				if (((e.getKeyChar() < '0') || (e.getKeyChar() > '9')) && e.getKeyChar() != java.awt.event.KeyEvent.VK_BACK_SLASH) {
					e.consume();
				}
			}
			
		}); //Only accepts numbers
		seedLabel.setBounds(GameScreen.size.width / 2 - 250, GameScreen.size.height / 2 - 40, 200, 40);
		seedLabel.setFont(new Font("sans-serif", Font.PLAIN, 20));
		seedLabel.setForeground(Color.WHITE);
		
		//Size picker
		sizePicker = new JComboBox<Object>(sizes);
		sizePicker.setBounds(GameScreen.size.width / 2 - 250, GameScreen.size.height / 2 + 100, 200, 40);
		sizePicker.setSelectedIndex(2);
		sizePicker.setBackground(new Color(15, 87, 201));
		sizePicker.setForeground(Color.WHITE);
		sizePicker.setFont(new Font("sans-serif", Font.BOLD, getFont().getSize() + 2));
		sizePicker.setBorder(BorderFactory.createLineBorder(new Color(0, 72, 186), 3, true));
		sizePickerLabel.setBounds(GameScreen.size.width / 2 - 250, GameScreen.size.height / 2 + 60, 200, 40);
		sizePickerLabel.setFont(new Font("sans-serif", Font.PLAIN, 20));
		sizePickerLabel.setForeground(Color.WHITE);
		
		//Water level picker
		waterLevelPicker = new JComboBox<Object>(waterLevels);
		waterLevelPicker.setBounds(GameScreen.size.width / 2 + 50, GameScreen.size.height / 2 - 100, 200, 40);
		waterLevelPicker.setSelectedIndex(1);
		waterLevelPicker.setBackground(new Color(15, 87, 201));
		waterLevelPicker.setForeground(Color.WHITE);
		waterLevelPicker.setFont(new Font("sans-serif", Font.BOLD, getFont().getSize() + 2));
		waterLevelPicker.setBorder(BorderFactory.createLineBorder(new Color(0, 72, 186), 3, true));
		waterLevelPickerLabel.setBounds(GameScreen.size.width / 2 + 50, GameScreen.size.height / 2 - 140, 200, 40);
		waterLevelPickerLabel.setFont(new Font("sans-serif", Font.PLAIN, 20));
		waterLevelPickerLabel.setForeground(Color.WHITE);
		
		//Elevation picker
		elevationPicker = new JComboBox<Object>(elevations);
		elevationPicker.setBounds(GameScreen.size.width / 2 + 50, GameScreen.size.height / 2, 200, 40);
		elevationPicker.setSelectedIndex(1);
		elevationPicker.setBackground(new Color(15, 87, 201));
		elevationPicker.setForeground(Color.WHITE);
		elevationPicker.setFont(new Font("sans-serif", Font.BOLD, getFont().getSize() + 2));
		elevationPicker.setBorder(BorderFactory.createLineBorder(new Color(0, 72, 186), 3, true));
		elevationPickerLabel.setBounds(GameScreen.size.width / 2 + 50, GameScreen.size.height / 2 - 40, 200, 40);
		elevationPickerLabel.setFont(new Font("sans-serif", Font.PLAIN, 20));
		elevationPickerLabel.setForeground(Color.WHITE);
		
		//mapType picker
		mapTypePicker = new JComboBox<Object>(mapTypes);
		mapTypePicker.setBounds(GameScreen.size.width / 2 + 50, GameScreen.size.height / 2 + 100, 200, 40);
		mapTypePicker.setSelectedIndex(0);
		mapTypePicker.setBackground(new Color(15, 87, 201));
		mapTypePicker.setForeground(Color.WHITE);
		mapTypePicker.setFont(new Font("sans-serif", Font.BOLD, getFont().getSize() + 2));
		mapTypePicker.setBorder(BorderFactory.createLineBorder(new Color(0, 72, 186), 3, true));
		mapTypePickerLabel.setBounds(GameScreen.size.width / 2 + 50, GameScreen.size.height / 2 + 60, 200, 40);
		mapTypePickerLabel.setFont(new Font("sans-serif", Font.PLAIN, 20));
		mapTypePickerLabel.setForeground(Color.WHITE);
		
		//Start button
		generateButton.setLocation(GameScreen.size.width / 2 - 50, GameScreen.size.height - 175);
		
		//Cancel button
		cancelButton.setLocation(GameScreen.size.width / 2 - 50, GameScreen.size.height - 100);
		
		//Adding
		add(title);
		add(worldNameLabel);
		add(worldName);
		add(seedLabel);
		add(seed);
		add(sizePickerLabel);
		add(sizePicker);
		add(waterLevelPickerLabel);
		add(waterLevelPicker);
		add(elevationPickerLabel);
		add(elevationPicker);
		add(mapTypePickerLabel);
		add(mapTypePicker);
		add(generateButton);
		add(cancelButton);
		
	}
	
}