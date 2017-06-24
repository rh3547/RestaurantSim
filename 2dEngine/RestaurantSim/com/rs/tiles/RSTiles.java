package com.rs.tiles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.engine.gameObject.GameObject;
import com.engine.resources.ResourceLoader;
import com.engine.world.Tile;
import com.rs.main.RSResources;
import com.rs.objects.RSIds;

public class RSTiles
{
	private static final int TILE_WIDTH = 64;
	private static final int TILE_HEIGHT = 64;
	
	/*-----------------------------
	 * Tiles
	 ----------------------------*/
	// Every tile in the game
	private static List<Tile> tiles = new ArrayList<Tile>();
	
	public static RSTile tileGrid;
	
	// Interior
	
	// Floors
	public static RSTile tileWoodFloor1;
	public static RSTile tileWoodFloor2;
	public static RSTile tileWoodFloor3;
	public static RSTile tileWoodFloor4;
	public static RSTile tileWoodFloor5;
	public static RSTile tileWoodFloor6;
	public static RSTile tileCarpetFloorBlack;
	public static RSTile tileCarpetFloorBlue;
	public static RSTile tileCarpetFloorGreen;
	public static RSTile tileCarpetFloorOrange;
	public static RSTile tileCarpetFloorPink;
	public static RSTile tileCarpetFloorPurple;
	public static RSTile tileCarpetFloorRed;
	public static RSTile tileCarpetFloorTan;
	public static RSTile tileCarpetFloorWhite;
	public static RSTile tileCarpetFloorYellow;
	public static RSTile tileTileFloor1;
	public static RSTile tileTileFloor2;
	public static RSTile tileTileFloor3;
	public static RSTile tileTileFloorBlack;
	public static RSTile tileTileFloorBlue;
	public static RSTile tileTileFloorTan;
	public static RSTile tileTileFloorWhite;
	
	// Walls
	public static RSTile tileWallPlasterWhite;
	public static RSTile tileWallBrick1;
	public static RSTile tileWallBrick2;
	
	// Furniture
	public static RSTile tileFurnitureChair1;
	public static RSTile tileFurnitureChair2;
	public static RSTile tileFurnitureChair3;
	public static RSTile tileFurnitureTable1;
	public static RSTile tileFurnitureTable2;
	public static RSTile tileFurnitureTable3;
	
	// Exterior
	public static RSTile tileGrass;
	public static RSTile tileRoad;
	public static RSTile tileSidewalk;
	
	/**
	 * Load the tile data.
	 */
	public static void loadTiles()
	{
		try
		{
			// Grid
			tileGrid = new RSTile(RSIds.tileGrid, ResourceLoader.scaleImage(RSResources.grid, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Grid", RSTile.FLOOR);
			tiles.add(tileGrid); 
			
			/*
			 * Floor
			 */
			
			// Wood floor 1
			tileWoodFloor1 = new RSTile(RSIds.tileWoodFloor1, ResourceLoader.scaleImage(RSResources.woodFloor1, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(175, 150, 112), GameObject.NO_COLLIDE, "Rough Wood Floor", RSTile.FLOOR);
			tiles.add(tileWoodFloor1);
			
			// Wood Floor 2
			tileWoodFloor2 = new RSTile(RSIds.tileWoodFloor2, ResourceLoader.scaleImage(RSResources.woodFloor2, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Light Wood Floor", RSTile.FLOOR);
			tiles.add(tileWoodFloor2);
			
			// Wood Floor 3
			tileWoodFloor3 = new RSTile(RSIds.tileWoodFloor3, ResourceLoader.scaleImage(RSResources.woodFloor3, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Nice Wood Floor", RSTile.FLOOR);
			tiles.add(tileWoodFloor3);
			
			// Wood Floor 4
			tileWoodFloor4 = new RSTile(RSIds.tileWoodFloor4, ResourceLoader.scaleImage(RSResources.woodFloor4, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Dark Wood Floor", RSTile.FLOOR);
			tiles.add(tileWoodFloor4);
			
			// Wood Floor 5
			tileWoodFloor5 = new RSTile(RSIds.tileWoodFloor5, ResourceLoader.scaleImage(RSResources.woodFloor5, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Disarray Wood Floor", RSTile.FLOOR);
			tiles.add(tileWoodFloor5);
			
			// Wood Floor 6
			tileWoodFloor6 = new RSTile(RSIds.tileWoodFloor6, ResourceLoader.scaleImage(RSResources.woodFloor6, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Elegant Wood Floor", RSTile.FLOOR);
			tiles.add(tileWoodFloor6);
			
			// Carpet Black
			tileCarpetFloorBlack = new RSTile(RSIds.tileCarpetFloorBlack, ResourceLoader.scaleImage(RSResources.carpetFloorBlack, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Black Carpet", RSTile.FLOOR);
			tiles.add(tileCarpetFloorBlack);
			
			// Carpet Blue
			tileCarpetFloorBlue = new RSTile(RSIds.tileCarpetFloorBlue, ResourceLoader.scaleImage(RSResources.carpetFloorBlue, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Blue Carpet", RSTile.FLOOR);
			tiles.add(tileCarpetFloorBlue);
			
			// Carpet Green
			tileCarpetFloorGreen = new RSTile(RSIds.tileCarpetFloorGreen, ResourceLoader.scaleImage(RSResources.carpetFloorGreen, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Green Carpet", RSTile.FLOOR);
			tiles.add(tileCarpetFloorGreen);
			
			// Carpet Orange
			tileCarpetFloorOrange = new RSTile(RSIds.tileCarpetFloorOrange, ResourceLoader.scaleImage(RSResources.carpetFloorOrange, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Orange Carpet", RSTile.FLOOR);
			tiles.add(tileCarpetFloorOrange);
			
			// Carpet Pink
			tileCarpetFloorPink = new RSTile(RSIds.tileCarpetFloorPink, ResourceLoader.scaleImage(RSResources.carpetFloorPink, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Pink Carpet", RSTile.FLOOR);
			tiles.add(tileCarpetFloorPink);
			
			// Carpet Purple
			tileCarpetFloorPurple = new RSTile(RSIds.tileCarpetFloorPurple, ResourceLoader.scaleImage(RSResources.carpetFloorPurple, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Purple Carpet", RSTile.FLOOR);
			tiles.add(tileCarpetFloorPurple);
			
			// Carpet Red
			tileCarpetFloorRed = new RSTile(RSIds.tileCarpetFloorRed, ResourceLoader.scaleImage(RSResources.carpetFloorRed, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Red Carpet", RSTile.FLOOR);
			tiles.add(tileCarpetFloorRed);
			
			// Carpet Tan
			tileCarpetFloorTan = new RSTile(RSIds.tileCarpetFloorTan, ResourceLoader.scaleImage(RSResources.carpetFloorTan, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Tan Carpet", RSTile.FLOOR);
			tiles.add(tileCarpetFloorTan);
			
			// Carpet White
			tileCarpetFloorWhite = new RSTile(RSIds.tileCarpetFloorWhite, ResourceLoader.scaleImage(RSResources.carpetFloorWhite, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Black Carpet", RSTile.FLOOR);
			tiles.add(tileCarpetFloorWhite);
			
			// Carpet Yellow
			tileCarpetFloorYellow = new RSTile(RSIds.tileCarpetFloorYellow, ResourceLoader.scaleImage(RSResources.carpetFloorYellow, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Black Carpet", RSTile.FLOOR);
			tiles.add(tileCarpetFloorYellow);
			
			// Tile 1
			tileTileFloor1 = new RSTile(RSIds.tileTileFloor1, ResourceLoader.scaleImage(RSResources.tileFloor1, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Generic Tile", RSTile.FLOOR);
			tiles.add(tileTileFloor1);
			
			// Tile 2
			tileTileFloor2 = new RSTile(RSIds.tileTileFloor2, ResourceLoader.scaleImage(RSResources.tileFloor2, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Diamond Tile", RSTile.FLOOR);
			tiles.add(tileTileFloor2);
			
			// Tile 3
			tileTileFloor3 = new RSTile(RSIds.tileTileFloor3, ResourceLoader.scaleImage(RSResources.tileFloor3, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Large Tile", RSTile.FLOOR);
			tiles.add(tileTileFloor3);
			
			// Tile Black
			tileTileFloorBlack = new RSTile(RSIds.tileTileFloorBlack, ResourceLoader.scaleImage(RSResources.tileFloorBlack, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Black Small Tile", RSTile.FLOOR);
			tiles.add(tileTileFloorBlack);
			
			// Tile Blue
			tileTileFloorBlue = new RSTile(RSIds.tileTileFloorBlue, ResourceLoader.scaleImage(RSResources.tileFloorBlue, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Blue Small Tile", RSTile.FLOOR);
			tiles.add(tileTileFloorBlue);
			
			// Tile Tan
			tileTileFloorTan = new RSTile(RSIds.tileTileFloorTan, ResourceLoader.scaleImage(RSResources.tileFloorTan, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Tan Small Tile", RSTile.FLOOR);
			tiles.add(tileTileFloorTan);
			
			// Tile White
			tileTileFloorWhite = new RSTile(RSIds.tileTileFloorWhite, ResourceLoader.scaleImage(RSResources.tileFloorWhite, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "White Small Tile", RSTile.FLOOR);
			tiles.add(tileTileFloorWhite);
			
			/*
			 * Wall
			 */
			
			// Plaster Wall White
			tileWallPlasterWhite = new RSTile(RSIds.tileWallPlasterWhite, ResourceLoader.scaleImage(RSResources.plasterWallWhite, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(125, 115, 100), GameObject.NO_COLLIDE, "White Plaster Wall", RSTile.WALL);
			tiles.add(tileWallPlasterWhite);
			
			// Brick Wall 1
			tileWallBrick1 = new RSTile(RSIds.tileWallBrick1, ResourceLoader.scaleImage(RSResources.brickWall1, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Limestone Brick Wall", RSTile.WALL);
			tiles.add(tileWallBrick1);
			
			// Brick Wall 1
			tileWallBrick2 = new RSTile(RSIds.tileWallBrick2, ResourceLoader.scaleImage(RSResources.brickWall2, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Cinder Brick Wall", RSTile.WALL);
			tiles.add(tileWallBrick2);
			
			/*
			 * Furniture
			 */
			
			// Chair 1
			tileFurnitureChair1 = new RSTile(RSIds.tileFurnitureChair1, RSResources.woodChair1, 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Basic Wood Chair", RSTile.FURNITURE);
			
			// Chair 2
			tileFurnitureChair2 = new RSTile(RSIds.tileFurnitureChair2, RSResources.woodChair2, 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Padded Wood Chair", RSTile.FURNITURE);
			tiles.add(tileFurnitureChair2);
			
			// Chair 3
			tileFurnitureChair3 = new RSTile(RSIds.tileFurnitureChair3, RSResources.woodChair3, 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Country Wood Chair", RSTile.FURNITURE);
			tiles.add(tileFurnitureChair3);
			
			// Table 1
			tileFurnitureTable1 = new RSTile(RSIds.tileFurnitureTable1, RSResources.woodTable1, 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Small Wood Table", RSTile.FURNITURE);
			tiles.add(tileFurnitureTable1);
			
			// Table 2
			tileFurnitureTable2 = new RSTile(RSIds.tileFurnitureTable2, RSResources.woodTable2, 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Medium Wood Table", RSTile.FURNITURE);
			
			// Table 3
			tileFurnitureTable3 = new RSTile(RSIds.tileFurnitureTable3, RSResources.woodTable3, 
					false, new Color(255, 255, 255), GameObject.NO_COLLIDE, "Circle Wood Table", RSTile.FURNITURE);
			tiles.add(tileFurnitureTable3);
			
			/*
			 * Exterior
			 */
			
			// Grass
			tileGrass = new RSTile(RSIds.tileGrass, ResourceLoader.scaleImage(RSResources.grass, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(32, 160, 50), GameObject.NO_COLLIDE, "Grass", RSTile.UNEDITABLE);
			tiles.add(tileGrass);
			
			// Road
			tileRoad = new RSTile(RSIds.tileRoad, ResourceLoader.scaleImage(RSResources.road, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(27, 27, 27), GameObject.NO_COLLIDE, "Road", RSTile.UNEDITABLE);
			tiles.add(tileRoad);
			
			// Sidewalk
			tileSidewalk = new RSTile(RSIds.tileSidewalk, ResourceLoader.scaleImage(RSResources.sidewalk, TILE_WIDTH, TILE_HEIGHT), 
					false, new Color(77, 77, 77), GameObject.NO_COLLIDE, "Sidewalk", RSTile.UNEDITABLE);
			tiles.add(tileSidewalk);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("A tile failed to load.  This is most likely because a texture was not found.");
		}
	}
	
	/**
	 * @return the tiles
	 */
	public static List<Tile> getTiles()
	{
		return tiles;
	}
}