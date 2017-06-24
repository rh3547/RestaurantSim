package com.engine.graphics;

import com.engine.main.Game;

/**
 * ScreenHandler is the driver class for Screen.
 * A Screen can be shown or obtained here.
 * 
 * @author Ryan Hochmuth
 *
 */
public class ScreenHandler
{
	/***********************************
	 * Game Variables
	 **********************************/
	private final Game game;
	private Screen currentScreen;
	private Screen screenOnDeck;
	
	/***********************************
	 * Load Variables
	 **********************************/
	private boolean gameLoading = true;
	
	/**
	 * Create a new ScreenHandler.
	 * @param game
	 */
	public ScreenHandler(Game game)
	{
		this.game = game;
	}
	
	/**
	 * A general update method.
	 */
	public void update()
	{
		
	}
	
	/**
	 * If the game is loaded this method
	 * sets the given currentScreen as the game's current Screen
	 * and runs it's onCreate method to show it.
	 * 
	 * @param currentScreen
	 */
	public void showScreen(Screen currentScreen)
	{
		if (!gameLoading)
		{
			// Set current screen
			this.currentScreen = currentScreen;
			// Run the current screen's onCreate method
			this.currentScreen.onCreate();
		}
	}
	
	/**
	 * Unload the screen that is currently shown.
	 */
	public void unloadCurrentScreen()
	{
		game.setGameLoading(true);
		game.getCanvas().setScreenLoaded(false);
		
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		currentScreen.unload();
		
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		if (screenOnDeck != null)
			showScreen(screenOnDeck);
		
		game.setGameLoading(false);
	}

	/**
	 * Get the currently shown Screen.
	 * @return currentScreen
	 */
	public Screen getCurrentScreen()
	{
		return currentScreen;
	}
	
	/**
	 * Set the current screen.
	 * @param Screen screen
	 */
	public void setCurrentScreen(Screen screen)
	{
		this.currentScreen = screen;
	}
	
	/**
	 * Set the next screen to show.
	 * @param Screen screen
	 */
	public void setScreenOnDeck(Screen screen)
	{
		this.screenOnDeck = screen;
	}

	/**
	 * @return the screenOnDeck
	 */
	public Screen getScreenOnDeck()
	{
		return screenOnDeck;
	}

	/**
	 * Get the Game.
	 * @return game
	 */
	public Game getGame()
	{
		return game;
	}
	
	/**
	 * Set the boolean flag gameLoading depending
	 * on if the game is loading or not.
	 * @param gameLoading
	 */
	public void setGameLoading(boolean gameLoading)
	{
		this.gameLoading = gameLoading;
	}
}
