package br.com.caetec.epidemia.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JPanel;

import br.com.caetec.epidemia.controller.AutoRender;
import br.com.caetec.epidemia.controller.Clock;
import br.com.caetec.epidemia.controller.Controller;
import br.com.caetec.epidemia.controller.GameCore;
import br.com.caetec.epidemia.controller.Mouse;
import br.com.caetec.epidemia.field.FieldView;
import br.com.caetec.epidemia.field.Tile;
import br.com.caetec.epidemia.graphics.ImageStore;
import br.com.caetec.epidemia.graphics.ShadowManager;
import br.com.caetec.epidemia.system.ErrorManager;


public class GamePanel extends JPanel
{
	private static final long serialVersionUID = -1809568356243249099L;

	private void renderAll(Graphics g)
	{

		clearPanel(g);
		FieldView.getInstance().render(g);
		Controller.renderPeople(g);
		ShadowManager.render((Graphics2D) g);
		drawListToRender(g);
		drawCursor(g);
		Clock.render(g);

		// loadField(); depois quando o server tiver pronto por esse kra na
		// ativa

	}

	private void drawListToRender(Graphics g)
	{
		for(AutoRender auto : Controller.toRenderList)
			auto.render(g);
	}

	private void drawCursor(Graphics g)
	{
		this.setCursor(Mouse.getFakeCursor());
		Point p = MouseInfo.getPointerInfo().getLocation();

		g.drawImage(ImageStore.getImage("crosshair1"), new Double(p.getX()).intValue() - Tile.size
				/ 2, new Double(p.getY()).intValue() - Tile.size / 2, null);
	}

	private void clearPanel(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1024, 640);
	}

	public void render()
	{
		this.repaint();
	}

	public void paint(Graphics g)
	{
		try
		{
			if (!GameCore.isRunning())
				return;

			renderAll(g);
		}
		catch (Exception e)
		{
			ErrorManager.printAndExit(e);
		}
	}

	// private void loadField()
	// {
	// if (!Keyboard.moving)
	// return;
	//
	// if (fieldView.isFieldUpOver())
	// {
	// fieldView.getPosition().add('y', -Tile.size);
	// List<FieldLine> lista = new ArrayList<FieldLine>();
	//
	// lista.add(new FieldLine(newCode));
	// for (FieldLine line : fieldView.getLines())
	// {
	// lista.add(line);
	// }
	// lista.remove(lista.size() - 1);
	// fieldView.setLines(lista);
	// }
	//
	// if (fieldView.isFieldDownOver())
	// {
	// fieldView.getPosition().add('y', Tile.size);
	// fieldView.getLines().remove(0);
	// fieldView.getLines().add(new FieldLine(newCode));
	// }
	//
	// if (fieldView.isFieldRightOver())
	// {
	//
	// fieldView.getPosition().add('x', Tile.size);
	// for (FieldLine line : fieldView.getLines())
	// {
	// line.getTiles().remove(0);
	// line.getTiles().add(new Tile());
	// }
	// }
	//
	// if (fieldView.isFieldLeftOver())
	// {
	// fieldView.getPosition().add('x', -Tile.size);
	// for (FieldLine line : fieldView.getLines())
	// {
	// List<Tile> tiles = new ArrayList<Tile>();
	// tiles.add(new Tile());
	// for (int i = line.getTam() - 1; i > 0; i--)
	// {
	// tiles.add(line.getTiles().get(i));
	// }
	// line.setTiles(tiles);
	// }
	// }
	// }
}
