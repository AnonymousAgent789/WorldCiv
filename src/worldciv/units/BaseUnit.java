package worldciv.units;

import java.util.ArrayList;
import worldciv.main.Game;

public class BaseUnit {
	
	public static final String[] UNIT_CLASS = {"Civilian", "Military"};
	public static final String[] UNIT_TYPE = {"Explorer"};
	
	public final int ID;
	public int tileID;
	public int x;
	public int y;
	public int health;
	public int strength;
	public int moves;
	public int unitClass;
	public int unitType;
	public String unitClassString;
	public String unitTypeString;
	public String owner;
	public ArrayList<String> upgrades = new ArrayList<String>();
	
	BaseUnit(int tileID, String owner, int type, int uClass) {
		
		this.tileID = tileID;
		x = Game.WORLD.get(tileID)[0];
		y = Game.WORLD.get(tileID)[1];
		this.owner = owner;
		unitTypeString = UNIT_TYPE[type];
		unitClassString = UNIT_CLASS[uClass];
		ID = Game.UNIT_ID_COUNTER;
		Game.WORLD.get(tileID)[6] = ID;
		Game.UNIT_ID_COUNTER++;
		
	}
	
	public void move(int tileID) {
		
		Game.WORLD.get(this.tileID)[6] = -1;
		Game.WORLD.get(tileID)[6] = ID;
		this.tileID = tileID;
		x = Game.WORLD.get(tileID)[0];
		y = Game.WORLD.get(tileID)[1];
		System.out.println("Unit #" + ID + " has been moved");
		
	}

	public void upgrade(String upgrade) {

		upgrades.add(upgrade);
		
	}
	
	public void kill() {
		
		Game.WORLD.get(tileID)[6] = -1;
		Game.UNITS.remove(ID);
		System.out.println("Unit #" + ID + " has been killed");
		
	}

	public void clicked() {
		
		System.out.println("Unit #" + ID + " has been clicked");
		if (Game.CURRENT_SELECTED_UNIT != ID) {
			
			Game.CURRENT_SELECTED_UNIT = ID;
			System.out.println("The current selected unit is " + Game.CURRENT_SELECTED_UNIT);
			
		} else {
			
			deselect();
			
		}
		
	}
	
	public void deselect() {
		
		Game.CURRENT_SELECTED_UNIT = -1;
		System.out.println("Unit #" + ID + " has been deselected");
		
	}
	
}