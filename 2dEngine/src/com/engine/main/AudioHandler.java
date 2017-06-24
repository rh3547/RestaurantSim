package com.engine.main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Port;

/**
 * AudioHandler is the game's audio engine.
 * All sound is processed here.
 * 
 * @author Ryan Hochmuth
 *
 */
public class AudioHandler
{
	/*----------------------------------
	 * Sound Types
	 ---------------------------------*/
	public static final int SFX = 0;
	public static final int MUSIC = 1;
	
	private final Game game;
	
	/*----------------------------------
	 * Audio Variables
	 ---------------------------------*/
	// The computer's line out
	Port lineOut;
	// Used for volume control
	float musicVolume = 0;
	float sfxVolume = 0;
	
	public AudioHandler(Game game)
	{
		this.game = game;
	}
	
	/**
	 * Play the sound with name audioName once.
	 * @param audioName
	 */
	public synchronized void playSound(final String audioName, final int soundType) 
	{
		// Creates a new Thread to run the audio in
		new Thread(new Runnable() 
		{
			public void run()
		    {
				try 
				{
					// Create a Clip
					Clip clip = AudioSystem.getClip();
					// Obtain the proper audio file
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(game.getRespath() + audioName));
					// Open the clip and prepare for play back
					clip.open(inputStream);
					// Get the gainControl to control volume
					FloatControl gainControl = 
						    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					// Set the volume for the audio clip
					switch(soundType)
					{
						case SFX:
							gainControl.setValue(sfxVolume);
							break;
							
						case MUSIC:
							gainControl.setValue(musicVolume);
							break;
					}
					// Start playing the audio clip
					clip.start(); 
				} 
				catch (Exception e) 
				{
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
		    }
		}).start();	// Start the Thread
	}
	
	/**
	 * Stop the sound playing with name audioName.
	 * @param audioName
	 */
	public synchronized void stopSound(final String audioName, final int soundType) 
	{
		new Thread(new Runnable() 
		{
			public void run() 
		    {
				try 
				{
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(game.getRespath() + audioName));
					clip.open(inputStream);
					FloatControl gainControl = 
						    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					// Set the volume for the audio clip
					switch(soundType)
					{
						case SFX:
							gainControl.setValue(sfxVolume);
							break;
							
						case MUSIC:
							gainControl.setValue(musicVolume);
							break;
					}
					clip.stop(); 
				} 
				catch (Exception e) 
				{
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
		    }
		}).start();
	}
	
	/**
	 * Loop the sounds with name audioName as many times as count.
	 * @param audioName
	 * @param count
	 */
	public synchronized void loopSound(final String audioName, final int count, final int soundType) 
	{
		new Thread(new Runnable() 
		{
			public void run() 
		    {
				try 
				{
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(game.getRespath() + audioName));
					clip.open(inputStream);
					FloatControl gainControl = 
						    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					// Set the volume for the audio clip
					switch(soundType)
					{
						case SFX:
							gainControl.setValue(sfxVolume);
							break;
							
						case MUSIC:
							gainControl.setValue(musicVolume);
							break;
					}
					clip.loop(count-1);
				} 
				catch (Exception e) 
				{
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
		    }
		}).start();
	}
	
	/**
	 * Set the music volume of audio play back.
	 * To reduce volume give a negative number (ex. -10.0f).
	 * To increase volume give a positive number (ex. 6.0f).
	 * NOTE: When increasing volume, a maximum of 6.0f can be given.
	 * @param volume
	 */
	public void setMusicVolume(float musicVolume)
	{
		this.musicVolume = musicVolume;
	}
	
	/**
	 * Set the sfx volume of audio play back.
	 * To reduce volume give a negative number (ex. -10.0f).
	 * To increase volume give a positive number (ex. 6.0f).
	 * NOTE: When increasing volume, a maximum of 6.0f can be given.
	 * @param volume
	 */
	public void setSfxVolume(float sfxVolume)
	{
		this.sfxVolume = sfxVolume;
	}
}
