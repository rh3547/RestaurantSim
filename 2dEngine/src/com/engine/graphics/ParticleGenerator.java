package com.engine.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleGenerator
{
	// Particle scopes
	public static final int CONE_UP = 0;
	public static final int CONE_DOWN = 1;
	public static final int CONE_LEFT = 2;
	public static final int CONE_RIGHT = 3;
	public static final int SPHERE = 4;
	
	// Particle data
	private BufferedImage[] images;
	private float spread;
	private float rate;
	private float speed;
	private float duration;
	private int scope;
	
	// Generation data
	private List<Particle> particles = new ArrayList<Particle>();
	
	// Timer
	private int time = 0;
	private int count;
	private int speedCount = 0;
	private boolean running = false;
	
	/**
	 * Create a new Particle generator
	 * @param images
	 * @param spread
	 * @param rate
	 * @param duration
	 * @param scope
	 */
	public ParticleGenerator(BufferedImage[] images, float spread, float rate, float speed, float duration, int scope)
	{
		this.images = images;
		this.spread = spread;
		this.rate = rate;
		this.speed = speed;
		this.duration = duration;
		this.scope = scope;
		
		this.count = (int)duration * (int)rate;
		this.speedCount = (int)speed;
	}
	
	public void startGenerator()
	{
		if (!running)
		{
			running = true;
		}
	}
	
	public void stopGenerator()
	{
		if (running)
		{
			running = false;
			time = 0;
			particles.clear();
		}
	}

	/**
	 * Generate and randomize particles.
	 * @param g - the graphics to use for drawing
	 * @param xPos - the x position to draw at
	 * @param yPos - the y position to draw at
	 */
	public void generateParticles(Graphics g, float xPos, float yPos)
	{
		// Only generate when supposed to
		if (running)
		{
			Random rand = new Random();
			// The randomized image for a new particle
			int imageNum = 0;
			
			// Increment time for duration control
			time++;
			
			// Generate particles if during the duration
			if (time < duration * 60)
			{
				// Increment the rate count
				count++;
				
				// Generate a new particle if the count exceeds
				// or equals the rate of this particle generator
				if (count >= duration * rate)
				{
					// Reset the count
					count = 0;
					
					// Randomize a new imageNum
					imageNum = rand.nextInt(images.length);
					
					// Create a new particle
					particles.add(new Particle(images[imageNum], xPos, yPos));
				}
				
				speedCount++;
				
				if (speedCount >= speed)
				{
					speedCount = 0;
					
					// Increment through every particle currently in this
					// particle generator
					for (int x = 0; x < particles.size(); x++)
					{
						// The amount to offset the particle
						int xAmount = 0;
						int yAmount = 0;
						
						// Change the particle path based on the generators scope
						switch(scope)
						{
							case 0: // CONE_UP
								switch(rand.nextInt(2))
								{
									case 0:
										xAmount = -rand.nextInt((int)spread);
										break;
										
									case 1:
										xAmount = rand.nextInt((int)spread);
										break;
								}
								
								yAmount = -(rand.nextInt((int)spread));
								break;
								
							case 1: // CONE_DOWN
								switch(rand.nextInt(2))
								{
									case 0:
										xAmount = -rand.nextInt((int)spread);
										break;
										
									case 1:
										xAmount = rand.nextInt((int)spread);
										break;
								}
								
								yAmount = (rand.nextInt((int)spread) + (int)spread);
								break;
								
							case 2: // CONE_LEFT
								xAmount = -(rand.nextInt((int)spread));
								
								switch(rand.nextInt(2))
								{
									case 0:
										yAmount = -(rand.nextInt((int)spread) + (int)spread);
										break;
										
									case 1:
										yAmount = (rand.nextInt((int)spread) + (int)spread);
										break;
								}
								break;
								
							case 3: // CONE_RIGHT
								xAmount = (rand.nextInt((int)spread));
								
								switch(rand.nextInt(2))
								{
									case 0:
										yAmount = -(rand.nextInt((int)spread) + (int)spread);
										break;
										
									case 1:
										yAmount = (rand.nextInt((int)spread) + (int)spread);
										break;
								}
								break;
								
							case 4: // SPHERE
								switch(rand.nextInt(2))
								{
									case 0:
										xAmount = -rand.nextInt((int)spread);
										break;
										
									case 1:
										xAmount = rand.nextInt((int)spread);
										break;
								}
								
								switch(rand.nextInt(2))
								{
									case 0:
										yAmount = -(rand.nextInt((int)spread) + (int)spread);
										break;
										
									case 1:
										yAmount = (rand.nextInt((int)spread) + (int)spread);
										break;
								}
								break;
						}
						
						// Update the particle positions
						particles.get(x).updateXPos(xPos, xAmount);
						particles.get(x).updateYPos(yPos, yAmount);
					}
				}
				
				for (int x = 0; x < particles.size(); x++)
				{
					// Draw the particle
					particles.get(x).drawParticle(g);
				}
			}
			else // When the duration is over
			{
				// Stop the particles
				stopGenerator();
			}
		}
	}

	/**
	 * @return the running
	 */
	public boolean isRunning()
	{
		return running;
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running)
	{
		this.running = running;
	}
}
