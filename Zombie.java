package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Zombie 
{
	//creatign the required varaibles 
	private Image imgZombie, imgZombie1; 
	private ImageView iviewZombie; 
	private int xPos, yPos, width, height;
	private Random rand; 
	
	//constructor that initializes variables 
	public Zombie()
	{
		imgZombie = new Image("file:zombie2left.gif");
		iviewZombie = new ImageView(imgZombie); 
		
		imgZombie1= new Image("file:zombie1Left.gif");
		
		rand = new Random(); 
		
		xPos = -100; 
		yPos = -100; 
		
		width = (int) iviewZombie.getImage().getWidth(); 
		height = (int) iviewZombie.getImage().getHeight(); 
	}
	//returns the Imageview of the zombie
	public ImageView getNode(boolean Level1)
	{
		if (Level1==true)
		{
			iviewZombie.setImage(imgZombie);
			return iviewZombie;
		}
		else
		{
			iviewZombie.setImage(imgZombie1);
			return iviewZombie;
		}
	}
	//sets the xPos of the zombie to x position that is passed in
	public void setX(int x)
	{
		xPos = x; 
		iviewZombie.setX(xPos);
	}
	//sets the ypos of the zombie to the y position passed in 
	public void setY(int y)
	{
		yPos = y; 
		iviewZombie.setY(yPos); 
	}
	//returns the x Position of the zombie
	public int getX()
	{
		return xPos; 
	}
	//returns the y Position of the zombie
	public int getY()
	{
		return yPos; 
	}
	//return height of the zombie image 
	public int getHeight()
	{
		return height; 
	}
	//return width of the zombie image
	public int getWidth()
	{
		return width; 
	}
	//move the zombie left based on the speed passed in
	public void move(int pixels)
	{
		xPos-= pixels; 
		iviewZombie.setX(xPos); 
	}
	//setting the location of the zombie
	public void setLocation(int frameWidth, int frameHeight)
	{
		//xPos id outside the room on the right side 
		xPos =  frameWidth + width;
		//random y position along the y axis from top to bottom on right side 
		yPos = rand.nextInt(frameHeight - (int) height ) + 35;
		
		//setting the position of the image 
		iviewZombie.setX(xPos);
		iviewZombie.setY(yPos);
	}
}
