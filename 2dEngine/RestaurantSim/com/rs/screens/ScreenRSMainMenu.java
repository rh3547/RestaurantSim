package com.rs.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.engine.gameObject.GameObject;
import com.engine.graphics.Screen;
import com.engine.graphics.ScreenHandler;
import com.engine.gui.GuiButton;
import com.engine.gui.GuiComponent;
import com.engine.main.AudioHandler;
import com.engine.resources.ResourceLoader;
import com.rs.main.RSResources;

public class ScreenRSMainMenu extends Screen
{
	// Window sizes
	private int windowX = ResourceLoader.game.getWindow().getWidth();
	private int windowY = ResourceLoader.game.getWindow().getHeight();
	private int lastWindowX = 0;
	private int lastWindowY = 0;
	
	private Image bgImage = ResourceLoader.scaleImage(RSResources.mainBg, windowX, windowY);
	
	private GuiButton playBtn = new GuiButton(RSResources.mainBtn, RSResources.mainBtnHover, 500, 60);
	private GuiButton exitBtn = new GuiButton(RSResources.mainBtn, RSResources.mainBtnHover, 500, 60);
	
	public ScreenRSMainMenu(ScreenHandler screenHandler)
	{
		super(screenHandler);
	}

	@Override
	public void onCreate()
	{
		// Tell the canvas that this screen is loading
		ResourceLoader.game.getCanvas().setScreenLoaded(false);
		
		playBtn.setTextColor(Color.black);
		playBtn.setText("Play");
		playBtn.setTextOffset(225, 38);
		playBtn.setFont(RSResources.fontMain32);
		
		exitBtn.setTextColor(Color.black);
		exitBtn.setText("Exit");
		exitBtn.setTextOffset(225, 38);
		exitBtn.setFont(RSResources.fontMain32);
		
		// Set the starting volume
		ResourceLoader.game.getAudioHandler().setMusicVolume(-8.0F); // -8
		ResourceLoader.game.getAudioHandler().setSfxVolume(-8.0F); // -8
		
		ResourceLoader.game.getAudioHandler().playSound("audio/music/Malt Shop Bop.wav", AudioHandler.MUSIC);
		
		// Tell the canvas that this screen is done loading
		ResourceLoader.game.getCanvas().setScreenLoaded(true);
	}

	@Override
	public void onUpdate()
	{
		// Update the local window size
		windowX = ResourceLoader.game.getWindow().getWidth();
		windowY = ResourceLoader.game.getWindow().getHeight();
		
		if (windowX != lastWindowX)
		{
			lastWindowX = windowX;
			
			if (windowY != lastWindowY)
			{
				lastWindowY = windowY;
			}
			
			bgImage = ResourceLoader.scaleImage(RSResources.mainBg, windowX, windowY);
		}
	}

	@Override
	public void unload()
	{
		
	}

	@Override
	public void drawStaticBackground(Graphics g)
	{
		// Draw the image background
		g.drawImage(bgImage, 0, 0, null);
	}

	@Override
	public void drawBackground(Graphics g)
	{
		
	}

	@Override
	public void drawForeground(Graphics g)
	{
		
	}

	@Override
	public void drawGui(Graphics g)
	{
		playBtn.drawButton(g, (windowX / 2) - 300, 300);
		exitBtn.drawButton(g, (windowX / 2) - 300, 400);
	}

	@Override
	public void guiComponentClicked(GuiComponent component, int button)
	{
		if (component == playBtn)
		{
			ResourceLoader.game.getAudioHandler().playSound("audio/sfx/btnPress.wav", AudioHandler.SFX);
			ResourceLoader.game.getMouseHandler().guiComponents.remove(playBtn);
			ResourceLoader.game.getMouseHandler().guiComponents.remove(exitBtn);
			ResourceLoader.game.getScreenHandler().showScreen(new ScreenRSPlay(this.getScreenHandler()));
		}
		
		if (component == exitBtn)
		{
			ResourceLoader.game.getAudioHandler().playSound("audio/sfx/btnPress.wav", AudioHandler.SFX);
			System.exit(0);
		}
	}

	@Override
	public void guiComponentEntered(GuiComponent component)
	{
		if (component == playBtn)
		{
			ResourceLoader.game.getAudioHandler().playSound("audio/sfx/btnRoll.wav", AudioHandler.SFX);
			playBtn.setTextOffset(170, 38);
		}
		
		if (component == exitBtn)
		{
			ResourceLoader.game.getAudioHandler().playSound("audio/sfx/btnRoll.wav", AudioHandler.SFX);
			exitBtn.setTextOffset(170, 38);
		}
	}

	@Override
	public void guiComponentExited(GuiComponent component)
	{
		if (component == playBtn)
		{
			playBtn.setTextOffset(225, 38);
		}
		
		if (component == exitBtn)
		{
			exitBtn.setTextOffset(225, 38);
		}
	}

	@Override
	public void gameObjectClicked(GameObject object, int button)
	{
		
	}
	
	@Override
	public void mouseClicked(int button)
	{
		
	}

	@Override
	public void keyPressed(int keyCode)
	{
		
	}

	@Override
	public void keyReleased(int keyCode)
	{
		
	}
}