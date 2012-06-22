package br.com.caetec.epidemia.controller;

import java.awt.MouseInfo;
import java.awt.Point;

import br.com.caetec.epidemia.metrics.Position;

public class EpidemiaUtils
{
	private static Position mousePosition = new Position(0, 0);

	public static Position getMousePosition()
	{
		Point p = MouseInfo.getPointerInfo().getLocation();
		int x = new Double(p.getX()).intValue();
		int y = new Double(p.getY()).intValue();

		mousePosition.setX(x);
//		mousePosition.setY(y - 24); 
		mousePosition.setY(y- 24*2);
		//XXX essa porra de valor errado se altera as vezes

		return mousePosition;
	}

}
