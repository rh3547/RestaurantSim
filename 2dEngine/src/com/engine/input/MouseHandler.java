package com.engine.input;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import com.engine.gui.GuiButton;
import com.engine.gui.GuiComponent;
import com.engine.gui.GuiTextArea;
import com.engine.main.Game;
import com.engine.resources.ResourceLoader;

/**
 * MouseHandler is used to detect mouse clicks and movement.
 * 
 * @author Ryan Hochmuth
 *
 */
public class MouseHandler extends MouseAdapter
{
	/*
	 * Mouse Button Constants
	 */
	public static final int LEFT_MOUSE = 1;
	public static final int MIDDLE_MOUSE = 2;
	public static final int RIGHT_MOUSE = 3;
	
	// A list of all GuiComponents
	public List<GuiComponent> guiComponents = new ArrayList<GuiComponent>();
	
	private final Game game;
	
	private boolean textAreaClicked = false;
	private boolean dragging = false;
	
	/**
	 * Create a new MouseHandler
	 * @param game
	 */
	public MouseHandler(Game game)
	{
		// Add the proper listeners to the game
		game.getWindow().addMouseListener(this);
		game.getWindow().addMouseMotionListener(this);
		
		this.game = game;
	}
	
	/**
	 * Called whenever a mouse button is clicked.
	 * @param e
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		Point ogPoint = new Point(e.getX(), e.getY());
		Point point = new Point((int) (game.getMouseAnchorX() + ogPoint.getX()), (int) (game.getMouseAnchorY() + ogPoint.getY()));
		
		game.setWorldMouseX((int)point.getX());
		game.setWorldMouseY((int)point.getY());
		
		// Get which mouse button was clicked
		int mouseButton = e.getButton();
		// Create a new invisible Rectangle where the mouse pointer is
		Rectangle rect = new Rectangle((int)point.getX(), (int)point.getY(), 1, 1);
		
		if (mouseButton != MouseHandler.MIDDLE_MOUSE)
		{
			// Test if any Tiles were clicked
			if (game.usesWorld())
				for(int x = 0; x < game.getWorldHandler().getChunks().size(); x++)
				{
					if (game.getWorldHandler().getChunks().get(x).getxPos() > ResourceLoader.game.getCamera().getPlayer().getxPos() - 
						ResourceLoader.game.getChunkLoadDistance() &&
						game.getWorldHandler().getChunks().get(x).getxPos() < ResourceLoader.game.getCamera().getPlayer().getxPos() + 
						ResourceLoader.game.getChunkLoadDistance() 
						&&
						game.getWorldHandler().getChunks().get(x).getyPos() > ResourceLoader.game.getCamera().getPlayer().getyPos() - 
						ResourceLoader.game.getChunkLoadDistance() &&
						game.getWorldHandler().getChunks().get(x).getyPos() < ResourceLoader.game.getCamera().getPlayer().getyPos() + 
						ResourceLoader.game.getChunkLoadDistance())
					{	
						//if (game.getWorldHandler().getChunks().get(x).getxPos())
						for(int i = 0; i < game.getWorldHandler().getChunks().get(x).getTiles().size(); i++)
						{	
							// Test intersection between the mouse Rectangle and the Tile
							if (rect.intersects(game.getWorldHandler().getChunks().get(x).getTiles().get(i).getRect()))
							{
								// Send the signal that a mouse button was pressed to GameObjectListener
								if (game.getScreenHandler().getCurrentScreen() != null)
									ResourceLoader.game.getScreenHandler().getCurrentScreen()
									.gameObjectClicked(game.getWorldHandler().getChunks().get(x).getTiles().get(i), mouseButton);
								
								// Run the Tile's clicked() method
								game.getWorldHandler().getChunks().get(x).getTiles().get(i).clicked();
								break;
							}
						}
					}
				}
			
			// Go through every GameObject currently being shown
			if (game.usesGameObjects())
				for(int x = 0; x < game.getObjectHandler().getGameObjects().size(); x++)
				{
					// Test intersection between the mouse Rectangle and the GameObject
					if (rect.intersects(game.getObjectHandler().getGameObjects().get(x).getRect()))
					{
						// Send the signal that a mouse button was pressed to GameObjectListener
						if (game.getScreenHandler().getCurrentScreen() != null)
							ResourceLoader.game.getScreenHandler().getCurrentScreen()
							.gameObjectClicked(game.getObjectHandler().getGameObjects().get(x), mouseButton);
						
						// Run the GameObject's clicked() method
						game.getObjectHandler().getGameObjects().get(x).clicked();
						break;
					}
				}
			
			rect = new Rectangle((int) ogPoint.getX(), (int) ogPoint.getY(), 1, 1);
			
			// Go through every GuiComponent currently being shown
			for(int x = 0; x < guiComponents.size(); x++)
			{
				// Test intersection between the mouse Rectangle and the GuiComponent
				if (rect.intersects(guiComponents.get(x).getRect()))
				{
					if (guiComponents.get(x) instanceof GuiButton)
					{
						if (((GuiButton)guiComponents.get(x)).isEnabled())
						{
							// Run the GuiComponent's clicked method
							guiComponents.get(x).clicked();
							
							// Send the signal that a mouse button was pressed to GuiComponentListener
							if (game.getScreenHandler().getCurrentScreen() != null)
								ResourceLoader.game.getScreenHandler().getCurrentScreen()
								.guiComponentClicked(guiComponents.get(x), mouseButton);
						}
						
						textAreaClicked = false;
					}
					else if (guiComponents.get(x) instanceof GuiTextArea)
					{
						// Run the GuiComponent's clicked method
						guiComponents.get(x).clicked();
						
						textAreaClicked = true;
					}
					else
					{
						// Send the signal that a mouse button was pressed to GuiComponentListener
						if (game.getScreenHandler().getCurrentScreen() != null)
							ResourceLoader.game.getScreenHandler().getCurrentScreen()
							.guiComponentClicked(guiComponents.get(x), mouseButton);
						
						// Run the GuiComponent's clicked method
						guiComponents.get(x).clicked();
						
						textAreaClicked = false;
					}
					break;
				}
			}
			
			if (!textAreaClicked)
			{
				ResourceLoader.game.getKeyboardHandler().setWordTyping(false);
				
				if (ResourceLoader.game.getKeyboardHandler().getTextAreaFocus() != null)
					ResourceLoader.game.getKeyboardHandler().getTextAreaFocus().setTyping(false);
			}
		}
		else
		{
			textAreaClicked = false;
			ResourceLoader.game.getKeyboardHandler().setWordTyping(false);
			if (ResourceLoader.game.getKeyboardHandler().getTextAreaFocus() != null)
				ResourceLoader.game.getKeyboardHandler().getTextAreaFocus().setTyping(false);
		}
		
		// Send the signal that a mouse button was pressed
		if (game.getScreenHandler().getCurrentScreen() != null)
			ResourceLoader.game.getScreenHandler().getCurrentScreen()
			.mouseClicked(mouseButton);
	}
	
	/**
	 * Called whenever the mouse moves.
	 * @param e
	 */
	@Override
	public void mouseMoved(MouseEvent e)
	{
		game.setMouseMoving(true);
		game.updateMouseCount();
		
		Point ogPoint = new Point(e.getX(), e.getY());
		Point point = new Point((int) (game.getMouseAnchorX() + ogPoint.getX()), (int) (game.getMouseAnchorY() + ogPoint.getY()));
		
		game.setWorldMouseX((int)point.getX());
		game.setWorldMouseY((int)point.getY());
		
		game.setMousePosX((int)ogPoint.getX());
		game.setMousePosY((int)ogPoint.getY());
		
		// Create a new invisible Rectangle where the mouse pointer is
		Rectangle rect = new Rectangle((int)ogPoint.getX(), (int)ogPoint.getY(), 1, 1);
		
		// Go through every GuiComponent currently being shown
		for(int x = 0; x < guiComponents.size(); x++)
		{
			// Test intersection between the mouse Rectangle and the GuiComponent
			if (rect.intersects(guiComponents.get(x).getRect())) // If it is intersecting
			{
				if (!guiComponents.get(x).isMouseEntered())
				{
					// Send the signal that the mouse entered a GuiComponent
					ResourceLoader.game.getScreenHandler().getCurrentScreen()
					.guiComponentEntered(guiComponents.get(x));
				
					// Run the GuiComponent's entered method
					guiComponents.get(x).mouseEntered();
				}
			}
			else // If it is not intersecting
			{
				if (guiComponents.get(x).isMouseEntered())
				{
					// Run the GuiComponent's exited method
					guiComponents.get(x).mouseExited();
					
					// Send the signal that the mouse exited a GuiComponent
					ResourceLoader.game.getScreenHandler().getCurrentScreen()
					.guiComponentExited(guiComponents.get(x));
				}
			}
		}
	}
	
	/**
	 * Called when a mouse button is released.
	 * @param e
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{
		dragging = false;
	}
	
	/**
	 * Called when the mouse is clicked and dragged.
	 * @param e
	 */
	@Override
	public void mouseDragged(MouseEvent e)
	{
		game.setMouseMoving(true);
		game.updateMouseCount();
		
		Point ogPoint = new Point(e.getX(), e.getY());
		Point point = new Point((int) (game.getMouseAnchorX() + ogPoint.getX()), (int) (game.getMouseAnchorY() + ogPoint.getY()));
		
		game.setWorldMouseX((int)point.getX());
		game.setWorldMouseY((int)point.getY());
		
		game.setMousePosX((int)ogPoint.getX());
		game.setMousePosY((int)ogPoint.getY());
		
		// Get which mouse button was clicked
		int mouseButton = e.getButton();
		// Create a new invisible Rectangle where the mouse pointer is
		Rectangle rect = new Rectangle((int)point.getX(), (int)point.getY(), 1, 1);
		
		if (mouseButton != MouseHandler.MIDDLE_MOUSE)
		{
			// Test if any Tiles were clicked
			if (game.usesWorld())
				for(int x = 0; x < game.getWorldHandler().getChunks().size(); x++)
				{
					if (game.getWorldHandler().getChunks().get(x).getxPos() > ResourceLoader.game.getCamera().getPlayer().getxPos() - 
						ResourceLoader.game.getChunkLoadDistance() &&
						game.getWorldHandler().getChunks().get(x).getxPos() < ResourceLoader.game.getCamera().getPlayer().getxPos() + 
						ResourceLoader.game.getChunkLoadDistance() 
						&&
						game.getWorldHandler().getChunks().get(x).getyPos() > ResourceLoader.game.getCamera().getPlayer().getyPos() - 
						ResourceLoader.game.getChunkLoadDistance() &&
						game.getWorldHandler().getChunks().get(x).getyPos() < ResourceLoader.game.getCamera().getPlayer().getyPos() + 
						ResourceLoader.game.getChunkLoadDistance())
					{	
						//if (game.getWorldHandler().getChunks().get(x).getxPos())
						for(int i = 0; i < game.getWorldHandler().getChunks().get(x).getTiles().size(); i++)
						{	
							// Test intersection between the mouse Rectangle and the Tile
							if (rect.intersects(game.getWorldHandler().getChunks().get(x).getTiles().get(i).getRect()))
							{
								// Send the signal that a mouse button was pressed to GameObjectListener
								if (game.getScreenHandler().getCurrentScreen() != null)
									ResourceLoader.game.getScreenHandler().getCurrentScreen()
									.gameObjectClicked(game.getWorldHandler().getChunks().get(x).getTiles().get(i), mouseButton);
								
								// Run the Tile's clicked() method
								game.getWorldHandler().getChunks().get(x).getTiles().get(i).clicked();
								break;
							}
						}
					}
				}
			
			// Go through every GameObject currently being shown
			if (game.usesGameObjects())
				for(int x = 0; x < game.getObjectHandler().getGameObjects().size(); x++)
				{
					// Test intersection between the mouse Rectangle and the GameObject
					if (rect.intersects(game.getObjectHandler().getGameObjects().get(x).getRect()))
					{
						// Send the signal that a mouse button was pressed to GameObjectListener
						if (game.getScreenHandler().getCurrentScreen() != null)
							ResourceLoader.game.getScreenHandler().getCurrentScreen()
							.gameObjectClicked(game.getObjectHandler().getGameObjects().get(x), mouseButton);
						
						// Run the GameObject's clicked() method
						game.getObjectHandler().getGameObjects().get(x).clicked();
						break;
					}
				}
			
			if (!dragging)
			{
				rect = new Rectangle((int)ogPoint.getX(), (int)ogPoint.getY(), 1, 1);
				
				// Go through every GuiComponent currently being shown
				for(int x = 0; x < guiComponents.size(); x++)
				{
					// Test intersection between the mouse Rectangle and the GuiComponent
					if (rect.intersects(guiComponents.get(x).getRect()))
					{
						if (guiComponents.get(x) instanceof GuiButton)
						{
							if (((GuiButton)guiComponents.get(x)).isEnabled())
							{
								// Run the GuiComponent's clicked method
								guiComponents.get(x).clicked();
								
								// Send the signal that a mouse button was pressed to GuiComponentListener
								if (game.getScreenHandler().getCurrentScreen() != null)
									ResourceLoader.game.getScreenHandler().getCurrentScreen()
									.guiComponentClicked(guiComponents.get(x), mouseButton);
							}
							
							textAreaClicked = false;
						}
						else if (guiComponents.get(x) instanceof GuiTextArea)
						{
							// Run the GuiComponent's clicked method
							guiComponents.get(x).clicked();
							
							textAreaClicked = true;
						}
						else
						{
							// Send the signal that a mouse button was pressed to GuiComponentListener
							if (game.getScreenHandler().getCurrentScreen() != null)
								ResourceLoader.game.getScreenHandler().getCurrentScreen()
								.guiComponentClicked(guiComponents.get(x), mouseButton);
							
							// Run the GuiComponent's clicked method
							guiComponents.get(x).clicked();
							
							textAreaClicked = false;
						}
						break;
					}
				}
			}
			
			dragging = true;
			
			if (!textAreaClicked)
			{
				ResourceLoader.game.getKeyboardHandler().setWordTyping(false);
				
				if (ResourceLoader.game.getKeyboardHandler().getTextAreaFocus() != null)
					ResourceLoader.game.getKeyboardHandler().getTextAreaFocus().setTyping(false);
			}
		}
		else
		{
			textAreaClicked = false;
			ResourceLoader.game.getKeyboardHandler().setWordTyping(false);
			if (ResourceLoader.game.getKeyboardHandler().getTextAreaFocus() != null)
				ResourceLoader.game.getKeyboardHandler().getTextAreaFocus().setTyping(false);
		}
		
		// Send the signal that a mouse button was pressed
		if (game.getScreenHandler().getCurrentScreen() != null)
			ResourceLoader.game.getScreenHandler().getCurrentScreen()
			.mouseClicked(mouseButton);
	}
}
