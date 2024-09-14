package worldciv.logic.generation;

import java.util.Random;
import worldciv.main.Game;

public abstract class Spawner {
	
	public static int findUnitSpawningLocation() {
		
		Random random = new Random();
		int validSpawningLocation = random.nextInt(Game.WORLD_SIZE - 1);
		
		while (Game.WORLD.get(validSpawningLocation)[2] != 1) {
			
			validSpawningLocation = random.nextInt(Game.WORLD_SIZE - 1);
			
		}
		
		return validSpawningLocation;
		
	}
	
}