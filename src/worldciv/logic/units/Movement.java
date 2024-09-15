package worldciv.logic.units;

import java.util.HashMap;
import worldciv.main.Game;

public abstract class Movement {
	
	public static HashMap<Integer, Integer> move(int tileID, int moves) { //TileID, movement cost
		
		HashMap<Integer, Integer> validMovementLocations = new HashMap<Integer, Integer>();
		validMovementLocations.put(tileID, 0);
		
		if (moves > 0) {
			
			for (int i = 0; i < moves; i++) {
				
				HashMap<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
				
				for (int currentTile : validMovementLocations.keySet()) {
				
					checkIndividualTile(currentTile, moves - i, moves).forEach((tile, cost) -> tempMap.merge(tile, cost, (cost1, cost2) -> Math.min(cost1, cost2)));
				
				}
				
				tempMap.forEach((tile, cost) -> validMovementLocations.merge(tile, cost, (cost1, cost2) -> Math.min(cost1, cost2)));

			}
			
		}
		
		validMovementLocations.remove(tileID);
		//remove all where cost biger than movement
		
		return validMovementLocations;
		
	}
	
	private static HashMap<Integer, Integer> checkIndividualTile(int tileID, int moves, int initialMoves) {
		
		HashMap<Integer, Integer> validMovementLocations = new HashMap<Integer, Integer>();
		
		if (Game.WORLD.get(tileID)[2] == 1) {
		
			if (moves == 0) {

				validMovementLocations.put(tileID, initialMoves);

			} else if (moves > 0) {

				if (Game.WORLD.get(tileID - Game.WORLD_WIDTH - 1)[2] == 1 //Check if it is land     //Top-left
						&& (Game.WORLD.get(tileID - Game.WORLD_WIDTH - 1)[3] == 1 ? 2 : 1) <= moves //Check if unit has enough movement due to hill
						&& Game.WORLD.get(tileID - Game.WORLD_WIDTH - 1)[6] == -1) { //Check that tile is unoccupied

					validMovementLocations.put(tileID - Game.WORLD_WIDTH - 1, initialMoves - moves + (Game.WORLD.get(tileID - Game.WORLD_WIDTH - 1)[3] == 1 ? 2 : 1));

				}
				if (Game.WORLD.get(tileID - 1)[2] == 1 //Top
						&& (Game.WORLD.get(tileID - 1)[3] == 1 ? 2 : 1) <= moves
						&& Game.WORLD.get(tileID - 1)[6] == -1) {

					validMovementLocations.put(tileID - 1, initialMoves - moves + (Game.WORLD.get(tileID - 1)[3] == 1 ? 2 : 1));

				}
				if (Game.WORLD.get(tileID + Game.WORLD_WIDTH - 1)[2] == 1 //Top-right
						&& (Game.WORLD.get(tileID + Game.WORLD_WIDTH - 1)[3] == 1 ? 2 : 1) <= moves
						&& Game.WORLD.get(tileID + Game.WORLD_WIDTH - 1)[6] == -1) {

					validMovementLocations.put(tileID + Game.WORLD_WIDTH - 1, initialMoves - moves + (Game.WORLD.get(tileID + Game.WORLD_WIDTH - 1)[3] == 1 ? 2 : 1));

				}
				if (Game.WORLD.get(tileID - Game.WORLD_WIDTH)[2] == 1 //Left
						&& (Game.WORLD.get(tileID - Game.WORLD_WIDTH)[3] == 1 ? 2 : 1) <= moves
						&& Game.WORLD.get(tileID - Game.WORLD_WIDTH)[6] == -1) {

					validMovementLocations.put(tileID - Game.WORLD_WIDTH, initialMoves - moves + (Game.WORLD.get(tileID - Game.WORLD_WIDTH)[3] == 1 ? 2 : 1));

				}
				if (Game.WORLD.get(tileID + Game.WORLD_WIDTH)[2] == 1 //Right
						&& (Game.WORLD.get(tileID + Game.WORLD_WIDTH)[3] == 1 ? 2 : 1) <= moves
						&& Game.WORLD.get(tileID + Game.WORLD_WIDTH)[6] == -1) {

					validMovementLocations.put(tileID + Game.WORLD_WIDTH, initialMoves - moves + (Game.WORLD.get(tileID + Game.WORLD_WIDTH)[3] == 1 ? 2 : 1));

				}
				if (Game.WORLD.get(tileID - Game.WORLD_WIDTH + 1)[2] == 1 //Bottom-left
						&& (Game.WORLD.get(tileID - Game.WORLD_WIDTH + 1)[3] == 1 ? 2 : 1) <= moves
						&& Game.WORLD.get(tileID - Game.WORLD_WIDTH + 1)[6] == -1) {

					validMovementLocations.put(tileID - Game.WORLD_WIDTH + 1, initialMoves - moves + (Game.WORLD.get(tileID - Game.WORLD_WIDTH + 1)[3] == 1 ? 2 : 1));

				}
				if (Game.WORLD.get(tileID + 1)[2] == 1 //Bottom
						&& (Game.WORLD.get(tileID + 1)[3] == 1 ? 2 : 1) <= moves
						&& Game.WORLD.get(tileID + 1)[6] == -1) {

					validMovementLocations.put(tileID + 1, initialMoves - moves + (Game.WORLD.get(tileID + 1)[3] == 1 ? 2 : 1));

				}
				if (Game.WORLD.get(tileID + Game.WORLD_WIDTH + 1)[2] == 1 //Bottom-right
						&& (Game.WORLD.get(tileID + Game.WORLD_WIDTH + 1)[3] == 1 ? 2 : 1) <= moves
						&& Game.WORLD.get(tileID + Game.WORLD_WIDTH + 1)[6] == -1) {

					validMovementLocations.put(tileID + Game.WORLD_WIDTH + 1, initialMoves - moves + (Game.WORLD.get(tileID + Game.WORLD_WIDTH + 1)[3] == 1 ? 2 : 1));

				}

			}
		
		}
		
		return validMovementLocations;
		
	}
	
}