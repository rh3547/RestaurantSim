package com.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.engine.gui.GuiTextArea;
import com.engine.main.Game;

/**
 * KeyboardHandler handles the game's keyboard input.
 * 
 * @author Ryan Hochmuth
 *
 */
public class KeyboardHandler implements KeyListener
{
	private final Game game;
	
	/***********************************
	 * Input Variables
	 **********************************/
	// An array of key flags.  If a key is pressed it's flag will be true
	// This is for all a keyboard's keys, it is general
	private boolean[] keys = new boolean[256];
	
	private boolean wordTyping = false;
	private GuiTextArea textAreaFocus = null;
	
	/**
	 * Creates a new KeyboardHandler and adds it
	 * as a KeyListener to the game window.
	 * @param game
	 */
	public KeyboardHandler(Game game)
	{
		this.game = game;
		
		// Add the KeyboardHandler to the window to detect key presses
		game.getWindow().addKeyListener(this);
	}
	
	/**
	 * keyPressed() is called as an event
	 * when a key on the keyboard is
	 * pressed.
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
		if (!wordTyping)
		{
			// Set this key as pressed in the general keys
			keys[e.getKeyCode()] = true;
			// Set this specific game key as pressed
			game.getResourceLoader().checkKey(e.getKeyCode(), true);
			// Update the GameKeyListener keyPressed() method
			if (game.getScreenHandler().getCurrentScreen() != null)
				game.getScreenHandler().getCurrentScreen()
				.keyPressed(e.getKeyCode());
			
			if (!game.isDebugShown() && e.getKeyCode() == KeyEvent.VK_F3)
			{
				game.setDebugShown(true);
			}
			else if (game.isDebugShown() && e.getKeyCode() == KeyEvent.VK_F3)
			{
				game.setDebugShown(false);
			}	
		}
		else
		{
			
		}
	}
	
	/**
	 * keyReleased() is called as an event
	 * when a key on the keyboard is
	 * released.
	 */
	@Override
	public void keyReleased(KeyEvent e)
	{
		if (!wordTyping)
		{
			// Set this key as not pressed in the general keys
			keys[e.getKeyCode()] = false;
			// Set this specific game key as not pressed
			game.getResourceLoader().checkKey(e.getKeyCode(), false);
			// Update the GameKeyListener keyPressed() method
			if (game.getScreenHandler().getCurrentScreen() != null)
				game.getScreenHandler().getCurrentScreen()
				.keyReleased(e.getKeyCode());
		}
		else
		{
			/*
			for (int x = 0; x < game.getMouseHandler().guiComponents.size(); x++)
			{
				if (game.getMouseHandler().guiComponents.get(x) instanceof GuiTextArea)
				{
					if (((GuiTextArea)game.getMouseHandler().guiComponents.get(x)).isTyping())
					{
						((GuiTextArea)game.getMouseHandler().guiComponents.get(x)).type(e.getKeyCode(), e.getKeyChar());
					}
				}
			}
			*/
			
			if (textAreaFocus != null)
				textAreaFocus.type(e.getKeyCode(), e.getKeyChar());
		}
	}

	/**
	 * keyTyped() is called as an event
	 * when a key on the keyboard is
	 * typed.
	 */
	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	/***********************************
	 * General Key Methods
	 **********************************/
	/**
	 * Checks if the given key code is pressed.
	 * @param key
	 * @return boolean value
	 */
	public boolean isKeyPressed(int key)
	{
		return keys[key];
	}
	
	/**
	 * Checks if the given key code is released.
	 * @param key
	 * @return boolean value
	 */
	public boolean isKeyReleased(int key)
	{
		return !keys[key];
	}

	/**
	 * @return the wordTyping
	 */
	public boolean isWordTyping()
	{
		return wordTyping;
	}

	/**
	 * @param wordTyping the wordTyping to set
	 */
	public void setWordTyping(boolean wordTyping)
	{
		this.wordTyping = wordTyping;
	}

	/**
	 * @return the textAreaFocus
	 */
	public GuiTextArea getTextAreaFocus()
	{
		return textAreaFocus;
	}

	/**
	 * @param textAreaFocus the textAreaFocus to set
	 */
	public void setTextAreaFocus(GuiTextArea textAreaFocus)
	{
		this.textAreaFocus = textAreaFocus;
	}
}
