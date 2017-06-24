package com.engine.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Light
{
	private int xPos;
	private int yPos;
	private int radius;
	
	private BufferedImage image;
	private Graphics2D g2d;
	
	public Light(int xPos, int yPos, int radius, int luminosity)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.radius = radius;
		
		image = new BufferedImage(radius * 2, radius * 2, BufferedImage.TYPE_INT_ARGB);
		g2d = (Graphics2D)image.getGraphics();
		
		int step = 4;
		int numSteps = radius / step;
		
		g2d.setColor(new Color(231, 222, 74, luminosity));
		
		for(int x = 0; x < numSteps; x++)
		{
			g2d.fillOval(radius - x * step, radius - x * step, x * step * 2, x * step * 2);
		}
	}
	
	public void draw(Graphics2D g) 
	{
		g.drawImage(image, xPos - radius, yPos - radius, null);
	}
}
