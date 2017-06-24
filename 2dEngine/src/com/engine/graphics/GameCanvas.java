package com.engine.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import com.engine.main.Game;

/**
 * GameCanvas is a graphical component that
 * renders all of the game's graphics.
 * 
 * @author Ryan Hochmuth
 *
 */
public class GameCanvas extends Canvas
{
	private static final long serialVersionUID = 5328656016785715913L;
	
	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH/12 * 9;
	public static final int SCALE = 3;
	
	/**************************
	 * Game Variables
	 *************************/
	private final Game game;
	private boolean screenLoaded = true;
	private int timeCount = 0;
	private Font loadingFont = null;
	
	/**************************
	 * Graphics Variables
	 *************************/
	private Graphics g;
	private Graphics2D g2d;
	
	/**
	 * Create a new GameCanvas.
	 * @param game
	 */
	public GameCanvas(Game game)
	{
		this.game = game;
		
		// Set the sizes of this Canvas
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		// Add the game's KeyboardHandler so keys can be detected
		this.addKeyListener(game.getKeyboardHandler());
		// Add the game's MouseHandler so mouse clicks can be detected
		this.addMouseListener(game.getMouseHandler());
		this.addMouseMotionListener(game.getMouseHandler());
		
		// Add this GameCanvas to the main window
		game.getWindow().add(this);
		
		// Place this GameCanvas at the proper location and size
		this.setBounds(0, 0, game.getWindowX(), game.getWindowY());
	}
	
	/**
	 * Updated in GameClock at the rate of game.getFps().
	 * Used for general tick updates.
	 */
	public void update()
	{
		// Place this GameCanvas at the proper location and size
		this.setBounds(0, 0, game.getWindow().getWidth(), game.getWindow().getHeight());
		
		if (timeCount > game.getFps())
			timeCount = 0;
		
		timeCount++;
	}
	
	/**
	 * Updated in GameClock at the rate of game.getFps().
	 * Used for rendering updates.
	 */
	public void render()
	{
		// Get or create the BufferStrategy to plan rendering
		BufferStrategy bs = getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		// Get the graphics that can be drawn to
		g = bs.getDrawGraphics();
		
		// Set the Graphics2D
		g2d = (Graphics2D)g;
		
		// Create a black rectangle to fill the canvas as a base background
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if (screenLoaded)
		{
			// Draw the static background
			if (game.getScreenHandler().getCurrentScreen() != null)
				game.getScreenHandler().getCurrentScreen().drawStaticBackground(g);
			
			// Translate the screen based on the Camera
			if (game.getCamera() != null && game.usesCamera())
				g2d.translate(game.getCamera().getxPos(), 
						  	  game.getCamera().getyPos());
			
			// Draw the background layer
			if (game.getScreenHandler().getCurrentScreen() != null)
				game.getScreenHandler().getCurrentScreen().drawBackground(g);
			
			// Render all of the Tiles
			if (game.getScreenHandler().getCurrentScreen() != null && game.usesWorld())
				game.getWorldHandler().renderTiles(g);
			
			// Render all of the GameObjects
			if (game.getScreenHandler().getCurrentScreen() != null && game.usesGameObjects())
				game.getObjectHandler().renderGameObjects(g);
			
			// Draw the foreground layer
			if (game.getScreenHandler().getCurrentScreen() != null)
				game.getScreenHandler().getCurrentScreen().drawForeground(g);
			
			// Draw the lighting
			if (game.getScreenHandler().getCurrentScreen() != null && game.usesLighting())
			g.drawImage(game.getLightHandler().getLightmap(), -1500, -3100, null);
			
			// Translate the screen based on the Camera
			if (game.getCamera() != null && game.usesCamera())
				g2d.translate(-game.getCamera().getxPos(), 
						  	  -game.getCamera().getyPos());
			
			// Draw the gui layer
			if (game.getScreenHandler().getCurrentScreen() != null)
				game.getScreenHandler().getCurrentScreen().drawGui(g);
			
			// Draw the debug layer
			if (game.getScreenHandler().getCurrentScreen() != null
				&& game.getDebugScreen() != null
				&& game.isDebugShown())
				game.getDebugScreen().showDebug(g);
			
			// Draw the loading "curtain" if the screen is still loading
			if (game.getScreenHandler().getCurrentScreen() != null &&
				!screenLoaded)
			{
				if (timeCount < game.getFps() / 4)
				{
					g.setColor(Color.black);
					g.fillRect(0, 0, game.getWindow().getWidth(), 
							game.getWindow().getHeight());
					g.setColor(Color.white);
					if (loadingFont == null)
						g.setFont(new Font("Verdana", Font.PLAIN, 32));
					else
						g.setFont(loadingFont);
					g.drawString("Loading . ", (game.getWindow().getWidth() / 2) - 80, 
							(game.getWindow().getHeight() / 2) - 80);
				}
				else if (timeCount < (game.getFps() / 4) * 2)
				{
					g.setColor(Color.black);
					g.fillRect(0, 0, game.getWindow().getWidth(), 
							game.getWindow().getHeight());
					g.setColor(Color.white);
					if (loadingFont == null)
						g.setFont(new Font("Verdana", Font.PLAIN, 32));
					else
						g.setFont(loadingFont);
					g.drawString("Loading . ", (game.getWindow().getWidth() / 2) - 80, 
							(game.getWindow().getHeight() / 2) - 80);
				}
				else if (timeCount < (game.getFps() / 4) * 3)
				{
					g.setColor(Color.black);
					g.fillRect(0, 0, game.getWindow().getWidth(), 
							game.getWindow().getHeight());
					g.setColor(Color.white);
					if (loadingFont == null)
						g.setFont(new Font("Verdana", Font.PLAIN, 32));
					else
						g.setFont(loadingFont);
					g.drawString("Loading . . ", (game.getWindow().getWidth() / 2) - 80, 
							(game.getWindow().getHeight() / 2) - 80);
				}
				else
				{
					g.setColor(Color.black);
					g.fillRect(0, 0, game.getWindow().getWidth(), 
							game.getWindow().getHeight());
					g.setColor(Color.white);
					if (loadingFont == null)
						g.setFont(new Font("Verdana", Font.PLAIN, 32));
					else
						g.setFont(loadingFont);
					g.drawString("Loading . . . ", (game.getWindow().getWidth() / 2) - 80, 
							(game.getWindow().getHeight() / 2) - 80);
				}
			}
			
			// Dispose of all graphics
			g.dispose();
			// Show all graphics prepared on the BufferStrategy
			bs.show();
		}
		else
		{
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			if (timeCount < game.getFps() / 4)
			{
				g.setColor(Color.black);
				g.fillRect(0, 0, game.getWindow().getWidth(), 
						game.getWindow().getHeight());
				g2.setColor(Color.white);
				if (loadingFont == null)
					g2.setFont(new Font("Verdana", Font.PLAIN, 32));
				else
					g2.setFont(loadingFont);
				g2.drawString("Loading . ", (game.getWindow().getWidth() / 2) - 80, 
						(game.getWindow().getHeight() / 2) - 80);
			}
			else if (timeCount < (game.getFps() / 4) * 2)
			{
				g.setColor(Color.black);
				g.fillRect(0, 0, game.getWindow().getWidth(), 
						game.getWindow().getHeight());
				g2.setColor(Color.white);
				if (loadingFont == null)
					g2.setFont(new Font("Verdana", Font.PLAIN, 32));
				else
					g2.setFont(loadingFont);
				g2.drawString("Loading . ", (game.getWindow().getWidth() / 2) - 80, 
						(game.getWindow().getHeight() / 2) - 80);
			}
			else if (timeCount < (game.getFps() / 4) * 3)
			{
				g.setColor(Color.black);
				g.fillRect(0, 0, game.getWindow().getWidth(), 
						game.getWindow().getHeight());
				g2.setColor(Color.white);
				if (loadingFont == null)
					g2.setFont(new Font("Verdana", Font.PLAIN, 32));
				else
					g2.setFont(loadingFont);
				g2.drawString("Loading . . ", (game.getWindow().getWidth() / 2) - 80, 
						(game.getWindow().getHeight() / 2) - 80);
			}
			else
			{
				g.setColor(Color.black);
				g.fillRect(0, 0, game.getWindow().getWidth(), 
						game.getWindow().getHeight());
				g2.setColor(Color.white);
				if (loadingFont == null)
					g2.setFont(new Font("Verdana", Font.PLAIN, 32));
				else
					g2.setFont(loadingFont);
				g2.drawString("Loading . . . ", (game.getWindow().getWidth() / 2) - 80, 
						(game.getWindow().getHeight() / 2) - 80);
			}
		}
		
		// Dispose of all graphics
		g.dispose();
		// Show all graphics prepared on the BufferStrategy
		bs.show();
	}

	/**
	 * Get the Graphics this GameCanvas uses.
	 * @return g - the graphics
	 */
	public Graphics getGraphics()
	{
		return g;
	}

	/**
	 * Is the game's current screen loaded.
	 * @return screenLoaded
	 */
	public boolean isScreenLoaded() 
	{
		return screenLoaded;
	}
	
	/**
	 * Set if the game's current screen is loaded.
	 * @param screenLoaded
	 */
	public void setScreenLoaded(boolean screenLoaded) 
	{
		this.screenLoaded = screenLoaded;
	}

	/**
	 * @return the loadingFont
	 */
	public Font getLoadingFont() 
	{
		return loadingFont;
	}

	/**
	 * @param loadingFont the loadingFont to set
	 */
	public void setLoadingFont(Font loadingFont) 
	{
		this.loadingFont = loadingFont;
	}
}
