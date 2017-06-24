package com.engine.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * An animation is a set of images
 * shown in succession to simulate
 * "movement".  An animation is
 * played at the given speed, which
 * determines the delay between
 * image changes.
 * 
 * NOTE: A higher speed number
 * will make the animation run
 * slower.
 * 
 * @author Ryan Hochmuth
 *
 */
public class Animation
{
	private int count = 0;
	private int index = 0;
	private int speed;
	private int frames;
	
	private BufferedImage currentImage;
	private BufferedImage[] animation;
	
	public Animation(int speed, BufferedImage[] animation)
	{
		this.speed = speed;
		this.animation = animation;
		this.frames = animation.length;
	}
	
	/**
	 * Start the animation.
	 */
	public void runAnimation()
	{
		index++;
		
		if (index > speed)
		{
			index = 0;
			
			nextFrame();
		}
	}
	
	/**
	 * Jump to the next frame of the animation.
	 */
	public void nextFrame()
	{
		for(int x = 0; x < frames; x++)
		{
			if (count == x)
				currentImage = animation[x];
		}
		
		count++;
		
		if (count > frames)
			count = 0;
	}
	
	/**
	 * Draw the animation on the screen.
	 * @param g
	 * @param xPos
	 * @param yPos
	 */
	public void drawAnimation(Graphics g, float xPos, float yPos)
	{
		g.drawImage(currentImage, (int)xPos, (int)yPos, null);
	}
}
