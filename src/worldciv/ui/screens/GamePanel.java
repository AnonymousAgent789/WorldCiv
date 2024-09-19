package worldciv.ui.screens;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import worldciv.logic.units.Movement;
import worldciv.main.Game;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	public static int tileSize = 10;
	int centreX;
	int centreY;
	int screenX;
	int screenY;
	int centreXOfFocus;
	int centreYOfFocus;
	int mouseXWhenDown;
	int mouseYWhenDown;
	int relativeMouseX;
	int relativeMouseY;
	int tilesOnScreen;
	boolean mouseDown = false;
	BufferedImage tileSet;
	BufferedImage unitSet;
	java.util.HashMap<Integer, Integer> validMovementLocations = new java.util.HashMap<Integer, Integer>();
	DragAndMoveScreenThread dragAndMoveScreenThread = new DragAndMoveScreenThread();
	
	public GamePanel() {
		
		//Screen
		setBackground(new Color(0, 0, 0));
		setBounds(0, 0, GameScreen.size.width, GameScreen.size.height);
		setEnabled(true);
		setLayout(null);
		setVisible(false);
		
		//Viewpoints
		centreX = getSize().width / 2;
		centreY = getSize().height / 2;
		
		//Tileset
		try {
			tileSet = ImageIO.read(new File("./resources/images/Tiles.png"));
			unitSet = ImageIO.read(new File("./resources/images/Units.png"));
		} catch (IOException e) {e.printStackTrace();}
		
		//Mouse interaction
		addMouseWheelListener(new resizeOnScroll());
		addMouseListener(new DragAndMoveScreen());
		addMouseListener(new ClickScreen());
		
	}
	
	public void setup() {
		
		screenX = Game.WORLD_WIDTH / 2 * tileSize;
		screenY = Game.WORLD_WIDTH / 2 * tileSize;
		centreXOfFocus = screenX;
		centreYOfFocus = screenY;
		relativeMouseX = 0;
		relativeMouseY = 0;
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		tilesOnScreen = 0;
		
		//Background
		g2.setPaint(Color.black);
		g2.fillRect(0, 0, getSize().width, getSize().height);

		for (int i = screenX < centreX ? 0 : (screenX - centreX) / tileSize; i < (screenX > (Game.WORLD_WIDTH - 1) * tileSize - centreX ? Game.WORLD_WIDTH : (screenX / tileSize) + (centreX / tileSize) + 2); i++) { //Only tiles on screen appear
			
			for (int j = screenY < centreY ? 0 : (screenY - centreY) / tileSize; j < (screenY > (Game.WORLD_WIDTH - 1) * tileSize - centreY ? Game.WORLD_WIDTH : (screenY / tileSize) + (centreY / tileSize) + 2); j++) { //Only tiles on screen appear
				
				int current = (int) ((i * Game.WORLD_WIDTH) + j);
				
				//Base terrain
				if (Game.WORLD.get(current)[2] == 2) { //Mountains
					
					g2.drawImage(tileSet.getSubimage(0, 300, 100, 100).getScaledInstance(tileSize, tileSize, BufferedImage.SCALE_FAST), getXOnScreen(Game.WORLD.get(current)[0]), getYOnScreen(Game.WORLD.get(current)[1]), null);
					
				} else if (Game.WORLD.get(current)[2] == 1) { //Land
					
					g2.drawImage(tileSet.getSubimage(0, 0, 100, 100).getScaledInstance(tileSize, tileSize, BufferedImage.SCALE_FAST), getXOnScreen(Game.WORLD.get(current)[0]), getYOnScreen(Game.WORLD.get(current)[1]), null);
					
				} else if (Game.WORLD.get(current)[2] == -1) { //Shore
					
					g2.drawImage(tileSet.getSubimage(0, 200, 100, 100).getScaledInstance(tileSize, tileSize, BufferedImage.SCALE_FAST), getXOnScreen(Game.WORLD.get(current)[0]), getYOnScreen(Game.WORLD.get(current)[1]), null);
					
				} else if (Game.WORLD.get(current)[2] == -2) { //Ocean
					
					g2.drawImage(tileSet.getSubimage(0, 100, 100, 100).getScaledInstance(tileSize, tileSize, BufferedImage.SCALE_FAST), getXOnScreen(Game.WORLD.get(current)[0]), getYOnScreen(Game.WORLD.get(current)[1]), null);
					
				}
				
				//Terrain features
				if (Game.WORLD.get(current)[3] == 1) { //Hill
					
					g2.drawImage(tileSet.getSubimage(0, 400, 100, 100).getScaledInstance(tileSize, tileSize, BufferedImage.SCALE_FAST), getXOnScreen(Game.WORLD.get(current)[0]), getYOnScreen(Game.WORLD.get(current)[1]), null);
					
				} else if (Game.WORLD.get(current)[3] == 2) { //Forest
					
					g2.drawImage(tileSet.getSubimage(0, 500, 100, 100).getScaledInstance(tileSize, tileSize, BufferedImage.SCALE_FAST), getXOnScreen(Game.WORLD.get(current)[0]), getYOnScreen(Game.WORLD.get(current)[1]), null);
					
				} else if (Game.WORLD.get(current)[3] == 3) { //Lake
					
					g2.drawImage(tileSet.getSubimage(0, 600, 100, 100).getScaledInstance(tileSize, tileSize, BufferedImage.SCALE_FAST), getXOnScreen(Game.WORLD.get(current)[0]), getYOnScreen(Game.WORLD.get(current)[1]), null);
					
				}
				
				//Units
				if (Game.WORLD.get(current)[6] >= 0) {
					
					int currentUnitType = Game.UNITS.get(Game.WORLD.get(current)[6]).unitType;
					g2.drawImage(unitSet.getSubimage(Math.floorDiv(currentUnitType, 10) * 100, Math.floorMod(currentUnitType, 10) * 100, 100, 100).getScaledInstance(tileSize, tileSize, BufferedImage.SCALE_FAST), getXOnScreen(Game.WORLD.get(current)[0]), getYOnScreen(Game.WORLD.get(current)[1]), null);
					
				}
				
				//Outline
				g2.setColor(new Color(50, 50, 50, 50));
				g2.drawRect(getXOnScreen(Game.WORLD.get(current)[0]), getYOnScreen(Game.WORLD.get(current)[1]), tileSize, tileSize);
				
				tilesOnScreen++;
				
			}
			
		}
		
		//Unit movement
		if (Game.CURRENT_SELECTED_UNIT != -1) {
			
			validMovementLocations = Movement.move(Game.UNITS.get(Game.CURRENT_SELECTED_UNIT).tileID, Game.UNITS.get(Game.CURRENT_SELECTED_UNIT).moves);
			
			for (Entry<Integer, Integer> i : validMovementLocations.entrySet()) {
				
				g2.setColor(new Color(255, 255, 255, 125));
				g2.fillOval(getXOnScreen(Game.WORLD.get(i.getKey())[0]) + tileSize / 4, getYOnScreen(Game.WORLD.get(i.getKey())[1]) + tileSize / 4, tileSize / 2, tileSize / 2);
				
			}
			
		}
		
		//Crosshair
		g2.setColor(new Color(50, 50, 50, 50));
		g2.drawLine(centreX - 5, centreY, centreX + 5, centreY);
		g2.drawLine(centreX, centreY - 5, centreX, centreY + 5);
		
		//Mouse
		relativeMouseX = getRelativeXFromScreen(MouseInfo.getPointerInfo().getLocation().x);
		relativeMouseY = getRelativeYFromScreen(MouseInfo.getPointerInfo().getLocation().y - 30);

		//Debug
		g2.setColor(new Color(255, 255, 255));
		g2.drawString("Game.WORLD_WIDTH: " + Game.WORLD_WIDTH, 10, 15);
		g2.drawString("Game.WORLD_SIZE: " + Game.WORLD_SIZE, 10, 30);
		g2.drawString("tileSize: " + tileSize, 10, 45);
		g2.drawString("tileOnScreen: " + tilesOnScreen, 10, 60);
		g2.drawString("centreX: " + centreX, 10, 75);
		g2.drawString("centreY: " + centreY, 10, 90);
		g2.drawString("screenX: " + screenX, 10, 105);
		g2.drawString("screenY: " + screenY, 10, 120);
		g2.drawString("centreXOfFocus: " + centreXOfFocus, 10, 135);
		g2.drawString("centreYOfFocus: " + centreYOfFocus, 10, 150);
		g2.drawString("centredTileID: " + getTileIDFromRelativePos(centreXOfFocus, centreYOfFocus), 10, 165);
		g2.drawString("centredTileTerrain: " + Game.WORLD.get(getTileIDFromRelativePos(centreXOfFocus, centreYOfFocus))[2], 10, 180);
		g2.drawString("centredTileFeature: " + Game.WORLD.get(getTileIDFromRelativePos(centreXOfFocus, centreYOfFocus))[3], 10, 195);
		g2.drawString("centredTileOccupied: " + Game.WORLD.get(getTileIDFromRelativePos(centreXOfFocus, centreYOfFocus))[6], 10, 210);
		g2.drawString("relativeMouseX: " + relativeMouseX, 10, 225);
		g2.drawString("relativeMouseY: " + relativeMouseY, 10, 240);
		g2.drawString("getTileIDFromMousePos(): " + getTileIDFromMousePos(), 10, 255);
		g2.drawString("tileUnderMouseTerrain: " + Game.WORLD.get(getTileIDFromMousePos())[2], 10, 270);
		g2.drawString("tileUnderMouseFeature: " + Game.WORLD.get(getTileIDFromMousePos())[3], 10, 285);
		g2.drawString("tileUnderMouseOccupied: " + Game.WORLD.get(getTileIDFromMousePos())[6], 10, 300);
		
	}
	
	int getXOnScreen(int xPosOfObjectRelative) {
		
		return centreX - screenX + (xPosOfObjectRelative * tileSize);
		
	}
	
	int getYOnScreen(int yPosOfObjectRelative) {
		
		return centreY - screenY + (yPosOfObjectRelative * tileSize);
		
	}
	
	int getRelativeXFromScreen(int xOnScreen) {
		
		int x = (xOnScreen - centreX + screenX) / tileSize;
		return x < 0 ? 0 : (x >= Game.WORLD_WIDTH ? Game.WORLD_WIDTH - 1 : x);
		
	}
	
	int getRelativeYFromScreen(int yOnScreen) {
		
		int y = (yOnScreen - centreY + screenY) / tileSize;
		return y < 0 ? 0 : (y >= Game.WORLD_WIDTH ? Game.WORLD_WIDTH - 1 : y);
		
	}
	
	int getTileIDFromRelativePos(int xPosRelative, int yPosRelative) {

		int ID = ((xPosRelative / 10) * Game.WORLD_WIDTH) + (yPosRelative / 10);
		return ID < 0 ? 0 : ID >= Game.WORLD_SIZE ? Game.WORLD_SIZE - 1 : ID; //Out of bounds
		
	}
	
	int getTileIDFromMousePos() {
		
		int ID = relativeMouseX * 40 + relativeMouseY;
		return ID < 0 ? 0 : ID >= Game.WORLD_SIZE ? Game.WORLD_SIZE - 1 : ID; //Out of bounds
		
	}
	
	private class resizeOnScroll implements MouseWheelListener {

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			
			//Mouse info
			int scrolls = e.getWheelRotation();
			
			//Scroll and zoom amount
			if (scrolls < 0 && tileSize < 210 - (-scrolls * 10)) {
				
				tileSize -= scrolls * 10;
				screenX += centreXOfFocus * -scrolls;
				screenY += centreYOfFocus * -scrolls;
				
			}
			
			if (scrolls > 0 && tileSize > 0 + (scrolls * 10)) {
				
				tileSize -= scrolls * 10;
				screenX -= centreXOfFocus * scrolls;
				screenY -= centreYOfFocus * scrolls;
				
			}

			//Out of bounds
			if (screenX < 0) {screenX = 0;}
			if (screenX > tileSize * Game.WORLD_WIDTH) {screenX = (int) (tileSize * Game.WORLD_WIDTH);}
			if (screenY < 0) {screenY = 0;}
			if (screenY > tileSize * Game.WORLD_WIDTH) {screenY = (int) (tileSize * Game.WORLD_WIDTH);}
			
			repaint();
			
		}
		
	}
	
	private class DragAndMoveScreen extends MouseAdapter {
		
		@Override
		public void mousePressed(MouseEvent e) {
			
			dragAndMoveScreenThread.start();
			
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
			mouseDown = false;
			dragAndMoveScreenThread.interrupt();
			dragAndMoveScreenThread = new DragAndMoveScreenThread();
			
		}
		
	}
	
	private class DragAndMoveScreenThread extends Thread {
		
		@Override
		public void run() {
			
			//Mouse info
			mouseDown = true;
			mouseXWhenDown = MouseInfo.getPointerInfo().getLocation().x;
			mouseYWhenDown = MouseInfo.getPointerInfo().getLocation().y;
			int newMouseX;
			int newMouseY;
			
			while (mouseDown) {
				
				newMouseX = MouseInfo.getPointerInfo().getLocation().x;
				newMouseY = MouseInfo.getPointerInfo().getLocation().y;
				
				screenX -= newMouseX - mouseXWhenDown;
				screenY -= newMouseY - mouseYWhenDown;
				mouseXWhenDown = MouseInfo.getPointerInfo().getLocation().x;
				mouseYWhenDown = MouseInfo.getPointerInfo().getLocation().y;
				
				//Out of bounds
				if (screenX < 0) {screenX = 0;}
				if (screenX > tileSize * Game.WORLD_WIDTH - 1) {screenX = (int) (tileSize * Game.WORLD_WIDTH - 1);}
				if (screenY < 0) {screenY = 0;}
				if (screenY > tileSize * Game.WORLD_WIDTH - 1) {screenY = (int) (tileSize * Game.WORLD_WIDTH - 1);}
				
				centreXOfFocus = screenX / (tileSize / 10);
				centreYOfFocus = screenY / (tileSize / 10);
				
				repaint();
				
				try {
					Thread.sleep(33);
				} catch (InterruptedException e) {}
				
			}
			
		}
		
	}
	
	private class ClickScreen extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if (relativeMouseX > 0 && relativeMouseX < Game.WORLD_WIDTH && relativeMouseY > 0 && relativeMouseY < Game.WORLD_WIDTH) { //Within bounds
				
				int tileID = getTileIDFromMousePos();
				
				if (Game.WORLD.get(tileID)[6] > -1) { //If tile occupied by unit
					
					Game.UNITS.get(Game.WORLD.get(tileID)[6]).clicked();
					repaint();
					
				} else if (Game.CURRENT_SELECTED_UNIT != -1) { //If a unit is currently selected
					
					if (validMovementLocations.containsKey(tileID)) { //Move unit
					
						Game.UNITS.get(Game.CURRENT_SELECTED_UNIT).move(tileID);
						Game.UNITS.get(Game.CURRENT_SELECTED_UNIT).moves -= validMovementLocations.get(tileID);
						Game.UNITS.get(Game.CURRENT_SELECTED_UNIT).deselect();
						repaint();
					
					} else {
					
						validMovementLocations.clear();
						Game.UNITS.get(Game.CURRENT_SELECTED_UNIT).deselect();
						repaint();
					
					}
					
				}
				
			}
			
		}
		
	}
	
}