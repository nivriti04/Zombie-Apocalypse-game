package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//player class extending the ImageView class
public class Player extends ImageView{

	//making variables
	private Image imgRight, imgLeft, imgUp, imgDown, imgDead; 
	private int dir; 
	public final static int RIGHT =0, LEFT =1, UP=2, DOWN=3; 
	
	//constructor
	public Player(double xPos, double yPos, String player)
	{
		//calling the constructor of the superclass
		super(); 
		
		//setting the x pos and y pos based on the values passed in
		super.setX(xPos);
		super.setY(yPos);
		
		//setting the images for different positions of the player
		imgRight = new Image("file:" + player + "Right.png");
		imgLeft = new Image("file:" + player + "Left.png");
		imgUp = new Image("file:" + player + "Up.png");
		imgDown = new Image("file:" + player + "Down.png");
		imgDead = new Image("file:" + player + "Dead.png");
		
		super.setFitWidth(imgDown.getWidth());
		super.setFitHeight(imgDown.getHeight());
		super.setImage(imgDown);
		
	}

	//return the width of the player image
	public double getWidth() {

		return super.getFitWidth();
	}

	//return the heigth of the player image
	public double getHeight() {

		return super.getFitHeight();
	}

	//set the width of the player image
	public void setWidth(double width) 
	{
		super.setFitWidth(width);
		super.setPreserveRatio(true);
		super.setSmooth(true);
	}
	
	//set the height of the player image
	public void setHeight(double height) 
	{
		super.setFitHeight(height);
		super.setPreserveRatio(true);
		super.setSmooth(true);
	}

	//method to move the player right
	public void moveRight() 
	{
		//sets the direction and image to right and moves the player at a speed of 8
		dir = RIGHT; 
		super.setImage(imgRight);
		super.setX(super.getX() + 8);
	
	}
	
	//method to move the player left
	public void moveLeft()
	{
		//sets the direction and image to left and moves the player at a speed of 8
		dir = LEFT; 
		super.setImage(imgLeft);
		super.setX(super.getX() - 8);
	}
	
	//method to move the player up
	public void moveUp()
	{
		//sets the image to up and moves the player at a speed of 8
		super.setImage(imgUp);
		super.setY(super.getY() - 8);
	}
	
	//method to move the player down
	public void moveDown()
	{
		//sets the image to down and moves the player at a speed of 8
		super.setImage(imgDown);
		super.setY(super.getY() + 8);
	}
	
	//return the direction the player is facing
	public int getDirection()
	{
		return dir; 
	}
}

//The keyword super refers to the superclass (ImageView).

	