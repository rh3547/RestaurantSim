package com.rs.objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * A FloatingNumber is used to show a 
 * number that will graphically "float"
 * away and become transparent.
 * 
 * @author Ryan Hochmuth
 *
 */
public class FloatingNumber
{
	private double number; // The value
	private float opacity = 1.0f; // The current opacity this number is being shown at
	private float xPos = 0; // The x position
	private float yPos = 0; // The y position
	
	/**
	 * Create a new FloatingNumber.
	 * @param number - the value
	 * @param initialX - the starting x position
	 * @param initialY - the starting y position
	 */
	public FloatingNumber(double number, float initialX, float initialY)
	{
		this.number = number;
		this.xPos = initialX;
		this.yPos = initialY;
	}
	
	/**
	 * Draw this floating number on screen.
	 * @param g
	 */
	public void draw(Graphics g)
	{
		// Obtain the Graphics2D (Better for text than Graphics)
		Graphics2D g2 = (Graphics2D)g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		
		if (getNumber() > 0) // If the number is positive, make it green
		{
			g2.setColor(Color.green);
			
			// Set the opacity and draw the number on screen
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getOpacity()));
			g2.drawString("+" + String.valueOf(getNumber()), getxPos(), getyPos());
		}
		else if (getNumber() == 0) // If the number is 0, make it yellow
		{
			g2.setColor(Color.yellow);
			
			// Set the opacity and draw the number on screen
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getOpacity()));
			g2.drawString(String.valueOf(getNumber()), getxPos(), getyPos());
		}
		else // If the number is negative, make it red
		{
			g2.setColor(Color.red);
			
			// Set the opacity and draw the number on screen
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getOpacity()));
			g2.drawString(String.valueOf(getNumber()), getxPos(), getyPos());
		}
		
		setOpacity(getOpacity() - 0.025f); // Update the opacity to fade the number away
		randomizePosition(); // Randomize the numbers next position
	}

	/**
	 * @return the number
	 */
	public double getNumber()
	{
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(double number)
	{
		this.number = number;
	}

	/**
	 * @return the opacity
	 */
	public float getOpacity()
	{
		return opacity;
	}

	/**
	 * @param opacity the opacity to set
	 */
	public void setOpacity(float opacity)
	{
		this.opacity = opacity;
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
	 * Randomize the next position.
	 */
	public void randomizePosition()
	{
		Random rand = new Random();
		this.xPos += rand.nextInt(4) - 2;
		this.yPos += rand.nextInt(3) + 3;
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
}