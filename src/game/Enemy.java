package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy extends Human {
	
	private Weapon weapon;
	private double xvel;
	private double yvel;

	public Enemy(float x, float y) throws SlickException {
		// TODO Auto-generated constructor stub
		super(x, y);
		image = new Image("images/enemy/enemy.png");
		this.weapon = new Weapon();
	}
	
	public void draw(Graphics g) {
		weapon.draw(g);
		super.draw();
	}
	
	public void update(int delta, Player player) {
		float playerx = player.getX();
		float playery = player.getY();
		float xDistance = playerx - location.x;
		float yDistance = playery - location.y;
		float angle = (float)Math.toDegrees(Math.atan2(yDistance, xDistance));
		angle += 90;
		image.setRotation(angle);
		
		xvel = 0;
		yvel = 0;
		
		if (Math.sqrt(yDistance * yDistance + xDistance * xDistance) > 150) {
			float vel = VELOCITY * delta;
			xvel = vel * Math.cos(Math.toRadians(angle - 90));
			yvel = vel * Math.sin(Math.toRadians(angle - 90));
		}
		
		location.x += xvel;
		location.y += yvel;
		weapon.fire();
		weapon.update(location.x, location.y, angle, delta);
	}
	
	public boolean isDead() {
		return false;
	}
}
