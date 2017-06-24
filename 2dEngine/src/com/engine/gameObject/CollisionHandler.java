package com.engine.gameObject;

import com.engine.resources.ResourceLoader;
import com.engine.world.Chunk;

/**
 * A collision handler calculates collisions
 * for an object.  This is done in a separate 
 * class for the sake of making changes easily
 * and allowing different objects to have
 * different collision detection.
 * 
 * @author Ryan Hochmuth
 *
 */
public class CollisionHandler 
{
	public static final int ALT_COL = 100;
	public static final int UP_COL = 0;
	public static final int RIGHT_COL = 1;
	public static final int DOWN_COL = 2;
	public static final int LEFT_COL = 3;
	public static final int UP_RIGHT_COL = 4;
	public static final int UP_LEFT_COL = 5;
	public static final int DOWN_RIGHT_COL = 6;
	public static final int DOWN_LEFT_COL = 7;
	
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
	
	public void checkBasicTileCollision(GameObject colObj)
	{
		// Only test collisions if this object has collisions enabled
		if (colObj.getCollisionType() == GameObject.COLLIDE || 
				colObj.getCollisionType() == GameObject.CHECK_NO_COLLIDE) 
		{
			// Test against all Tiles
			for (int x = 0; x < ResourceLoader.game.getWorldHandler().getChunks().size(); x++)
			{
				Chunk chunk = ResourceLoader.game.getWorldHandler().getChunks().get(x);
				
				if (chunk.getxPos() > ResourceLoader.game.getCamera().getPlayer().getxPos() - 
					ResourceLoader.game.getChunkLoadDistance() &&
					chunk.getxPos() < ResourceLoader.game.getCamera().getPlayer().getxPos() + 
					ResourceLoader.game.getChunkLoadDistance() 
					&&
					chunk.getyPos() > ResourceLoader.game.getCamera().getPlayer().getyPos() - 
					ResourceLoader.game.getChunkLoadDistance() &&
					chunk.getyPos() < ResourceLoader.game.getCamera().getPlayer().getyPos() + 
					ResourceLoader.game.getChunkLoadDistance())
				{
					for(int y = 0; y < chunk.getTiles().size(); y++)
					{
						GameObject obj = chunk.getTiles().get(y);
						
						// Only check tiles in range
						if (obj.getxPos() > colObj.getxPos() - 
							(ResourceLoader.game.getCollisionCheckDistance() * 32) &&
							obj.getxPos() < colObj.getxPos() + 
							(ResourceLoader.game.getCollisionCheckDistance() * 32) 
							&&
							obj.getyPos() > colObj.getyPos() - 
							(ResourceLoader.game.getCollisionCheckDistance() * 32) &&
							obj.getyPos() < colObj.getyPos() + 
							(ResourceLoader.game.getCollisionCheckDistance() * 32))
							{
								
							}
							else
								continue;
						
						// If the object being tested has collisions enabled and isn't this object
						if (obj.getCollisionType() == GameObject.COLLIDE && obj != colObj 
							|| obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE 
							|| obj.getCollisionType() == GameObject.COLLIDE_EXCEPT_PLAYER && obj != colObj)
						{
							if (colObj.getBottomBounds().intersects(obj.getTopBounds()) &&
								colObj.getRightBounds().intersects(obj.getLeftBounds()))
							{		
								if (obj.getCollisionType() == GameObject.COLLIDE)
								{
									obj.collided(colObj, CollisionHandler.UP_LEFT_COL);
								}
								else if (obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							else if (colObj.getBottomBounds().intersects(obj.getTopBounds()) &&
									 colObj.getLeftBounds().intersects(obj.getRightBounds()))
							{
								if (obj.getCollisionType() == GameObject.COLLIDE)
								{
									obj.collided(colObj, CollisionHandler.UP_RIGHT_COL);
								}
								else if (obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							// If this object's bottom collides with another's top
							else if (colObj.getBottomBounds().intersects(obj.getTopBounds()))
							{
								if (obj.getCollisionType() == GameObject.COLLIDE)
								{
									obj.collided(colObj, CollisionHandler.UP_COL);
								}
								else if (obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							
							// If this object's top collides with another's bottom
							if (colObj.getTopBounds().intersects(obj.getBottomBounds()))
							{
								if (obj.getCollisionType() == GameObject.COLLIDE)
								{
									obj.collided(colObj, CollisionHandler.DOWN_COL);
								}
								else if (obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							
							// If this object's left collides with another's right
							if (colObj.getLeftBounds().intersects(obj.getRightBounds()))
							{
								if (obj.getCollisionType() == GameObject.COLLIDE)
								{
									obj.collided(colObj, CollisionHandler.RIGHT_COL);
								}
								else if (obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE)
								{
									
								}
							}
							
							// If this object's right collides with another's left
							if (colObj.getRightBounds().intersects(obj.getLeftBounds()))
							{
								if (obj.getCollisionType() == GameObject.COLLIDE)
								{
									obj.collided(colObj, CollisionHandler.LEFT_COL);
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
	
	public void checkBasicObjectCollision(GameObject colObj)
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
				
				// Only check objects in range
				if (obj.getxPos() > colObj.getxPos() - 
					(ResourceLoader.game.getCollisionCheckDistance() * 32) &&
					obj.getxPos() < colObj.getxPos() + 
					(ResourceLoader.game.getCollisionCheckDistance() * 32) 
					&&
					obj.getyPos() > colObj.getyPos() - 
					(ResourceLoader.game.getCollisionCheckDistance() * 32) &&
					obj.getyPos() < colObj.getyPos() + 
					(ResourceLoader.game.getCollisionCheckDistance() * 32))
					{
						
					}
					else
						continue;
				
				// If the object being tested has collisions enabled and isn't this object
				if (obj.getCollisionType() == GameObject.COLLIDE && obj != colObj 
					|| obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE
					|| obj.getCollisionType() == GameObject.COLLIDE_EXCEPT_PLAYER && obj != colObj)
				{
					if (!(obj instanceof Pickup))
					{
						// If this object's bottom collides with another's top
						if (colObj.getBottomBounds().intersects(obj.getTopBounds()))
						{
							if (obj.getCollisionType() == GameObject.COLLIDE)
							{
								obj.collided(colObj, CollisionHandler.UP_COL);
							}
							else if (obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE)
							{
								
							}
						}
						
						// If this object's top collides with another's bottom
						if (colObj.getTopBounds().intersects(obj.getBottomBounds()))
						{
							if (obj.getCollisionType() == GameObject.COLLIDE)
							{
								obj.collided(colObj, CollisionHandler.DOWN_COL);
							}
							else if (obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE)
							{
								
							}
						}
						
						// If this object's left collides with another's right
						if (colObj.getLeftBounds().intersects(obj.getRightBounds()))
						{
							if (obj.getCollisionType() == GameObject.COLLIDE)
							{
								obj.collided(colObj, CollisionHandler.RIGHT_COL);
							}
							else if (obj.getCollisionType() == GameObject.CHECK_NO_COLLIDE)
							{
								
							}
						}
						
						// If this object's right collides with another's left
						if (colObj.getRightBounds().intersects(obj.getLeftBounds()))
						{
							if (obj.getCollisionType() == GameObject.COLLIDE)
							{
								obj.collided(colObj, CollisionHandler.LEFT_COL);
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