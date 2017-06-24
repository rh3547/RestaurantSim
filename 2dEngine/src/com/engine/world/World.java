package com.engine.world;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import com.engine.entity.Player;
import com.engine.resources.ResourceLoader;

/**
 * A world is a level for a game that is
 * constructed of many tiles.
 * Worlds can be generated in different ways.
 * 
 * Current Generation Options:
 * 1.) Image based color decryption
 * 
 * Current Generation Types:
 * 1.) 2d top down ( via loadWorld() )
 * 2.) Zig-zag isometric ( via loadWorldIsoZig() )
 * 3.) Diamond isometric ( via loadWorldIsoDiamond() )
 * 
 * @author Ryan Hochmuth
 *
 */
public class World 
{
	private int tileWidth; // The pixel width of a single tile
	private int tileHeight; // The pixel height of a single tile
	
	private int worldNum; // A unique id number representing a specific world
	private List<Tile> tiles; // A list of possible, different tiles in the game
	private TileAdder tileAdder; // A class to generate tiles specific to a game
	private Player player; // The player being used on this world
	private boolean playerPlaced = false; // Has the player been "spawned" in the world yet
	
	/*-----------------------------
	 * Color Decryption Generation
	 -----------------------------*/
	private BufferedImage worldBackImage = null; // The image to base this world gen background on
	private BufferedImage worldImage = null; // The image to base this world gen foreground on
	private BufferedImage worldFrontImage = null; // The image to base this world gen overlay on
	
	/**
	 * Create a new world with the following parameters.
	 * @param worldNum - the unique number of this world
	 * @param worldImages - the images used for color decryption
	 * @param tiles - the list of possible tiles used for generation
	 * @param tileAdder - the class to generate game specific tiles
	 * @param player - the player to "spawn" in the world
	 */
	public World(int worldNum, Image[] worldImages, List<Tile> tiles, TileAdder tileAdder, Player player, int tileWidth, int tileHeight)
	{
		this.worldNum = worldNum;
		this.worldImage = ResourceLoader.toBufferedImage(worldImages[0]);
		this.worldBackImage = ResourceLoader.toBufferedImage(worldImages[1]);
		this.worldFrontImage = ResourceLoader.toBufferedImage(worldImages[2]);
		this.tiles = tiles;
		this.tileAdder = tileAdder;
		this.player = player;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}
	
	/**
	 * Load the game world based on the world images
	 * using color decryption.
	 * This method will load the World into Chunks.
	 * A World loaded into Chunks is more efficient.
	 */
	public void loadWorld()
	{
		// Clear the game's previously loaded world data
		ResourceLoader.game.getWorldHandler().getChunks().clear();
		ResourceLoader.game.getObjectHandler().getGameObjects().clear();
		
		if (worldImage != null) // Load the world with color decryption
		{
			BufferedImage image = null;
			
			for (int step = 0; step < 3; step++) // Load the back, then mid, then front
			{
				switch(step)
				{
					case 0:
						image = worldBackImage;
						break;
						
					case 1:
						image = worldImage;
						break;
						
					case 2:
						image = worldFrontImage;
						break;
				}
				
				// For loops to increment through the pixels of the world image
				// by Chunk
				for(int y = 0; y < 32; y++)
				{
					for(int x = 0; x < 32; x++)
					{
						// Create a new chunk at the correct location based on the current index in the world images
						ResourceLoader.game.getWorldHandler().addChunk(new Chunk(x * (16 * tileWidth), y * (16 * tileWidth)));
						
						Chunk chunk = ResourceLoader.game.getWorldHandler().getChunks().get((y * 32) + x);
						
						for(int j = 0; j < 16; j++) // Increment through the chunk vertically
						{
							for(int i = 0; i < 16; i++) // Increment through the chunk horizontally
							{
								// Gets the per pixel color data of the
								// current pixel being tested
								int pixel = image.getRGB((x * 16) + i, (y * 16) + j);
								int red = (pixel >> 16) & 0xff;
								int green = (pixel >> 8) & 0xff;
								int blue = (pixel) & 0xff;
								
								// If the spawn color is detected, place the player there
								if (red == 255 && green == 0 && blue == 0)
								{
									if (!playerPlaced)
									{
										player.setxPos((chunk.getxPos() + (i * tileWidth)) - 1500);
										ResourceLoader.game.setMouseAnchorX((int)((chunk.getxPos() + (i * tileWidth)) - 1500));
										player.setyPos((chunk.getyPos() + (j * tileHeight)) - 3100);
										ResourceLoader.game.setMouseAnchorY((int)((chunk.getyPos() + (j * tileHeight)) - 3100));
										
										playerPlaced = true;
									}
									
									continue;
								}
								
								// Increment through every possible tile type
								for (int z = 0; z < tiles.size(); z++)
								{
									// If the current pixel's color matches
									// a Tile's, create a new Tile at that location
									if (red == tiles.get(z).getMapColor().getRed() && 
										green == tiles.get(z).getMapColor().getGreen() && 
										blue == tiles.get(z).getMapColor().getBlue())
									{
										// Place the tile through the tile adder
										tileAdder.addCustomTile(tiles.get(z), chunk, 
												(chunk.getxPos() + (i * tileWidth)) - 1500, 
												(chunk.getyPos() + (j * tileWidth)) - 3100);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Load the world as an isometric map
	 * in a zig-zag pattern.
	 * This method will load the world in chunks.
	 */
	public void loadWorldIsoZig()
	{
		ResourceLoader.game.getWorldHandler().getChunks().clear();
		ResourceLoader.game.getObjectHandler().getGameObjects().clear();
		
		if (worldImage != null) // Load the world with color decryption
		{
			BufferedImage image = null;
			
			for (int step = 0; step < 3; step++) // Load the back, then mid, then front
			{
				switch(step)
				{
					case 0:
						image = worldBackImage;
						break;
						
					case 1:
						image = worldImage;
						break;
						
					case 2:
						image = worldFrontImage;
						break;
				}
				
				// For loops to increment through the pixels of the world image
				// by Chunk
				for(int y = 0; y < 32; y++)
				{
					for(int x = 0; x < 32; x++)
					{
						if (y == 0)
							ResourceLoader.game.getWorldHandler().addChunk(new Chunk(x * (16 * tileWidth), y * (16 * tileHeight)));
						else
							ResourceLoader.game.getWorldHandler().addChunk(new Chunk(x * (16 * tileWidth), 
									(y * (16 * tileHeight)) - ((9 * tileHeight) * y)));
						
						Chunk chunk = ResourceLoader.game.getWorldHandler().getChunks().get((y * 32) + x);
						
						for(int j = 0; j < 16; j++) // Increment through the chunk vertically
						{
							for(int i = 0; i < 16; i++) // Increment through the chunk horizontally
							{
								// Gets the per pixel color data of the
								// current pixel being tested
								int pixel = image.getRGB((x * 16) + i, (y * 16) + j);
								int red = (pixel >> 16) & 0xff;
								int green = (pixel >> 8) & 0xff;
								int blue = (pixel) & 0xff;
								
								// If the spawn color is detected, place the player there
								if (red == 255 && green == 0 && blue == 0)
								{
									if (!playerPlaced)
									{
										player.setxPos((chunk.getxPos() + (i * tileWidth)) - 1500);
										ResourceLoader.game.setMouseAnchorX((int)((chunk.getxPos() + (i * tileWidth)) - 1500));
										player.setyPos((chunk.getyPos() + (j * tileHeight)) - 3100);
										ResourceLoader.game.setMouseAnchorY((int)((chunk.getyPos() + (j * tileHeight)) - 3100));
										
										playerPlaced = true;
									}
									
									continue;
								}
								
								// Increment through every possible tile type
								for (int z = 0; z < tiles.size(); z++)
								{
									// If the current pixel's color matches
									// a Tile's, create a new Tile at that location
									if (red == tiles.get(z).getMapColor().getRed() && 
										green == tiles.get(z).getMapColor().getGreen() && 
										blue == tiles.get(z).getMapColor().getBlue())
									{
										if (j == 0)
											// Place the tile
											tileAdder.addCustomTile(tiles.get(z), chunk, 
													(chunk.getxPos() + (i * tileWidth)) - 1500, 
													((chunk.getyPos() + (j * tileHeight))) - 3100);
										else if (j == 1)
											// Place the tile 
											tileAdder.addCustomTile(tiles.get(z), chunk, 
													((chunk.getxPos() + (i * tileWidth)) + tileWidth / 2) - 1500, 
													(((chunk.getyPos() + (j * (tileHeight))) - tileHeight / 2)) - 3100);
										else
											if (j % 2 == 0)
											{
												// Place the tile
												tileAdder.addCustomTile(tiles.get(z), chunk, 
														(chunk.getxPos() + (i * tileWidth)) - 1500, 
														((chunk.getyPos() + ((j - (j / 2)) * tileHeight))) - 3100);
											}
											else
												// Place the tile 
												tileAdder.addCustomTile(tiles.get(z), chunk, 
														((chunk.getxPos() + (i * tileWidth)) + tileWidth / 2) - 1500, 
														(((chunk.getyPos() + ((j - (j / 2)) * (tileHeight))) - tileHeight / 2)) - 3100);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Load the world as an isometric map
	 * in a diamond pattern.
	 * This method will load the world in chunks.
	 */
	public void loadWorldIsoDiamond()
	{
		ResourceLoader.game.getWorldHandler().getChunks().clear();
		ResourceLoader.game.getObjectHandler().getGameObjects().clear();
		
		RGB[][] tileArray = new RGB[16][16];
		int chunkSizeX = (16 * tileWidth);
		int chunkSizeY = (16 * tileHeight);
		
		if (worldImage != null) // Load the world with color decryption
		{
			BufferedImage image = null;
			
			for (int step = 0; step < 3; step++) // Load the back, then mid, then front
			{
				switch(step)
				{
					case 0:
						image = worldBackImage;
						break;
						
					case 1:
						image = worldImage;
						break;
						
					case 2:
						image = worldFrontImage;
						break;
				}
				
				// For loops to increment through the pixels of the world image
				// by Chunk
				for(int y = 0; y < 32; y++)
				{
					for(int x = 0; x < 32; x++)
					{
						ResourceLoader.game.getWorldHandler().addChunk(new Chunk((0 - (y * (chunkSizeX / 2))) + (x * (chunkSizeX / 2)), 
								(0 + (y * (chunkSizeY / 2))) + (x * (chunkSizeY / 2))));
						
						Chunk chunk = ResourceLoader.game.getWorldHandler().getChunks().get((y * 32) + x);
						
						for(int j = 0; j < 16; j++) // Increment through the chunk vertically
						{
							for(int i = 0; i < 16; i++) // Increment through the chunk horizontally
							{
								// Gets the per pixel color data of the
								// current pixel being tested
								int pixel = image.getRGB((x * 16) + i, (y * 16) + j);
								int red = (pixel >> 16) & 0xff;
								int green = (pixel >> 8) & 0xff;
								int blue = (pixel) & 0xff;
								
								tileArray[i][j] = new RGB(red, green, blue);
							}
						}
						
						for (int i = 0; i < 16; i++)
						{
						    for (int j = 15; j >= 0; j--)
						    {
						    	for (int z = 0; z < tiles.size(); z++)
								{
						    		if (tileArray[i][j].getRed() == 255 && 
						    			tileArray[i][j].getGreen() == 0 && 
						    			tileArray[i][j].getBlue() == 0)
									{
										if (!playerPlaced)
										{
											player.setxPos((chunk.getxPos() + (i * tileWidth)) - 1500);
											ResourceLoader.game.setMouseAnchorX((int)((chunk.getxPos() + (i * tileWidth)) - 1500));
											player.setyPos((chunk.getyPos() + (j * tileHeight)) - 3100);
											ResourceLoader.game.setMouseAnchorY((int)((chunk.getyPos() + (j * tileHeight)) - 3100));
											
											playerPlaced = true;
										}
										
										continue;
									}
						    		
									// If the current pixel's color matches
									// a Tile's, create a new Tile at that location
									if (tileArray[i][j].getRed() == tiles.get(z).getMapColor().getRed() && 
										tileArray[i][j].getGreen() == tiles.get(z).getMapColor().getGreen() && 
										tileArray[i][j].getBlue() == tiles.get(z).getMapColor().getBlue())
									{
										// Place the tile 
										tileAdder.addCustomTile(tiles.get(z), chunk, 
												((chunk.getxPos() + (j * tileWidth / 2)) + (i * tileWidth / 2)) - 1500, 
												(((chunk.getyPos() + (i * (tileHeight / 2))) - (j * tileHeight / 2))) - 3100);
									}
								}
						    }
						}
					}
				}
			}
		}
	}

	/**
	 * Get the id number of this World.
	 * @return worldNum
	 */
	public int getWorldNum()
	{
		return worldNum;
	}

	/**
	 * Get the width of a tile in this world.
	 * @return tileWidth
	 */
	public int getTileWidth()
	{
		return tileWidth;
	}

	/**
	 * Set the width of a single tile in this world.
	 * @param tileWidth - the width of a single tile
	 */
	public void setTileWidth(int tileWidth)
	{
		this.tileWidth = tileWidth;
	}

	/**
	 * Get the height of a single tile in this world.
	 * @return tileHeight
	 */
	public int getTileHeight()
	{
		return tileHeight;
	}

	/**
	 * Set the height of a single tile in this world.
	 * @param tileHeight - the height of a single tile
	 */
	public void setTileHeight(int tileHeight)
	{
		this.tileHeight = tileHeight;
	}
}

/**
 * The RGB class is an inner class inside
 * the World class.  It is used to hold
 * the RGB (red, green, blue) color data
 * of a pixel when generating a world using
 * color decryption.
 * 
 * @author Ryan Hochmuth
 *
 */
class RGB
{
	private int red;
	private int green;
	private int blue;
	
	public RGB(int red, int green, int blue)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public int getRed()
	{
		return red;
	}
	
	public int getGreen()
	{
		return green;
	}
	
	public int getBlue()
	{
		return blue;
	}
}
