package worldciv.main;

import java.util.HashMap;
import java.util.Random;
import worldciv.logic.generation.WorldGenerator;
import worldciv.ui.screens.GameScreen;
import worldciv.units.*;

public class Game {
	
	//public static List<List<Integer>> WORLD = new ArrayList<List<Integer>>(7);
	public static HashMap<Integer, int[]> WORLD = new HashMap<Integer, int[]>();
	public static HashMap<Integer, BaseUnit> UNITS = new HashMap<Integer, BaseUnit>();
	public static int WORLD_SIZE;
	public static int WORLD_WIDTH;
	public static int UNIT_ID_COUNTER = 0;
	static GameScreen gameScreen;
	static Random random = new Random();
	
	public static void initiate() {
		
		gameScreen = new GameScreen();
		
	}
	
	public static void changeScreen(int current, int next) {
		
		switch(current) {
		
			case GameScreen.START_MENU_SCREEN:
				
				switch(next) {
					
					case GameScreen.START_NEW_SCREEN:
					
						GameScreen.startNewPanel.setVisible(true);
						GameScreen.startPanel.setVisible(false);
						break;
				
					case GameScreen.OPTIONS_SCREEN:
					
						GameScreen.optionsPanel.setVisible(true);
						GameScreen.startPanel.buttonDisable();
						break;
				
					case GameScreen.INFO_SCREEN:
					
						GameScreen.infoPanel.setVisible(true);
						GameScreen.startPanel.buttonDisable();
						break;
				
					case GameScreen.HELP_SCREEN:
						
						GameScreen.helpPanel.setVisible(true);
						GameScreen.startPanel.buttonDisable();
						break;
					
				
				}
				
				break;
			
			case GameScreen.START_NEW_SCREEN:
				
				GameScreen.startNewPanel.setVisible(false);
				GameScreen.startPanel.setVisible(true);
				break;
				
			case GameScreen.OPTIONS_SCREEN:
				
				GameScreen.optionsPanel.setVisible(false);
				GameScreen.startPanel.buttonEnable();
				break;
				
			case GameScreen.INFO_SCREEN:
				
				GameScreen.infoPanel.setVisible(false);
				GameScreen.startPanel.buttonEnable();
				break;
				
			case GameScreen.HELP_SCREEN:
				
				GameScreen.helpPanel.setVisible(false);
				GameScreen.startPanel.buttonEnable();
				break;
				
		}
		
	}
	
	public static void generateNewGame() {
		
		long seed = 0;
		try {
			seed = Long.parseLong(GameScreen.startNewPanel.seed.getText());
		} catch (java.lang.NumberFormatException e) { //Invalid
			seed = random.nextLong();
		}
		WORLD = WorldGenerator.generate(seed, //World seed 
								 (String) GameScreen.startNewPanel.sizePicker.getSelectedItem(), //World size
								 (String) GameScreen.startNewPanel.waterLevelPicker.getSelectedItem(), //Water level
								 (String) GameScreen.startNewPanel.elevationPicker.getSelectedItem(), //Elevation
								 (String) GameScreen.startNewPanel.mapTypePicker.getSelectedItem()); //Map type
		WORLD_SIZE = WORLD.size();
		WORLD_WIDTH = WORLD.get(WORLD_SIZE - 1)[0] + 1;
		GameScreen.startNewPanel.setVisible(false);
		GameScreen.gamePanel.setVisible(true);
		
		int validSpawningLocation = random.nextInt(WORLD_SIZE - 1);
		while (WORLD.get(validSpawningLocation)[2] != 1) {
			
			validSpawningLocation = random.nextInt(WORLD_SIZE - 1);
			
		}
		UNITS.put(UNIT_ID_COUNTER, new Explorer(WORLD.get(validSpawningLocation)[0], WORLD.get(validSpawningLocation)[1], "mada"));
		
		GameScreen.gamePanel.setup();
		
	}

}
