package game;

import org.newdawn.slick.Graphics;

public class Bullet {
	
	private final static float VELOCITY = 0.8f;
	private float initialx;
	private float initialy;
	private float x;
	private float y;
	private float oldx;
	private float oldy;
	private double angle;

	public Bullet(float x, float y, double angle) {
		initialx = x;
		initialy = y;
		oldx = x;
		oldy = y;
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	public void draw(Graphics g) {
		g.drawLine(oldx, oldy, x, y);
	}
	
	public void update(int delta) {
		float vel = VELOCITY * delta;
		double xvel = vel * Math.cos(Math.toRadians(angle));
		double yvel = vel * Math.sin(Math.toRadians(angle));
		x += xvel;
		y += yvel;
		double xdistance = x - oldx;
		double ydistance = y - oldy;
		if (Math.sqrt(xdistance * xdistance + ydistance * ydistance) > 50) {
			oldx += xvel;
			oldy += yvel;
		}
	}
	
	public boolean finished() {
		double xdistance = x - initialx;
		double ydistance = y - initialy;
		if (Math.sqrt(xdistance * xdistance + ydistance * ydistance) > 2000) {
			return true;
		}
		return false;
	}
}
