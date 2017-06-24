package com.engine.input;

import com.engine.gameObject.GameObject;

/**
 * The GameObjectListener is used in conjunction
 * with MouseHandler to detect GameObject actions.
 * 
 * @author Ryan Hochmuth
 *
 */
public interface GameObjectListener 
{
	/**
	 * Called when a GameObject is clicked.
	 * @param object the GameObject that was clicked
	 * @param button the mouse button that was clicked
	 */
	public void gameObjectClicked(GameObject object, int button);
}
