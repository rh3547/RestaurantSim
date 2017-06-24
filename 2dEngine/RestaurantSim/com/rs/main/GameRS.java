package com.rs.main;

import java.io.File;

import javax.swing.JOptionPane;

import com.engine.main.Game;
import com.rs.screens.ScreenRSMainMenu;

public class GameRS
{
	/***********************************
	 * Game Variables
	 **********************************/
	private final Game game;
	// The title to display at the top of the window
	private String title = "Restaurant Sim";
	// The window's width
	private int windowX = 1280;
	// the window's height
	private int windowY = 720;
	// The rate to update the game's rendering
	private int fps = 60;
	// The rate to update the game's ticks
	private int tps = 60;
	
	// The user's operating system
	private String OS = "";
	
	/**
	 * Create a new game.
	 */
	public GameRS()
	{
		// Create the game
		game = new Game(title, windowX, windowY, fps, tps);
		game.start();
		
		game.getWindow().setResizable(false);
		
		game.getCanvas().setScreenLoaded(false);
		
		game.setResourceLoader(new RSResources(game));
		game.setDebugScreen(null);
		game.setResourcePack("default");
		
		// Detect the user's operating system
		OS = game.getResourceLoader().getOS();
		// Set the game's res and file paths based on OS
		if (OS.equals("windows"))
		{
			// System.getProperty("user.home") + "\\Documents\\" + title + "\\res\\"
			game.setRespath("RestaurantSim/res/");
			game.setFilepath(System.getProperty("user.home") + "\\Documents\\" + title);
		}
		else if (OS.equals("mac"))
		{
			game.setRespath("RestaurantSim/res/");
			game.setFilepath(System.getProperty("user.home") + "\\" + title);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Operating system not supported");
			System.exit(0);
		}
		
		game.setWindowIcon("graphics/icons/icon.png");
		
		// Create the game's base directory at filepath
		File dir = new File(game.getFilepath());
		if (!dir.exists())
			try
			{
		        dir.mkdir();
		    } 
			catch(SecurityException se)
			{
		        
		    }
		
		// Load the game resources
		game.getResourceLoader().loadResources();
		
		game.getCanvas().setLoadingFont(RSResources.fontMain32);
		
		// Create and show the Screen ScreenMainMenu
		game.getScreenHandler().showScreen(new ScreenRSMainMenu(game.getScreenHandler()));
	}
  
	public static void main(String[] args)
	{
		new GameRS();
	}
}
