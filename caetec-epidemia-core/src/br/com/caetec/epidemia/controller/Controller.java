package br.com.caetec.epidemia.controller;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import br.com.caetec.epidemia.controller.firegun.ShotLine;
import br.com.caetec.epidemia.controller.move.Move;
import br.com.caetec.epidemia.controller.move.MoveNPC;
import br.com.caetec.epidemia.field.FieldView;
import br.com.caetec.epidemia.field.Tile;
import br.com.caetec.epidemia.graphics.ImageStore;
import br.com.caetec.epidemia.metrics.Position;
import br.com.caetec.epidemia.people.Humanoid;
import br.com.caetec.epidemia.people.PlayerCharacter;
import br.com.caetec.epidemia.people.npc.Zombie;


public class Controller
{
	private static int time;
	
	public static PlayerCharacter player;
	public static Zombie zombieTest;
	public static List<AutoRender> toRenderList = new ArrayList<AutoRender>();
	
	public static void load()
	{
		player = new PlayerCharacter();
		player.setPosition(PlayerCharacter.playerPos);
		player.setMove(new Move(player.getPosition(), player.getPixelPos()));

		zombieTest = new Zombie();
		zombieTest.setPosition(new Position(18, 16));
		zombieTest.setMove(new MoveNPC(zombieTest.getPosition(), zombieTest.getPixelPos()));
	}

	public static void renderPeople(Graphics g)
	{
		g.drawImage(player.getGraphic().getImage(), 1024 / 2, (640 / 2) - 32, null);

		Tile tileZombie = FieldView.getInstance().mapTiles.get(zombieTest.getPosition().getKey());

		g.drawImage(ImageStore.getImage("zombie_teste"), tileZombie.getPixelPos().getX()
				+ zombieTest.getPixelPos().getX() + 4, zombieTest.getPixelPos().getY()
				+ tileZombie.getPixelPos().getY() + 4, null);

	}

	public static void execute()
	{
		player.getMove().move();
		zombieTest.getMove().move();
	}

	public static void updateTime(int time)
	{
		Controller.time += time;
		player.getGraphic().update(time);
		player.getMove().update(time);
		zombieTest.getMove().update(time);
	}

	public static void verifyPixelPos(Humanoid human)
	{
		Position pp = human.getPixelPos();
		Position tp = human.getPosition();

		int acceptable = Tile.size / 2;

		if (human instanceof Zombie)
			acceptable = Tile.size;

		if (pp.getX() > acceptable)
		{
			tp.add('x', 1);
			pp.add('x', -Tile.size);
			human.getMove().tileChangeListener();
		}

		if (pp.getX() < -acceptable)
		{
			tp.add('x', -1);
			pp.add('x', Tile.size);
			human.getMove().tileChangeListener();
		}

		if (pp.getY() > acceptable)
		{
			tp.add('y', 1);
			pp.add('y', -Tile.size);
			human.getMove().tileChangeListener();
		}

		if (pp.getY() < -acceptable)
		{
			tp.add('y', -1);
			pp.add('y', Tile.size);
			human.getMove().tileChangeListener();
		}
	}
	
	public static void shot(Position target)
	{
		ShotLine shotLine = new ShotLine();
		
		Position playerPos = FieldView.getInstance().mapTiles.get(
				PlayerCharacter.playerPos.getKey()).getPixelPos().getNewPosition();
		
		
		playerPos.add('x', Tile.size / 2);
		playerPos.add('y', Tile.size / 2);
		
		shotLine.calculeShot(playerPos, target);
		Controller.toRenderList.add(shotLine);
	}
	
	
}
