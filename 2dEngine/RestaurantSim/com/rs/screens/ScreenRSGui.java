package com.rs.screens;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.engine.data.CatalogItem;
import com.engine.gameObject.GameObject;
import com.engine.graphics.Screen;
import com.engine.graphics.ScreenHandler;
import com.engine.gui.GuiButton;
import com.engine.gui.GuiComponent;
import com.engine.input.MouseHandler;
import com.engine.main.AudioHandler;
import com.engine.resources.ResourceLoader;
import com.rs.main.RSFlags;
import com.rs.main.RSResources;
import com.rs.objects.FloatingNumber;
import com.rs.objects.RSCatalogItem;
import com.rs.objects.RSObjects;
import com.rs.objects.RSPlayerController;
import com.rs.tiles.RSTile;

public class ScreenRSGui extends Screen
{
	// Window sizes
	private int windowX = ResourceLoader.game.getWindow().getWidth();
	private int windowY = ResourceLoader.game.getWindow().getHeight();
	private int lastWindowX = 0;
	private int lastWindowY = 0;
	
	private Image topBar = ResourceLoader.scaleImage(RSResources.topBar, windowX, 100);
	private Image bottomBar = ResourceLoader.scaleImage(RSResources.bottomBar, windowX, 100);
	private Image toolBar = ResourceLoader.scaleImage(RSResources.toolBarBg, 90, 500);
	private int bottomBarY = windowY;
	private int toolBarX = -100;
	
	// Top bar
	private GuiButton menuBtn = new GuiButton(RSResources.menuBtn, RSResources.menuBtnHover, 32, 33);
	private GuiButton playBtn = new GuiButton(RSResources.playBtn, RSResources.playBtnHover, 40, 31);
	private GuiButton fastBtn = new GuiButton(RSResources.fastBtn, RSResources.fastBtnHover, 40, 31);
	
	// Bottom bar
	private GuiButton liveBtn = new GuiButton(RSResources.liveBtn, RSResources.liveBtnHover, RSResources.liveBtnHover, 45, 45);
	private GuiButton constructBtn = new GuiButton(RSResources.constructBtn, RSResources.constructBtnHover, RSResources.constructBtnHover, 45, 45);
	private GuiButton furnishBtn = new GuiButton(RSResources.furnishBtn, RSResources.furnishBtnHover, RSResources.furnishBtnHover, 45, 45);
	private GuiButton manageBtn = new GuiButton(RSResources.manageBtn, RSResources.manageBtnHover, RSResources.manageBtnHover, 45, 45);
	private GuiButton gridBtn = new GuiButton(RSResources.gridBtn, RSResources.gridBtnHover, 35, 35);
	private GuiButton catalogBtn = new GuiButton(RSResources.openCatalogBtn, RSResources.openCatalogBtnHover, 140, 45);
	
	// Tool bar
	private GuiButton placeBtn = new GuiButton(RSResources.placeBtn, RSResources.placeBtnHover, RSResources.placeBtnHover, 45, 45);
	private GuiButton demolishBtn = new GuiButton(RSResources.demolishBtn, RSResources.demolishBtnHover, RSResources.demolishBtnHover, 45, 45);
	
	// Flags
	private boolean paused = true;
	private boolean catalogOpen = false;
	
	// Other
	CatalogItem<RSCatalogItem> clickedItem = null;
	BufferedImage placeImage = null;
	
	private List<FloatingNumber> moneyChanges = new ArrayList<FloatingNumber>();
	
	public ScreenRSGui(ScreenHandler screenHandler)
	{
		super(screenHandler);
	}

	@Override
	public void onCreate()
	{
		// Tell the canvas that this screen is loading
		ResourceLoader.game.getCanvas().setScreenLoaded(false);
		
		// Load the World
		RSResources.world.loadWorld();
		
		// Add the Player
		ResourceLoader.game.getObjectHandler().getGameObjects().add(RSObjects.player);
		
		// Set the game's Camera
		ResourceLoader.game.setCamera(RSObjects.camera);
		
		ResourceLoader.game.getCamera().setxPos(RSObjects.player.getxPos());
		ResourceLoader.game.getCamera().setyPos(RSObjects.player.getyPos());
		
		// Tell the canvas that this screen is done loading
		ResourceLoader.game.getCanvas().setScreenLoaded(true);
		
		liveBtn.setEnabled(false);
	}

	@Override
	public void onUpdate()
	{
		// Update the local window size
		windowX = ResourceLoader.game.getWindow().getWidth();
		windowY = ResourceLoader.game.getWindow().getHeight();
		
		if (windowX != lastWindowX)
		{
			lastWindowX = windowX;
			
			if (windowY != lastWindowY)
			{
				lastWindowY = windowY;
			}
		}
		
		if (!catalogOpen)
			RSFlags.usingGui = false;
		
		if (ResourceLoader.game.getWindow().getMousePosition() != null)
		{
			Point mousePoint = new Point(ResourceLoader.game.getMousePosX(), ResourceLoader.game.getMousePosY());
			
			if (RSFlags.tool != RSFlags.PLACE_TOOL)
			{
				// Bottom bar location
				if (mousePoint.getY() >= (windowY - 100))
				{
					RSFlags.usingGui = true;
					
					if (bottomBarY > (windowY - 100))
						bottomBarY -= 4;
				}
				else
				{
					if (bottomBarY < windowY)
						bottomBarY += 4;
				}
				
				// Tool bar location
				if (mousePoint.getX() <= 50 && (RSFlags.mode == RSFlags.BUILD_MODE || RSFlags.mode == RSFlags.BUY_MODE))
				{
					RSFlags.usingGui = true;
					
					if (toolBarX < -10)
						toolBarX += 4;
				}
				else
				{
					if (toolBarX > -100)
						toolBarX -= 4;
				}
				
				if (mousePoint != null)
					if (mousePoint.getY() <= 100 || mousePoint.getX() <= 50)
						RSFlags.usingGui = true;
				
				if (mousePoint != null)
					if (mousePoint.getY() > 100 &&
						mousePoint.getY() < (windowY - 100) &&
						mousePoint.getX() > 50)
						RSFlags.usingGui = false;
			}
			else
			{
				// Bottom bar location
				if (mousePoint.getY() >= (windowY - 75))
				{
					RSFlags.usingGui = true;
					
					if (bottomBarY > (windowY - 100))
						bottomBarY -= 4;
				}
				else
				{
					if (bottomBarY < windowY)
						bottomBarY += 4;
				}
				
				// Tool bar location
				if (mousePoint.getX() <= 40 && (RSFlags.mode == RSFlags.BUILD_MODE || RSFlags.mode == RSFlags.BUY_MODE))
				{
					RSFlags.usingGui = true;
					
					if (toolBarX < -10)
						toolBarX += 4;
				}
				else
				{
					if (toolBarX > -100)
						toolBarX -= 4;
				}
				
				if (mousePoint != null)
					if (mousePoint.getY() <= 75 || mousePoint.getX() <= 40)
						RSFlags.usingGui = true;
				
				if (mousePoint != null)
					if (mousePoint.getY() > 75 &&
						mousePoint.getY() < (windowY - 100) &&
						mousePoint.getX() > 40)
						RSFlags.usingGui = false;
			}
		}
		
		if (catalogOpen)
			RSFlags.usingGui = true;
		
		if (RSFlags.mode == RSFlags.PLAY_MODE || RSFlags.mode == RSFlags.MANAGE_MODE)
			catalogBtn.setEnabled(false);
		else
			catalogBtn.setEnabled(true);
		
		if (RSFlags.tool == RSFlags.PLACE_TOOL)
		{
			placeBtn.setEnabled(false);
			demolishBtn.setEnabled(true);
		}
		else if (RSFlags.tool == RSFlags.DEMOLISH_TOOL)
		{
			placeBtn.setEnabled(true);
			demolishBtn.setEnabled(false);
		}
		
		if (!RSFlags.usingGui && (RSFlags.tool == RSFlags.PLACE_TOOL || RSFlags.tool == RSFlags.DEMOLISH_TOOL))
		{
			ResourceLoader.game.getWindow().getContentPane().setCursor(RSResources.blankCursor);
		}
		else
			ResourceLoader.game.getWindow().getContentPane().setCursor(Cursor.getDefaultCursor());
		
		// Money changes
		if (RSFlags.moneyChanged)
		{
			moneyChanges.add(new FloatingNumber(RSFlags.moneyChangeAmount, 1120, 35));
			
			RSFlags.moneyChanged = false;
			RSFlags.moneyChangeAmount = 0;
		}
	}

	@Override
	public void unload()
	{
		ResourceLoader.game.getWorldHandler().getChunks().clear();
		ResourceLoader.game.getObjectHandler().getGameObjects().clear();
		
		ResourceLoader.game.getScreenHandler()
		.showScreen(new ScreenRSMainMenu(ResourceLoader.game.getScreenHandler()));
	}

	@Override
	public void drawStaticBackground(Graphics g)
	{
		
	}

	@Override
	public void drawBackground(Graphics g)
	{
		
	}

	@Override
	public void drawForeground(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		if (!RSFlags.usingGui && !catalogOpen && RSFlags.tool == RSFlags.PLACE_TOOL)
		{
			if (clickedItem != null && RSFlags.currentTile != null)
			{
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
				g2.drawImage(placeImage, 
						ResourceLoader.game.getWorldMouseX() - 32, 
						ResourceLoader.game.getWorldMouseY() - 32, null);
			}
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			g2.drawImage(RSResources.hand, 
					ResourceLoader.game.getWorldMouseX() - 16, 
					ResourceLoader.game.getWorldMouseY() - 16, null);
		}
		else if (!RSFlags.usingGui && !catalogOpen && RSFlags.tool == RSFlags.DEMOLISH_TOOL)
		{
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			g2.drawImage(RSResources.xImage, 
					ResourceLoader.game.getWorldMouseX() - 16, 
					ResourceLoader.game.getWorldMouseY() - 16, null);
		}
	}

	@Override
	public void drawGui(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		
		g.drawImage(topBar, 0, 0, null);
		g.drawImage(bottomBar, 0, bottomBarY, null);
		
		// Top bar buttons
		menuBtn.drawButton(g, 20, 12);
		playBtn.drawButton(g, 167, 13);
		fastBtn.drawButton(g, 203, 13);
		
		// Bottom bar buttons
		liveBtn.drawButton(g, 20, bottomBarY + 20);
		constructBtn.drawButton(g, 95, bottomBarY + 20);
		furnishBtn.drawButton(g, 170, bottomBarY + 20);
		manageBtn.drawButton(g, 245, bottomBarY + 20);
		gridBtn.drawButton(g, windowX - 55, bottomBarY + 25);
		catalogBtn.drawButton(g, (windowX / 2) - 70, bottomBarY + 25);
		
		// Tool bar
		g.drawImage(toolBar, toolBarX, 100, null);
		placeBtn.drawButton(g, toolBarX + 20, 150);
		demolishBtn.drawButton(g, toolBarX + 20, 220);
		
		// Draw the GUI strings
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setFont(RSResources.fontMain16);
		g2.setColor(new Color(53, 53, 53));
		
		g2.drawString("10:00 am", 70, 35); // The time
		g2.drawString("M", 150, 35); // The day of the week
		g2.drawString("Update...", 370, 35); // The notification
		g2.drawString(String.valueOf(RSObjects.money.getBalance()), 1120, 35); // The money
		
		if (RSObjects.panel.isShown())
			RSObjects.panel.drawPanel(g, (windowX / 2) + 230, 160);
		
		if (catalogOpen)
		{
			RSObjects.constructCatalog.drawCatalog(g, (windowX / 2) - 260, 75);
			RSObjects.furnishCatalog.drawCatalog(g, (windowX / 2) - 260, 75);
			RSObjects.ingredientsCatalog.drawCatalog(g, (windowX / 2) - 260, 75);
		}
		
		if (moneyChanges.size() > 0)
		{
			for (int x = 0; x < moneyChanges.size(); x++)
			{
				if (moneyChanges.get(x).getOpacity() < 0.1f)
				{
					moneyChanges.remove(moneyChanges.get(x));
					continue;
				}
				
				moneyChanges.get(x).draw(g);
			}
		}
	}

	@Override
	public void guiComponentClicked(GuiComponent component, int button)
	{
		// Top bar buttons
		if (component == menuBtn)
		{
			ResourceLoader.game.getAudioHandler().playSound("audio/sfx/click.wav", AudioHandler.SFX);
			
			getScreenHandler().unloadCurrentScreen();
		}
		
		if (component == playBtn)
		{
			ResourceLoader.game.getAudioHandler().playSound("audio/sfx/click.wav", AudioHandler.SFX);
			
			if (paused)
			{
				playBtn.setImage(RSResources.pauseBtn);
				playBtn.setHoverImage(RSResources.pauseBtnHover);
				paused = false;
			}
			else
			{
				playBtn.setImage(RSResources.playBtn);
				playBtn.setHoverImage(RSResources.playBtnHover);
				paused = true;
			}
		}
		
		if (component == fastBtn)
		{
			ResourceLoader.game.getAudioHandler().playSound("audio/sfx/click.wav", AudioHandler.SFX);
		}
		
		// Bottom bar buttons
		if (component == liveBtn)
		{
			if (RSFlags.mode != RSFlags.PLAY_MODE)
			{
				// Hide the furnish and construct catalogs
				if (catalogOpen)
				{
					catalogOpen = false;
					
					if (RSObjects.panel.isShown())
					{
						RSObjects.panel.hidePanel();
					}
					
					if (RSObjects.constructCatalog.isShown())
						RSObjects.constructCatalog.hideCatalog();
					if (RSObjects.furnishCatalog.isShown())
						RSObjects.furnishCatalog.hideCatalog();
				}
				
				RSFlags.tool = RSFlags.NO_TOOL;
				RSFlags.currentTile = null;
				
				ResourceLoader.game.getAudioHandler().playSound("audio/sfx/click.wav", AudioHandler.SFX);
				
				RSFlags.mode = RSFlags.PLAY_MODE; // Change the game to live mode
				liveBtn.setEnabled(false);
				
				constructBtn.setEnabled(true);
				furnishBtn.setEnabled(true);
				manageBtn.setEnabled(true);
			}
		}
		
		if (component == constructBtn)
		{
			if (RSFlags.mode != RSFlags.BUILD_MODE)
			{
				// Hide the furnish catalog
				if (catalogOpen)
				{
					catalogOpen = false;
					
					if (RSObjects.panel.isShown())
					{
						RSObjects.panel.hidePanel();
					}
					RSObjects.furnishCatalog.hideCatalog();
				}
				
				RSFlags.tool = RSFlags.NO_TOOL;
				RSFlags.currentTile = null;
				
				ResourceLoader.game.getAudioHandler().playSound("audio/sfx/click.wav", AudioHandler.SFX);
				
				RSFlags.mode = RSFlags.BUILD_MODE; // Change the game to construct mode
				constructBtn.setEnabled(false);
				
				liveBtn.setEnabled(true);
				furnishBtn.setEnabled(true);
				manageBtn.setEnabled(true);
			}
		}
		
		if (component == furnishBtn)
		{
			if (RSFlags.mode != RSFlags.BUY_MODE)
			{
				// Hide the construct catalog
				if (catalogOpen)
				{
					catalogOpen = false;
					
					if (RSObjects.panel.isShown())
					{
						RSObjects.panel.hidePanel();
					}
					RSObjects.constructCatalog.hideCatalog();
				}
				
				RSFlags.tool = RSFlags.NO_TOOL;
				RSFlags.currentTile = null;
				
				ResourceLoader.game.getAudioHandler().playSound("audio/sfx/click.wav", AudioHandler.SFX);
				
				RSFlags.mode = RSFlags.BUY_MODE; // Change the game to furnish mode
				furnishBtn.setEnabled(false);
				
				liveBtn.setEnabled(true);
				constructBtn.setEnabled(true);
				manageBtn.setEnabled(true);
			}
		}
		
		if (component == manageBtn)
		{
			if (RSFlags.mode != RSFlags.MANAGE_MODE)
			{
				// Hide the furnish and construct catalogs
				if (catalogOpen)
				{
					catalogOpen = false;
					
					if (RSObjects.panel.isShown())
					{
						RSObjects.panel.hidePanel();
					}
					
					if (RSObjects.constructCatalog.isShown())
						RSObjects.constructCatalog.hideCatalog();
					if (RSObjects.furnishCatalog.isShown())
						RSObjects.furnishCatalog.hideCatalog();
				}
				
				RSFlags.tool = RSFlags.NO_TOOL;
				RSFlags.currentTile = null;
				
				ResourceLoader.game.getAudioHandler().playSound("audio/sfx/click.wav", AudioHandler.SFX);
				
				RSFlags.mode = RSFlags.MANAGE_MODE; // Change the game to manage mode
				manageBtn.setEnabled(false);
				
				liveBtn.setEnabled(true);
				constructBtn.setEnabled(true);
				furnishBtn.setEnabled(true);
			}
		}
		
		if (component == gridBtn)
		{
			ResourceLoader.game.getAudioHandler().playSound("audio/sfx/click.wav", AudioHandler.SFX);
			
			if (!RSFlags.showGrid)
			{
				RSFlags.showGrid = true;
				gridBtn.setImage(RSResources.gridBtnHover);
			}
			else
			{
				RSFlags.showGrid = false;
				gridBtn.setImage(RSResources.gridBtn);
			}
		}
		
		// If the catalog button was clicked
		if (component == catalogBtn)
		{
			ResourceLoader.game.getAudioHandler().playSound("audio/sfx/click.wav", AudioHandler.SFX);
			
			if (RSFlags.mode == RSFlags.BUILD_MODE) // If currently in construct mode
			{
				if (catalogOpen)
				{
					if (RSObjects.panel.isShown())
					{
						RSObjects.panel.hidePanel();
					}
					RSObjects.constructCatalog.hideCatalog();
					catalogOpen = false;
				}
				else
				{
					RSObjects.constructCatalog.showCatalog();
					catalogOpen = true;
				}
			}
			else if (RSFlags.mode == RSFlags.BUY_MODE) // If currently in furnish mode
			{
				if (catalogOpen)
				{
					if (RSObjects.panel.isShown())
					{
						RSObjects.panel.hidePanel();
					}
					RSObjects.furnishCatalog.hideCatalog();
					catalogOpen = false;
				}
				else
				{
					RSObjects.furnishCatalog.showCatalog();
					catalogOpen = true;
				}
			}
		}
		
		CatalogItem<RSCatalogItem> tempClickedItem = null;

		if (catalogOpen)
		{
			if (RSFlags.mode == RSFlags.BUILD_MODE) // If currently in construct mode
			{
				try
				{
					tempClickedItem = RSObjects.constructCatalog.checkItemClicked(component);
					
					if (tempClickedItem != null)
						clickedItem = tempClickedItem;
				}
				catch(NullPointerException e)
				{
							
				}
			}
			else if (RSFlags.mode == RSFlags.BUY_MODE) // If currently in furnish mode
			{
				try
				{
					tempClickedItem = RSObjects.furnishCatalog.checkItemClicked(component);
					
					if (tempClickedItem != null)
						clickedItem = tempClickedItem;
				}
				catch(NullPointerException e)
				{
							
				}
			}
			
			if (tempClickedItem != null)
			{
				if (RSObjects.panel.isShown())
				{
					RSObjects.panel.hidePanel();
					RSObjects.panel.showPanel(clickedItem);
				}
				else
					RSObjects.panel.showPanel(clickedItem);
			}
			
			// Buy btn
			if (RSObjects.panel.isShown())
				if (component == RSObjects.panel.getBuyBtn())
				{
					ResourceLoader.game.getAudioHandler().playSound("audio/sfx/btnPress.wav", AudioHandler.SFX);
					
					if (RSObjects.constructCatalog.isShown() || RSObjects.furnishCatalog.isShown())
					{
						if (clickedItem.getData().getObject() != null)
						{
							BufferedImage master = ResourceLoader.toBufferedImage(clickedItem.getData().getObject().getImage());
					        BufferedImage mask = ResourceLoader.generateMask(master, Color.white, 0.3f);
					        placeImage = ResourceLoader.tint(master, mask);
							
							RSFlags.currentTile = (RSTile) clickedItem.getData().getObject();
							RSFlags.selectedItem = clickedItem;
							
							RSFlags.tool = RSFlags.PLACE_TOOL;
						}
					}
					
					catalogOpen = false;
					
					RSObjects.panel.hidePanel();
					
					if (RSObjects.constructCatalog.isShown())
						RSObjects.constructCatalog.hideCatalog();
					if (RSObjects.furnishCatalog.isShown())
						RSObjects.furnishCatalog.hideCatalog();
				}
		}
		
		// Place btn
		if (component == placeBtn)
		{
			ResourceLoader.game.getAudioHandler().playSound("audio/sfx/click.wav", AudioHandler.SFX);
			
			placeBtn.setEnabled(false);
			demolishBtn.setEnabled(true);
			
			RSFlags.tool = RSFlags.PLACE_TOOL;
		}
		
		// Demolish btn
		if (component == demolishBtn)
		{
			ResourceLoader.game.getAudioHandler().playSound("audio/sfx/click.wav", AudioHandler.SFX);
			
			demolishBtn.setEnabled(false);
			placeBtn.setEnabled(true);
			
			RSFlags.tool = RSFlags.DEMOLISH_TOOL;
		}
	}

	@Override
	public void guiComponentEntered(GuiComponent component)
	{
		
	}

	@Override
	public void guiComponentExited(GuiComponent component)
	{
		
	}

	@Override
	public void gameObjectClicked(GameObject object, int button)
	{
		
	}
	
	@Override
	public void mouseClicked(int button)
	{
		if (button == MouseHandler.MIDDLE_MOUSE)
		{
			System.out.println("MOVE!!!");
			
			RSObjects.player.setxPos(ResourceLoader.game.getWorldMouseX());
			RSObjects.player.setyPos(ResourceLoader.game.getWorldMouseY());
		}
	}

	@Override
	public void keyPressed(int keyCode)
	{
		RSPlayerController.keyPressed(keyCode);
		
		if (keyCode == RSResources.escape.getKeyCode())
		{
			if (catalogOpen)
			{
				if (RSFlags.mode == RSFlags.BUILD_MODE) // If currently in construct mode
				{
					RSObjects.constructCatalog.hideCatalog();
					catalogOpen = false;
				}
				else if (RSFlags.mode == RSFlags.BUY_MODE) // If currently in furnish mode
				{
					RSObjects.furnishCatalog.hideCatalog();
					catalogOpen = false;
				}
				
				RSObjects.panel.hidePanel();
			}
			else if (RSFlags.tool != RSFlags.NO_TOOL)
			{
				RSFlags.tool = RSFlags.NO_TOOL;
				RSFlags.currentTile = null;
			}
		}
		
		if (keyCode == RSResources.action.getKeyCode())
		{
			if (RSFlags.mode == RSFlags.BUY_MODE)
			{
				if (RSFlags.tool == RSFlags.PLACE_TOOL && clickedItem != null && RSFlags.currentTile != null && RSFlags.currentTile.isRotatable())
				{
					RSFlags.currentTile.changeRotation();
					
					BufferedImage master = ResourceLoader.toBufferedImage(clickedItem.getData().getObject().getImage());
			        BufferedImage mask = ResourceLoader.generateMask(master, Color.white, 0.3f);
			        placeImage = ResourceLoader.tint(master, mask);
				}
			}
		}
	}

	@Override
	public void keyReleased(int keyCode)
	{
		RSPlayerController.keyReleased(keyCode);
	}
}