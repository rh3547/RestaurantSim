package com.engine.world;

import com.engine.gameObject.GameObject;
import com.engine.resources.ResourceLoader;

/**
 * The camera that follows the Player allowing
 * side-scrolling of worlds.
 * 
 * @author Ryan Hochmuth
 *
 */
public class Camera
{
	// The camera's x position
	private float xPos;
	// The camera's y position
	private float yPos;
	// The Player object to follow
	private GameObject player;
	
	/**
	 * Create a new Camera.
	 * @param xPos
	 * @param yPos
	 * @param player
	 */
	public Camera(float xPos, float yPos, GameObject player)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.player = player;
	}
	
	// Update the position of the Camera
	public void tick()
	{
		ResourceLoader.game.setMouseAnchorX(-(int)xPos);
		
		ResourceLoader.game.setMouseAnchorY(-(int)yPos);
		
		xPos += ((-player.getxPos() + 
				ResourceLoader.game.getWindow().getWidth() / 2) - xPos) * 0.0275F;
		
		yPos += ((-player.getyPos() + 
				ResourceLoader.game.getWindow().getHeight() / 2) - yPos) * 0.075F;
	}

	/**
	 * Get the camera's x position.
	 * @return the xPos
	 */
	public float getxPos()
	{
		return xPos;
	}

	/**
	 * get the camera's y position.
	 * @return the yPos
	 */
	public float getyPos()
	{
		return yPos;
	}
	
	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(float xPos)
	{
		this.xPos = xPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(float yPos)
	{
		this.yPos = yPos;
	}

	/**
	 * Get the GameObject this Camera is following.
	 * @return player
	 */
	public GameObject getPlayer()
	{
		return player;
	}
}
