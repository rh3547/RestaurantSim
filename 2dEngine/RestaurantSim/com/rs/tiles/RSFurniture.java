package com.rs.tiles;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import com.engine.world.Tile;

public class RSFurniture extends RSTile
{
	private List<RSTile> connectedFurniture = new ArrayList<RSTile>();
	
	public RSFurniture(int id, Image image, boolean usePhysics, Color mapColor, int collisionType, String name, int tileType) 
	{
		super(id, image, usePhysics, mapColor, collisionType, name, tileType);
	}
	
	public RSFurniture(int id, Image[] images, boolean usePhysics, Color mapColor, int collisionType, String name, int tileType) 
	{
		super(id, images[1], usePhysics, mapColor, collisionType, name, tileType);
	}
	
	public RSFurniture(float xPos, float yPos, Tile tile) 
	{
		super(xPos, yPos, tile);
	}
	
	public List<RSTile> getConnectedFurniture() 
	{
		return connectedFurniture;
	}

	public void setConnectedFurniture(List<RSTile> connectedFurniture) 
	{
		this.connectedFurniture = connectedFurniture;
	}
}
