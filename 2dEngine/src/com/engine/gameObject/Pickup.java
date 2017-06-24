package com.engine.gameObject;

import java.awt.Image;

import com.engine.entity.Player;
import com.engine.resources.ResourceLoader;

/**
 * A pickup is a game object that can
 * be "picked up" or "taken" by the
 * player or other entity.  Typically
 * when a pickup is interacted with,
 * it is removed from the game world
 * and something associated with it
 * happens.
 * 
 * <h3><b>Basic Functionality:</b></h3>
 *  The base functionality of a pickup
 *  is as follows:
 *  <ul>
 *  <li>All aspects of a basic game object</li>
 *  <li>Removed from the world when collided with</li>
 *  <li>When it is interacted with, an event is triggered</li>
 *  </ul>
 * 
 * @author Ryan Hochmuth
 *
 */
public class Pickup extends GameObject
{
	/**
	 * Create a new pickup.
	 * @param xPos
	 * @param yPos
	 * @param id
	 * @param image
	 * @param usePhysics
	 */
	public Pickup(float xPos, float yPos, int id, Image image, boolean usePhysics, String name) 
	{
		super(xPos, yPos, id, image, usePhysics, name);
		
		this.bImage = ResourceLoader.toBufferedImage(image);
		
		this.width = bImage.getWidth();
		this.height = bImage.getHeight();
		
		ResourceLoader.game.getObjectHandler().getGameObjects().add(this);
	}
	
	/**
	 * Create a new pickup with no specified position.
	 * @param id
	 * @param image
	 * @param usePhysics
	 * @param mapColor
	 */
	public Pickup(int id, Image image, boolean usePhysics, int collisionType, String name) 
	{
		super(id, image, usePhysics, name);
		
		this.bImage = ResourceLoader.toBufferedImage(image);
		
		this.width = bImage.getWidth();
		this.height = bImage.getHeight();
		
		this.collisionType = collisionType;
	}
	
	/**
	 * Create a new pickup based on another pickup.
	 * @param xPos
	 * @param yPos
	 * @param tile
	 */
	public Pickup(float xPos, float yPos, Pickup pickup) 
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.id = pickup.getId();
		this.image = pickup.getImage();
		this.usePhysics = pickup.doesUsePhysics();
		
		this.setCollisionType(pickup.getCollisionType());
		
		this.bImage = ResourceLoader.toBufferedImage(image);
		
		this.width = bImage.getWidth();
		this.height = bImage.getHeight();
		
		ResourceLoader.game.getObjectHandler().getGameObjects().add(this);
	}
	
	/**
	 * The method called when a pickup is
	 * "picked up" by something.  This
	 * method takes in a GameObject
	 * to interact with whatever
	 * picked this pickup up.
	 */
	public void pickUp(GameObject obj)
	{
		kill();
	}
	
	/**
	 * Checks if this game object is colliding with
	 * other game object's that collide.
	 * <h3>Basic Collision Detection Process</h3>
	 * The list of game objects that 
	 * are in view are searched
	 * through one by one.  Each game object
	 * can detect collision from all four
	 * directions by generating a rectangle
	 * on a specific side.  This game object
	 * and the one being tested both generate
	 * rectangles to test collision.  For example,
	 * this object may generate it's bottom rectangle
	 * and may test the rectangle generated on top
	 * of the other game object.  If the rectangles 
	 * are colliding then this object's bottom is 
	 * colliding with the other's top.  Once a
	 * collision is detected, the velocities of
	 * the objects are altered depending on the
	 * case of collision.
	 */
	@Override
	public void checkCollision()
	{
		if (col != null)
		{
			col.checkBasicTileCollision(this);
		}
		
		if (collisionType == GameObject.COLLIDE 
			|| collisionType == GameObject.CHECK_NO_COLLIDE
			|| getCollisionType() == GameObject.COLLIDE_EXCEPT_PLAYER) // Only test collisions if this object has collisions enabled
			{
				// Test against all other GameObjects
				for (int x = 0; x < ResourceLoader.game.getObjectHandler().getGameObjects().size(); x++)
				{
					if (ResourceLoader.game.getObjectHandler().getGameObjects().get(x).getxPos() > 
						ResourceLoader.game.getCamera().getPlayer().getxPos() - 
						ResourceLoader.game.getChunkLoadDistance() &&
						ResourceLoader.game.getObjectHandler().getGameObjects().get(x).getxPos() < 
						ResourceLoader.game.getCamera().getPlayer().getxPos() + 
						ResourceLoader.game.getChunkLoadDistance() 
						&&
						ResourceLoader.game.getObjectHandler().getGameObjects().get(x).getyPos() > 
						ResourceLoader.game.getCamera().getPlayer().getyPos() - 
						ResourceLoader.game.getChunkLoadDistance() &&
						ResourceLoader.game.getObjectHandler().getGameObjects().get(x).getyPos() < 
						ResourceLoader.game.getCamera().getPlayer().getyPos() + 
						ResourceLoader.game.getChunkLoadDistance())
					{
						GameObject obj = ResourceLoader.game.getObjectHandler().getGameObjects().get(x);
						// If the object being tested has collisions enabled and isn't this object
						if (obj.getCollisionType() == GameObject.COLLIDE && obj != this 
							|| obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE
							|| obj.getCollisionType() == GameObject.COLLIDE_EXCEPT_PLAYER&& obj != this)
						{
							if (getBottomBounds().intersects(obj.getTopBounds()) &&
								getRightBounds().intersects(obj.getLeftBounds()))
							{
								if (obj.getCollisionType() == GameObject.COLLIDE)
								{
									if (obj instanceof Player)
									{
										pickUp(obj);
									}
									else
									{
										if (jumping)
										{
											xVel = 1;
										}
										else
										{
											yVel = 0;
										}
									}
								}
								else if (obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							else if (getBottomBounds().intersects(obj.getTopBounds()) &&
									 getLeftBounds().intersects(obj.getRightBounds()))
							{
								if (obj.getCollisionType() == GameObject.COLLIDE)
								{
									if (obj instanceof Player)
									{
										pickUp(obj);
									}
									else
									{
										if (jumping)
										{
											xVel = -1;
										}
										else
										{
											yVel = 0;
										}
									}
								}
								else if (obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							// If this object's bottom collides with another's top
							else if (getBottomBounds().intersects(obj.getTopBounds()))
							{
								if (obj.getCollisionType() == GameObject.COLLIDE)
								{
									if (obj instanceof Player)
									{
										pickUp(obj);
									}
									else
									{
										yVel = 0; // Negate all y velocity
										// Set this object to be on top of the other
										yPos = obj.getyPos() - height;
									
										// If this object was jumping, it is not jumping any more
										jumping = false;
									}
								}
								else if (obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							
							// If this object's top collides with another's bottom
							if (getTopBounds().intersects(obj.getBottomBounds()))
							{
								if (obj.getCollisionType() == GameObject.COLLIDE)
								{
									if (obj instanceof Player)
									{
										pickUp(obj);
									}
									else
									{
										yVel = 0;
										yPos = obj.getyPos() + obj.getObjectHeight();
									}
								}
								else if (obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							
							// If this object's left collides with another's right
							if (getLeftBounds().intersects(obj.getRightBounds()))
							{
								if (obj.getCollisionType() == GameObject.COLLIDE)
								{
									if (obj instanceof Player)
									{
										pickUp(obj);
									}
									else
									{
										xVel = 0;
										xPos = obj.getxPos() + obj.getObjectWidth();
									}
								}
								else if (obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							
							// If this object's right collides with another's left
							if (getRightBounds().intersects(obj.getLeftBounds()))
							{
								if (obj.getCollisionType() == GameObject.COLLIDE)
								{
									if (obj instanceof Player)
									{
										pickUp(obj);
									}
									else
									{
										xVel = 0;
										xPos = obj.getxPos() - width;
									}
								}
								else if (obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
						}
					}
				}
			}
	}
}