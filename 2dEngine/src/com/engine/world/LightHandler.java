package com.engine.world;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The LightHandler class contains a list of every
 * Light currently in the game.  Those Lights
 * can all be ticked and rendered through here.
 * 
 * @author Ryan Hochmuth
 *
 */
public class LightHandler
{
	// List of all the LightTiles
	private static ArrayList<Light> lights = new ArrayList<Light>();
	
	// Used to render the lighting
	private BufferedImage lightmap;
	private Graphics2D light2d;
	
	// The "brightness" of the world
	private int globalLightValue = 0;
	
	public LightHandler()
	{
		this.globalLightValue = 0;
	}
	
	/**
	 * Draw the lighting.
	 * @param g
	 */
	public void renderLighting()
	{
		lightmap = new BufferedImage(232 * 32, 232 * 32, BufferedImage.TYPE_INT_ARGB);
		light2d = (Graphics2D)lightmap.getGraphics();
		
		light2d.setColor(new Color(0, 0, 0, globalLightValue)); // 240
		light2d.fillRect(0, 0, lightmap.getWidth(), lightmap.getHeight());
		light2d.setComposite(AlphaComposite.DstOut);
		
		for(Light light : lights) 
		{
			light.draw(light2d);
		}
	}
	
	/**
	 * Adds the given Light to the complete list.
	 * @param light
	 */
	public void addLight(Light light)
	{
		lights.add(light);
	}
	
	/**
	 * Get the complete list of Lights.
	 * @return
	 */
	public ArrayList<Light> getLights()
	{
		return lights;
	}
	
	/**
	 * Get the global light value.
	 * @return globalLightValue
	 */
	public int getGlobalLightValue()
	{
		return globalLightValue;
	}
	
	/**
	 * Set the global light value.
	 * @param value
	 */
	public void setGlobalLightValue(int value)
	{
		this.globalLightValue = value;
	}

	/**
	 * Get the game's light map.
	 * @return the light map
	 */
	public BufferedImage getLightmap() 
	{
		return lightmap;
	}

	/**
	 * Set the game's light map.
	 * @param lightmap the light map to set
	 */
	public void setLightmap(BufferedImage lightmap) 
	{
		this.lightmap = lightmap;
	}
}
