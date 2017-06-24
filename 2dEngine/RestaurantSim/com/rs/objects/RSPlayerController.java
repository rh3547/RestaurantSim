package com.rs.objects;

import com.engine.data.Bank;
import com.engine.gameObject.GameObject;
import com.rs.main.RSFlags;
import com.rs.main.RSResources;

public class RSPlayerController
{
	private static boolean actionHeld = false;
	private static final int speed = 8;
	
	public static void keyPressed(int keyCode)
	{
		if (RSResources.up.isPressed())
		{
			RSObjects.player.setyVel(-speed);
			RSObjects.player.setMovingDir(GameObject.NORTH);
		}
		
		if (RSResources.right.isPressed())
		{
			RSObjects.player.setxVel(speed);
			RSObjects.player.setMovingDir(GameObject.EAST);
		}
		
		if (RSResources.down.isPressed())
		{
			RSObjects.player.setyVel(speed);
			RSObjects.player.setMovingDir(GameObject.SOUTH);
		}
		
		if (RSResources.left.isPressed())
		{
			RSObjects.player.setxVel(-speed);
			RSObjects.player.setMovingDir(GameObject.WEST);
		}
		
		if (RSResources.jump.isPressed() &&
		!RSObjects.player.isJumping())
		{
			//RSObjects.player.setyVel(-15);
			//RSObjects.player.setJumping(true);
		}
		
		if (RSResources.action.isPressed())
		{
			if (!actionHeld)
			{
				
			}
			
			actionHeld = true;
		}
		
		if (RSResources.one.isPressed())
		{
			if (RSObjects.money.changeBalance(500, Bank.ADD))
			{
				RSFlags.moneyChanged = true;
				RSFlags.moneyChangeAmount = 500;
			}
		}
		if (RSResources.two.isPressed())
		{
			
		}
		if (RSResources.three.isPressed())
		{
			
		}
	}
	
	public static void keyReleased(int keyCode)
	{
		
		if (!RSResources.up.isPressed())
		{
			if (RSResources.right.isPressed())
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(speed);
				RSObjects.player.setMovingDir(GameObject.EAST);
			}
			else if (RSResources.down.isPressed())
			{
				RSObjects.player.setyVel(speed);
				RSObjects.player.setMovingDir(GameObject.SOUTH);
			}
			else if (RSResources.left.isPressed())
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(-speed);
				RSObjects.player.setMovingDir(GameObject.WEST);
			}
			else
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(GameObject.NODIR);
			}
		}
		if (!RSResources.right.isPressed())
		{
			if (RSResources.down.isPressed())
			{
				RSObjects.player.setyVel(speed);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(GameObject.SOUTH);
			}
			else if (RSResources.left.isPressed())
			{
				RSObjects.player.setxVel(-speed);
				RSObjects.player.setMovingDir(GameObject.WEST);
			}
			else if (RSResources.up.isPressed())
			{
				RSObjects.player.setyVel(-speed);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(GameObject.NORTH);
			}
			else
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(GameObject.NODIR);
			}
		}
		if (!RSResources.down.isPressed())
		{
			if (RSResources.left.isPressed())
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(-speed);
				RSObjects.player.setMovingDir(GameObject.WEST);
			}
			else if (RSResources.up.isPressed())
			{
				RSObjects.player.setyVel(-speed);
				RSObjects.player.setMovingDir(GameObject.NORTH);
			}
			else if (RSResources.right.isPressed())
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(speed);
				RSObjects.player.setMovingDir(GameObject.EAST);
			}
			else
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(GameObject.NODIR);
			}
		}
		if (!RSResources.left.isPressed())
		{
			if (RSResources.up.isPressed())
			{
				RSObjects.player.setyVel(-speed);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(GameObject.NORTH);
			}
			else if (RSResources.right.isPressed())
			{
				RSObjects.player.setxVel(speed);
				RSObjects.player.setMovingDir(GameObject.EAST);
			}
			else if (RSResources.down.isPressed())
			{
				RSObjects.player.setyVel(speed);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(GameObject.SOUTH);
			}
			else
			{
				RSObjects.player.setyVel(0);
				RSObjects.player.setxVel(0);
				RSObjects.player.setMovingDir(GameObject.NODIR);
			}
		}
		
		if (!RSResources.jump.isPressed() && 
				RSObjects.player.isJumping())
		{
			
		}
		
		if (keyCode == RSResources.action.getKeyCode())
			actionHeld = false;
	}
}