package com.engine.input;

/**
 * The GameKeyListener interface is used
 * access key updates when they occur.
 * 
 * @author Ryan Hochmuth
 *
 */
public interface GameKeyListener
{
	/**
	 * Called when a key is pressed.
	 */
	public void keyPressed(int keyCode);
	
	/**
	 * Called when a key is released.
	 */
	public void keyReleased(int keyCode);
}
