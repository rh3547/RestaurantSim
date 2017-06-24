package com.engine.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import com.engine.resources.ResourceLoader;

/**
 * A GuiTextArea is a GuiComponent used
 * to take keyboard input from the user.
 * 
 * @author Ryan Hochmuth
 *
 */
public class GuiTextArea extends GuiComponent
{
	private String text = "";
	private Image image = null;
	
	private Font font = null;
	private Color color = null;
	private int textOffsetX = 0;
	private int textOffsetY = 0;
	
	private boolean typing = false;
	
	private int cursorCount = 0;
	
	/**
	 * Create a new basic GuiTextArea.
	 */
	public GuiTextArea()
	{
		this.image = null;
		this.width = 100;
		this.height = 30;
		
		ResourceLoader.game.getMouseHandler().guiComponents.add(this);
	}
	
	/**
	 * Create a new custom sized
	 * GuiTextArea.
	 */
	public GuiTextArea(int width, int height)
	{
		this.image = null;
		this.width = width;
		this.height = height;
		
		ResourceLoader.game.getMouseHandler().guiComponents.add(this);
	}
	
	/**
	 * Create a new custom sized
	 * GuiTextArea.  with custom
	 * font and text color.
	 */
	public GuiTextArea(int width, int height, Font font, Color color)
	{
		this.image = null;
		this.width = width;
		this.height = height;
		this.font = font;
		this.color = color;
		
		ResourceLoader.game.getMouseHandler().guiComponents.add(this);
	}
	
	/**
	 * Create a completely custom
	 * GuiTextArea.
	 */
	public GuiTextArea(int width, int height, Font font, Color color, Image image)
	{
		this.width = width;
		this.height = height;
		this.font = font;
		this.color = color;
		this.image = image;
		this.image = prepareImage(this.image);
		
		ResourceLoader.game.getMouseHandler().guiComponents.add(this);
	}
	
	/**
	 * Scales an image to the proper size.
	 * @param image
	 */
	public Image prepareImage(Image image)
	{
		return image.getScaledInstance((int)(width), (int)(height), Image.SCALE_SMOOTH);
	}
	
	/**
	 * Draw the GuiTextArea on screen.
	 * @param g
	 */
	public void draw(Graphics g, int xPos, int yPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		
		if (image != null)
			g.drawImage(image, xPos, yPos, null);
		else
		{
			g.setColor(Color.white);
			g.fillRect(xPos, yPos, width, height);
		}
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if (font != null)
			g2.setFont(font);
		
		if (color != null)
			g2.setColor(color);
		else
			g2.setColor(Color.black);
		
		g2.drawString(text, xPos + textOffsetX, yPos + textOffsetY);
		
		if (typing)
		{
			if (image == null)
			{
				g.setColor(new Color(53, 53, 53));
				g.drawRect(this.xPos, this.yPos, this.width, this.height);
			}
			
			if (cursorCount < 50)
			{
				g.fillRect(xPos + textOffsetX + (g.getFontMetrics().stringWidth(text)) + 1, yPos + textOffsetY - (height / 2), 2, height - (height / 2));
			}
			else if (cursorCount > 100)
			{
				cursorCount = -1;
			}
			
			cursorCount++;
		}
	}
	
	/**
	 * Type a letter into this GuiTextArea.
	 * @param keyCode
	 */
	public void type(int keyCode, char keyChar)
	{
		if (keyCode == KeyEvent.VK_BACK_SPACE && this.text.length() > 0)
		{
			this.text = text.substring(0, text.length() - 1);
		}
		else if (keyCode == KeyEvent.VK_DELETE)
		{
			this.text = "";
		}
		else if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_ESCAPE)
		{
			ResourceLoader.game.getKeyboardHandler().setWordTyping(false);
			typing = false;
		}
		else if (keyCode == KeyEvent.VK_SPACE)
		{
			this.text += " ";
		}
		else
		{
			if (keyCode != KeyEvent.VK_SHIFT && keyCode != KeyEvent.VK_CONTROL
					&& keyCode != KeyEvent.VK_ALT && keyCode != KeyEvent.VK_TAB
					&& keyCode != KeyEvent.VK_BACK_SPACE && keyCode != KeyEvent.VK_DELETE)
			this.text += keyChar;
		}
	}

	@Override
	public void clicked()
	{
		typing = true;
		ResourceLoader.game.getKeyboardHandler().setWordTyping(true);
		ResourceLoader.game.getKeyboardHandler().setTextAreaFocus(this);
	}

	@Override
	public void mouseEntered()
	{
		
	}

	@Override
	public void mouseExited()
	{
		
	}

	/**
	 * @return the text
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * @return the image
	 */
	public Image getImage()
	{
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image)
	{
		this.image = image;
		this.image = prepareImage(this.image);
	}

	/**
	 * @return the width
	 */
	public int getTextAreaWidth()
	{
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setTextAreaWidth(int width)
	{
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getTextAreaHeight()
	{
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setTextAreaHeight(int height)
	{
		this.height = height;
	}

	/**
	 * @return the typing
	 */
	public boolean isTyping()
	{
		return typing;
	}

	/**
	 * @param typing the typing to set
	 */
	public void setTyping(boolean typing)
	{
		this.typing = typing;
	}

	/**
	 * @return the font
	 */
	public Font getFont()
	{
		return font;
	}

	/**
	 * @param font the font to set
	 */
	public void setFont(Font font)
	{
		this.font = font;
	}

	/**
	 * @return the color
	 */
	public Color getColor()
	{
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color)
	{
		this.color = color;
	}

	/**
	 * @return the textOffsetX
	 */
	public int getTextOffsetX()
	{
		return textOffsetX;
	}

	/**
	 * @param textOffsetX the textOffsetX to set
	 */
	public void setTextOffsetX(int textOffsetX)
	{
		this.textOffsetX = textOffsetX;
	}

	/**
	 * @return the textOffsetY
	 */
	public int getTextOffsetY()
	{
		return textOffsetY;
	}

	/**
	 * @param textOffsetY the textOffsetY to set
	 */
	public void setTextOffsetY(int textOffsetY)
	{
		this.textOffsetY = textOffsetY;
	}
}