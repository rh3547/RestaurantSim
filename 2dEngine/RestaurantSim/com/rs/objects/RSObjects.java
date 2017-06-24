package com.rs.objects;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

import com.engine.data.Bank;
import com.engine.data.Catalog;
import com.engine.data.CatalogItem;
import com.engine.entity.Player;
import com.engine.gameObject.GameObject;
import com.engine.resources.ResourceLoader;
import com.engine.world.Camera;
import com.rs.main.RSResources;
import com.rs.tiles.RSTile;
import com.rs.tiles.RSTiles;

public class RSObjects
{
	// Player
	public static Player player = new Player(0, 0, RSIds.player, 
			RSResources.playerImages, 
			false, null, "player1");
	
	// The game Camera that will follow the Player
	public static Camera camera = new Camera(0, 0, player);
	
	// Catalogs
	public static CatalogInfoPanel<RSCatalogItem> panel;
	
	public static Catalog<RSCatalogItem> constructCatalog;
	public static Catalog<RSCatalogItem> furnishCatalog;
	public static Catalog<RSCatalogItem> ingredientsCatalog;
	
	// Banks
	public static Bank money = new Bank(2000);
	
	public static void setupObjects()
	{
		player.setCollisionType(GameObject.NO_COLLIDE);
		player.enablePhysics(false);
		
		Image[] nextBtnImages = new Image[3];
		Image[] prevBtnImages = new Image[3];
		nextBtnImages[0] = RSResources.nextBtn;
		nextBtnImages[1] = RSResources.nextBtnHover;
		nextBtnImages[2] = RSResources.nextBtnDisabled;
		prevBtnImages[0] = RSResources.prevBtn;
		prevBtnImages[1] = RSResources.prevBtnHover;
		prevBtnImages[2] = RSResources.prevBtnDisabled;
		
		// Catalog info panel
		panel = new CatalogInfoPanel<RSCatalogItem>();
		
		// Catalog: Construct
		constructCatalog = new Catalog<RSCatalogItem>(75, 75, 20, "Construct", RSResources.catalogBg, nextBtnImages, prevBtnImages);
		constructCatalog.setSearchBox(150, 35, RSResources.fontMain24, new Color(53, 53, 53), RSResources.searchBox);
		constructCatalog.setSearchBoxOffsetX(175);
		constructCatalog.setSearchBoxOffsetY(508);
		constructCatalog.setSearchBoxTextOffsetX(10);
		constructCatalog.setSearchBoxTextOffsetY(25);
		constructCatalog.setTitleFont(RSResources.fontMain48);
		constructCatalog.setTitleColor(new Color(53, 53, 53));
		constructCatalog.setTextOffsetX(137);
		constructCatalog.setTextOffsetY(50);
		constructCatalog.setTileOffsetX(30);
		constructCatalog.setTileOffsetY(110);
		constructCatalog.setNextBtnOffsetX(-90);
		constructCatalog.setNextBtnOffsetY(-40);
		constructCatalog.setPrevBtnOffsetX(35);
		constructCatalog.setPrevBtnOffsetY(-40);
		 
		addNewCatalogItem(constructCatalog, "Rough Wood Floor", "Rundown, rough...but cheap!", 2.00, RSTile.FLOOR, RSTiles.tileWoodFloor1 
				, RSResources.woodFloor1);
		addNewCatalogItem(constructCatalog, "Light Wood Floor", "A slightly less ugly wooden floor.", 3.00, RSTile.FLOOR, RSTiles.tileWoodFloor2
				, RSResources.woodFloor2);
		addNewCatalogItem(constructCatalog, "Dark Wood Floor", "A dark wood floor.", 3.25, RSTile.FLOOR, RSTiles.tileWoodFloor4
				, RSResources.woodFloor4);
		addNewCatalogItem(constructCatalog, "Disarray Wood Floor", "Planks in disarray.", 3.50, RSTile.FLOOR, RSTiles.tileWoodFloor5
				, RSResources.woodFloor5);
		addNewCatalogItem(constructCatalog, "Nice Wood Floor", "A decent quality wood floor.", 4.00, RSTile.FLOOR, RSTiles.tileWoodFloor3
				, RSResources.woodFloor3);
		addNewCatalogItem(constructCatalog, "Elegant Wood Floor", "Prim...and proper!", 5.00, RSTile.FLOOR, RSTiles.tileWoodFloor6
				, RSResources.woodFloor6);
		addNewCatalogItem(constructCatalog, "Black Carpet", "It's black...and fuzzy.", 4.00, RSTile.FLOOR, RSTiles.tileCarpetFloorBlack
				, RSResources.carpetFloorBlack);
		addNewCatalogItem(constructCatalog, "Blue Carpet", "It's blue...and fuzzy.", 4.00, RSTile.FLOOR, RSTiles.tileCarpetFloorBlue
				, RSResources.carpetFloorBlue);
		addNewCatalogItem(constructCatalog, "Green Carpet", "It's green...and fuzzy.", 4.00, RSTile.FLOOR, RSTiles.tileCarpetFloorGreen
				, RSResources.carpetFloorGreen);
		addNewCatalogItem(constructCatalog, "Orange Carpet", "It's orange...and fuzzy.", 4.00, RSTile.FLOOR, RSTiles.tileCarpetFloorOrange
				, RSResources.carpetFloorOrange);
		addNewCatalogItem(constructCatalog, "Pink Carpet", "It's pink...and fuzzy.", 4.00, RSTile.FLOOR, RSTiles.tileCarpetFloorPink
				, RSResources.carpetFloorPink);
		addNewCatalogItem(constructCatalog, "Purple Carpet", "It's purple...and fuzzy.", 4.00, RSTile.FLOOR, RSTiles.tileCarpetFloorPurple
				, RSResources.carpetFloorPurple);
		addNewCatalogItem(constructCatalog, "Red Carpet", "It's red...and fuzzy.", 4.00, RSTile.FLOOR, RSTiles.tileCarpetFloorRed
				, RSResources.carpetFloorRed);
		addNewCatalogItem(constructCatalog, "Tan Carpet", "It's tan...and fuzzy.", 4.00, RSTile.FLOOR, RSTiles.tileCarpetFloorTan
				, RSResources.carpetFloorTan);
		addNewCatalogItem(constructCatalog, "White Carpet", "It's white...and fuzzy.", 4.00, RSTile.FLOOR, RSTiles.tileCarpetFloorWhite
				, RSResources.carpetFloorWhite);
		addNewCatalogItem(constructCatalog, "Yellow", "It's yellow...and fuzzy.", 4.00, RSTile.FLOOR, RSTiles.tileCarpetFloorYellow
				, RSResources.carpetFloorYellow);
		addNewCatalogItem(constructCatalog, "Generic Tile", "A generic tile.", 2.00, RSTile.FLOOR, RSTiles.tileTileFloor1
				, RSResources.tileFloor1);
		addNewCatalogItem(constructCatalog, "Large Tile", "A large, plain tile.", 2.50, RSTile.FLOOR, RSTiles.tileTileFloor3
				, RSResources.tileFloor3);
		addNewCatalogItem(constructCatalog, "Diamond Tile", "Quite the beautiful sight!", 4.00, RSTile.FLOOR, RSTiles.tileTileFloor2
				, RSResources.tileFloor2);
		addNewCatalogItem(constructCatalog, "Small Black Tile", "Small tiles tinted black.", 3.00, RSTile.FLOOR, RSTiles.tileTileFloorBlack
				, RSResources.tileFloorBlack);
		addNewCatalogItem(constructCatalog, "Small Blue Tile", "Small tiles tinted blue.", 3.00, RSTile.FLOOR, RSTiles.tileTileFloorBlue
				, RSResources.tileFloorBlue);
		addNewCatalogItem(constructCatalog, "Small Tan Tile", "Small tiles tinted tan.", 3.00, RSTile.FLOOR, RSTiles.tileTileFloorTan
				, RSResources.tileFloorTan);
		addNewCatalogItem(constructCatalog, "Small White Tile", "Small tiles tinted white.", 3.00, RSTile.FLOOR, RSTiles.tileTileFloorWhite
				, RSResources.tileFloorWhite);
				
		addNewCatalogItem(constructCatalog, "White Plaster Wall", "It's totally plastered.", 1.50, RSTile.WALL, RSTiles.tileWallPlasterWhite
				, RSResources.plasterWallWhite);
		addNewCatalogItem(constructCatalog, "Limestone Brick Wall", "A brick wall.", 2.00, RSTile.WALL, RSTiles.tileWallBrick1
				, RSResources.brickWall1);
		addNewCatalogItem(constructCatalog, "Cinder Brick Wall", "Cinder bricks!", 2.50, RSTile.WALL, RSTiles.tileWallBrick2
				, RSResources.brickWall2);
		
		// Catalog: Furnish
		furnishCatalog = new Catalog<RSCatalogItem>(75, 75, 20, "Furnish", RSResources.catalogBg, nextBtnImages, prevBtnImages);
		furnishCatalog.setSearchBox(150, 35, RSResources.fontMain24, new Color(53, 53, 53), RSResources.searchBox);
		furnishCatalog.setSearchBoxOffsetX(175);
		furnishCatalog.setSearchBoxOffsetY(508);
		furnishCatalog.setSearchBoxTextOffsetX(10);
		furnishCatalog.setSearchBoxTextOffsetY(25);
		furnishCatalog.setTitleFont(RSResources.fontMain48);
		furnishCatalog.setTitleColor(new Color(53, 53, 53));
		furnishCatalog.setTextOffsetX(175);
		furnishCatalog.setTextOffsetY(50);
		furnishCatalog.setTileOffsetX(30);
		furnishCatalog.setTileOffsetY(110);
		furnishCatalog.setNextBtnOffsetX(-90);
		furnishCatalog.setNextBtnOffsetY(-40);
		furnishCatalog.setPrevBtnOffsetX(35);
		furnishCatalog.setPrevBtnOffsetY(-40);
		
		addNewCatalogItem(furnishCatalog, "Basic Wood Chair", "Definitly a start-up chair.", 15.00, RSTile.FURNITURE, RSTiles.tileFurnitureChair1
				, RSResources.woodChair1[1]);
		addNewCatalogItem(furnishCatalog, "Padded Wood Chair", "Slightly more comfortable!", 20.00, RSTile.FURNITURE, RSTiles.tileFurnitureChair2
				, RSResources.woodChair2[1]);
		addNewCatalogItem(furnishCatalog, "Country Wood Chair", "Sausage gravy and biscuits...nuff said.", 20.00, RSTile.FURNITURE, RSTiles.tileFurnitureChair3
				, RSResources.woodChair3[1]);
		addNewCatalogItem(furnishCatalog, "Small Wood Table", "A small, run of the mill table.", 40.00, RSTile.FURNITURE, RSTiles.tileFurnitureTable1
				, RSResources.woodTable1);
		addNewCatalogItem(furnishCatalog, "Round Wood Table", "A small, round table.", 55.00, RSTile.FURNITURE, RSTiles.tileFurnitureTable3
				, RSResources.woodTable3);
		addNewCatalogItem(furnishCatalog, "Medium Wood Table", "A table for a typical family.", 80.00, RSTile.FURNITURE, RSTiles.tileFurnitureTable2
				, RSResources.woodTable2[1]);
		
		// Catalog: Ingredients
		ingredientsCatalog = new Catalog<RSCatalogItem>(75, 75, 20, "Ingredients", RSResources.catalogBg, nextBtnImages, prevBtnImages);
		ingredientsCatalog.setSearchBox(150, 35, RSResources.fontMain24, new Color(53, 53, 53), RSResources.searchBox);
		ingredientsCatalog.setSearchBoxOffsetX(175);
		ingredientsCatalog.setSearchBoxOffsetY(508);
		ingredientsCatalog.setSearchBoxTextOffsetX(10);
		ingredientsCatalog.setSearchBoxTextOffsetY(25);
		ingredientsCatalog.setTitleFont(RSResources.fontMain48);
		ingredientsCatalog.setTitleColor(new Color(53, 53, 53));
		ingredientsCatalog.setTextOffsetX(125);
		ingredientsCatalog.setTextOffsetY(50);
		ingredientsCatalog.setTileOffsetX(30);
		ingredientsCatalog.setTileOffsetY(110);
		ingredientsCatalog.setNextBtnOffsetX(-90);
		ingredientsCatalog.setNextBtnOffsetY(-40);
		ingredientsCatalog.setPrevBtnOffsetX(35);
		ingredientsCatalog.setPrevBtnOffsetY(-40);
		
		addNewCatalogItem(ingredientsCatalog, "White Bread Loaf", "A fresh baked loaf of white bread.", 3.00, RSTile.FLOOR, null
				, RSResources.breadLoaf);
	}
	
	/**
	 * Create a new RSCatalogItem and add it to a catalog.
	 */
	public static void addNewCatalogItem(Catalog<RSCatalogItem> catalog, String name, String desc, double price, int tileType, 
			GameObject obj, Image image)
	{
		CatalogItem<RSCatalogItem> item = new CatalogItem<RSCatalogItem>(
				new RSCatalogItem(name, desc, price, tileType, obj), 
				ResourceLoader.scaleImage(image, 48, 48), 14, 14, 75, 75, RSResources.catalogBtn, RSResources.catalogBtnHover);
		
		item.addKeyWord(name.toLowerCase());
		
		switch (item.getData().getType())
		{
			case RSTile.FLOOR:
				item.addKeyWord("floor");
				break;
				
			case RSTile.WALL:
				item.addKeyWord("wall");
				break;
				
			case RSTile.FURNITURE:
				item.addKeyWord("furniture");
				break;
		}
		
		catalog.addToCatalog(item);	
	}
	
	/**
	 * Create a new RSCatalogItem and add it to a catalog.
	 */
	public static void addNewCatalogItemWithKeywords(Catalog<RSCatalogItem> catalog, String name, String desc, double price, int tileType, 
			GameObject obj, Image image, List<String> keyWords)
	{
		CatalogItem<RSCatalogItem> item = new CatalogItem<RSCatalogItem>(
				new RSCatalogItem(name, desc, price, tileType, obj), 
				ResourceLoader.scaleImage(image, 48, 48), 14, 14, 75, 75, RSResources.catalogBtn, RSResources.catalogBtnHover);
		
		item.addKeyWord(name.toLowerCase());
		
		switch (item.getData().getType())
		{
			case RSTile.FLOOR:
				item.addKeyWord("floor");
				break;
				
			case RSTile.WALL:
				item.addKeyWord("wall");
				break;
				
			case RSTile.FURNITURE:
				item.addKeyWord("furniture");
				break;
		}
		
		for (int x = 0; x < keyWords.size(); x++)
		{
			item.addKeyWord(keyWords.get(x).toLowerCase());
		}
		
		catalog.addToCatalog(item);	
	}
}