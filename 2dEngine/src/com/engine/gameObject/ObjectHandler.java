package com.engine.gameObject;

import java.awt.Graphics;
import java.util.ArrayList;

import com.engine.resources.ResourceLoader;

/**
 * The ObjectHandler class contains a list of every
 * GameObject currently in the game.  Those GameObjects
 * can all be ticked and rendered through here.
 * 
 * @author Ryan Hochmuth
 *
 */
public class ObjectHandler
{
	// A list of every GameObject
	private static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	private int objLoaded = 0;
	private int topObjLoaded = 0;
	private int totalObj = 0;
	private int topTotalObj = 0;
	
	public ObjectHandler()
	{
		
	}
	
	/**
	 * Ticks every GameObject currently in the game.
	 */
	public void tickGameObjects()
	{
		for(int x = 0; x < gameObjects.size(); x++)
		{
			if (gameObjects.get(x).getxPos() > ResourceLoader.game.getCamera().getPlayer().getxPos() - 
				ResourceLoader.game.getChunkLoadDistance() &&
				gameObjects.get(x).getxPos() < ResourceLoader.game.getCamera().getPlayer().getxPos() + 
				ResourceLoader.game.getChunkLoadDistance() 
				&&
				gameObjects.get(x).getyPos() > ResourceLoader.game.getCamera().getPlayer().getyPos() - 
				ResourceLoader.game.getChunkLoadDistance() &&
				gameObjects.get(x).getyPos() < ResourceLoader.game.getCamera().getPlayer().getyPos() + 
				ResourceLoader.game.getChunkLoadDistance())
			{
				GameObject obj = gameObjects.get(x);
				
				// Only check objects in range
				if (obj.getxPos() > ResourceLoader.game.getCamera().getPlayer().getxPos() - 
				(ResourceLoader.game.getLoadDistance() * 32) &&
				obj.getxPos() < ResourceLoader.game.getCamera().getPlayer().getxPos() + 
				(ResourceLoader.game.getLoadDistance() * 32) 
				&&
				obj.getyPos() > ResourceLoader.game.getCamera().getPlayer().getyPos() - 
				(ResourceLoader.game.getLoadDistance() * 32) &&
				obj.getyPos() < ResourceLoader.game.getCamera().getPlayer().getyPos() + 
				(ResourceLoader.game.getLoadDistance() * 32))
				{
					
				}
				else
					continue;
				
				objLoaded++;
				gameObjects.get(x).tick();
			}
			
			totalObj++;
		}
		
		topObjLoaded = objLoaded;
		objLoaded = 0;
		topTotalObj = totalObj;
		totalObj = 0;
	}
	
	/**
	 * Renders every GameObject currently in the game.
	 * @param g
	 */
	public void renderGameObjects(Graphics g)
	{
		for(int x = 0; x < gameObjects.size(); x++)
		{
			if (gameObjects.get(x).getxPos() > ResourceLoader.game.getCamera().getPlayer().getxPos() - 
				ResourceLoader.game.getChunkLoadDistance() &&
				gameObjects.get(x).getxPos() < ResourceLoader.game.getCamera().getPlayer().getxPos() + 
				ResourceLoader.game.getChunkLoadDistance() 
				&&
				gameObjects.get(x).getyPos() > ResourceLoader.game.getCamera().getPlayer().getyPos() - 
				ResourceLoader.game.getChunkLoadDistance() &&
				gameObjects.get(x).getyPos() < ResourceLoader.game.getCamera().getPlayer().getyPos() + 
				ResourceLoader.game.getChunkLoadDistance())
			{
				GameObject obj = gameObjects.get(x);
				
				// Only check objects in range
				if (obj.getxPos() > ResourceLoader.game.getCamera().getPlayer().getxPos() - 
				(ResourceLoader.game.getLoadDistance() * 32) &&
				obj.getxPos() < ResourceLoader.game.getCamera().getPlayer().getxPos() + 
				(ResourceLoader.game.getLoadDistance() * 32) 
				&&
				obj.getyPos() > ResourceLoader.game.getCamera().getPlayer().getyPos() - 
				(ResourceLoader.game.getLoadDistance() * 32) &&
				obj.getyPos() < ResourceLoader.game.getCamera().getPlayer().getyPos() + 
				(ResourceLoader.game.getLoadDistance() * 32))
				{
					
				}
				else
					continue;
				
				gameObjects.get(x).renderObject(g);
			}
		}
	}
	
	/**
	 * Adds the given GameObject to the complete list.
	 * @param object
	 */
	public void addGameObject(GameObject object)
	{
		gameObjects.add(object);
	}
	
	/**
	 * Remove the given GameObject from the complete list.
	 * @param object
	 */
	public void removeGameObject(GameObject object)
	{
		gameObjects.remove(object);
	}
	
	/**
	 * Get the complete list of GameObjects.
	 * @return
	 */
	public ArrayList<GameObject> getGameObjects()
	{
		return gameObjects;
	}
	
	/**
	 * @return the topObjLoaded
	 */
	public int getTopObjLoaded()
	{
		return topObjLoaded;
	}

	/**
	 * @return the topTotalObj
	 */
	public int getTopTotalObj()
	{
		return topTotalObj;
	}
}
