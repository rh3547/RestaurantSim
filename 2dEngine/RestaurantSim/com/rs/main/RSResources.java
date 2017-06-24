package com.rs.main;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.engine.input.Key;
import com.engine.main.Game;
import com.engine.resources.ResourceLoader;
import com.engine.world.World;
import com.rs.objects.RSObjects;
import com.rs.tiles.RSTiles;
import com.rs.world.RSTileAdder;

public class RSResources extends ResourceLoader
{
	public RSResources(Game game)
	{
		super(game);
	}
	
	/*-----------------------------------
	 * Fonts
	 ----------------------------------*/
	public static Font fontMain16;
	public static Font fontMain24;
	public static Font fontMain32;
	public static Font fontMain48;
	public static Font fontMain64;

	@Override
	public void loadFonts()
	{
		fontMain16 = registerFont("fonts/CaviarDreams_Bold.ttf", "Caviar Dreams", 16, false);
		fontMain24 = registerFont("fonts/CaviarDreams_Bold.ttf", "Caviar Dreams", 24, false);
		fontMain32 = registerFont("fonts/CaviarDreams_Bold.ttf", "Caviar Dreams", 32, false);
		fontMain48 = registerFont("fonts/CaviarDreams_Bold.ttf", "Caviar Dreams", 48, false);
		fontMain64 = registerFont("fonts/CaviarDreams_Bold.ttf", "Caviar Dreams", 64, false);
	}

	/*-----------------------------
	 * Backgrounds
	 ----------------------------*/
	public static Image mainBg;
	public static Image basicBg;
	
	@Override
	public void loadBackgrounds()
	{
		ImageIcon mainBgII = new ImageIcon(game.getRespath() + "graphics/backgrounds/mainBg.png");
		mainBg = mainBgII.getImage();
		
		ImageIcon basicBgII = new ImageIcon(game.getRespath() + "graphics/backgrounds/basicBg.png");
		basicBg = basicBgII.getImage();
	}

	/*-----------------------------
	 * GUI
	 ----------------------------*/
	public static Image mainBtn;
	public static Image mainBtnHover;
	
	// Top bar GUI
	public static Image topBar;
	public static Image menuBtn;
	public static Image menuBtnHover;
	public static Image playBtn;
	public static Image playBtnHover;
	public static Image pauseBtn;
	public static Image pauseBtnHover;
	public static Image fastBtn;
	public static Image fastBtnHover;
	
	// Bottom bar GUI
	public static Image bottomBar;
	public static Image liveBtn;
	public static Image liveBtnHover;
	public static Image constructBtn;
	public static Image constructBtnHover;
	public static Image furnishBtn;
	public static Image furnishBtnHover;
	public static Image gridBtn;
	public static Image gridBtnHover;
	public static Image openCatalogBtn;
	public static Image openCatalogBtnHover;
	public static Image manageBtn;
	public static Image manageBtnHover;
	
	// Catalog GUI
	public static Image catalogBg;
	public static Image catalogBtn;
	public static Image catalogBtnHover;
	public static Image nextBtn;
	public static Image nextBtnHover;
	public static Image nextBtnDisabled;
	public static Image prevBtn;
	public static Image prevBtnHover;
	public static Image prevBtnDisabled;
	public static Image catalogInfoBg;
	public static Image buyBtn;
	public static Image buyBtnHover;
	public static Image buyBtnDisabled;
	public static Image searchBox;
	
	// Tool bar GUI
	public static Image toolBarBg;
	public static Image placeBtn;
	public static Image placeBtnHover;
	public static Image demolishBtn;
	public static Image demolishBtnHover;

	// Misc.
	public static Image xImage;
	public static Image hand;
	
	@Override
	public void loadGuiImages()
	{
		ImageIcon mainBtnII = new ImageIcon(game.getRespath() + "graphics/buttonImages/mainBtn.png");
		mainBtn = mainBtnII.getImage();
		ImageIcon mainBtnHoverII = new ImageIcon(game.getRespath() + "graphics/buttonImages/mainBtnHover.png");
		mainBtnHover = mainBtnHoverII.getImage();
		
		// Top bar
		ImageIcon topBarII = new ImageIcon(game.getRespath() + "graphics/gui/topBar.png");
		topBar = topBarII.getImage();
		ImageIcon menuBtnII = new ImageIcon(game.getRespath() + "graphics/gui/menuBtn.png");
		menuBtn = menuBtnII.getImage();
		ImageIcon menuBtnHoverII = new ImageIcon(game.getRespath() + "graphics/gui/menuBtnHover.png");
		menuBtnHover = menuBtnHoverII.getImage();
		ImageIcon playBtnII = new ImageIcon(game.getRespath() + "graphics/gui/playBtn.png");
		playBtn = playBtnII.getImage();
		ImageIcon playBtnHoverII = new ImageIcon(game.getRespath() + "graphics/gui/playBtnHover.png");
		playBtnHover = playBtnHoverII.getImage();
		ImageIcon pauseBtnII = new ImageIcon(game.getRespath() + "graphics/gui/pauseBtn.png");
		pauseBtn = pauseBtnII.getImage();
		ImageIcon pauseBtnHoverII = new ImageIcon(game.getRespath() + "graphics/gui/pauseBtnHover.png");
		pauseBtnHover = pauseBtnHoverII.getImage();
		ImageIcon fastBtnII = new ImageIcon(game.getRespath() + "graphics/gui/fastBtn.png");
		fastBtn = fastBtnII.getImage();
		ImageIcon fastBtnHoverII = new ImageIcon(game.getRespath() + "graphics/gui/fastBtnHover.png");
		fastBtnHover = fastBtnHoverII.getImage();
		
		// Bottom bar
		ImageIcon bottomBarII = new ImageIcon(game.getRespath() + "graphics/gui/bottomBar.png");
		bottomBar = bottomBarII.getImage();
		ImageIcon liveBtnII = new ImageIcon(game.getRespath() + "graphics/gui/liveBtn.png");
		liveBtn = liveBtnII.getImage();
		ImageIcon liveBtnHoverII = new ImageIcon(game.getRespath() + "graphics/gui/liveBtnHover.png");
		liveBtnHover = liveBtnHoverII.getImage();
		ImageIcon constructBtnII = new ImageIcon(game.getRespath() + "graphics/gui/constructBtn.png");
		constructBtn = constructBtnII.getImage();
		ImageIcon constructBtnHoverII = new ImageIcon(game.getRespath() + "graphics/gui/constructBtnHover.png");
		constructBtnHover = constructBtnHoverII.getImage();
		ImageIcon furnishBtnII = new ImageIcon(game.getRespath() + "graphics/gui/furnishBtn.png");
		furnishBtn = furnishBtnII.getImage();
		ImageIcon furnishBtnHoverII = new ImageIcon(game.getRespath() + "graphics/gui/furnishBtnHover.png");
		furnishBtnHover = furnishBtnHoverII.getImage();
		ImageIcon gridBtnII = new ImageIcon(game.getRespath() + "graphics/gui/gridBtn.png");
		gridBtn = gridBtnII.getImage();
		ImageIcon gridBtnHoverII = new ImageIcon(game.getRespath() + "graphics/gui/gridBtnHover.png");
		gridBtnHover = gridBtnHoverII.getImage();
		ImageIcon openCatalogBtnII = new ImageIcon(game.getRespath() + "graphics/gui/openCatalogBtn.png");
		openCatalogBtn = openCatalogBtnII.getImage();
		ImageIcon openCatalogBtnHoverII = new ImageIcon(game.getRespath() + "graphics/gui/openCatalogBtnHover.png");
		openCatalogBtnHover = openCatalogBtnHoverII.getImage();
		manageBtn = (new ImageIcon(game.getRespath() + "graphics/gui/manageBtn.png")).getImage();
		manageBtnHover = (new ImageIcon(game.getRespath() + "graphics/gui/manageBtnHover.png")).getImage();
		
		// Catalog
		ImageIcon catalogBgII = new ImageIcon(game.getRespath() + "graphics/gui/catalogBg.png");
		catalogBg = catalogBgII.getImage();
		ImageIcon catalogBtnII = new ImageIcon(game.getRespath() + "graphics/gui/catalogBtn.png");
		catalogBtn = catalogBtnII.getImage();
		ImageIcon catalogBtnHoverII = new ImageIcon(game.getRespath() + "graphics/gui/catalogBtnHover.png");
		catalogBtnHover = catalogBtnHoverII.getImage();
		ImageIcon nextBtnII = new ImageIcon(game.getRespath() + "graphics/gui/nextBtn.png");
		nextBtn = nextBtnII.getImage();
		ImageIcon nextBtnHoverII = new ImageIcon(game.getRespath() + "graphics/gui/nextBtnHover.png");
		nextBtnHover = nextBtnHoverII.getImage();
		ImageIcon nextBtnDisabledII = new ImageIcon(game.getRespath() + "graphics/gui/nextBtnDisabled.png");
		nextBtnDisabled = nextBtnDisabledII.getImage();
		ImageIcon prevBtnII = new ImageIcon(game.getRespath() + "graphics/gui/prevBtn.png");
		prevBtn = prevBtnII.getImage();
		ImageIcon prevBtnHoverII = new ImageIcon(game.getRespath() + "graphics/gui/prevBtnHover.png");
		prevBtnHover = prevBtnHoverII.getImage();
		ImageIcon prevBtnDisabledII = new ImageIcon(game.getRespath() + "graphics/gui/prevBtnDisabled.png");
		prevBtnDisabled = prevBtnDisabledII.getImage();
		catalogInfoBg = (new ImageIcon(game.getRespath() + "graphics/gui/catalogInfoBg.png")).getImage();
		buyBtn = (new ImageIcon(game.getRespath() + "graphics/gui/buyBtn.png")).getImage();
		buyBtnHover = (new ImageIcon(game.getRespath() + "graphics/gui/buyBtnHover.png")).getImage();
		buyBtnDisabled = (new ImageIcon(game.getRespath() + "graphics/gui/buyBtnDisabled.png")).getImage();
		searchBox = (new ImageIcon(game.getRespath() + "graphics/gui/searchBox.png")).getImage();
		
		// Tool bar
		toolBarBg = (new ImageIcon(game.getRespath() + "graphics/gui/toolBarBg.png")).getImage();
		placeBtn = (new ImageIcon(game.getRespath() + "graphics/gui/placeBtn.png")).getImage();
		placeBtnHover = (new ImageIcon(game.getRespath() + "graphics/gui/placeBtnHover.png")).getImage();
		demolishBtn = (new ImageIcon(game.getRespath() + "graphics/gui/demolishBtn.png")).getImage();
		demolishBtnHover = (new ImageIcon(game.getRespath() + "graphics/gui/demolishBtnHover.png")).getImage();
		
		// Misc.
		xImage = (new ImageIcon(game.getRespath() + "graphics/gui/x.png")).getImage();
		hand = (new ImageIcon(game.getRespath() + "graphics/gui/hand.png")).getImage();
	}
	
	/*-----------------------------
	 * Sprites
	 ----------------------------*/
	public static Image[] playerImages = new Image[1];
	
	// Outdoor tiles
	public static Image grass;
	public static Image sidewalk;
	public static Image road;
	
	// Construct tiles
	public static Image woodFloor1;
	public static Image woodFloor2;
	public static Image woodFloor3;
	public static Image woodFloor4;
	public static Image woodFloor5;
	public static Image woodFloor6;
	public static Image carpetFloorBlack;
	public static Image carpetFloorBlue;
	public static Image carpetFloorGreen;
	public static Image carpetFloorOrange;
	public static Image carpetFloorPink;
	public static Image carpetFloorPurple;
	public static Image carpetFloorRed;
	public static Image carpetFloorTan;
	public static Image carpetFloorWhite;
	public static Image carpetFloorYellow;
	public static Image tileFloor1;
	public static Image tileFloor2;
	public static Image tileFloor3;
	public static Image tileFloorBlack;
	public static Image tileFloorBlue;
	public static Image tileFloorTan;
	public static Image tileFloorWhite;
	
	public static Image plasterWallWhite;
	public static Image brickWall1;
	public static Image brickWall2;
	
	// Furnish tiles
	public static Image[] woodChair1 = new Image[4];
	public static Image[] woodChair2 = new Image[4];
	public static Image[] woodChair3 = new Image[4];
	public static Image woodTable1;
	public static Image[] woodTable2 = new Image[4];
	public static Image woodTable3;
	
	// Ingredient items
	public static Image breadLoaf;
	
	// Misc.
	public static Image grid;

	@Override
	
	public void loadSpriteImages()
	{
		ImageIcon playerImgII = new ImageIcon(game.getRespath() + "graphics/sprites/player.png");
		playerImages[0] = playerImgII.getImage();
		
		// Outdoor tiles
		grass = (new ImageIcon(game.getRespath() + "graphics/tiles/tileGrass.png")).getImage();
		sidewalk = (new ImageIcon(game.getRespath() + "graphics/tiles/tileSidewalk.png")).getImage();
		road = (new ImageIcon(game.getRespath() + "graphics/tiles/tileRoad.png")).getImage();
		
		// Construct tiles
		woodFloor1 = (new ImageIcon(game.getRespath() + "graphics/tiles/tileWoodFloor1.png")).getImage();
		woodFloor2 = (new ImageIcon(game.getRespath() + "graphics/tiles/tileWoodFloor2.png")).getImage();
		woodFloor3 = (new ImageIcon(game.getRespath() + "graphics/tiles/tileWoodFloor3.png")).getImage();
		woodFloor4 = (new ImageIcon(game.getRespath() + "graphics/tiles/tileWoodFloor4.png")).getImage();
		woodFloor5 = (new ImageIcon(game.getRespath() + "graphics/tiles/tileWoodFloor5.png")).getImage();
		woodFloor6 = (new ImageIcon(game.getRespath() + "graphics/tiles/tileWoodFloor6.png")).getImage();
		carpetFloorBlack = (new ImageIcon(game.getRespath() + "graphics/tiles/tileCarpetFloorBlack.png")).getImage();
		carpetFloorBlue = (new ImageIcon(game.getRespath() + "graphics/tiles/tileCarpetFloorBlue.png")).getImage();
		carpetFloorGreen = (new ImageIcon(game.getRespath() + "graphics/tiles/tileCarpetFloorGreen.png")).getImage();
		carpetFloorOrange = (new ImageIcon(game.getRespath() + "graphics/tiles/tileCarpetFloorOrange.png")).getImage();
		carpetFloorPink = (new ImageIcon(game.getRespath() + "graphics/tiles/tileCarpetFloorPink.png")).getImage();
		carpetFloorPurple = (new ImageIcon(game.getRespath() + "graphics/tiles/tileCarpetFloorPurple.png")).getImage();
		carpetFloorRed = (new ImageIcon(game.getRespath() + "graphics/tiles/tileCarpetFloorRed.png")).getImage();
		carpetFloorTan = (new ImageIcon(game.getRespath() + "graphics/tiles/tileCarpetFloorTan.png")).getImage();
		carpetFloorWhite = (new ImageIcon(game.getRespath() + "graphics/tiles/tileCarpetFloorWhite.png")).getImage();
		carpetFloorYellow = (new ImageIcon(game.getRespath() + "graphics/tiles/tileCarpetFloorYellow.png")).getImage();
		tileFloor1 = (new ImageIcon(game.getRespath() + "graphics/tiles/tileTileFloor1.png")).getImage();
		tileFloor2 = (new ImageIcon(game.getRespath() + "graphics/tiles/tileTileFloor2.png")).getImage();
		tileFloor3 = (new ImageIcon(game.getRespath() + "graphics/tiles/tileTileFloor3.png")).getImage();
		tileFloorBlack = (new ImageIcon(game.getRespath() + "graphics/tiles/tileTileFloorBlack.png")).getImage();
		tileFloorBlue = (new ImageIcon(game.getRespath() + "graphics/tiles/tileTileFloorBlue.png")).getImage();
		tileFloorTan = (new ImageIcon(game.getRespath() + "graphics/tiles/tileTileFloorTan.png")).getImage();
		tileFloorWhite = (new ImageIcon(game.getRespath() + "graphics/tiles/tileTileFloorWhite.png")).getImage();
		
		plasterWallWhite = (new ImageIcon(game.getRespath() + "graphics/tiles/tileWallPlasterWhite.png")).getImage();
		brickWall1 = (new ImageIcon(game.getRespath() + "graphics/tiles/tileWallBrick1.png")).getImage();
		brickWall2 = (new ImageIcon(game.getRespath() + "graphics/tiles/tileWallBrick2.png")).getImage();
		
		// Furnish tiles
		woodChair1[0] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureChair1Up.png")).getImage();
		woodChair1[1] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureChair1Right.png")).getImage();
		woodChair1[2] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureChair1Down.png")).getImage();
		woodChair1[3] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureChair1Left.png")).getImage();
		woodChair2[0] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureChair2Up.png")).getImage();
		woodChair2[1] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureChair2Right.png")).getImage();
		woodChair2[2] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureChair2Down.png")).getImage();
		woodChair2[3] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureChair2Left.png")).getImage();
		woodChair3[0] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureChair3Up.png")).getImage();
		woodChair3[1] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureChair3Right.png")).getImage();
		woodChair3[2] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureChair3Down.png")).getImage();
		woodChair3[3] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureChair3Left.png")).getImage();
		woodTable1 = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureTable1.png")).getImage();
		woodTable2[0] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureTable2Up.png")).getImage();
		woodTable2[1] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureTable2Right.png")).getImage();
		woodTable2[2] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureTable2Up.png")).getImage();
		woodTable2[3] = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureTable2Right.png")).getImage();
		woodTable3 = (new ImageIcon(game.getRespath() + "graphics/tiles/tileFurnitureTable3.png")).getImage();
		
		// Ingredient items
		ImageIcon breadLoafII = new ImageIcon(game.getRespath() + "graphics/items/ingredients/breadLoaf.png");
		breadLoaf = breadLoafII.getImage();
		
		// Misc.
		grid = (new ImageIcon(game.getRespath() + "graphics/tiles/tileGrid.png")).getImage();
	}

	/*-----------------------------
	 * Other Images
	 ----------------------------*/
	public static Image worldImg;
	public static Image worldImgBack;
	public static Image worldImgFront;
	
	@Override
	public void loadOtherImages()
	{
		ImageIcon worldImgII = new ImageIcon(game.getRespath() + "worlds/world.png");
		worldImg = worldImgII.getImage();
		ImageIcon worldImgBackII = new ImageIcon(game.getRespath() + "worlds/worldBack.png");
		worldImgBack = worldImgBackII.getImage();
		ImageIcon worldImgFrontII = new ImageIcon(game.getRespath() + "worlds/worldFront.png");
		worldImgFront = worldImgFrontII.getImage();
	}

	/*-----------------------------
	 * Misc.
	 ----------------------------*/
	public static World world;
	
	public static Cursor blankCursor;
	
	@Override
	public void loadMisc()
	{
		RSTiles.loadTiles();
		RSObjects.setupObjects();
		
		Image[] worldImages = new Image[3];
		worldImages[0] = worldImg;
		worldImages[1] = worldImgBack;
		worldImages[2] = worldImgFront;
		
		world = new World(1, worldImages, RSTiles.getTiles(), new RSTileAdder(), RSObjects.player, 64, 64);
		ResourceLoader.game.setTileWidth(64);
		ResourceLoader.game.setTileHeight(64);
		
		// Transparent 16 x 16 pixel cursor image.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
	}

	@Override
	public void postLoad()
	{
		game.getCanvas().setScreenLoaded(true);
	}

	/*-----------------------------
	 * Keys
	 ----------------------------*/
	public static Key up = new Key(KeyEvent.VK_W);
	public static Key down = new Key(KeyEvent.VK_S);
	public static Key left = new Key(KeyEvent.VK_A);
	public static Key right = new Key(KeyEvent.VK_D);
	public static Key jump = new Key(KeyEvent.VK_SPACE);
	public static Key action = new Key(KeyEvent.VK_E);
	
	public static Key one = new Key(KeyEvent.VK_1);
	public static Key two = new Key(KeyEvent.VK_2);
	public static Key three = new Key(KeyEvent.VK_3);
	public static Key four = new Key(KeyEvent.VK_4);
	public static Key five = new Key(KeyEvent.VK_5);
	
	public static Key escape = new Key(KeyEvent.VK_ESCAPE);
	
	@Override
	public void checkKey(int keyCode, boolean isPressed)
	{
		if (keyCode == up.getKeyCode()) // If up was pressed
			up.toggle(isPressed);
		if (keyCode == down.getKeyCode()) // If down was pressed
			down.toggle(isPressed);
		if (keyCode == left.getKeyCode()) // If left was pressed
			left.toggle(isPressed);
		if (keyCode == right.getKeyCode()) // If right was pressed
			right.toggle(isPressed);
		if (keyCode == jump.getKeyCode()) // If the jump key was pressed
			jump.toggle(isPressed);
		if (keyCode == action.getKeyCode()) // If the action key was pressed
			action.toggle(isPressed);
		if (keyCode == one.getKeyCode())
			one.toggle(isPressed);
		if (keyCode == two.getKeyCode())
			two.toggle(isPressed);
		if (keyCode == three.getKeyCode())
			three.toggle(isPressed);
		if (keyCode == four.getKeyCode())
			four.toggle(isPressed);
		if (keyCode == five.getKeyCode())
			five.toggle(isPressed);
		if (keyCode == escape.getKeyCode())
			escape.toggle(isPressed);
	}
}
