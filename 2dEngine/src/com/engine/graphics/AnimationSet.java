package com.engine.graphics;

/**
 * An animation set is a set of
 * animations combined into a single
 * array for ease of use.
 * 
 * @author Ryan Hochmuth
 *
 */
public class AnimationSet 
{
	// The animations in this set
	private Animation[] animations;
	private int numAni = 0;
	
	/**
	 * Create a new animation set with the given initial size.
	 * @param size
	 */
	public AnimationSet(int size)
	{
		this.animations = new Animation[size];
	}
	
	/**
	 * Add a new animation to this set.
	 * @param ani - the animation to add
	 */
	public void addAnimation(Animation ani)
	{
		if ((numAni + 1) > animations.length)
		{
			Animation[] newAni = new Animation[numAni + 1];
			System.arraycopy(animations, 0, newAni, 0, numAni);
			newAni[numAni] = ani;
			animations = newAni;
			numAni++;
		}
		else
		{
			animations[numAni] = ani;
			numAni++;
		}
	}
	
	/**
	 * Get the animation in this set at the specified index.
	 * @param index - the index of the requested animation
	 * @return the animation at the given index
	 */
	public Animation getAnimation(int index)
	{
		if (index > numAni - 1)
			throw new IndexOutOfBoundsException("There is no animation at that index");
		
		return animations[index];
	}
}