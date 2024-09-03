package worldciv.units;

import java.util.ArrayList;
import worldciv.main.Game;

public class BaseUnit {
	
	public static final String[] UNIT_CLASS = {"Civilian", "Military"};
	public static final String[] UNIT_TYPE = {"Explorer"};
	
	public final int ID;
	public int x;
	public int y;
	public int health;
	public int strength;
	public int moves;
	public String unitClass;
	public String unitType;
	public String owner;
	public ArrayList<String> upgrades = new ArrayList<String>();
	
	BaseUnit(int x, int y, String owner, int type, int uClass) {
		
		this.x = x;
		this.y = y;
		this.owner = owner;
		unitType = UNIT_TYPE[type];
		unitClass = UNIT_CLASS[uClass];
		Game.WORLD.get(x * Game.WORLD_WIDTH + y)[6] = type;
		ID = Game.UNIT_ID_COUNTER;
		Game.UNIT_ID_COUNTER++;
		
	}
	
	public void move(int targetX, int targetY) {

		x = targetX;
		y = targetY;
		
	}

	public void upgrade(String upgrade) {

		upgrades.add(upgrade);
		
	}
	
	public void kill() {
		
		Game.WORLD.get(x * Game.WORLD_WIDTH + y)[6] = -1;
		Game.UNITS.remove(ID);
		
	}
	
}