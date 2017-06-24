package com.engine.world;

/**
 * A tile loader is used in conjunction
 * with a world to load custom tiles into
 * the world without having to hard code
 * a new world class.
 * 
 * @author Ryan Hochmuth
 *
 */
public abstract class TileAdder
{
	/**
	 * Load in a specific custom tile.
	 * @param tile - the tile to add
	 * @param chunk - the chunk to add the tile to
	 * @param i - x position
	 * @param j - y position
	 */
	public abstract void addCustomTile(Tile tile, Chunk chunk, float xPos, float yPos);
}