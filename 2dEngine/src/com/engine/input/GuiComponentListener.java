package com.engine.input;

import com.engine.gui.GuiComponent;

/**
 * The GuiComponentListener is used in conjunction
 * with MouseHandler to detect component actions.
 * 
 * @author Ryan Hochmuth
 *
 */
public interface GuiComponentListener 
{
	/**
	 * Called when a component is clicked.
	 * @param component
	 * @param button
	 */
	public void guiComponentClicked(GuiComponent component, int button);
	/**
	 * Called when the mouse enters a component.
	 * @param component
	 */
	public void guiComponentEntered(GuiComponent component);
	/**
	 * Called when the mouse exits a component.
	 * @param component
	 */
	public void guiComponentExited(GuiComponent component);
}
