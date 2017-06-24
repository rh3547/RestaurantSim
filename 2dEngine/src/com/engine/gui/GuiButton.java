package com.engine.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;

import com.engine.resources.ResourceLoader;

/**
 * GuiButton is a standard button for use in the game.
 * The way the engine is set up, using JButtons isn't
 * a good way to use buttons.  Use this instead.
 * 
 * @author Ryan Hochmuth
 *
 */
public class GuiButton extends GuiComponent
{
	private Image image; // The base image for the button
	private  Image hoverImage; // The image displayed for the button when hovered over
	private Image disabledImage; // The image to display when this button is disabled
	private String text; // The button's text
	private Color textColor; // The color of the button's text
	private int textOffsetX; // The button text x offset
	private int textOffsetY; // The button text y offset
	private Font font; // The font of the button's text
	private boolean firstDraw = true;
	private boolean enabled = true;
	
	private Image currentImage; // The image this button is currently displaying
	
	/**
	 * Create a new GuiButton with a custom image.
	 * @param image
	 * @param hoverImage
	 * @param width
	 * @param height
	 */
	public GuiButton(Image image, Image hoverImage, int width, int height)
	{
		this.width = width;
		this.height = height;
		
		this.image = image;
		this.hoverImage = hoverImage;
		this.image = prepareImage(this.image);
		this.hoverImage = prepareImage(this.hoverImage);
		this.currentImage = this.image;
		disabledImage = this.image;

		ResourceLoader.game.getMouseHandler().guiComponents.add(this);
	}
	
	/**
	 * Create a new GuiButton with a custom image.  And
	 * a disabled image.
	 * @param image
	 * @param hoverImage
	 * @param width
	 * @param height
	 */
	public GuiButton(Image image, Image hoverImage, Image disabledImage, int width, int height)
	{
		this.width = width;
		this.height = height;
		
		this.image = image;
		this.hoverImage = hoverImage;
		this.disabledImage = disabledImage;
		this.image = prepareImage(this.image);
		this.hoverImage = prepareImage(this.hoverImage);
		this.disabledImage = prepareImage(this.disabledImage);
		this.currentImage = this.image;

		ResourceLoader.game.getMouseHandler().guiComponents.add(this);
	}
	
	/**
	 * Create a new basic GuiButton with a custom size.
	 * @param width
	 * @param height
	 */
	public GuiButton(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		ImageIcon basicButtonII = new ImageIcon("src/res/defaultBtn.png");
		image = basicButtonII.getImage();
		ImageIcon basicButtonHoverII = new ImageIcon("src/res/defaultBtnHover.png");
		hoverImage = basicButtonHoverII.getImage();
		
		image = prepareImage(image);
		hoverImage = prepareImage(hoverImage);
		disabledImage = image;
		
		this.currentImage = image;
		
		ResourceLoader.game.getMouseHandler().guiComponents.add(this);
	}
	
	/**
	 * Create a new basic GuiButton.
	 */
	public GuiButton()
	{
		this.width = 100;
		this.height = 40;
		
		ImageIcon basicButtonII = new ImageIcon("src/res/defaultBtn.png");
		image = basicButtonII.getImage();
		ImageIcon basicButtonHoverII = new ImageIcon("src/res/defaultBtnHover.png");
		hoverImage = basicButtonHoverII.getImage();
		disabledImage = image;

		this.currentImage = image;
		
		ResourceLoader.game.getMouseHandler().guiComponents.add(this);
	}
	
	/**
	 * Scales an image to the proper size.
	 * @param image
	 */
	public Image prepareImage(Image image)
	{
		return image.getScaledInstance((int)(width), (int)(height), Image.SCALE_SMOOTH);
		//return ResourceLoader.scaleImage(image, (int)width, (int)height);
	}
	
	/**
	 * Draw this GuiButton on the screen at a specific location.
	 * @param g
	 * @param posX
	 * @param posY
	 */
	public void drawButton(Graphics g, int posX, int posY)
	{
		this.xPos = posX;
		this.yPos = posY;
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(new Color(255, 255, 255, 0));
		g.drawRect(posX, posY, width, height);
		
		if (currentImage != null)
		{
			if (firstDraw)
			{
				g.drawImage(currentImage, posX, posY, null);
				currentImage = hoverImage;
				g.drawImage(currentImage, posX, posY, null);
				currentImage = image;
				
				firstDraw = false;
			}
			
			if (enabled)
				g.drawImage(currentImage, posX, posY, null);
			else
				g.drawImage(disabledImage, posX, posY, null);
		}
		
		if (text != null)
		{
			if (textColor != null)
				g2.setColor(textColor);
			else
				g2.setColor(Color.black);
			
			if (font != null)
				g2.setFont(font);
			
			g2.drawString(text, posX + textOffsetX, posY + textOffsetY);
		}
	}

	/**
	 * Get the button's base image.
	 * @return image
	 */
	public Image getImage()
	{
		return image;
	}

	/**
	 * Set the button's base image.
	 * @param image
	 */
	public void setImage(Image image)
	{
		this.image = image;
		this.image = prepareImage(this.image);
		this.currentImage = this.image;
	}

	/**
	 * Get the button's hover image.
	 * @return hoverImage
	 */
	public Image getHoverImage()
	{
		return hoverImage;
	}

	/**
	 * Set the button's hover image.
	 * @param hoverImage
	 */
	public void setHoverImage(Image hoverImage)
	{
		this.hoverImage = hoverImage;
		this.hoverImage = prepareImage(this.hoverImage);
	}

	/**
	 * Get the button's text.
	 * @return text
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * Set the button's text.
	 * @param text
	 */
	public void setText(String text)
	{
		this.text = text;
	}
	
	/**
	 * Set the offset at which the text should 
	 * be drawn on the button.
	 * @param x
	 * @param y
	 */
	public void setTextOffset(int x, int y)
	{
		this.textOffsetX = x;
		this.textOffsetY = y;
	}

	/**
	 * Get the button's text color.
	 * @return textColor
	 */
	public Color getTextColor()
	{
		return textColor;
	}

	/**
	 * Set the button's text color.
	 * @param textColor
	 */
	public void setTextColor(Color textColor)
	{
		this.textColor = textColor;
	}

	/**
	 * Get the button's text font.
	 * @return font
	 */
	public Font getFont()
	{
		return font;
	}

	/**
	 * Set the button's text font.
	 * @param font
	 */
	public void setFont(Font font)
	{
		this.font = font;
	}

	@Override
	public void clicked()
	{
		
	}

	@Override
	public void mouseEntered()
	{
		if (enabled)
		{
			mouseEntered = true;
			currentImage = hoverImage;
		}
	}

	@Override
	public void mouseExited()
	{
		if (enabled)
		{
			mouseEntered = false;
			currentImage = image;
		}
	}

	/**
	 * @return the disabledImage
	 */
	public Image getDisabledImage()
	{
		return disabledImage;
	}

	/**
	 * @param disabledImage the disabledImage to set
	 */
	public void setDisabledImage(Image disabledImage)
	{
		this.disabledImage = disabledImage;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled()
	{
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
}
