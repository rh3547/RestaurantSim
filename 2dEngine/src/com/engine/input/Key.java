package com.engine.input;

/**
 * A specific key on the keyboard
 * to monitor.
 * 
 * @author Ryan Hochmuth
 *
 */
public class Key
{
	// Is this key pressed
	private boolean pressed = false;
	// The keyCode of this key
	private int keyCode;
	// How many times the key was pressed
	private int timesPressed = 0;
	
	/**
	 * Creates a new Key object and sets it's keyCode.
	 * @param keyCode
	 */
	public Key(int keyCode)
	{
		this.keyCode = keyCode;
	}
	
	/**
	 * Get whether or not this key is pressed.
	 * @return
	 */
	public boolean isPressed()
	{
		return pressed;
	}
	
	// Change whether or not this key is pressed.
	public void toggle(boolean isPressed)
	{
		this.pressed = isPressed;
		if (isPressed) timesPressed++;
	}
	
	/**
	 * Get this key's key code.
	 * @return
	 */
	public int getKeyCode()
	{
		return keyCode;
	}
	
	/**
	 * Set this key's key code.
	 * @param keyCode
	 */
	public void setKeyCode(int keyCode)
	{
		this.keyCode = keyCode;
	}
	
	/**
	 * Get the number of times this Key
	 * has been pressed.
	 * @return timesPressed
	 */
	public int getTimesPressed()
	{
		return timesPressed;
	}
}