package game;

import org.newdawn.slick.SlickException;

public class Teleport {
	
	private String newmap;
	private float x;
	private float y;
	private float playerx;
	private float playery;

	public Teleport(String newmap, float x, float y, float playerx, float playery) {
		// TODO Auto-generated constructor stub
		this.newmap = newmap;
		this.x = x;
		this.y = y;
		this.playerx = playerx;
		this.playery = playery;
	}
	
	public void update(Player player, Game game) throws SlickException {
		if (player.getX() < x + 40 && player.getX() > x - 40) {
			if (player.getY() < y + 40 && player.getY() > y - 40) {
				game.loadNewMap(newmap, playerx, playery);
			}
		}
	}

}
