package game;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Weapon {
	
	private Image image;
	private ArrayList<Bullet> bullets;
	private float x;
	private float y;
	private int cooldown;
	
	public Weapon() throws SlickException {
		// TODO Auto-generated constructor stub
		image = new Image("images/player/gun.png");
		bullets = new ArrayList<Bullet>();
	}
	
	public void draw(Graphics g) {
		for (Bullet b : bullets) {
			b.draw(g);
		}
		image.drawCentered(x, y);
	}
	
	public void update(float gunx, float guny, float theta, int delta) {
		image.setRotation(theta);
		gunx += (float) (10 * Math.cos(Math.toRadians(theta - 90)));
		guny += (float) (10 * Math.sin(Math.toRadians(theta - 90)));
		gunx += (float) (20 * Math.cos(Math.toRadians(theta)));
		guny += (float) (20 * Math.sin(Math.toRadians(theta)));
		x = gunx;
		y = guny;
		
		Iterator<Bullet> itr = bullets.iterator();
		while(itr.hasNext()) {
			Bullet b = itr.next();
			b.update(delta);
			if (b.finished()) {
				itr.remove();
			}
		}
		
		if (cooldown > 0) {
			cooldown -= delta;
		}
	}
	
	public void fire() {
		if (cooldown <= 0) {
			float offset = (int)(Math.random()*6 - 3);
			bullets.add(new Bullet(x, y, image.getRotation() - 90 + offset));
			cooldown = 100;
		}
	}
}
