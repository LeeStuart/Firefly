package game;

import java.awt.geom.Point2D;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Camera {
	
	private GameContainer container;
	private Map map;
	private Point2D.Float location;

	public Camera(GameContainer container, Map map) {
		// TODO Auto-generated constructor stub
		this.container = container;
		this.map = map;
		location = new Point2D.Float(0, 0);
	}
	
	public void draw(Graphics g) {
		g.translate(-location.x, -location.y);
		map.draw(g);
	}
	
	public void update() {
		Point2D.Float center = new Point2D.Float(container.getWidth() / 2, container.getHeight() / 2);
		Point2D.Float playerLocation = new Point2D.Float(center.x, center.y);
		Point2D.Float size = new Point2D.Float(map.getWidth() - container.getWidth(), map.getHeight() - container.getHeight());
		Player player = map.getPlayer();
		
		location.x = player.getX() - center.x;
		location.y = player.getY() - center.y;
		
		if (location.x > size.x) {
			playerLocation.x -= size.x - location.x;
			location.x = size.x;
		}
		if (location.x < 0) {
			playerLocation.x -= 0 - location.x;
			location.x = 0;
		}
		if (location.y > size.y) {
			playerLocation.y -= size.y - location.y;
			location.y = size.y;
		}
		if (location.y < 0) {
			playerLocation.y -= 0 - location.y;
			location.y = 0;
		}
		
		player.setScreenLocation(playerLocation);
	}

}
