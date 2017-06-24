package com.rs.tiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.engine.world.Tile;
import com.rs.main.RSFlags;
import com.rs.main.WorldInteraction;

public class RSTile extends Tile
{
	public static final int UNEDITABLE = -1;
	public static final int FLOOR = 0;
	public static final int WALL = 1;
	public static final int FURNITURE = 2;
	
	public static final int ROT_UP = 0;
	public static final int ROT_RIGHT = 1;
	public static final int ROT_DOWN = 2;
	public static final int ROT_LEFT = 3;
	
	private int tileType;
	private Image[] furnitureImages = null;
	private int furnitureRotation = 1;
	private boolean rotatable = false;
	
	public RSTile(int id, Image image, boolean usePhysics, Color mapColor, int collisionType, String name, int tileType) 
	{
		super(id, image, usePhysics, mapColor, collisionType, name);
		
		this.tileType = tileType;
	}
	
	public RSTile(int id, Image[] images, boolean usePhysics, Color mapColor, int collisionType, String name, int tileType) 
	{
		super(id, images[1], usePhysics, mapColor, collisionType, name);
		
		this.tileType = tileType;
		this.furnitureImages = images;
		this.rotatable = true;
	}
	
	/**
	 * Create a new Tile based on another Tile.
	 * @param xPos
	 * @param yPos
	 * @param tile
	 */
	public RSTile(float xPos, float yPos, Tile tile) 
	{
		super(xPos, yPos, tile);
		
		this.tileType = ((RSTile)tile).getTileType();
	}
	
	@Override
	public void renderObject(Graphics g)
	{
		g.drawImage(image, (int)xPos, (int)yPos, null);
		
		if (tileType == RSTile.FLOOR || tileType == RSTile.WALL)
			if (RSFlags.showGrid == true)
				g.drawImage(RSTiles.tileGrid.getImage(), (int)xPos, (int)yPos, null);
	}
	
	@Override
	public void clicked()
	{
		if (RSFlags.mode == RSFlags.BUILD_MODE || RSFlags.mode == RSFlags.BUY_MODE)
		{
			if (RSFlags.usingGui == false && RSFlags.tool != RSFlags.NO_TOOL)
				WorldInteraction.checkInteraction((int)xPos, (int)yPos);
		}
	}
	
	/**
	 * Change the rotation of this object.
	 */
	public void changeRotation()
	{
		if (tileType == RSTile.FURNITURE)
		{
			if (furnitureRotation <= 2)
				furnitureRotation++;
			else
				furnitureRotation = 0;
			
			this.setImage(furnitureImages[furnitureRotation]);
		}
	}

	/**
	 * @return the tileType
	 */
	public int getTileType()
	{
		return tileType;
	}

	/**
	 * @param tileType the tileType to set
	 */
	public void setTileType(int tileType)
	{
		this.tileType = tileType;
	}

	/**
	 * @return the furnitureImages
	 */
	public Image[] getFurnitureImages()
	{
		return furnitureImages;
	}

	/**
	 * @param furnitureImages the furnitureImages to set
	 */
	public void setFurnitureImages(Image[] furnitureImages)
	{
		this.furnitureImages = furnitureImages;
	}

	/**
	 * @return the furnitureRotation
	 */
	public int getFurnitureRotation()
	{
		return furnitureRotation;
	}

	/**
	 * @param furnitureRotation the furnitureRotation to set
	 */
	public void setFurnitureRotation(int furnitureRotation)
	{
		this.furnitureRotation = furnitureRotation;
	}
	
	/**
	 * @return the rotatable
	 */
	public boolean isRotatable()
	{
		return rotatable;
	}

	/**
	 * @param rotatable the rotatable to set
	 */
	public void setRotatable(boolean rotatable)
	{
		this.rotatable = rotatable;
	}
}