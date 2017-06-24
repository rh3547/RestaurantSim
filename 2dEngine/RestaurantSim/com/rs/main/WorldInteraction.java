package com.rs.main;

import java.awt.Rectangle;

import com.engine.data.Bank;
import com.engine.main.AudioHandler;
import com.engine.resources.ResourceLoader;
import com.engine.world.Chunk;
import com.rs.objects.RSObjects;
import com.rs.tiles.RSTile;

public class WorldInteraction
{
	public static void checkInteraction(int xPos, int yPos)
	{
		boolean foundWall = false;
		RSTile wall = null;
		boolean foundFloor = false;
		RSTile floor = null;
		boolean foundFurniture = false;
		RSTile furniture = null;
		
		// Search through all the tiles and examine the ones at the given position
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
					RSTile obj = null;
					
					// Only check if the object is a RSTile
					if (chunk.getTiles().get(y) instanceof RSTile)
					{
						obj = (RSTile) chunk.getTiles().get(y);
						
						if (obj != null)
						{
							if ((obj.getxPos() == xPos) && (obj.getyPos() == yPos))
							{
								// Declare whether or not a wall, floor, or furniture is found
								if (obj.getTileType() == RSTile.WALL)
								{
									foundWall = true;
									wall = obj;
								}
								else if (obj.getTileType() == RSTile.FLOOR)
								{
									foundFloor = true;
									floor = obj;
								}
							}
						}
					}
				}
			}
		}
		
		// Search through the game objects for furniture
		for (int x = 0; x < ResourceLoader.game.getObjectHandler().getGameObjects().size(); x++)
		{
			if (ResourceLoader.game.getObjectHandler().getGameObjects().get(x) instanceof RSTile)
			{
				RSTile obj = (RSTile) ResourceLoader.game.getObjectHandler().getGameObjects().get(x);
				
				if (obj.getTileType() == RSTile.FURNITURE)
				{
					Rectangle rect = null;
					
					if (RSFlags.tool == RSFlags.DEMOLISH_TOOL)
						rect = new Rectangle(xPos + 16, yPos + 16, 1, 1);
					else if (RSFlags.tool == RSFlags.PLACE_TOOL && RSFlags.currentTile != null && RSFlags.currentTile.getTileType() == RSTile.FURNITURE)
						rect = new Rectangle(xPos + 16, yPos + 16, RSFlags.currentTile.getObjectWidth() - 16, RSFlags.currentTile.getObjectHeight() - 16);
					else
						rect = new Rectangle(xPos + 16, yPos + 16, 1, 1);
					
					if (rect.intersects(obj.getRect()))
					{
						foundFurniture = true;
						furniture = obj;
					}
				}
			}	
		}
		
		/*-----------------------
		 * Tile adding/removing
		 -----------------------*/
		Chunk chunk = getChunkWithTileAtCoords(xPos, yPos);
		
		/*
		 * Construct Mode (Walls/Floors)
		 */
		if (RSFlags.mode == RSFlags.BUILD_MODE) // If the player is in construct mode
		{
			if (RSFlags.tool == RSFlags.DEMOLISH_TOOL) // Trying to demolish an existing tile
			{
				if (foundWall) // If a wall is found, only the wall can be occupying that space, so demolish it
				{
					ResourceLoader.game.getWorldHandler().breakTile(wall);
				}
				else if (foundFloor && !foundFurniture) // If a floor is found but not furniture, demolish it
				{
					ResourceLoader.game.getWorldHandler().breakTile(floor);
				}
			}
			else if (RSFlags.tool == RSFlags.PLACE_TOOL) // Trying to place a new tile
			{
				if (!foundWall && !foundFloor) // There is no wall or floor occupying the space
				{
					if (chunk != null)
					{
						if (RSObjects.money.changeBalance(RSFlags.selectedItem.getData().getPrice(), Bank.SUBTRACT))
						{
							RSFlags.moneyChanged = true;
							RSFlags.moneyChangeAmount = -RSFlags.selectedItem.getData().getPrice();
							
							chunk.addTile(new RSTile(xPos, yPos, RSFlags.currentTile));
							
							ResourceLoader.game.getAudioHandler().playSound("audio/sfx/place.wav", AudioHandler.SFX);
						}
					}
				}
				else if (!foundFloor && foundWall) // There is no floor but is a wall
				{
					if (RSFlags.currentTile.getTileType() == RSTile.WALL && !wall.getName().equals(RSFlags.currentTile.getName()))
					{
						if (chunk != null)
						{
							if (RSObjects.money.changeBalance(RSFlags.selectedItem.getData().getPrice(), Bank.SUBTRACT))
							{
								RSFlags.moneyChanged = true;
								RSFlags.moneyChangeAmount = -RSFlags.selectedItem.getData().getPrice();
								
								ResourceLoader.game.getWorldHandler().breakTile(wall); // Break the old wall
						
								chunk.addTile(new RSTile(xPos, yPos, RSFlags.currentTile));
								
								ResourceLoader.game.getAudioHandler().playSound("audio/sfx/place.wav", AudioHandler.SFX);
							}
						}
					}
					else
					{
						//System.out.println("Can't place a floor on a wall.");
					}
				}
				else if (!foundWall && foundFloor && !foundFurniture) // There is no wall or furniture, but a floor
				{
					if (RSFlags.currentTile.getTileType() == RSTile.FLOOR && !floor.getName().equals(RSFlags.currentTile.getName()))
					{
						if (chunk != null)
						{
							if (RSObjects.money.changeBalance(RSFlags.selectedItem.getData().getPrice(), Bank.SUBTRACT))
							{
								RSFlags.moneyChanged = true;
								RSFlags.moneyChangeAmount = -RSFlags.selectedItem.getData().getPrice();
								
								ResourceLoader.game.getWorldHandler().breakTile(floor); // Break the old floor

								chunk.addTile(new RSTile(xPos, yPos, RSFlags.currentTile));
								
								ResourceLoader.game.getAudioHandler().playSound("audio/sfx/place.wav", AudioHandler.SFX);
							}
						}
					}
					else
					{
						//System.out.println("Can't place a wall on a floor.");
					}
				}
				else if (!foundWall && foundFloor && foundFurniture) // There is no wall, but a floor and furniture
				{
					if (RSFlags.currentTile.getTileType() == RSTile.FLOOR && !floor.getName().equals(RSFlags.currentTile.getName()))
					{
						if (chunk != null)
						{
							if (RSObjects.money.changeBalance(RSFlags.selectedItem.getData().getPrice(), Bank.SUBTRACT))
							{
								RSFlags.moneyChanged = true;
								RSFlags.moneyChangeAmount = -RSFlags.selectedItem.getData().getPrice();
								
								ResourceLoader.game.getWorldHandler().breakTile(floor); // Break the old floor
								ResourceLoader.game.getObjectHandler().removeGameObject(furniture); // Break the furniture
						
	
								chunk.addTile(new RSTile(xPos, yPos, RSFlags.currentTile));
								ResourceLoader.game.getObjectHandler().addGameObject(new RSTile(xPos, yPos, furniture));
								
								ResourceLoader.game.getAudioHandler().playSound("audio/sfx/place.wav", AudioHandler.SFX);
							}
						}
					}
				}
				else if (foundWall)
				{
					//System.out.println("Can't place anything on a wall.");
				}
			}
		}
		
		/*
		 * Furnish mode (Furniture)
		 */
		else if (RSFlags.mode == RSFlags.BUY_MODE) // If the player is in furnish mode
		{
			if (RSFlags.tool == RSFlags.DEMOLISH_TOOL) // Trying to demolish an existing tile
			{
				if (foundFurniture) // There is no wall, but is furniture
				{
					ResourceLoader.game.getObjectHandler().removeGameObject(furniture); // Break the furniture
				}
			}
			else if (RSFlags.tool == RSFlags.PLACE_TOOL) // Trying to place a new tile
			{
				if (!foundWall && !foundFurniture && foundFloor) // There is no wall or furniture, but is a floor
				{
					if (chunk != null)
					{
						if (RSObjects.money.changeBalance(RSFlags.selectedItem.getData().getPrice(), Bank.SUBTRACT))
						{
							RSFlags.moneyChanged = true;
							RSFlags.moneyChangeAmount = -RSFlags.selectedItem.getData().getPrice();
							
							ResourceLoader.game.getObjectHandler().addGameObject(new RSTile(xPos, yPos, RSFlags.currentTile));
							
							ResourceLoader.game.getAudioHandler().playSound("audio/sfx/place.wav", AudioHandler.SFX);
						}
					}
				}
				else if (foundWall)
				{
					//System.out.println("Cannot place furniture on a wall.");
				}
				else if (!foundFloor)
				{
					//System.out.println("Must place furniture on a floor.");
				}
			}
		}
	}
	
	public static Chunk getChunkWithTileAtCoords(float xPos, float yPos)
	{
		for (int x = 1024; x < 2047; x++)
		{
			Chunk chunk = ResourceLoader.game.getWorldHandler().getChunks().get(x);
			
			if (xPos >= chunk.getxPos() && xPos <= chunk.getxPos() + (16 * RSResources.world.getTileWidth()))
			{
				if (yPos >= chunk.getyPos() && yPos <= chunk.getyPos() + (16 * RSResources.world.getTileHeight()))
				{
					return chunk;
				}
			}
		}
		
		return null;
	}
}