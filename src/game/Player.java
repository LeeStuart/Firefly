package game;

import java.awt.geom.Point2D;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Human {
	
	private boolean alternateControl;
	private Weapon weapon;
	private Map map;
	private Point2D.Float screenLocation;

	public Player() throws SlickException {
		// TODO Auto-generated constructor stub
		screenLocation = new Point2D.Float(0, 0);
		image = new Image("images/player/player.png");
		alternateControl = false;
		weapon = new Weapon();
	}
	
	public void load(float x, float y, Map map) {
		location.x = x;
		location.y = y;
		this.map = map;
	}
	
	public void draw(Graphics g, GameContainer container) {
		weapon.draw(g);
		image.drawCentered(location.x, location.y);
		g.drawString(Float.toString(location.x), location.x + 50, location.y + 50);
	}
	
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		float mouseX = input.getMouseX();
		float mouseY = input.getMouseY();
		float xDistance = mouseX - screenLocation.x;
		float yDistance = mouseY - screenLocation.y;
		double angle = Math.toDegrees(Math.atan2(yDistance, xDistance));
		angle += 90;
		image.setRotation((float)angle);
		
		float vel = VELOCITY * delta;		
		Point2D.Float vector = checkMovement(container);
		Point2D.Float velocity = move(vector, vel);
		
//		if (alternateControl) {
//			angle -= 90;
//			if (input.isKeyDown(Input.KEY_W)) {
//				velocity.x += vel * Math.cos(Math.toRadians(angle));
//				velocity.y += vel * Math.sin(Math.toRadians(angle));
//			}
//			if (input.isKeyDown(Input.KEY_A)) {
//				velocity.x += vel * Math.cos(Math.toRadians(angle - 90));
//				velocity.y += vel * Math.sin(Math.toRadians(angle - 90));
//			}
//			if (input.isKeyDown(Input.KEY_S)) {
//				velocity.x += vel * Math.cos(Math.toRadians(angle - 180));
//				velocity.y += vel * Math.sin(Math.toRadians(angle - 180));
//			}
//			if (input.isKeyDown(Input.KEY_D)) {
//				velocity.x += vel * Math.cos(Math.toRadians(angle - 270));
//				velocity.y += vel * Math.sin(Math.toRadians(angle - 270));
//			}
//			angle += 90;
//		} else {
//			if (input.isKeyDown(Input.KEY_W)) {
//				velocity.y -= vel;
//			}		
//			if (input.isKeyDown(Input.KEY_A)) {
//				velocity.x -= vel;
//			}
//			if (input.isKeyDown(Input.KEY_S)) {
//				velocity.y += vel;
//			}
//			if (input.isKeyDown(Input.KEY_D)) {
//				velocity.x += vel;
//			}
//			if (Math.abs(velocity.x) == Math.abs(velocity.y)) {
//				velocity.x = velocity.x * Math.cos(Math.toRadians(45));
//				velocity.y = velocity.y * Math.cos(Math.toRadians(45));
//			}
//		}
		
		location.x += velocity.x;
		location.y += velocity.y;
		checkBounds(velocity.x, velocity.y);
		
		if (input.isKeyPressed(Input.KEY_N)){ 
			map.spawnEnemy();
		}
		
		weapon.update(location.x, location.y, (float)angle, delta);
		
		if (input.isMouseButtonDown(0)) {
			weapon.fire();
		}
		
		if (input.isKeyPressed(Input.KEY_E)) {
			location.x = 300;
			location.y = 300;
		}
		
		if (input.isKeyPressed(Input.KEY_Z)) {
			alternateControl = !alternateControl; 
		}
	}
	
	private Point2D.Float checkMovement(GameContainer container) {
		Input input = container.getInput();
		Point2D.Float vector = new Point2D.Float(0, 0);
		
		if (input.isKeyDown(Input.KEY_W)) { 
			vector.y += -1;
		}
		if (input.isKeyDown(Input.KEY_A)) {
			vector.x += -1;
		}
		if (input.isKeyDown(Input.KEY_S)) {
			vector.y += 1;
		}
		if (input.isKeyDown(Input.KEY_D)) {
			vector.x += 1;
		}
		
		return vector;
	}
	
	private Point2D.Float move(Point2D.Float vector, float vel) {
		Point2D.Float velocity = new Point2D.Float(0, 0);
		
		if (!alternateControl) {
			if (Math.abs(vector.x) == Math.abs(vector.y)) {
				vector.x = vector.x * (float)Math.cos(Math.toRadians(45));
				vector.y = vector.y * (float)Math.cos(Math.toRadians(45));
			}
			velocity.x = vector.x * vel;
			velocity.y = vector.y * vel;
		}
		
		return velocity;
	}
	
	public void setScreenLocation(Point2D.Float screenLocation) {
		this.screenLocation = screenLocation;
	}
	
	private void checkBounds(double xvel, double yvel) {
		if (location.x - (image.getWidth() / 2) < 0 || location.x + (image.getWidth() / 2) > map.getWidth()) {
			location.x -= xvel;
			xvel = 0;
		}
		if (location.y - (image.getHeight() / 2) < 0 || location.y + (image.getHeight() / 2) > map.getHeight()) {
			location.y -= yvel;
			yvel = 0;
		}
	}
}
