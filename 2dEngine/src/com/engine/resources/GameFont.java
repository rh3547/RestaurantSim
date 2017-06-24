package com.engine.resources;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

/**
 * GameFont is a Font registered with
 * the game that can be used anywhere.
 * 
 * @author Ryan Hochmuth
 *
 */
public class GameFont
{
	private String fontPath;
	private String fontName;
	private int fontSize;
	private Font font;
	
	/**
	 * Create a new GameFont using the Font at the fontPath
	 * as the Font to register.
	 * @param fontPath
	 * @param fontName
	 * @param fontSize
	 * @param font
	 */
	public GameFont(String fontPath, String fontName, int fontSize, Font font, boolean isBold)
	{
		this.fontPath = fontPath;
		this.fontName = fontName;
		this.fontSize = fontSize;
		this.font = font;
		
		registerFont(fontPath);
		
		if (isBold)
			this.font = new Font(fontName, Font.BOLD, fontSize);
		else
			this.font = new Font(fontName, Font.PLAIN, fontSize);
	}
	
	/**
	 * Registers a new Font using the given fontPath
	 * @param fontPath
	 */
	private void registerFont(String fontPath)
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		try
		{
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Get the font path.
	 * @return fontPath
	 */
	public String getFontPath()
	{
		return fontPath;
	}

	/**
	 * Set the font path.
	 * @param fontPath
	 */
	public void setFontPath(String fontPath)
	{
		this.fontPath = fontPath;
	}

	/**
	 * Get the font name.
	 * @return fontName
	 */
	public String getFontName()
	{
		return fontName;
	}

	/**
	 * Set the font name.
	 * @param fontName
	 */
	public void setFontName(String fontName)
	{
		this.fontName = fontName;
	}

	/**
	 * Get the font size.
	 * @return fontSize
	 */
	public int getFontSize()
	{
		return fontSize;
	}

	/**
	 * Set the font size.
	 * @param fontSize
	 */
	public void setFontSize(int fontSize)
	{
		this.fontSize = fontSize;
	}

	/**
	 * Get the font.
	 * @return font
	 */
	public Font getFont()
	{
		return font;
	}

	/**
	 * Set the font.
	 * @param font
	 */
	public void setFont(Font font)
	{
		this.font = font;
	}
}
