package com.engine.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.engine.gameObject.GameObject;
import com.engine.resources.ResourceLoader;

/**
 * WorldHandler is used to control the game's Chunks.
 * 
 * @author Ryan Hochmuth
 *
 */
public class WorldHandler
{
	private List<Chunk> chunks = new ArrayList<Chunk>();
	
	private int tilesLoaded = 0;
	private int topTilesLoaded = 0;
	private int totalTiles = 0;
	private int topTotalTiles = 0;
	
	public WorldHandler()
	{
		
	}
	
	/**
	 * Tick all of the Tiles within render distance.
	 */
	public void tickTiles()
	{
		for(int x = 0; x < chunks.size(); x++)
		{
			if (chunks.get(x).getxPos() > ResourceLoader.game.getCamera().getPlayer().getxPos() - 
					ResourceLoader.game.getChunkLoadDistance() &&
				chunks.get(x).getxPos() < ResourceLoader.game.getCamera().getPlayer().getxPos() + 
				ResourceLoader.game.getChunkLoadDistance() 
				&&
				chunks.get(x).getyPos() > ResourceLoader.game.getCamera().getPlayer().getyPos() - 
				ResourceLoader.game.getChunkLoadDistance() &&
				chunks.get(x).getyPos() < ResourceLoader.game.getCamera().getPlayer().getyPos() + 
				ResourceLoader.game.getChunkLoadDistance())
			{
				for(int i = 0; i < chunks.get(x).getTiles().size(); i++)
				{
					GameObject obj = chunks.get(x).getTiles().get(i);
					
					// Only check tiles in range
					if (obj.getxPos() > ResourceLoader.game.getCamera().getPlayer().getxPos() - 
					(ResourceLoader.game.getLoadDistance() * 32) &&
					obj.getxPos() < ResourceLoader.game.getCamera().getPlayer().getxPos() + 
					(ResourceLoader.game.getLoadDistance() * 32) 
					&&
					obj.getyPos() > ResourceLoader.game.getCamera().getPlayer().getyPos() - 
					(ResourceLoader.game.getLoadDistance() * 32) &&
					obj.getyPos() < ResourceLoader.game.getCamera().getPlayer().getyPos() + 
					(ResourceLoader.game.getLoadDistance() * 32))
					{
						
					}
					else
						continue;
					
					tilesLoaded++;
					chunks.get(x).getTiles().get(i).tick();
				}
			}
			
			totalTiles += chunks.get(x).getTiles().size();
		}
		
		topTilesLoaded = tilesLoaded;
		tilesLoaded = 0;
		topTotalTiles = totalTiles;
		totalTiles = 0;
	}
	
	/**
	 * Render all the Tiles within render distance.
	 */
	public void renderTiles(Graphics g)
	{
		for(int x = 0; x < chunks.size(); x++)
		{
			if (chunks.get(x).getxPos() > ResourceLoader.game.getCamera().getPlayer().getxPos() - 
				ResourceLoader.game.getChunkLoadDistance() &&
				chunks.get(x).getxPos() < ResourceLoader.game.getCamera().getPlayer().getxPos() + 
				ResourceLoader.game.getChunkLoadDistance() 
				&&
				chunks.get(x).getyPos() > ResourceLoader.game.getCamera().getPlayer().getyPos() - 
				ResourceLoader.game.getChunkLoadDistance() &&
				chunks.get(x).getyPos() < ResourceLoader.game.getCamera().getPlayer().getyPos() + 
				ResourceLoader.game.getChunkLoadDistance())
			{
				for(int i = 0; i < chunks.get(x).getTiles().size(); i++)
				{
					GameObject obj = chunks.get(x).getTiles().get(i);
					
					// Only check tiles in range
					if (obj.getxPos() > ResourceLoader.game.getCamera().getPlayer().getxPos() - 
					(ResourceLoader.game.getLoadDistance() * 32) &&
					obj.getxPos() < ResourceLoader.game.getCamera().getPlayer().getxPos() + 
					(ResourceLoader.game.getLoadDistance() * 32) 
					&&
					obj.getyPos() > ResourceLoader.game.getCamera().getPlayer().getyPos() - 
					(ResourceLoader.game.getLoadDistance() * 32) &&
					obj.getyPos() < ResourceLoader.game.getCamera().getPlayer().getyPos() + 
					(ResourceLoader.game.getLoadDistance() * 32))
					{
						
					}
					else
						continue;
					
					chunks.get(x).getTiles().get(i).renderObject(g);
				}
			}
		}
	}
	
	/**
	 * Get a tile at the specified set of coordinates.
	 * @param xPos - the x position of the tile
	 * @param yPos - the y position of the tile
	 * @return Tile - the tile at those coordinates
	 */
	public Tile getTileAtCoords(float xPos, float yPos)
	{
		for(int x = 0; x < chunks.size(); x++)
		{
			if (chunks.get(x).getxPos() > ResourceLoader.game.getCamera().getPlayer().getxPos() - 
				ResourceLoader.game.getChunkLoadDistance() &&
				chunks.get(x).getxPos() < ResourceLoader.game.getCamera().getPlayer().getxPos() + 
				ResourceLoader.game.getChunkLoadDistance() 
				&&
				chunks.get(x).getyPos() > ResourceLoader.game.getCamera().getPlayer().getyPos() - 
				ResourceLoader.game.getChunkLoadDistance() &&
				chunks.get(x).getyPos() < ResourceLoader.game.getCamera().getPlayer().getyPos() + 
				ResourceLoader.game.getChunkLoadDistance())
			{
				for(int i = 0; i < chunks.get(x).getTiles().size(); i++)
				{
					if (chunks.get(x).getTiles().get(i).getxPos() == xPos)
						if (chunks.get(x).getTiles().get(i).getyPos() == yPos)
							return chunks.get(x).getTiles().get(i);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Get the chunk that contains the tile at the
	 * given coordinates.
	 * @param xPos - the x position of the tile
	 * @param yPos - the y position of the tile
	 * @return Chunk - the chunk that contains the coords
	 */
	public Chunk getChunkWithTileAtCoords(float xPos, float yPos)
	{
		for(int x = 0; x < chunks.size(); x++)
		{
			if (chunks.get(x).getxPos() > ResourceLoader.game.getCamera().getPlayer().getxPos() - 
				ResourceLoader.game.getChunkLoadDistance() &&
				chunks.get(x).getxPos() < ResourceLoader.game.getCamera().getPlayer().getxPos() + 
				ResourceLoader.game.getChunkLoadDistance() 
				&&
				chunks.get(x).getyPos() > ResourceLoader.game.getCamera().getPlayer().getyPos() - 
				ResourceLoader.game.getChunkLoadDistance() &&
				chunks.get(x).getyPos() < ResourceLoader.game.getCamera().getPlayer().getyPos() + 
				ResourceLoader.game.getChunkLoadDistance())
			{
				
			}
		}
		
		return null;
	}
	
	/**
	 * Get the full list of Chunks.
	 * @return chunks
	 */
	public List<Chunk> getChunks()
	{
		return chunks;
	}
	
	/**
	 * Add a new Chunk to the Chunk list.
	 * @param chunk
	 */
	public void addChunk(Chunk chunk)
	{
		this.chunks.add(chunk);
	}
	
	/**
	 * Remove the given tile from the world.
	 * @param tile - the tile to remove
	 */
	public void breakTile(Tile tile)
	{
		for(int x = 0; x < chunks.size(); x++)
		{
			if (chunks.get(x).getxPos() > ResourceLoader.game.getCamera().getPlayer().getxPos() - 
				ResourceLoader.game.getChunkLoadDistance() &&
				chunks.get(x).getxPos() < ResourceLoader.game.getCamera().getPlayer().getxPos() + 
				ResourceLoader.game.getChunkLoadDistance() 
				&&
				chunks.get(x).getyPos() > ResourceLoader.game.getCamera().getPlayer().getyPos() - 
				ResourceLoader.game.getChunkLoadDistance() &&
				chunks.get(x).getyPos() < ResourceLoader.game.getCamera().getPlayer().getyPos() + 
				ResourceLoader.game.getChunkLoadDistance())
			{
				for(int i = 0; i < chunks.get(x).getTiles().size(); i++)
				{
					if (chunks.get(x).getTiles().get(i).getxPos()
						> ResourceLoader.game.getCamera().getPlayer().getxPos() - 100 &&
						chunks.get(x).getTiles().get(i).getxPos() 
						< ResourceLoader.game.getCamera().getPlayer().getxPos() + 100 
							&&
						chunks.get(x).getTiles().get(i).getyPos() 
						> ResourceLoader.game.getCamera().getPlayer().getyPos() - 100 &&
						chunks.get(x).getTiles().get(i).getyPos() 
						< ResourceLoader.game.getCamera().getPlayer().getyPos() + 100)
					{
						chunks.get(x).getTiles().remove(tile);
					}
					else
						chunks.get(x).getTiles().remove(tile);
				}
			}
		}
	}

	/**
	 * @return the topTilesLoaded
	 */
	public int getTopTilesLoaded()
	{
		return topTilesLoaded;
	}

	/**
	 * @return the topTotalTiles
	 */
	public int getTopTotalTiles()
	{
		return topTotalTiles;
	}
}