package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//class for shooting
public class Shoot 
{
	//declaring variables
	private double x, y, width, height;
	private Image imgRight, imgLeft;
	private ImageView iweapon;
	private int dir; 
	public final static int RIGHT =0, LEFT =1;
	
	//constructor passing in the chosen weapon so that the weapon that the user selected can be used
	public Shoot(String chosenWeapon)
	{
		
		imgRight = new Image("file:" + chosenWeapon + ".png");
		imgLeft = new Image("file:" + chosenWeapon + ".png");
		
		//setting the x and y positions of the weapon
		iweapon= new ImageView (imgRight);
		
		iweapon.setX(0);
		iweapon.setY(0);
		
		width= iweapon.getImage().getWidth();
		height=iweapon.getImage().getHeight();
	}
	
	//move method moves the projectile at a speed of 5
	public void move()
	{
		//if direction is right
		if (dir==RIGHT)
		{
			x+=5;
		}
		
		//if direction if left
		else if (dir==LEFT)
		{
			x-=5;
		}

		iweapon.setX(x);
	}
	
	
	//returns the Imageview of the weapon based on the direction of the player
	public ImageView getNode()
	{
		if (dir==RIGHT)
		{
			iweapon.setImage(imgRight);
		}
		
		else if (dir==LEFT)
		{
			iweapon.setImage(imgLeft);
		}
		
		return iweapon;
	}
	
	//sets the position of the weapon based on the position of the player and the direction it is facing
	public void setPosition(double playerX, double playerY, int dir)
	{
		this.dir=dir;
		
		if (this.dir==RIGHT)
		{
			x=playerX+35;
		}
		else
		{
			x=playerX;
		}
		
		y=playerY+40;
			
		//updating the x and y position to the new coordinates
		iweapon.setX(x);
		iweapon.setY(y);
	}
	
	//sets the xPos of the weapon to x position that is passed in
	public void setX(int xPos)
	{
		x=xPos;
		iweapon.setX(x);
	}
	
	//sets the ypos of the weapon to the y position passed in 
	public void setY(int yPos)
	{
		y=yPos;
		iweapon.setY(y);
	}
	
	//return the x position
	public double getX()
	{
		return x;
	}
	
	//return the y position
	public double getY()
	{
		return y;
	}
	
	//return the width of the image
	public double getWidth() 
	{
		return width;
	}

	//return the height of the image
	public double getHeight() 
	{
		return height;
	}
	
}
