package com.engine.graphics;

import java.awt.Graphics;

import com.engine.gameObject.GameObject;
import com.engine.gui.GuiComponent;
import com.engine.input.EngineMouseListener;
import com.engine.input.GameKeyListener;
import com.engine.input.GameObjectListener;
import com.engine.input.GuiComponentListener;

/**
 * A Screen is a particular level/menu of a game.
 * A screen has methods to update logic and
 * graphics based on the GameClock.  Screens
 * are handled through the ScreenHandler class.
 * 
 * @author Ryan Hochmuth
 *
 */
public abstract class Screen implements GuiComponentListener, GameObjectListener, GameKeyListener, EngineMouseListener
{
	private final ScreenHandler screenHandler;
	
	/**
	 * Create a new Screen.
	 * @param screenHandler
	 */
	public Screen(ScreenHandler screenHandler)
	{
		this.screenHandler = screenHandler;
	}
	
	/**
	 * Runs only once when the Screen is first created.
	 * onCreate() is used to set parameters
	 * before the onUpdate() method is called.
	 */
	public abstract void onCreate();
	
	/**
	 * Runs every time a game update occurs from
	 * GameClock.  onUpdate() is used to
	 * update important object parameters
	 * during runtime.
	 */
	public abstract void onUpdate();
	
	/**
	 * What process should be done
	 * to unload this screen.
	 */
	public abstract void unload();
	
	/**
	 * drawStaticBackground() runs every time a game update occurs from
	 * GameClock.  This method should be used to draw static
	 * background images.
	 * @param g
	 */
	public abstract void drawStaticBackground(Graphics g);
	/**
	 * drawBackground() runs every time a game update occurs from
	 * GameClock.  This method should be used to draw graphics
	 * on the background layer
	 * @param g
	 * @param pixels
	 * @param offset
	 * @param row
	 */
	public abstract void drawBackground(Graphics g);
	/**
	 * drawForeground() runs every time a game update occurs from
	 * GameClock.  This method should be used to draw graphics
	 * on the foreground layer.
	 * @param g
	 */
	public abstract void drawForeground(Graphics g);
	/**
	 * drawGui() runs every time a game update occurs from
	 * GameClock.  This method should be used to draw gui
	 * graphics on the up most layer.
	 * @param g
	 */
	public abstract void drawGui(Graphics g);
	
	/**
	 * Get the game's ScreenHandler.
	 * @return screenHandler
	 */
	public ScreenHandler getScreenHandler()
	{
		return screenHandler;
	}
	
	@Override
	public abstract void guiComponentClicked(GuiComponent component, int button);
	
	@Override
	public abstract void guiComponentEntered(GuiComponent component);
	
	@Override
	public abstract void guiComponentExited(GuiComponent component);
	
	@Override
	public abstract void gameObjectClicked(GameObject object, int button);
	
	@Override
	public abstract void mouseClicked(int button);
	
	@Override
	public abstract void keyPressed(int keyCode);
	
	@Override
	public abstract void keyReleased(int keyCode);
}
