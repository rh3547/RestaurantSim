package com.engineGui.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Creates the directories and files
 * for the new game.
 * 
 * @author Ryan Hochmuth
 *
 */
public class NewGame
{
	// Constructor data
	String path;
	String title;
	String windowX;
	String windowY;
	String fps;
	String tps;
	
	String fileTitle;
	
	/**
	 * Create a new game.
	 * @param path
	 * @param title
	 * @param windowX
	 * @param windowY
	 * @param fps
	 * @param tps
	 */
	public NewGame(String path, String title, String windowX, String windowY,
			String fps, String tps)
	{
		this.path = path;
		this.title = title;
		this.windowX = windowX;
		this.windowY = windowY;
		this.fps = fps;
		this.tps = tps;
		
		this.fileTitle = title.replace(" ", "");
		
		createDirectories();
		createGameFile();
	}
	
	/**
	 * Create the directories for the new game.
	 */
	private void createDirectories()
	{
		String currentPath = path;
		
		createDir(currentPath + "//" + fileTitle); // Create the base dir
		currentPath += "//" + fileTitle;
		
		createDir(currentPath + "//res"); // Create the res dir
		createDir(currentPath + "//res//audio"); // Create the audio dir
		createDir(currentPath + "//res//audio//music"); // Create the music dir
		createDir(currentPath + "//res//audio//sfx"); // Create the sfx dir
		createDir(currentPath + "//res//fonts"); // Create the fonts dir
		createDir(currentPath + "//res//graphics"); // Create the graphics dir
		createDir(currentPath + "//res//graphics//buttonImages"); // Create the buttonImages dir
		createDir(currentPath + "//res//graphics//icons"); // Create the icons dir
		createDir(currentPath + "//res//graphics//sprites"); // Create the sprites dir
		createDir(currentPath + "//res//graphics//sprites//player"); // Create the player dir
		createDir(currentPath + "//res//graphics//tiles"); // Create the tiles dir
		createDir(currentPath + "//res//worlds"); // Create the worlds dir
		
		createDir(currentPath + "//com"); // Create the com dir
		currentPath += "//com";
		
		createDir(currentPath + "//" + fileTitle.toLowerCase()); // Create the next package level dir
		currentPath += "//" + fileTitle.toLowerCase();
		
		createDir(currentPath + "//main"); // Create the main dir
		createDir(currentPath + "//objects"); // Create the objects dir
		createDir(currentPath + "//screens"); // Create the screens dir
		createDir(currentPath + "//tiles"); // Create the tiles dir
		createDir(currentPath + "//world"); // Create the world dir
	}
	
	/**
	 * Create one directory.
	 * @param path
	 */
	private void createDir(String path)
	{
		File dir = new File(path);
		if (!dir.exists())
			try
			{
		        dir.mkdir();
		    } 
			catch(SecurityException se)
			{
		        
		    }
	}
	
	/**
	 * Create the main game file.
	 */
	private void createGameFile()
	{
		PrintWriter pw = null;
		
		try
		{
			pw = new PrintWriter(new File(path + "//" 
					+ fileTitle + "//com//" + fileTitle.toLowerCase() + "//main//Game" + fileTitle + ".java"));
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		if (pw != null)
		{
			pw.println("package com." + fileTitle.toLowerCase() + ".main;");
			pw.println("");
			pw.println("public class Game" + fileTitle);
			pw.println("{");
			pw.println("\tpublic Game" + fileTitle + "()");
			pw.println("\t{");
			pw.println("\t\tSystem.out.println(\"Hello World!\");");
			pw.println("\t}");
			pw.println("");
			pw.println("\tpublic static void main(String[] args)");
			pw.println("\t{");
			pw.println("\t\tnew Game" + fileTitle + "();");
			pw.println("\t}");
			pw.println("}");
			
			pw.close();
		}
	}
}