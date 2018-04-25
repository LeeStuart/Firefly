package game;

import java.awt.geom.Point2D;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Human {

	protected final static float VELOCITY = 0.1f;
	protected Image image;
	protected Point2D.Float location;
	
	public Human() {
		location = new Point2D.Float(0, 0);
	}

	public Human(float x, float y) throws SlickException {
		// TODO Auto-generated constructor stub
		location = new Point2D.Float(x, y);
	}
	
	public void draw() {
		image.drawCentered(location.x, location.y);
	}
	
	public void update(int delta) {
		
	}
	
	public float getX() {
		return location.x;
	}
	
	public float getY() {
		return location.y;
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}
}
