package com.engine.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.engine.gameObject.GameObject;
import com.engine.resources.ResourceLoader;

/**
 * A Tile is a physical tile in the game world.
 * Tile's have different textures and properties
 * and they are what make up the world.
 * 
 * @author Ryan Hochmuth
 *
 */
public class Tile extends GameObject
{
	// The color of this Tile on a world map
	protected Color mapColor;

	/**
	 * Create a new Tile from scratch.
	 * @param xPos
	 * @param yPos
	 * @param id
	 * @param image
	 * @param usePhysics
	 * @param mapColor
	 */
	public Tile(float xPos, float yPos, int id, Image image, boolean usePhysics, Color mapColor, String name) 
	{
		super(xPos, yPos, id, image, usePhysics, name);
		
		this.bImage = ResourceLoader.toBufferedImage(image);
		
		this.width = bImage.getWidth();
		this.height = bImage.getHeight();
		
		this.mapColor = mapColor;
	}
	
	/**
	 * Create a new Tile with no specified position.
	 * @param id
	 * @param image
	 * @param usePhysics
	 * @param mapColor
	 */
	public Tile(int id, Image image, boolean usePhysics, Color mapColor, int collisionType, String name) 
	{
		super(id, image, usePhysics, name);
		
		this.bImage = ResourceLoader.toBufferedImage(image);
		
		this.width = bImage.getWidth();
		this.height = bImage.getHeight();
		
		this.mapColor = mapColor;
		this.collisionType = collisionType;
	}
	
	/**
	 * Create a new Tile based on another Tile.
	 * @param xPos
	 * @param yPos
	 * @param tile
	 */
	public Tile(float xPos, float yPos, Tile tile) 
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.id = tile.getId();
		this.image = tile.getImage();
		this.usePhysics = tile.doesUsePhysics();
		this.mapColor = tile.getMapColor();
		this.name = tile.getName();
		
		this.setCollisionType(tile.getCollisionType());
		
		this.bImage = ResourceLoader.toBufferedImage(image);
		
		this.width = bImage.getWidth();
		this.height = bImage.getHeight();
	}
	
	@Override
	public void tick()
	{
		xPos += xVel;
		yPos += yVel;
		
		fall();
	}
	
	@Override
	public void fall()
	{
		if (usePhysics)
			if (falling)
				yVel += gravity;
		
		checkCollision();
	}
	
	/**
	 * Check all collisions with this GameObject.
	 */
	@Override
	public void checkCollision()
	{
		
	}
	
	@Override
	public void renderObject(Graphics g) 
	{
		g.drawImage(image, (int)xPos, (int)yPos, null);
	}
	
	@Override
	public void clicked()
	{
		Tile tempTile = null;
		float tempX = 0;
		float tempY = 0;
		
		// Update surrounding tiles
		for (int x = 0; x < 8; x++)
		{
			switch (x)
			{
				case 0:
					tempX = xPos - width;
					tempY = yPos - height;
					break;
					
				case 1:
					tempX = xPos;
					tempY = yPos - height;
					break;
					
				case 2:
					tempX = xPos + width;
					tempY = yPos - height;
					break;
					
				case 3:
					tempX = xPos + width;
					tempY = yPos;
					break;
					
				case 4:
					tempX = xPos + width;
					tempY = yPos + height;
					break;
					
				case 5:
					tempX = xPos;
					tempY = yPos + height;
					break;
					
				case 6:
					tempX = xPos - width;
					tempY = yPos + height;
					break;
					
				case 7:
					tempX = xPos - width;
					tempY = yPos;
					break;
			}
			
			tempTile = ResourceLoader.game.getWorldHandler().getTileAtCoords(tempX, tempY);
			
			if (tempTile != null)
			{
				tempTile.update();
			}
		}
		
		// Remove the tile when clicked
		ResourceLoader.game.getWorldHandler().breakTile(this);
	}
	
	/**
	 * Get the top bounds of this GameObject.
	 * @return Rectangle The top bounds of this GameObject
	 */
	@Override
	public Rectangle getTopBounds()
	{
		return new Rectangle((int)xPos + 6, (int)yPos, width - 12, 10);
	}
	
	/**
	 * Get the bottom bounds of this GameObject.
	 * @return Rectangle The bottom bounds of this GameObject
	 */
	@Override
	public Rectangle getBottomBounds()
	{
		return new Rectangle((int)xPos, (int)yPos + height - 10, width, 10);
	}
	
	/**
	 * Get the left bounds of this GameObject.
	 * @return Rectangle The left bounds of this GameObject
	 */
	@Override
	public Rectangle getLeftBounds()
	{
		return new Rectangle((int)xPos, (int)yPos, 6, height - 10);
	}
	
	/**
	 * Get the right bounds of this GameObject.
	 * @return Rectangle The right bounds of this GameObject
	 */
	@Override
	public Rectangle getRightBounds()
	{
		return new Rectangle((int)xPos + width - 6, (int)yPos, 6, height - 10);
	}

	/**
	 * Get the color of this tile on a world map.
	 * @return the mapColor
	 */
	public Color getMapColor()
	{
		return mapColor;
	}

	/**
	 * Set the color of this tile on a world map.
	 * @param mapColor the mapColor to set
	 */
	public void setMapColor(Color mapColor)
	{
		this.mapColor = mapColor;
	}
}
