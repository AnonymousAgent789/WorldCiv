package worldciv.logic.generation;

import java.util.HashMap;
import java.util.Random;

public abstract class WorldGenerator {
	
	private static HashMap<Integer, int[]> world = new HashMap<Integer, int[]>(); //X, Y, Terrain (-2 = ocean; -1 = coast; 0 = null; 1 = land; 2 = mountain), Features (0 = null; 1 = hill; 2 = forest; 3 = lake), Resource type, Resource quantity, occupied by unit (-1 = null; else num = ID)
	
	public static HashMap<Integer, int[]> generate(long seed, String sizeString, String waterLevelString, String elevationString, String mapTypeString) {
		
		Random random = new Random(seed);
		
		//Determine variables
		final int size = sizeString == "Tiny" ? 20 : (sizeString == "Small" ? 30 : (sizeString == "Medium" ? 40 : (sizeString == "Large" ? 60 : 80)));
		final int waterLevel = waterLevelString == "Low" ? 150 : (waterLevelString == "Normal" ? 225 : 400);
		final int elevation = elevationString == "Low" ? 30 : (elevationString == "Normal" ? 50 : 80);
		final int mapType = mapTypeString == "Pangaea" ? 10 : (mapTypeString == "Continent and islands" ? 7 : 5);
		
		//Setting up world list
		for (int i = 0; i < size; i++) {
			
			for (int j = 0; j < size; j++) {
				
				int[] tempList = {i, j, 0, 0, 0, 0, -1};
				world.put(i * size + j, tempList);
				
			}
			
		}
		
		//BASE TERRAIN
		
		//Generating landmass
		if (mapTypeString == "Archipelago") {
			
			for (int current = 0; current < world.size(); current++) {
				
				int x = world.get(current)[0];
				int y = world.get(current)[1];
				
				if (x == 0 || y == 0 || x == size - 1 || y == size - 1) { //Edges
					
					world.get(current)[2] = -2;
					
				} else {
					
					if (waterLevelString == "Low") {
						
						world.get(current)[2] = random.nextInt(10) < 7 ? 1 : -2;
						
					}
					if (waterLevelString == "Normal") {
						
						world.get(current)[2] = random.nextInt(10) < 5 ? 1 : -2;
						
					}
					if (waterLevelString == "High") {
		
						world.get(current)[2] = random.nextInt(10) < 3 ? 1 : -2;
		
					}
					
				}
				
			}
			
		} else {
				
			for (int current = 0; current < size * size; current++) {

				int x = world.get(current)[0];
				int y = world.get(current)[1];

				if (x == 0 || y == 0 || x == size - 1 || y == size - 1) { //Edges

					world.get(current)[2] = -2;

				} else {

					//Distance from centre
					double xDistance = Math.abs((x + 0.5) - (size / 2));
					double yDistance = Math.abs((y + 0.5) - (size / 2));
					int distance = (int) Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
					//Max distance
					int maxDistance = (int) Math.sqrt((((size / 2) - 0.5) * ((size / 2) - 0.5)) * 2);
					//Counting adjacent land
					int count = 0;
					if (world.get(current - 1)[2] == 1) { //Above
						count += 20;
					}
					if (world.get(current - size - 1)[2] == 1) { //Above left
						count += 20;
					}
					if (world.get(current + 1 - size)[2] == 1) { //Below Left
						count += 20;
					}
					if (world.get(current - 1)[2] == 1) { //Left
						count += 20;
					}
					//Chance
					double chance = random.nextInt(201 + count);
					if (distance <= maxDistance / (waterLevel / 87.5)) {
						chance *= mapType / 4.0; //Inside land circle
					} else {
						chance *= 5.8 / mapType; //Outside land circle
					}
					int landOrSea = chance < (distance * waterLevel) / maxDistance ? -2 : 1;
					world.get(current)[2] = landOrSea;
				
				}
			}
			
		}
		
		//Adding mountains and lakes and removing islands
		for (int current = 0; current < size * size; current++) {
			
			int x = world.get(current)[0];
			int y = world.get(current)[1];
			
			if (x > 0 && y > 0 && x < size - 1 && y < size - 1) {
				
				//Mountains
				if (world.get(current)[2] == 1 && random.nextInt(100 - elevation) == 0) {
					
					world.get(current)[2] = 2;
					
				}
				
				if (mapTypeString != "Archipelago") { //Lakes
					
					if (world.get(current)[2] < 0 //Current
							&& world.get(current - 1)[2] > 0 //Above
							&& world.get(current - size)[2] > 0 //Left
							&& world.get(current + size)[2] > 0 //Right
							&& world.get(current + 1)[2] > 0) { //Below
							
						if (random.nextInt(2) == 0) {
							
							world.get(current)[2] = 2;
							world.get(current)[3] = 3;
							
						} else {
							
							world.get(current)[2] = 1;
							
						}
						
					}
				}
				
				if (mapTypeString == "Pangaea") { //Islands
					
					if (world.get(current)[2] == 1 //Current
							&& world.get(current - 1)[2] < 0 //Above
							&& world.get(current - size)[2] < 0 //Left
							&& world.get(current + size)[2] < 0 //Right
							&& world.get(current + 1)[2] < 0) { //Below

						world.get(current)[2] = -2;
					
					}
					
				}
				
			}
			
		}
		
		//Adding shore
		for (int current = 0; current < size * size; current++) {
			
			int x = world.get(current)[0];
			int y = world.get(current)[1];
				
			//Shore
			if (x == 0 && y == 0) { //Top-left corner
				
				if (world.get(current + 1 + size)[2] > 0) {
				
					world.get(current)[2] = -1;
				
				}
				
			} else if (x == 0 && y == size - 1) { //Bottom-left corner
				
				if (world.get(current - 1 + size)[2] > 0) {
				
					world.get(current)[2] = -1;
				
				}
				
			} else if (x == size - 1 && y == 0) { //Top-right corner
				
				if (world.get(current + 1 - size)[2] > 0) {
				
					world.get(current)[2] = -1;
				
				}
				
			} else if (x == size - 1 && y == size - 1) { //Bottom-right corner
				
				if (world.get(current - 1 - size)[2] > 0) {
				
					world.get(current)[2] = -1;
				
				}
				
			} else if (x == 0) { //First column
				
				if (world.get(current + size)[2] > 0 || world.get(current - 1 + size)[2] > 0 || world.get(current + 1 + size)[2] > 0) {
					
					world.get(current)[2] = -1;
					
				}
				
			} else if (y == 0) { //First row
				
				if (world.get(current + 1)[2] > 0 || world.get(current + 1 + size)[2] > 0 || world.get(current + 1 - size)[2] > 0) {
					
					world.get(current)[2] = -1;
					
				}
				
			} else if (x == size - 1) { //Last column
				
				if (world.get(current - size)[2] > 0 || world.get(current - 1 - size)[2] > 0 || world.get(current + 1 - size)[2] > 0) {
					
					world.get(current)[2] = -1;
					
				}
				
			} else if (y == size - 1) { //Last row
				
				if (world.get(current - 1)[2] > 0 || world.get(current - 1 + size)[2] > 0 || world.get(current - 1 - size)[2] > 0) {
					
					world.get(current)[2] = -1;
					
				}
				
			} else if (world.get(current)[2] == -2 //Non-edge
						&& (world.get(current - 1)[2] > 0 //Above
								|| world.get(current - size - 1)[2] > 0 //Above left
								|| world.get(current + size - 1)[2] > 0 //Above right
								|| world.get(current - size)[2] > 0 //Left
								|| world.get(current + size)[2] > 0 //Right
								|| world.get(current + 1)[2] > 0 //Below
								|| world.get(current - size + 1)[2] > 0 //Below left
								|| world.get(current + size + 1)[2] > 0)) { //Below right
					
				world.get(current)[2] = -1;
					
			}
			
		}
		
		//TERRAIN FEATURES
		
		for (int current = 0; current < size * size; current++) {
			
			int chance = random.nextInt(20);
			
			if (world.get(current)[2] == 1) {
				
				if (chance < 3) {
					
					world.get(current)[3] = 2; //Forest
					
				} else if (chance < elevation / 10) {
					
					world.get(current)[3] = 1; //Hill
					
				}
				
			}
			
		}
		
		return world;
		
	}
	
}