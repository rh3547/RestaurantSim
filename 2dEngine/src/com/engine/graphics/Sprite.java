package com.engine.graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite
{
	public String path;
	public int width;
	public int height;
	
	public int[] pixels;
	
	public Image texture;
	
	public Sprite(String path)
	{
		BufferedImage image = null;
		
		try
		{
			image = ImageIO.read(new File(path));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		if (image == null)
			return;
		
		this.path = path;
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		this.pixels = image.getRGB(0, 0, width, height, null, 0, width);
		
		this.texture = image;
	}
	
	public void drawSprite(Graphics g, int xPos, int yPos, int xOffset, int yOffset)
	{
		g.drawImage(texture, xPos + xOffset, yPos + yOffset, width, height, null);
	}
}
