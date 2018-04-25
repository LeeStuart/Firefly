package game;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Map {
	
	private Image background;
	private Player player;
	private GameContainer container;
	private ArrayList<Teleport> teleports;
	private ArrayList<Enemy> enemies;
	private Game game;

	public Map(Player player, float playerx, float playery, GameContainer container, String xmlfile, Game game) throws SlickException {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.player = player;
		this.container = container;
		teleports = new ArrayList<Teleport>();
		enemies = new ArrayList<Enemy>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(xmlfile);
			dom.getDocumentElement().normalize();
			background = new Image(dom.getElementsByTagName("background").item(0).getTextContent());
			parseTeleports(dom.getElementsByTagName("teleport"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		player.load(playerx, playery, this);
	}
	
	private void parseTeleports(NodeList teleports) {
		for (int i = 0; i < teleports.getLength(); i++) {
			Node n = teleports.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) n;
				String newmap = e.getElementsByTagName("newmap").item(0).getTextContent();
				float x = Float.parseFloat(e.getElementsByTagName("x").item(0).getTextContent());				
				float y = Float.parseFloat(e.getElementsByTagName("y").item(0).getTextContent());
				float playerx = Float.parseFloat(e.getElementsByTagName("playerx").item(0).getTextContent());
				float playery = Float.parseFloat(e.getElementsByTagName("playery").item(0).getTextContent());
				this.teleports.add(new Teleport(newmap, x, y, playerx, playery));
			}
		}
	}
	
	public void draw(Graphics g) {
		background.draw(0, 0);
		for (Enemy e: enemies) {
			e.draw(g);
		}
		player.draw(g, container);
	}
	
	public void update(GameContainer container, int delta) throws SlickException {
		player.update(container, delta);
		Iterator<Enemy> itr = enemies.iterator();
		while (itr.hasNext()) {
			Enemy e = itr.next();
			e.update(delta, player);
			if (e.isDead()) {
				itr.remove();
			}
		}
		for (Teleport t: teleports) {
			t.update(player, game);
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public int getWidth() {
		return background.getWidth();
	}
	
	public int getHeight() {
		return background.getHeight();
	}
	
	public void spawnEnemy() throws SlickException {
		enemies.add(new Enemy(500, 500));
	}

}
