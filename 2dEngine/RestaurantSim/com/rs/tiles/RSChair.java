package com.rs.tiles;

import java.awt.Color;
import java.awt.Image;

import com.engine.world.Tile;

public class RSChair extends RSFurniture
{
	private boolean occupied = false;
	
	public RSChair(int id, Image image, boolean usePhysics, Color mapColor, int collisionType, String name, int tileType) 
	{
		super(id, image, usePhysics, mapColor, collisionType, name, tileType);
	}
	
	public RSChair(int id, Image[] images, boolean usePhysics, Color mapColor, int collisionType, String name, int tileType) 
	{
		super(id, images[1], usePhysics, mapColor, collisionType, name, tileType);
	}
	
	public RSChair(float xPos, float yPos, Tile tile) 
	{
		super(xPos, yPos, tile);
	}

	public boolean isOccupied() 
	{
		return occupied;
	}

	public void setOccupied(boolean occupied) 
	{
		this.occupied = occupied;
	}
}