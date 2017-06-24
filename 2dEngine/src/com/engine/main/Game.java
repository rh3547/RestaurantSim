package com.engine.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.engine.gameObject.ObjectHandler;
import com.engine.graphics.DebugScreen;
import com.engine.graphics.GameCanvas;
import com.engine.graphics.ScreenHandler;
import com.engine.input.KeyboardHandler;
import com.engine.input.MouseHandler;
import com.engine.resources.ResourceLoader;
import com.engine.world.Camera;
import com.engine.world.LightHandler;
import com.engine.world.WorldHandler;

/**
 * Game handles all game components and
 * acts like a "bridge" between them.
 * All game components are accessed
 * through this class.
 * 
 * @author Ryan Hochmuth
 *
 */
public class Game implements Runnable
{
	/***********************************
	 * Game Variables
	 **********************************/
	private JFrame window = new JFrame();
	private String title;
	private int windowX;
	private int windowY;
	private int fps;
	private int tps;
	private boolean gameLoading = true;
	private boolean running;
	private int worldMouseX = 0;
	private int worldMouseY = 0;
	private int mouseAnchorX = 0;
	private int mouseAnchorY = 0;
	private int mousePosX = 0;
	private int mousePosY = 0;
	private boolean mouseMoving = false;
	private int mouseMoveCount = 0;
	private boolean debugShown = false;
	private String resourcePack = "";
	
	private int loadDistance;
	private int chunkLoadDistance;
	private int collisionCheckDistance;
	private int tileWidth;
	private int tileHeight;
	
	/***********************************
	 * Game Clocks
	 **********************************/
	private GameClock gameClock;
	private Thread gameThread;
	
	/***********************************
	 * Game Components
	 **********************************/
	private final ScreenHandler screenHandler;
	private ResourceLoader resourceLoader;
	private final AudioHandler audioHandler;
	private final KeyboardHandler keyboardHandler;
	private final MouseHandler mouseHandler;
	private final GameCanvas gameCanvas;
	private final ObjectHandler objectHandler;
	private final WorldHandler worldHandler;
	private final LightHandler lightHandler;
	private DebugScreen debugScreen = null;
	
	/***********************************
	 * Modules
	 **********************************/
	private Camera camera;
	
	/***********************************
	 * File Paths
	 **********************************/
	// The path to the games resources (images, audio, etc.)
	private String respath = "";
	// The root path to the game install location
	private String filepath = "";
	
	/***********************************
	 * Component Enablers
	 **********************************/
	private boolean useGameObjects = true;
	private boolean useWorld = true;
	private boolean useLighting = true;
	private boolean useCamera = true;
	
	/**
	 * Create a new Game.
	 * @param title
	 * @param windowX
	 * @param windowY
	 * @param fps
	 */
	public Game(String title, int windowX, int windowY, int fps, int tps)
	{
		this.title = title;
		this.windowX = windowX;
		this.windowY = windowY;
		this.fps = fps;
		this.tps = tps;
		
		// Set the size of the frame
		this.window.setSize(windowX, windowY);
		// Set the title of the frame
		this.window.setTitle(title);
		// Center the frame on the center of the screen
		this.window.setLocationRelativeTo(null);
		// Set the frame to exit when manually closed
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set the window's layout to null
		this.window.setLayout(null);
		// Set the window to be focusable
		this.window.setFocusable(true);
		// Show the frame
		this.window.setVisible(true);
		
		/**
		 *  Create game components
		 */
		// Create a new ScreenHandler
		screenHandler = new ScreenHandler(this);
		// Create a new AudioHandler
		audioHandler = new AudioHandler(this);
		// Create a new KeyboardHandler
		keyboardHandler = new KeyboardHandler(this);
		// Create a new MouseHandler
		mouseHandler = new MouseHandler(this);
		// Create a new GameCanvas
		gameCanvas = new GameCanvas(this);
		// Create a new ObjectHandler
		objectHandler = new ObjectHandler();
		// Create a new WorldHandler
		worldHandler = new WorldHandler();
		// Create a new LightHandler
		lightHandler = new LightHandler();
		
		loadDistance = (windowX / 32) + 8;
		chunkLoadDistance = 4000;
		collisionCheckDistance = 8;
		
		/**
		 *  Create and set up GameClock objects
		 */
		// Create and add main GameClock
		gameClock = new GameClock(this, fps, tps);
		// Create the gameThread and pair gameClock to it
		gameThread = new Thread(gameClock);
		
		// Set the focus of input to the window
		this.window.requestFocus();
		
		// Repaint the components in the window
		window.repaint();
	}
	
	/**
	 * An optional game clock.
	 * Disabled by default, to enable, change
	 * the variable 'running' in the start method
	 * to true.
	 */
	@Override
	public void run()
	{
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000.0 / 60.0;
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			
			while(delta >= 1)
			{
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			
			if (shouldRender)
			{
				frames++;
				render();
			}
			
			if (System.currentTimeMillis() - lastTimer >= 1000)
			{
				System.out.println("Frames: " + frames + " Ticks: " + ticks);
				lastTimer += 1000;
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	/**
	 * An optional update method controlled by the optional
	 * game clock.
	 * Disabled by default.  See run() to enable.
	 */
	public void tick()
	{
		
	}
	
	/**
	 * An optional render method controlled by the optional
	 * game clock.
	 * Disabled by default.  See run() to enable.
	 */
	public void render()
	{
		
	}
	
	/**
	 * Run this game in a new Thread and start that Thread.
	 */
	public synchronized void start()
	{
		running = false; // Change this to true to enable the optional clock
		new Thread(this).start();
		
		// Start the GameClock
		gameThread.start();
	}
	
	/**
	 * Stop the optional clock from running.
	 */
	public synchronized void stop()
	{
		running = false;
	}
	
	/***********************************
	 * Game Variables
	 **********************************/
	/**
	 * Get the game's main JFrame
	 * @return window
	 */
	public JFrame getWindow()
	{
		return window;
	}

	/**
	 * Set the game's main JFrame
	 * @param window
	 */
	public void setWindow(JFrame window)
	{
		this.window = window;
	}

	/**
	 * Get the game's title
	 * @return title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Set the game's title
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
		this.window.setTitle(title);
	}

	/**
	 * Get the window's current width.
	 * @return windowX
	 */
	public int getWindowX()
	{
		return windowX;
	}

	/**
	 * Set the window's width.
	 * @param windowX
	 */
	public void setWindowX(int windowX)
	{
		this.windowX = windowX;
		updateWindowSize();
	}

	/**
	 * Get the window's current height
	 * @return windowY
	 */
	public int getWindowY()
	{
		return windowY;
	}

	/**
	 * Set the window's height.
	 * @param windowY
	 */
	public void setWindowY(int windowY)
	{
		this.windowY = windowY;
		updateWindowSize();
	}
	
	/**
	 * Update the game's current window size.
	 */
	public void updateWindowSize()
	{
		this.window.setSize(windowX, windowY);
	}

	/**
	 * Get the game's current fps.
	 * @return fps
	 */
	public int getFps()
	{
		return fps;
	}

	/**
	 * Set the game's fps.
	 * @param fps
	 */
	public void setFps(int fps)
	{
		this.fps = fps;
	}
	
	/**
	 * Get the game's current tps.
	 * @return tps
	 */
	public int getTps()
	{
		return tps;
	}

	/**
	 * Set the game's tps.
	 * @param tps
	 */
	public void setTps(int tps)
	{
		this.tps = tps;
	}

	/**
	 * Get whether the game is loading or not.
	 * @return gameLoading
	 */
	public boolean isGameLoading()
	{
		return gameLoading;
	}

	/**
	 * Set whether the game is loading or not
	 * @param gameLoading
	 */
	public void setGameLoading(boolean gameLoading)
	{
		this.gameLoading = gameLoading;
	}
	
	/**
	 * Get the x position of the mouse
	 * relative to a position in
	 * the game world.
	 * @return the mouseX
	 */
	public int getWorldMouseX()
	{
		return worldMouseX;
	}

	/**
	 * Set the x position of the mouse
	 * in the game world.
	 * @param mouseX the mouseX to set
	 */
	public void setWorldMouseX(int mouseX)
	{
		this.worldMouseX = mouseX;
	}

	/**
	 * Get the y position of the mouse
	 * relative to a position in
	 * the game world.
	 * @return the mouseY
	 */
	public int getWorldMouseY()
	{
		return worldMouseY;
	}

	/**
	 * Set the y position of the mouse
	 * in the game world.
	 * @param mouseY the mouseY to set
	 */
	public void setWorldMouseY(int mouseY)
	{
		this.worldMouseY = mouseY;
	}

	/**
	 * Get the point on screen
	 * that is considered the
	 * anchor for determining
	 * the mouses world x position.
	 * @return the mousePointX
	 */
	public int getMouseAnchorX()
	{
		return mouseAnchorX;
	}

	/**
	 * Set the point on screen
	 * that is considered the
	 * anchor for determining
	 * the mouses world x position.
	 * @param mousePointX the mousePointX to set
	 */
	public void setMouseAnchorX(int mousePointX)
	{
		this.mouseAnchorX = mousePointX;
	}

	/**
	 * Get the point on screen
	 * that is considered the
	 * anchor for determining
	 * the mouses world y position.
	 * @return the mousePointY
	 */
	public int getMouseAnchorY()
	{
		return mouseAnchorY;
	}

	/**
	 * Set the point on screen
	 * that is considered the
	 * anchor for determining
	 * the mouses world y position.
	 * @param mousePointY the mousePointY to set
	 */
	public void setMouseAnchorY(int mousePointY)
	{
		this.mouseAnchorY = mousePointY;
	}
	
	/**
	 * Get the real x position
	 * of the mouse on screen.
	 * @return the mousePosX
	 */
	public int getMousePosX()
	{
		return mousePosX;
	}

	/**
	 * Set the real x position
	 * of the mouse on screen.
	 * @param mousePosX the mousePosX to set
	 */
	public void setMousePosX(int mousePosX)
	{
		this.mousePosX = mousePosX;
	}

	/**
	 * Get the real y position
	 * of the mouse on screen.
	 * @return the mousePosY
	 */
	public int getMousePosY()
	{
		return mousePosY;
	}

	/**
	 * Set the real y position
	 * of the mouse on screen.
	 * @param mousePosY the mousePosY to set
	 */
	public void setMousePosY(int mousePosY)
	{
		this.mousePosY = mousePosY;
	}

	/**
	 * @return the mouseMoving
	 */
	public boolean isMouseMoving()
	{
		return mouseMoving;
	}

	/**
	 * @param mouseMoving the mouseMoving to set
	 */
	public void setMouseMoving(boolean mouseMoving)
	{
		this.mouseMoving = mouseMoving;
	}

	/**
	 * @return the mouseMoveCount
	 */
	public int getMouseMoveCount()
	{
		return mouseMoveCount;
	}

	/**
	 * @param mouseMoveCount the mouseMoveCount to set
	 */
	public void setMouseMoveCount(int mouseMoveCount)
	{
		this.mouseMoveCount = mouseMoveCount;
	}
	
	/**
	 * Update the count of mouseCount.
	 * This is triggered every time the mouse moves.
	 * If this stops incrementing, then the
	 * mouse is not moving.
	 */
	public void updateMouseCount()
	{
		if ((mouseMoveCount + 1) < Integer.MAX_VALUE)
			this.mouseMoveCount++;
		else
			this.mouseMoveCount = 0;
	}

	/**
	 * @return the debugShown
	 */
	public boolean isDebugShown()
	{
		return debugShown;
	}

	/**
	 * @param debugShown the debugShown to set
	 */
	public void setDebugShown(boolean debugShown)
	{
		this.debugShown = debugShown;
	}

	/**
	 * @return the path to the resource pack
	 */
	public String getResourcePack() 
	{
		return resourcePack;
	}

	/**
	 * @param set the path to the resource path
	 */
	public void setResourcePack(String resourcePack) 
	{
		this.resourcePack = "resourcePacks/" + resourcePack;
	}

	/**
	 * Set the window icon of this game.
	 * @param path -  the path to the icon
	 */
	public void setWindowIcon(String path)
	{
		// Set the window's icon
		BufferedImage img = null;
	    try 
	    {
	       img = ImageIO.read(new File(getRespath() + path));
	    } 
	    catch (IOException e) 
	    {
	       System.out.println("\nImage not found");
	    }
	    window.setIconImage(img);
		window.repaint();
	}

	/***********************************
	 * Game Clocks
	 **********************************/
	/**
	 * Get the game's GameClock.
	 * @return gameClock
	 */
	public GameClock getGameClock()
	{
		return gameClock;
	}

	/**
	 * Set the game's GameClock.
	 * @param gameClock
	 */
	public void setGameClock(GameClock gameClock)
	{
		this.gameClock = gameClock;
	}

	/***********************************
	 * Game Components
	 **********************************/
	/**
	 * Get the game's ScreenHandler.
	 * @return screenHandler
	 */
	public ScreenHandler getScreenHandler()
	{
		return screenHandler;
	}

	/**
	 * Get the game's ResourceLoader
	 * @return resourceLoader
	 */
	public ResourceLoader getResourceLoader()
	{
		return resourceLoader;
	}

	/**
	 * Set the game's ResourceLoader
	 * @param resourceLoader
	 */
	public void setResourceLoader(ResourceLoader resourceLoader)
	{
		this.resourceLoader = resourceLoader;
	}

	/**
	 * Get the game's AudioHandler
	 * @return audioHandler
	 */
	public AudioHandler getAudioHandler()
	{
		return audioHandler;
	}
	
	/**
	 * Get the game's KeyboardHandler
	 * @return keyboardHandler
	 */
	public KeyboardHandler getKeyboardHandler()
	{
		return keyboardHandler;
	}
	
	/**
	 * Get the game's MouseHandler.
	 * @return mouseHandler
	 */
	public MouseHandler getMouseHandler()
	{
		return mouseHandler;
	}

	/**
	 * Get the game's GameCanvas.
	 * @return canvas
	 */
	public GameCanvas getCanvas()
	{
		return gameCanvas;
	}

	/**
	 * Get the game's ObjectHandler.
	 * @return objectHandler
	 */
	public ObjectHandler getObjectHandler()
	{
		return objectHandler;
	}
	
	/**
	 * Get this game's WorldHandler.
	 * @return the worldHandler
	 */
	public WorldHandler getWorldHandler()
	{
		return worldHandler;
	}

	/**
	 * Get the game's LightHandler.
	 * @return objectHandler
	 */
	public LightHandler getLightHandler()
	{
		return lightHandler;
	}

	/**
	 * @return the debugScreen
	 */
	public DebugScreen getDebugScreen()
	{
		return debugScreen;
	}

	/**
	 * @param debugScreen the debugScreen to set
	 */
	public void setDebugScreen(DebugScreen debugScreen)
	{
		this.debugScreen = debugScreen;
	}

	/***********************************
	 * Modules
	 **********************************/
	/**
	 * Get the game's Camera.
	 * @return the camera
	 */
	public Camera getCamera()
	{
		return camera;
	}

	/**
	 * Set the game's Camera.
	 * @param camera the camera to set
	 */
	public void setCamera(Camera camera)
	{
		this.camera = camera;
	}

	/***********************************
	 * File Paths
	 **********************************/
	/**
	 * Get the game's resource path.
	 * @return respath
	 */
	public String getRespath()
	{
		return respath + resourcePack + "/";
	}
	
	/**
	 * Set the game's resource path.
	 * @param respath
	 */
	public void setRespath(String respath)
	{
		this.respath = respath;
	}

	/**
	 * Get the game's file path.
	 * @return filepath
	 */
	public String getFilepath()
	{
		return filepath;
	}
	
	/**
	 * Set the game's file path.
	 * @param filepath
	 */
	public void setFilepath(String filepath)
	{
		this.filepath = filepath;
	}

	/**
	 * @return the useGameObjects
	 */
	public boolean usesGameObjects()
	{
		return useGameObjects;
	}

	/**
	 * @param useGameObjects the useGameObjects to set
	 */
	public void setUseGameObjects(boolean useGameObjects)
	{
		this.useGameObjects = useGameObjects;
	}

	/**
	 * @return the useWorld
	 */
	public boolean usesWorld()
	{
		return useWorld;
	}

	/**
	 * @param useWorld the useWorld to set
	 */
	public void setUseWorld(boolean useWorld)
	{
		this.useWorld = useWorld;
	}

	/**
	 * @return the useLighting
	 */
	public boolean usesLighting()
	{
		return useLighting;
	}

	/**
	 * @param useLighting the useLighting to set
	 */
	public void setUseLighting(boolean useLighting)
	{
		this.useLighting = useLighting;
	}

	/**
	 * @return the useCamera
	 */
	public boolean usesCamera()
	{
		return useCamera;
	}

	/**
	 * @param useCamera the useCamera to set
	 */
	public void setUseCamera(boolean useCamera)
	{
		this.useCamera = useCamera;
	}

	/**
	 * @return the loadDistance
	 */
	public int getLoadDistance()
	{
		return loadDistance;
	}

	/**
	 * @param loadDistance the loadDistance to set
	 */
	public void setLoadDistance(int loadDistance)
	{
		this.loadDistance = loadDistance;
	}

	/**
	 * @return the chunkLoadDistance
	 */
	public int getChunkLoadDistance()
	{
		return chunkLoadDistance;
	}

	/**
	 * @param chunkLoadDistance the chunkLoadDistance to set
	 */
	public void setChunkLoadDistance(int chunkLoadDistance)
	{
		this.chunkLoadDistance = chunkLoadDistance;
	}

	/**
	 * @return the collisionCheckDistance
	 */
	public int getCollisionCheckDistance()
	{
		return collisionCheckDistance;
	}

	/**
	 * @param collisionCheckDistance the collisionCheckDistance to set
	 */
	public void setCollisionCheckDistance(int collisionCheckDistance)
	{
		this.collisionCheckDistance = collisionCheckDistance;
	}

	/**
	 * @return the tileWidth
	 */
	public int getTileWidth()
	{
		return tileWidth;
	}

	/**
	 * @param tileWidth the tileWidth to set
	 */
	public void setTileWidth(int tileWidth)
	{
		this.tileWidth = tileWidth;
	}

	/**
	 * @return the tileHeight
	 */
	public int getTileHeight()
	{
		return tileHeight;
	}

	/**
	 * @param tileHeight the tileHeight to set
	 */
	public void setTileHeight(int tileHeight)
	{
		this.tileHeight = tileHeight;
	}
}
