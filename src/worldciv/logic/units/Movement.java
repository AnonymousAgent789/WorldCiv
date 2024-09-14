package worldciv.logic.units;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import worldciv.main.Game;

public abstract class Movement {
	
	public static ArrayList<Integer> move(int tileID, int moves) {
		
		ArrayList<Integer> validMovementLocations = new ArrayList<Integer>();
		
		if (moves == 0) {
			
			validMovementLocations.add(tileID);
			
		} else if (moves > 0) {
			
			if (Game.WORLD.get(tileID - Game.WORLD_WIDTH - 1)[2] == 1) { //Top-left
				
				validMovementLocations.addAll(move(tileID - Game.WORLD_WIDTH - 1, moves - (Game.WORLD.get(tileID - Game.WORLD_WIDTH - 1)[3] == 1 ? 2 : 1)));
				
			}
			if (Game.WORLD.get(tileID - 1)[2] == 1) { //Top
				
				validMovementLocations.addAll(move(tileID - 1, moves - (Game.WORLD.get(tileID - 1)[3] == 1 ? 2 : 1)));
				
			}
			if (Game.WORLD.get(tileID + Game.WORLD_WIDTH - 1)[2] == 1) { //Top-right
				
				validMovementLocations.addAll(move(tileID + Game.WORLD_WIDTH - 1, moves - (Game.WORLD.get(tileID + Game.WORLD_WIDTH - 1)[3] == 1 ? 2 : 1)));
				
			}
			if (Game.WORLD.get(tileID - Game.WORLD_WIDTH)[2] == 1) { //Left
				
				validMovementLocations.addAll(move(tileID - Game.WORLD_WIDTH, moves - (Game.WORLD.get(tileID - Game.WORLD_WIDTH)[3] == 1 ? 2 : 1)));
				
			}
			if (Game.WORLD.get(tileID + Game.WORLD_WIDTH)[2] == 1) { //Right
				
				validMovementLocations.addAll(move(tileID + Game.WORLD_WIDTH, moves - (Game.WORLD.get(tileID + Game.WORLD_WIDTH)[3] == 1 ? 2 : 1)));
				
			}
			if (Game.WORLD.get(tileID - Game.WORLD_WIDTH + 1)[2] == 1) { //Bottom-left
				
				validMovementLocations.addAll(move(tileID - Game.WORLD_WIDTH + 1, moves - (Game.WORLD.get(tileID - Game.WORLD_WIDTH + 1)[3] == 1 ? 2 : 1)));
				
			}
			if (Game.WORLD.get(tileID + 1)[2] == 1) { //Bottom
				
				validMovementLocations.addAll(move(tileID + 1, moves - (Game.WORLD.get(tileID + 1)[3] == 1 ? 2 : 1)));
				
			}
			if (Game.WORLD.get(tileID + Game.WORLD_WIDTH + 1)[2] == 1) { //Bottom-right
				
				validMovementLocations.addAll(move(tileID + Game.WORLD_WIDTH + 1, moves - (Game.WORLD.get(tileID + Game.WORLD_WIDTH + 1)[3] == 1 ? 2 : 1)));
				
			}
			
			Set<Integer> set = new HashSet<Integer>(validMovementLocations);
			validMovementLocations.clear();
			validMovementLocations.addAll(set);
			set.clear();
			
		}
		
		return validMovementLocations;
		
	}
	
}