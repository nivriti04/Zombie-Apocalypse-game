package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Attack 
{
	//creating the required variables 
	private Image imgOrb; 
	private ImageView iviewOrb; 
	private int xPos, yPos, width, height;
	private Random rand; 
	
	//constructor that initializes variables 
	public Attack()
	{
		imgOrb = new Image("file:orb.gif");
		iviewOrb = new ImageView(imgOrb); 
		
		rand = new Random(); 
		
		xPos = -100; 
		yPos = -100; 
		
		width = (int) iviewOrb.getImage().getWidth(); 
		height = (int) iviewOrb.getImage().getHeight(); 
	}
	//returns the Imageview of the Orb
	public ImageView getNode()
	{
			iviewOrb.setImage(imgOrb);
			return iviewOrb;
		
	}
	//sets the xPos of the Orb to x position that is passed in
	public void setX(int x)
	{
		xPos = x; 
		iviewOrb.setX(xPos);
	}
	//sets the ypos of the Orb to the y position passed in 
	public void setY(int y)
	{
		yPos = y; 
		iviewOrb.setY(yPos); 
	}
	//returns the x Position of the Orb
	public int getX()
	{
		return xPos; 
	}
	//returns the y Position of the Orb
	public int getY()
	{
		return yPos; 
	}
	//return height of the Orb image 
	public int getHeight()
	{
		return height; 
	}
	//return width of the Orb image
	public int getWidth()
	{
		return width; 
	}
	//move the Orb left based on the speed passed in
	public void move(int pixels)
	{
		xPos-= pixels; 
		iviewOrb.setX(xPos); 
	}
	public void moveFinal()
	{
		
	}
	//setting the location of the Orb
	public void setLocation(int zombieX, int zombieY)
	{
		//xPos of the zombie 
		xPos =  zombieX + 20;
		
		//yPos of the zombie
		yPos = zombieY - 20; 
		
		//setting the position of the image 
		iviewOrb.setX(xPos);
		iviewOrb.setY(yPos);
	}
}

