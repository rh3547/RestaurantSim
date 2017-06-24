package com.engine.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Particle
{
	// Constructor data
	private BufferedImage image;
	private float xPos;
	private float yPos;
	
	// Position data
	private float lastXAmount = 0;
	private float lastYAmount = 0;
	
	public Particle(BufferedImage image, float xPos, float yPos)
	{
		this.image = image;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void drawParticle(Graphics g)
	{
		g.drawImage(image, (int)xPos, (int)yPos, null);
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage()
	{
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}

	/**
	 * @return the xPos
	 */
	public float getxPos()
	{
		return xPos;
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(float xPos)
	{
		this.xPos = xPos;
	}
	
	/**
	 * Updates the x position of this particle
	 * by the passed amount.
	 * @param amount - the amount to move the particle
	 */
	public void updateXPos(float xPos, float amount)
	{
		this.xPos = this.xPos + amount;
		this.lastXAmount = amount;
	}

	/**
	 * @return the yPos
	 */
	public float getyPos()
	{
		return yPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(float yPos)
	{
		this.yPos = yPos;
	}
	
	/**
	 * Updates the y position of this particle
	 * by the passed amount.
	 * @param amount - the amount to move the particle
	 */
	public void updateYPos(float yPos, float amount)
	{
		this.yPos = this.yPos + amount;
		this.lastYAmount = amount;
	}

	/**
	 * @return the lastXAmount
	 */
	public float getLastXAmount()
	{
		return lastXAmount;
	}

	/**
	 * @param lastXAmount the lastXAmount to set
	 */
	public void setLastXAmount(float lastXAmount)
	{
		this.lastXAmount = lastXAmount;
	}

	/**
	 * @return the lastYAmount
	 */
	public float getLastYAmount()
	{
		return lastYAmount;
	}

	/**
	 * @param lastYAmount the lastYAmount to set
	 */
	public void setLastYAmount(float lastYAmount)
	{
		this.lastYAmount = lastYAmount;
	}
}
