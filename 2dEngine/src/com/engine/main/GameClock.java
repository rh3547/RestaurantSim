package com.engine.main;

/**
 * GameClock is a controlled clock that runs based on a set time.
 * This class updates Screen objects.
 * 
 * @author Ryan Hochmuth
 *
 */
public class GameClock implements Runnable
{
	/***********************************
	 * Game Variables
	 **********************************/
	private final Game game;
	
	/***********************************
	 * Clock Variables
	 **********************************/
	private int fps;
	private int tps;
	private boolean running = false;
	private boolean shouldRender = false;
	private int ticks = 0;
	private int frames = 0;
	private int topFrames = 0;
	private int topTicks = 0;
	
	private long lastTime = System.nanoTime();
	private double nsPerTick = 1000000000.0;
	private long lastTimer = System.currentTimeMillis();
	private double delta = 0;
	
	private long lastTime2 = System.nanoTime();
	private double nsPerTick2 = 1000000000.0;
	private long lastTimer2 = System.currentTimeMillis();
	private double delta2 = 0;
	
	private int totalTickCount = 0;
	
	private int lastMouseMoveCount = 0;
	
	/**
	 * Create a new GameClock.
	 * @param game
	 * @param fps
	 */
	public GameClock(Game game, int fps, int tps)
	{
		this.game = game;
		this.fps = fps;
		this.tps = tps;
		
		running = true;
	}
	
	/**
	 * Starts the GameClock timer
	 */
	public void run()
	{
		// Start comparison time in nanoseconds
		lastTime = System.nanoTime();
		// How many nanoseconds per tick the clock should update at
		// Based on fps
		nsPerTick = 1000000000.0 / fps;
		// Start comparison time in milliseconds
		lastTimer = System.currentTimeMillis();
		// Change in time between updates
		delta = 0;
		
		// Start comparison time in nanoseconds
		lastTime2 = System.nanoTime();
		// How many nanoseconds per tick the clock should update at
		// Based on fps
		nsPerTick2 = 1000000000.0 / tps;
		// Start comparison time in milliseconds
		lastTimer2 = System.currentTimeMillis();
		// Change in time between updates
		delta2 = 0;
		
		// Waits until the game is loaded
		while(game.isGameLoading())
		{
			try
			{
				Thread.sleep(10);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		// Start of the clock loop
		while(running)
		{
			/*
			 * FPS Timer
			 */
			
			// The current system time in nanoseconds (resets on update)
			long now = System.nanoTime();
			// Update delta every update based on difference in time
			delta += (now - lastTime) / nsPerTick;
			// Set the start comparison time to the last tested time
			lastTime = now;
			// Turn rendering off
			shouldRender = false;
			
			// Enters loop if an update should occur (fps ticks have occurred in a second)
			while(delta >= 1)
			{
				delta -= 1; // Reset delta for next update
				shouldRender = true; // Allow rendering
			}
			
			// If an update occurs, render graphics
			if (shouldRender)
			{
				render();
				
				frames++; // Increment frames
			}
			
			// If a second has gone by, reset all counts
			if (System.currentTimeMillis() - lastTimer >= 1000)
			{
				//System.out.println("Frames: " + frames + " Ticks: " + ticks);
				lastTimer += 1000;
				
				topFrames = frames;
				
				frames = 0;
			}
			
			/*
			 * Ticks Timer
			 */
			
			// The current system time in nanoseconds (resets on update)
			long now2 = System.nanoTime();
			// Update delta every update based on difference in time
			delta2 += (now2 - lastTime2) / nsPerTick2;
			// Set the start comparison time to the last tested time
			lastTime2 = now2;
			
			// Enters loop if an update should occur (tps ticks have occurred in a second)
			while(delta2 >= 1)
			{
				tick();
				
				totalTickCount++;
				ticks++; // Increment ticks
				delta2 -= 1; // Reset delta for next update
			}
			
			// Update half as often as a normal tick
			if ((ticks % 2) == 0)
			{
				halfTick();
			}
			
			// If a second has gone by, reset all counts
			if (System.currentTimeMillis() - lastTimer2 >= 1000)
			{
				lastTimer2 += 1000;
				
				topTicks = ticks;
				
				ticks = 0;
			}
		}
	}
	
	/**
	 * Perform a tick update.
	 */
	public void tick()
	{
		if (game.getCanvas().isScreenLoaded())
		{
			// If there is a Screen being shown, run it's onUpdate() method
			if (game.getScreenHandler().getCurrentScreen() != null)
				game.getScreenHandler().getCurrentScreen().onUpdate();
			
			// If a Camera exists, tick it
			if (game.getCamera() != null && game.usesCamera())
				game.getCamera().tick();
			
			// Only update these if the game uses worlds
			if (game.usesWorld())
			{
				// Update all of the Tiles
				game.getWorldHandler().tickTiles();
			}
			
			// Tick all of the GameObjects
			if (game.usesGameObjects())
				game.getObjectHandler().tickGameObjects();
		}
		
		if (game.isMouseMoving())
		{
			if (lastMouseMoveCount != game.getMouseMoveCount())
			{
				lastMouseMoveCount = game.getMouseMoveCount();
			}
			else
			{
				game.setMouseMoving(false);
				game.setMouseMoveCount(0);
			}
		}
		
		// Update the GameCanvas
		game.getCanvas().update();
		
		game.getScreenHandler().update();
	}
	
	/**
	 * Perform a tick less often.
	 */
	public void halfTick()
	{
		
	}
	
	/**
	 * Perform a render update.
	 */
	public void render()
	{
		// Render the GameCanvas
		game.getCanvas().render();
	}
	
	/**
	 * Get the total amount of ticks that have happened in the game so far.
	 * @return
	 */
	public int getTotalTickCount()
	{
		return totalTickCount;
	}

	/**
	 * @return the ticks
	 */
	public int getTicks()
	{
		return ticks;
	}

	/**
	 * @return the frames
	 */
	public int getFrames()
	{
		return frames;
	}
	
	/**
	 * @return the top ticks
	 */
	public int getTopTicks()
	{
		return topTicks;
	}

	/**
	 * @return the top frames
	 */
	public int getTopFrames()
	{
		return topFrames;
	}
}