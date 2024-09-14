package worldciv.units;

import worldciv.units.classes.Civilian;

public class Explorer extends BaseUnit implements Civilian {

	public Explorer(int tileID, String owner) {
		
		super(tileID, owner, 0, 0);
		health = 10;
		strength = 0;
		moves = 2;
		
	}
	
	@Override
	public void completeAction(String action) {
		
		
		
	}

	@Override
	public void captured(String owner) {
		
		this.owner = owner;
		
	}
	
}