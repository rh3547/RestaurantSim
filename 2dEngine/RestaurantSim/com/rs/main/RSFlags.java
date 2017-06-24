package com.rs.main;

import com.engine.data.CatalogItem;
import com.rs.objects.RSCatalogItem;
import com.rs.tiles.RSTile;

public class RSFlags
{
	// Modes
	public static int mode = 0; // The current mode that the game is in
	
	public static final int PLAY_MODE = 0;
	public static final int BUILD_MODE = 1;
	public static final int BUY_MODE = 2;
	public static final int MANAGE_MODE = 3;
	
	// Tools
	public static int tool = -1;
	
	public static final int NO_TOOL = -1;
	public static final int DEMOLISH_TOOL = 0;
	public static final int PLACE_TOOL = 1;
	
	public static CatalogItem<RSCatalogItem> selectedItem = null;
	public static RSTile currentTile = null;
	
	// Flags
	public static boolean showGrid = false; // Should the grid overlay be shown
	
	public static boolean usingGui = false; // Is the player navigating the GUI
	
	public static boolean moneyChanged = false; // Has the player's money changed
	public static double moneyChangeAmount = 0;
}