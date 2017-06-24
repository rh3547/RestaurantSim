package com.engine.entity;

import java.awt.Image;

import com.engine.gameObject.GameObject;
import com.engine.graphics.AnimationSet;

/**
 * A player is the main entity that is
 * typically controlled by the user. 
 *  
 * <h3><b>Basic Functionality:</b></h3>
 *  This base player class allows
 *  the same functionality as a basic game object
 *  as well as a positional world border.  
 *  For extended functionality, make a custom
 *  player class that extends this one.
 *  
 * <h3><b>Controls:</b></h3>
 * 	Player controls are not handled in the
 *  Player class.  To set up player controls
 *  create keys in the game's resource class
 *  and link actions to keys using a controller
 *  class.
 * 
 * <br></br>
 * @author Ryan Hochmuth
 *
 */
public class Player extends Entity
{
	/**
	 * Create a new player with an initial position.
	 * @param xPos - the x position of the player
	 * @param yPos - the y position of the player
	 * @param id - the game id of this player
	 * @param image - the base graphic of the player
	 * @param usePhysics - does the player "fall" because of gravity
	 */
	public Player(float xPos, float yPos, int id, Image[] images, boolean usePhysics, AnimationSet ani, String name)
	{
		super(xPos, yPos, id, images, usePhysics, Entity.PLAYER, name);
		
		super.aniSet = ani;
		
		// The player is set to collide by default
		// If you don't want it to, change the
		// collision type.  See GameObject for those
		setCollisionType(GameObject.COLLIDE);
	}

	/**
	 * Override of tick() in GameObject.
	 * This method is being overridden because
	 * the player should have a boundary in the 
	 * world.  If the player goes too far to the
	 * left or right in the world, it will be
	 * stopped.
	 */
	@Override
	public void tick()
	{
		xPos += xVel;
		yPos += yVel;
		
		fall(); // Run the fall method
	}
}