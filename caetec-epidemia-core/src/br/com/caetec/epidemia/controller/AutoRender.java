package br.com.caetec.epidemia.controller;

import java.awt.Graphics;

public interface AutoRender
{
	public void render(Graphics g);

	public boolean update(long passedMillis);
}
