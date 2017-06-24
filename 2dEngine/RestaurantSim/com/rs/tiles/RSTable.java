package com.rs.tiles;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

import com.engine.world.Tile;

public class RSTable extends RSFurniture
{
	public RSTable(int id, Image image, boolean usePhysics, Color mapColor, int collisionType, String name, int tileType) 
	{
		super(id, image, usePhysics, mapColor, collisionType, name, tileType);
	}
	
	public RSTable(int id, Image[] images, boolean usePhysics, Color mapColor, int collisionType, String name, int tileType) 
	{
		super(id, images[1], usePhysics, mapColor, collisionType, name, tileType);
	}
	
	public RSTable(float xPos, float yPos, Tile tile) 
	{
		super(xPos, yPos, tile);
	}
	
	/**
	 * Check if a seat at this table is open
	 * for a customer to sit in.
	 * @return boolean
	 */
	public boolean isSeatOpen()
	{
		List<RSTile> furn = getConnectedFurniture();
		
		if (furn.size() > 0)
		{
			for (int x = 0; x < furn.size(); x++)
			{
				if (furn.get(x) instanceof RSChair)
				{
					if (!((RSChair)furn.get(x)).isOccupied())
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}
}