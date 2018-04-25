package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {
	
	private Map map;
	private Camera camera;
	private Player player;
	private GameContainer container;

	public Game(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		camera.draw(g);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		player = new Player();
		map = new Map(player, 300, 300, container, "maps/test.xml", this);
		camera = new Camera(container, map);
		this.container = container;
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		//player.update(container, delta);
		map.update(container, delta);
		camera.update();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game("Firefly");
		AppGameContainer container;
		try {
			container = new AppGameContainer(game);
			container.setDisplayMode(800, 600, false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void loadNewMap(String newmap, float playerx, float playery) throws SlickException {
		map = new Map(player, playerx, playery, container, newmap, this);
		camera = new Camera(container, map);
	}

}
