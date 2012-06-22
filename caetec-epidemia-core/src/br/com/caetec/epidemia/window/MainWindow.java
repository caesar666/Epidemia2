package br.com.caetec.epidemia.window;

import java.awt.Dimension;

import javax.swing.JFrame;

import br.com.caetec.epidemia.controller.GameCore;
import br.com.caetec.epidemia.controller.Keyboard;
import br.com.caetec.epidemia.controller.Mouse;


public class MainWindow extends JFrame
{
	private static final long serialVersionUID = 6577115179726472971L;
	public static GamePanel panel;
	private static Runnable gameCore = new GameCore();
	
	public static int SIZE_GAME_PANEL_HEIGHT = 640; 
	public static int SIZE_HEIGHT = SIZE_GAME_PANEL_HEIGHT + 100;
	public static int SIZE_WIDTH = 1024;
	
	public MainWindow()
	{
		super("Epidemia 1.0 - SNAPSHOT");
		this.addKeyListener(new Keyboard());
		this.addMouseListener(new Mouse());
	}

	public static void main(String[] args)
	{
		configWindow();
		new Thread(gameCore).start();
	}

	private static void configWindow()
	{
		MainWindow mWindow = new MainWindow();
		mWindow.setSize(new Dimension(MainWindow.SIZE_WIDTH, MainWindow.SIZE_HEIGHT));
		panel = new GamePanel();
		mWindow.getContentPane().add(panel);
		mWindow.setVisible(true);
		mWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
