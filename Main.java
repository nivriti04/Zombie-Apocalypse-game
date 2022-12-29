package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class Main extends Application {
	
	//declaring all the variables
	private Pane root;
	private GridPane groot;
	private RadioButton []character, weapon;
	private Font horror, hbutton, regular, regularbutton;
	private Button[] buttons;
	private Label smoketime, chartitle, weapontitle, backtitle, lblhighscore, vs, timer, potionCollect, instructions, zombieKilled;
	private Scene scene;
	private String[] charoptions, weaponoptions;
	private ToggleGroup tg, tg1;
	private Image oswald, mer, mcdreamy,wanda, zombie1Right, zombie2Right, zombie3Right, imgBack;
	private Image redfire, bluefire,axe,spear, conwayLand, Plight, Embry, DeadLand;
	private ImageView ichar, izombie1, izombie2, izombie3, iweapon, iback, ioswald, imer, imcdreamy, iwanda;
	private AnimationTimer playerAni, smokeani, keyTimer, ani3;
	private ComboBox <String> background;
	private Stage stage;
	private Button start, highscore, exit, close, skiptown, skipattack, ready;
	private boolean moveUp, moveRight, moveLeft, moveDown; 
	private Player player; 
	private String chosenPlayer, chosenBack, chosenWeapon; 
	private ArrayList<ImageView> potion = new ArrayList<ImageView>(); 
	private ArrayList<ImageView> poison = new ArrayList<ImageView>(); 
	private ArrayList<ImageView> ppl = new ArrayList<ImageView>(); 
	private int potionCount, poisonCount, zombieCount, weaponCount; 
	private Image potionImg, poisonImg, killedImg, level3Back, key, graphic, cross, win, lose; 
	private Timeline attackTimer, smokeTimer, potionTimer, poisonTimer, zombieTimer, infoTimer, weaponTimer;
	ImageView igraphic, icross;
	private ImageView iviewBack, potionview, poisonview, ismoke, iKilled, ikey, iwin, ilose, ivZombie, iviewppl; 
	private Random rnd; 
	private int collected, timerCount, checklevel; 
	private ArrayList<Zombie> zombie = new ArrayList<Zombie>();  
	private ArrayList<Shoot> weaponshoot= new ArrayList<Shoot>();
	private ArrayList<Attack> zAttack= new ArrayList<Attack>();
	private Image hourglass, smoke, imgppl, zombieFinal; 
	private int seconds, townrows, towncol, attackrow, attackcol, zkilled, initialcollected;
	private TextArea txtArea, attackInfo, townInfo;
	private boolean check, towncontrol, attackcontrol, Level1, level3, poscheck, zombieUp;
	private int yPosZombie, pplCount, pplsave, attackCount, x, y, smokesec;

	public void start(Stage primaryStage) {
		try {
				
			//setting stage as primaryStage so that it can be used in methods as well (making it global)
			stage = primaryStage;
			
//			//if the user presses the close button	
//			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//				public void handle(WindowEvent e) 
//				{
//					// create an alert asking for confirmation if they really want to exit
//					Alert closealert = new Alert(AlertType.CONFIRMATION);
//					closealert.setContentText("Are you sure you want to exit?");
//					closealert.setTitle("Exit");
//					closealert.setHeaderText(null);
//					closealert.getButtonTypes().clear();
//					closealert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
//					
//					Optional<ButtonType> result = closealert.showAndWait();
//				
//					// if they choose yes
//					if (result.get() == ButtonType.YES)
//					{
//						//alert thanking them for using the book store
//						Alert exitconfirm = new Alert(AlertType.INFORMATION);
//						exitconfirm.setContentText("Thank you for playing!");
//						exitconfirm.setTitle("Thank You");
//						exitconfirm.setHeaderText(null);
//						
//						//display the thank you alert
//						Optional<ButtonType> exitresult = exitconfirm.showAndWait();
//						
//						//if they choose ok on that alert
//						if (exitresult.get()==ButtonType.OK)
//						{
//							//exit the program
//							System.exit(0);
//							Platform.exit();
//						}
//					}
//					
//					// if they choose no, take them back to the original screen
//					else
//					{
//						e.consume();
//					}
//				}
//					
//			});
			
			//declaring the layouts
			root = new Pane();
			groot= new GridPane();
			
			//initializing all the variables to be used in calculations and other places throughout the program
			level3 = false; 
			potionCount = -1; 
			poisonCount = -1; 
			zombieCount = -1; 
			weaponCount = -1; 
			attackCount = -1; 
			timerCount = 60; 
			zkilled=0;
			smokesec=50;
			pplsave=0;
			checklevel=0;
			initialcollected=0;
			rnd = new Random(); 
			towncontrol=true;
			attackcontrol=true;
			Level1=true;
			
			//creating images and imageviews for different elements used in the game
			potionImg = new Image("file:potionPurple.gif"); 
			poisonImg = new Image("file:potionRed.gif"); 
			killedImg = new Image("file:head.png"); 
			level3Back= new Image ("file:level3Back.png", 900, 600, false, false);
			
			graphic= new Image ("file:graphic.jpg", 120, 120, true, true);
			igraphic= new ImageView (graphic);
			
			cross= new Image ("file:cross.png", 120, 120, true, true);
			icross= new ImageView (cross);
			
			win= new Image ("file:win.png", 120, 120, true, true);
			iwin= new ImageView (win);
			
			lose= new Image ("file:lose.png", 120, 120, true, true);
			ilose= new ImageView (lose);
		
			hourglass = new Image("file:hourglass.png"); 
			
			yPosZombie = 0; 
			zombieFinal = new Image("file:zombieFinal.png"); 
			ivZombie = new ImageView(zombieFinal);
			ivZombie.setX(800);
			ivZombie.setY(yPosZombie);
			
			//images for characters
			oswald= new Image("file:Oswald.png");
			mer= new Image ("file:Mer.png");
			mcdreamy= new Image ("file:McDreamy.png");
			wanda= new Image ("file:Wanda.png");
			
			//setting the iview for characters
			ichar= new ImageView(oswald);
			ioswald= new ImageView(oswald);
			imer= new ImageView (mer);
			iwanda= new ImageView (wanda);
			imcdreamy= new ImageView (mcdreamy);
			
			//images for weapons
			bluefire= new Image ("file:blueFireRight.png");
			redfire=new Image ("file:redFireRight.png");
			axe= new Image ("file:axeRight.png");
			spear= new Image ("file:spearRight.png");
			
			//setting the iview for weapons
			iweapon= new ImageView (bluefire);
			
			//images for backgrounds
			DeadLand= new Image ("file:DeadLand.jpg", 250, 150, false, false);
			Plight=new Image ("file:Plight.jpg", 250, 150, false, false);
			conwayLand= new Image ("file:ConwayLand.jpg", 250, 150, false, false);
			Embry= new Image ("file:Embry.jpg", 250, 150, false, false);
			
			iback= new ImageView (DeadLand);
			
			//creating the background and setting its x and y position to the top left of the room
			imgBack= new Image("file:back.jpg");
			iviewBack= new ImageView (imgBack);
			iviewBack.setX(0);
			iviewBack.setY(0);
			
			//loading different fonts
			hbutton=Font.loadFont(new FileInputStream(new File("ZombieQueen.ttf")), 35);
			regular=Font.loadFont(new FileInputStream(new File("ZombieQueen.ttf")), 41);
			regularbutton=Font.loadFont(new FileInputStream(new File("ZombieQueen.ttf")), 21);

			//images and iviews for zombies
			zombie1Right= new Image ("file:zombie1Right.gif", 80, 150, false, false);
			izombie1= new ImageView (zombie1Right);
			
			zombie2Right= new Image ("file:zombie2Right.gif", 80, 150, false, false);
			izombie2= new ImageView (zombie2Right);
			
			zombie3Right= new Image ("file:zombie3Right.gif", 80, 150, false, false);
			izombie3= new ImageView (zombie3Right);
			
			//loading the scene and setting its size to the size of the background image
			scene = new Scene(root, imgBack.getWidth(),imgBack.getHeight());
			
			//array storing the 3 different buttons on the title page
			buttons= new Button[3];
			
			//for loop to create all 3 buttons and set their properties
			for (int i=0;i<3;i++)
			{
				buttons[i]= new Button();
				buttons[i].setPrefSize(120, 50);
				buttons[i].setFont(hbutton);
				buttons[i].setLayoutY(190);
				buttons[i].setTextFill(Color.WHITE);
				buttons[i].setBackground(null);
			}
			
			//setting the text, location and action when clicked for all 3 buttons
			buttons[0].setText("INFO");
			buttons[0].setLayoutX(100);
			buttons[0].setOnAction(e->info());
			
			buttons[1].setText("PLAY");
			buttons[1].setLayoutX(300);
			buttons[1].setOnAction(e->play());
			
			buttons[2].setText("EXIT");
			buttons[2].setLayoutX(500);
			buttons[2].setOnAction(e->exit());

			//setting the position of the 3 zombies on the scene
			izombie1.setLayoutX(10);
			izombie1.setLayoutY(310);
			izombie2.setLayoutX(70);
			izombie2.setLayoutY(310);
			izombie3.setLayoutX(130);
			izombie3.setLayoutY(310);
			
			//creating a label and setting its properties
			vs=new Label("VS.");
			vs.setTextAlignment(TextAlignment.CENTER);
			vs.setAlignment(Pos.CENTER);
			vs.setPrefSize(100, 50);
			vs.setTextFill(Color.WHITE);
			vs.setLayoutX(scene.getWidth()/2-50);
			vs.setLayoutY(340);
			vs.setFont(hbutton);
			
			//setting the position of the 4 characters on the scene
			ioswald.setLayoutX(460);
			ioswald.setLayoutY(300);
			imer.setLayoutX(510);
			imer.setLayoutY(300);
			iwanda.setLayoutX(560);
			iwanda.setLayoutY(300);
			imcdreamy.setLayoutX(610);
			imcdreamy.setLayoutY(300);
			
			//adding all the nodes to the root 
			root.getChildren().addAll(iviewBack, izombie1, izombie2, izombie3, vs, ioswald, imer, iwanda, imcdreamy);
			root.getChildren().addAll(buttons);
		 
			stage.setScene(scene);
			stage.show();
			
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//method for the info button
	public void info()
	{
		//removing all the contents from the previous pane
		root.getChildren().removeAll(iviewBack, izombie1, izombie2, izombie3, ioswald, imer, iwanda, imcdreamy, vs);
		root.getChildren().removeAll(buttons);
		
		//changing the background
		imgBack= new Image("file:town.jpg", 700, 450, false, false);
		iviewBack.setImage(imgBack);
		iviewBack.setX(0);
		iviewBack.setY(0);
		root.getChildren().add(iviewBack);	
		
		//creating a button to skip the background info about the town
		//setting its properties such as preferred size, font, position on the pane and text fill
		skiptown= new Button("SKIP");
		skiptown.setPrefSize(300, 30);
		skiptown.setFont(hbutton);
		skiptown.setLayoutY(350);
		skiptown.setLayoutX(450);
		skiptown.setTextFill(Color.WHITE);
		skiptown.setBackground(null);
		skiptown.setOnAction(e->skiptown());
		
		
		//creating a button to skip the background info about the attack
		//setting its properties such as preferred size, font, position on the pane and text fill	
		skipattack= new Button("SKIP");
		skipattack.setPrefSize(300, 30);
		skipattack.setFont(hbutton);
		skipattack.setLayoutY(350);
		skipattack.setLayoutX(450);
		skipattack.setTextFill(Color.WHITE);
		skipattack.setBackground(null);
		skipattack.setOnAction(e->skipattack());
			
		//2D array holding the information to be displayed as part of the information
		String info1[][]= {
							{"J","U","N","E", " ", "2", "7", " ", "2", "0", "0", "6", ":"},
							{"T", "O", "W", "N", " ", "O",  "F", " ", "A", "S", "H", "B", "Y"},
							{"J","U","N","E", " ", "2", "8", " ", "2", "0", "0", "6", ":"},
							{"Z", "O","M","B","I","E", " ", "A", "T", "T", "A", "C", "K"}
							};
		//initializing different variables
		seconds=30;
		
		//townrows starts from 0 since info about the town is stored in rows 0 and 1
		townrows=0; towncol=0;
		
		//attackrows starts from 2 since info about the attack is stored in rows 2 and 3
		attackrow=2;attackcol=0;
		
		//boolean variable to help in using the skip button
		check=false;
		
		//creating a label and setting its properties
		townInfo= new TextArea();
		townInfo.setLayoutX(20);
		townInfo.setLayoutY(340);
		townInfo.setPrefSize(250, 65);
		townInfo.setStyle("-fx-background-color: black");
		townInfo.setStyle("fx-control-inner-background: transparent");
		townInfo.setFont(regularbutton);
		townInfo.setEditable(false);
		
		//creating a label and setting its properties
		attackInfo= new TextArea();
		attackInfo.setLayoutX(20);
		attackInfo.setLayoutY(340);
		attackInfo.setPrefSize(250, 65);
		attackInfo.setStyle("-fx-background-color: black");
		townInfo.setStyle("fx-control-inner-background: transparent");
		attackInfo.setFont(regularbutton);
		attackInfo.setEditable(false);
	
		//adding cutscene 1 (info about the town if check is false)
		if (check==false)
		{
			root.getChildren().addAll(townInfo, skiptown);
		}
	
		
		// Declare and initialize a KeyFrame object
		KeyFrame kf = new KeyFrame(Duration.millis(350), new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) 
		{
			//if its cutscene 1 going on (the user has not skipped it)
			if (check==false)
			{
				//decrease seconds by 1 every 350 miliseconds
				seconds--;	
				
				//boolean variable towncontrol checks for if the last col of row 1 has been reached
				//if its true (meaning it has not been reached yet)
				if (towncontrol==true)
				{
					
					//append text stored in the first 2 rows to the text area
					townInfo.appendText(info1[townrows][towncol]);
				}
				
				//increase the towncol after each index creating the effect of slow automatic typing
				towncol++;

			//if seconds is 17 (this is the measured time it takes for the first row to finish)
			if (seconds==17)
			{
				//go to the next row and reset columns to 0
				townrows++;
				towncol=0;
				townInfo.appendText("\n");
			}
			
			//if the last letter has been reached of row 1 (last for cutscene 1)
			if (towncol==13 && townrows==1)
			{
				//change towncontrol to false so that it no longer appends text (this helps avoid index out of bounds error)
				towncontrol=false;
			}
			
			//if seconds have elapsed, go to the skiptown method (which is essentially just going to the next level)
			//this helps avoid repetition of code
			if (seconds==0)
			{
				skiptown();	
			}
			
			}
			
			//if check is true (its cutscene 2)
			else
			{
				//decrease seconds by 1 every 350 miliseconds
				seconds--;
				
				//boolean variable attackcontrol checks for if the last col of row 3 has been reached
				//if its true (meaning it has not been reached yet)
				if (attackcontrol==true)
				{
					//append text stored in rows 2 and 3
					attackInfo.appendText(info1[attackrow][attackcol]);
				}
				
				//increase the attack col by 1 every time creating the effect of slow automatic typing
				attackcol++;
				
				//if seconds is 17 (this is the measured time it takes for the row to finish)
				if (seconds==17)
				{
					//go to the next row and reset columns to 0
					attackrow++;
					attackcol=0;
					attackInfo.appendText("\n");
				}
				
				//if the last letter has been reached of row 3 (last for cutscene 2)
				if (attackcol==13 && attackrow==3)
				{
					//change attackcontrol to false so that it no longer appends text (this helps avoid index out of bounds error)
					attackcontrol=false;
				}
				
				//if seconds have elapsed, go to the skipattack method (which is essentially just going to the next level)
				//this helps avoid repetition of code
				if (seconds==0)
				{
					skipattack();	
				}
			}
		}
		});
		
		//Initialize the timeline object
		infoTimer = new Timeline(kf); 
		infoTimer.setCycleCount(Timeline.INDEFINITE);
		
		//play the timeline
		infoTimer.play();
	}
	
	//method for skipping town (for both if the user presses the skip button or the timer finishes)
	public void skiptown()
	{
		//stop the timer and remove various components from the background
		infoTimer.stop();
		root.getChildren().removeAll(iviewBack, townInfo, skiptown);
		
		//changing the background and adding components of the next cutscene
		imgBack= new Image("file:attack.jpg", 700, 450, false, false);
		iviewBack.setImage(imgBack);
		iviewBack.setX(0);
		iviewBack.setY(0);
		root.getChildren().addAll(iviewBack, skipattack, attackInfo);
		
		//resetting all variables
		seconds=30;
		check=true;
		infoTimer.play();
	}
	
	//method for skipping attack info (for both if the user presses the skip button or the timer finishes)
	public void skipattack()
	{
		//stop the timer and remove various components from the background
		infoTimer.stop();
		root.getChildren().removeAll(iviewBack, attackInfo, skipattack);

		//changing the background
		imgBack= new Image("file:info.jpg", 700, 450, false, false);
		iviewBack.setImage(imgBack);
		iviewBack.setX(0);
		iviewBack.setY(0);
		root.getChildren().addAll(iviewBack);
		
		//making a new label giving a little preface into what the game is about and setting its properties
		instructions= new Label("In a span of a day, a happy town's fate was changed to death "
				+ "and gloominess by the zombies. Although some people were able to escape, some are still stranded"
				+ " and it is your job agent to select the best player and the best weapon you see fit"
				+ " to carry out the task. More instructions will be given as you proceed.");
		instructions.setMaxSize(650, 200);	
		instructions.setWrapText(true);
		instructions.setLayoutX(25);
		instructions.setLayoutY(265);
		instructions.setStyle("-fx-background-color: white");
		instructions.setFont(regularbutton);
		
		//ready to play button for when the user has gotten to know all the info an seting its properties
		ready= new Button("READY TO PLAY");
		ready.setPrefSize(300, 40);
		ready.setLayoutX(400);
		ready.setLayoutY(370);
		ready.setFont(hbutton);
		ready.setTextFill(Color.WHITE);
		ready.setBackground(null);
		ready.setOnAction(e->readytoplay());
		
		//add nodes to the pane
		root.getChildren().addAll(instructions, ready);
	}
	
	//when the user presses the ready to play button
	public void readytoplay()
	{
		//remove components of cutscene 3
		root.getChildren().removeAll(iviewBack, instructions, ready);
		
		//creating the background and setting its x and y position to the top left of the room
		imgBack= new Image("file:back.jpg");
		iviewBack= new ImageView (imgBack);
		iviewBack.setX(0);
		iviewBack.setY(0);
		
		//add everything from the title page back onto the pane
		root.getChildren().addAll(iviewBack, izombie1, izombie2, izombie3, vs, ioswald, imer, iwanda, imcdreamy);
		root.getChildren().addAll(buttons);
	}
	
	//method for the play button
	public void play()
	{
		//removing all the contents from the previous pane
		root.getChildren().removeAll(iviewBack, izombie1, izombie2, izombie3, ioswald, imer, iwanda, imcdreamy, vs);
		root.getChildren().removeAll(buttons);
		
		//resetting the stage width and height
		stage.setWidth(650);
		stage.setHeight(585);
		
		//changing the background
		imgBack= new Image("file:highscoreback.jpg", 650, 585, false, false);
		iviewBack.setImage(imgBack);
		iviewBack.setX(0);
		iviewBack.setY(0);
		root.getChildren().add(iviewBack);
		
		//setting properties of the grid pane
		groot.setGridLinesVisible(false);
		groot.setPadding(new Insets (10,20,10,17));
		groot.setHgap(10);
		groot.setVgap(10);
		
		//creating the title
		Label intro= new Label ("Choose your character, weapon and area!");
		intro.setTextFill(Color.WHITE);
		intro.setPrefSize(680, 40);
		intro.setFont(regular);
		
		//array of radio buttons storing the four character options
		character= new RadioButton[4];
	
		//storing the four character names in a string array
		charoptions= new String[] {"Oswald", "Mer", "Wanda", "McDreamy"};
		
		//creating the radiobuttons, setting their size, font and text
		for (int i=0;i<4;i++)
		{
			character[i]= new RadioButton ();
			character[i].setPrefSize(150, 30);
			character[i].setText(charoptions[i]);
			character[i].setTextFill(Color.WHITESMOKE);
			character[i].setStyle("-fx-font-size: 13");
			
			//lambda expression for when any of the radiobuttons clicked
			character[i].setOnAction(e-> cclick());
		}
		
		// Grouping the RadioButtons so that only one can be clicked at a time. 
		tg = new ToggleGroup();
		tg.getToggles().addAll(character);
		
		//making a VBox and storing the radiobuttons in it
		VBox charchoice= new VBox();
		charchoice.setSpacing(5);
		charchoice.setPadding(new Insets(5,5,5,5));
		charchoice.getChildren().addAll(character);
		charchoice.setStyle("-fx-background-color: black");
		
		//titledpane that stores the vBox containing the radiobuttons and displays the exact title
		TitledPane ctitle = new TitledPane();
		ctitle.setText("Character Name");
		ctitle.setAlignment(Pos.CENTER);
		ctitle.setCollapsible(false);
		ctitle.setContent(charchoice);
		
		//array of radio buttons storing the four weapon options
		weapon= new RadioButton[4];
			
		//storing the four weapon titles in a string array
		weaponoptions= new String[] {"Blue Fire", "Red Fire", "Axe", "Spear"};
				
		//creating the radiobuttons, setting their size, font and text
		for (int i=0;i<4;i++)
		{
			weapon[i]= new RadioButton ();
			weapon[i].setPrefSize(150, 30);
			weapon[i].setText(weaponoptions[i]);
			weapon[i].setStyle("-fx-font-size: 13");
			weapon[i].setTextFill(Color.WHITESMOKE);

			
			//lambda expression for when any of the radiobuttons clicked
			weapon[i].setOnAction(e-> wclick());
		}
			
		// Grouping the RadioButtons so that only one can be clicked at a time. 
		tg1 = new ToggleGroup();
		tg1.getToggles().addAll(weapon);
			
		//making a VBox and storing the radiobuttons in it
		VBox weaponchoice= new VBox();
		weaponchoice.setSpacing(5);
		weaponchoice.setPadding(new Insets(5,5,5,5));
		weaponchoice.getChildren().addAll(weapon);
		weaponchoice.setStyle("-fx-background-color: black");
		
		//titledpane that stores the vBox containing the radiobuttons and displays the exact title
		TitledPane wtitle = new TitledPane();
		wtitle.setText("Weapon Choice");
		wtitle.setAlignment(Pos.CENTER);
		wtitle.setCollapsible(false);
		wtitle.setContent(weaponchoice);	
		
		//HBox to store the label and the comboxbox
		HBox backchoice= new HBox();
		backchoice.setSpacing(5);
		backchoice.setPadding(new Insets(10,10,10,10));
		backchoice.setStyle("-fx-background-color: black");
		
		//creating a combobox which contains options for which area the user wants to play in displaying
		//only 2 items at a time
		background= new ComboBox <String>();
		background.getItems().addAll(null, "Dead Land", "Embry", "Conway's Land", "Plight");
		background.setPrefWidth(100);
		background.setStyle("-fx-font-size: 15");
		background.setVisibleRowCount(3);
		background.setOnAction(e-> bclick());
		background.setStyle("-fx-background-color: white");
		
		//label asking the user to select an area
		Label prompt= new Label ("Select an area: ");
		prompt.setStyle("-fx-font-size: 14");
		prompt.setTextFill(Color.WHITESMOKE);
		
		//adding label and combobox to hbox
		backchoice.getChildren().addAll(prompt, background);
		
		//titledpane that stores the hBox containing the comboBox and the prompt label
		TitledPane btitle = new TitledPane();
		btitle.setText("Area Choice");
		btitle.setPrefHeight(174);
		btitle.setMaxWidth(250);
		btitle.setAlignment(Pos.CENTER);
		btitle.setCollapsible(false);
		btitle.setContent(backchoice);
		
		//creating label for holding the character title and the picture displayed at the bottom of it
		chartitle= new Label ("OSWALD");
		chartitle.setTextFill(Color.WHITE);
		chartitle.setFont(regularbutton);
		chartitle.setTextAlignment(TextAlignment.CENTER);
		chartitle.setAlignment(Pos.TOP_CENTER);
		chartitle.setGraphic(ichar);
		chartitle.setMaxWidth(300);
		chartitle.setMaxHeight(300);
		chartitle.setGraphicTextGap(20);
		chartitle.setContentDisplay(ContentDisplay.BOTTOM);
		
		//creating label for holding the weapon title and the picture displayed at the bottom of it
		weapontitle= new Label ("Blue Fire");
		weapontitle.setTextFill(Color.WHITE);
		weapontitle.setFont(regularbutton);
		weapontitle.setTextAlignment(TextAlignment.CENTER);
		weapontitle.setAlignment(Pos.TOP_CENTER);
		weapontitle.setGraphic(iweapon);
		weapontitle.setMaxWidth(300);
		weapontitle.setMaxHeight(300);
		weapontitle.setGraphicTextGap(65);
		weapontitle.setContentDisplay(ContentDisplay.BOTTOM);
		
		//creating label for holding the character title and the picture displayed at the bottom of it
		backtitle= new Label ("Dead Land");
		backtitle.setTextFill(Color.WHITE);
		backtitle.setFont(regularbutton);
		backtitle.setTextAlignment(TextAlignment.CENTER);
		backtitle.setAlignment(Pos.TOP_CENTER);
		backtitle.setGraphic(iback);
		backtitle.setMaxWidth(250);
		backtitle.setMaxHeight(300);
		backtitle.setGraphicTextGap(20);
		backtitle.setContentDisplay(ContentDisplay.BOTTOM);
			
		//making the start button for when they are ready to play the game and setting its properties
		start= new Button("START");
		start.setMaxSize(124, 50);
		start.setBackground(null);
		start.setTextFill(Color.WHITE);
		start.setFont(hbutton);
		
		//method for the start button
		start.setOnAction(e->start());
		
		//making the high scores button for if they want to look at the high scores before they start playing
		highscore= new Button("HIGH SCORE");
		highscore.setMaxSize(191, 50);
		highscore.setBackground(null);
		highscore.setTextFill(Color.WHITE);
		highscore.setFont(hbutton);
		
		//method for the highscore button
		highscore.setOnAction(e->highscore());
		
		//making the exit button for if they want to quit
		exit= new Button("EXIT");
		exit.setMaxSize(110, 50);
		exit.setBackground(null);
		exit.setTextFill(Color.WHITE);
		exit.setFont(hbutton);
		
		//method for the exit button
		exit.setOnAction(e->exit());
		
		//creating an hbox to store all the buttons
		HBox options= new HBox();
		options.setSpacing(20);
		options.setPadding(new Insets(10,10,10,60));
		options.getChildren().addAll(start, highscore, exit);
		
		//adding the main title
		GridPane.setColumnSpan(intro, 3);
		GridPane.setHalignment(intro, HPos.CENTER);
		groot.add(intro, 0, 0);
				
		//adding the titled panes and setting their alignment
		groot.add(ctitle, 0, 1);
		groot.add(wtitle, 1, 1);
		GridPane.setValignment(btitle, VPos.TOP);
		groot.add(btitle, 2, 1);
		
		//adding the character's name based on the selection of the user
		GridPane.setColumnSpan(chartitle, 1);
		groot.add(chartitle, 0, 2);
		
		//adding the weapon's name based on the selection of the user
		GridPane.setColumnSpan(weapontitle, 1);
		groot.add(weapontitle, 1, 2);
		
		//adding the background name based on the selection of the user
		GridPane.setColumnSpan(backtitle, 1);
		groot.add(backtitle, 2, 2);
			
		//adding all the buttons contained in the hbox
		GridPane.setColumnSpan(options, 3);
		groot.add(options, 0, 3);
		
		//when the user presses a key
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {

				//right, left, up and down are boolean variables controlling the movement of the player
				//depending upon whether the keys are pressed or released
				
				//if right key is pressed
				if (e.getCode() == KeyCode.RIGHT)
				{
					//set the variable to true
					moveRight = true;
				}
				
				//if left key is pressed
				else if (e.getCode() == KeyCode.LEFT)
				{
					//set the variable to true
					moveLeft = true;

				}
				
				//if up key is pressed
				else if (e.getCode() == KeyCode.UP)
				{
					//set the variable to true
					moveUp = true;
					
				}
				
				//if down key is pressed
				else if (e.getCode() == KeyCode.DOWN)
				{
					//set the variable to true
					moveDown = true;
					
				}				
			}
			
			});
	
		//when the user releases a specific key
		scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e) {
				
				//see line 818
				
				//if right key is released
				if (e.getCode() == KeyCode.RIGHT)
				{
					//set the variable to false
					moveRight = false;
				}
				
				//if left key is released
				else if (e.getCode() == KeyCode.LEFT)
				{
					//set the variable to false
					moveLeft = false;

				}
				
				//if up key is released
				else if (e.getCode() == KeyCode.UP)
				{
					//set the variable to false
					moveUp = false;
					
				}
				
				//if down key is released
				else if (e.getCode() == KeyCode.DOWN)
				{
					//set the variable to false
					moveDown = false;
					
				}
				
				//if space key is released and the boolean varibale level 1 is false (since we are using keyreleased only
				//once, it is important to use a boolean variable to check when the relese of a button should be
				//activated. In this case, pressing space will only shoot a weapon if the player is on level 2
				
				else if(e.getCode() == KeyCode.SPACE && Level1 == false && level3==false)
				{
					//increase the index value by one 
					weaponCount++; 
					
					//add a bullet (based on the properties of the constructor of the Shoot class
					//at the index
					weaponshoot.add(weaponCount, new Shoot(chosenWeapon));
					
					//set its location passing in the x and y position of the player along with its direction
					weaponshoot.get(weaponCount).setPosition(player.getX(), player.getY(), player.getDirection());
					root.getChildren().addAll(weaponshoot.get(weaponCount).getNode());
					
					//play the timer
					weaponTimer.play(); 
						
				}
		}
	}); 
		
		//AnimationTimer for the player
		playerAni= new AnimationTimer() 
		{
			public void handle (long now)
			{
				//if right is true (the key is pressed)
				if (moveRight == true)
				{
					//call the moveRight method from the player class 
					player.moveRight();
					
					//if it touches the right side of the room
					if(player.getX() + player.getWidth() > scene.getWidth())
					{
						//reset its position so it doesn't cross the boundary of the room
						player.setX(scene.getWidth() - player.getWidth());
					}
				}
				
				//if left is true (the key is pressed)
				else if(moveLeft ==  true)
				{
					//call the moveLeft method from the player class 
					player.moveLeft();
				
					//if it touches the left side of the room
					if(player.getX()<0) 
					{
						//reset its position so it doesn't cross the boundary of the room
						player.setX(0);
					}
				}
				
				//if down is true (the key is pressed)
				else if(moveDown == true)
				{
					//call the moveDown method from the player class 
					player.moveDown(); 
					
					//if it touches the bottom of the room
					if(player.getY() +  player.getHeight()  > scene.getHeight())
					{
						//reset its position so it doesn't cross the boundary of the room
						player.setY(scene.getHeight() - player.getHeight() );
					}
				}
				
				//if up is true (the key is pressed)
				else if(moveUp == true)
				{
					//call the moveUp method from the player class 
					player.moveUp();
					
					//if it touches the top of the room (a little lower than top since that is where the labels are displayed)
					if(player.getY()<38)
					{
						//reset its position so it doesn't cross the boundary of the room
						player.setY(38); 
					}
				}

				//for loop going through the potion arraylist
				for (int k = 0; k< potion.size(); k++)
				{
					//if the player collides with a potion
					if(potion.get(k).getBoundsInParent().intersects(player.getBoundsInParent()))
					{
						//remove that potion from the pane
						root.getChildren().remove(potion.get(k)); 
						
						//increase the count of collected and initialcollected by 1
						//collected is used in the last level and initialcollected is used to keep track of high scores
						collected++; 
						initialcollected++;
						
						//reset the text of the label and remove it from the arrayList
						potionCollect.setText(Integer.toString(collected)); 
						potion.remove(k);
						
						//decrease the index count
						potionCount--; 
					}
				}
				
				//for loop going through the poison arraylist
				for (int i = 0; i< poison.size(); i++)
				{
					//if the player collides with a poison
					if(poison.get(i).getBoundsInParent().intersects(player.getBoundsInParent()))
					{
						//remove it from the pane, the arrayLista nd decrease the index count by 1
						root.getChildren().remove(poison.get(i)); 
						poison.remove(i);
						poisonCount--; 
						
						//call the lose method
						lose(); 
					}
				}
		
				//for loop going through the zombie arrayList 
				for (int i=0; i<zombie.size(); i++)
				{
					//calling the move method from the zombie class and the getNode
					//boolean variable Level1 keeps track of which zombie image needs to be used based on which level the user is on
					//if the user is on level 1, the girl zombie is displayed and if on level 2, it is he guy zombie that shows up
					zombie.get(i).move(5);
					zombie.get(i).getNode(Level1); 
					
					//checking if the zombie is outside the room on the left side 
					if(zombie.get(i).getX()< - zombie.get(i).getWidth())
					{
						//removing the image and reducing the  zombieCount
						root.getChildren().remove(zombie.get(i).getNode(Level1));
						zombie.remove(i); 
						zombieCount--; 
					}
					
					//if the player intersects with the zombie
					if(getZombieBounds(i).intersects(getPlayerBounds()))
					{
						//removing the image and reducing the  zombieCount
						root.getChildren().remove(zombie.get(i).getNode(Level1)); 
						zombie.remove(i);
						zombieCount--; 
						
						//calling the lose method
						lose();	
					}
				}
				}
		};
		
		//adding the grid pane to the root
		root.getChildren().add(groot);
	}
	
	//method for if they click the exit button
	public void exit()
	{
		//display an alert asking for confirmation if they want to exit
		Alert exitalert = new Alert(AlertType.CONFIRMATION);
		exitalert.setContentText("Are you sure you want to exit?");
		exitalert.setTitle("Exit");
		exitalert.setGraphic(igraphic);
		exitalert.setHeaderText(null);
		
		//adding other buttons instead of yes/no
		exitalert.getButtonTypes().clear();
		exitalert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> result = exitalert.showAndWait();
		
		// if the user clicks yes
		if (result.get() == ButtonType.YES)
		{
			Alert quitconfirm = new Alert(AlertType.INFORMATION);
			quitconfirm.setContentText("Thank you for playing!");
			quitconfirm.setTitle("Thank You");
			quitconfirm.setGraphic(igraphic);
			quitconfirm.setHeaderText(null);
			
			//display the thank you alert made at the top
			Optional<ButtonType> quitresult = quitconfirm.showAndWait();
			
			//if they press ok on that
			if (quitresult.get()==ButtonType.OK)
			{
				//exit the program
				System.exit(0);
				Platform.exit();
			}
			
		}
	}
	
	//method for selecting a character
	public void cclick()
	{
		//*The string chosenPlayer stores the name of the character chosen which helps to draw the character
		//based on the selection of the user to the pane when the game loads
		
		//if the first radiobutton is selected
		if (character[0].isSelected())
		{
			//set the corresponding image and text
			ichar.setImage(oswald);
			chartitle.setText("OSWALD");
			chosenPlayer = "Oswald"; 
		}
		
		//if the second radiobutton is selected
		else if (character[1].isSelected())
		{
			//set the corresponding image and text
			ichar.setImage(mer);
			chartitle.setText("MER");
			chosenPlayer = "Mer"; 
		}
		
		//if the third radiobutton is selected
		else if (character[2].isSelected())
		{
			//set the corresponding image and text
			ichar.setImage(wanda);
			chartitle.setText("WANDA");
			chosenPlayer = "Wanda"; 
		}
		
		//if the fourth radiobutton is selected
		else
		{
			//set the corresponding image and text
			ichar.setImage(mcdreamy);
			chartitle.setText("McDreamy");
			chosenPlayer = "McDreamy"; 

		}
	}
	
	//method for selecting a weapon
	public void wclick()
	{
		//*The string chosenWeapon stores the name of the weapon chosen which helps to draw the weapon
		//based on the selection of the user to the pane when level2 loads
			
		//if the first radiobutton is selected
		if (weapon[0].isSelected())
		{
			//set the corresponding image and text
			iweapon.setImage(bluefire);
			weapontitle.setText("Blue Fire");
			chosenWeapon="bluefireRight";
		}
		
		//if the second radiobutton is selected
		else if (weapon[1].isSelected())
		{
			//set the corresponding image and text
			iweapon.setImage(redfire);
			weapontitle.setText("Red Fire");
			chosenWeapon="redFireRight";

		}
		
		//if the third radiobutton is selected
		else if (weapon[2].isSelected())
		{
			//set the corresponding image and text
			iweapon.setImage(axe);
			weapontitle.setText("Axe");
			chosenWeapon="axeRight";
		}
		
		//if the fourth radiobutton is selected
		else
		{
			//set the corresponding image and text
			iweapon.setImage(spear);
			weapontitle.setText("Spear");
			chosenWeapon="spearRight";

		}
	}
	
	//method for selecting a background from the comboBox
	public void bclick()
	{
		//bindex stores the index value of what choice the user made
		int bindex;
		bindex=background.getSelectionModel().getSelectedIndex();

		//**the string chosenBack is used to load the right background when the user starts to play
		
		//if index 1 was selected
		if (bindex==1)
		{
			//set the corresponding image, change the text and set the string chosenBack to "DeadLand"
			iback.setImage(DeadLand);
			backtitle.setText("Dead Land");
			chosenBack = "DeadLand"; 
		}
		
		//if index 2 was selected
		else if (bindex==2)
		{
			//set the corresponding image, change the text and set the string chosenBack to "Embry"
			iback.setImage(Embry);
			backtitle.setText("Embry");
			chosenBack = "Embry"; 
		}
		
		//if index 3 was selected
		else if (bindex==3)
		{
			//set the corresponding image, change the text and set the string chosenBack to "ConwayLand"
			iback.setImage(conwayLand);
			backtitle.setText("Conway's Land");
			chosenBack = "ConwayLand"; 
		}
		
		//if index 4 was selected
		else if (bindex==4)
		{
			//set the corresponding image, change the text and set the string chosenBack to "Plight"
			iback.setImage(Plight);
			backtitle.setText("Plight");
			chosenBack = "Plight"; 

		}
	}
	
	//method for the start button
	public void start()
	{
		//variables checking for valid input of data
		boolean charcheck, weaponcheck;
		boolean chardata=false, weapondata=false, backdata=false;
		
		//for loop that goes through the character radiobuttons checking if any one is selected
		for (int a=0;a<4;a++)
		{
			charcheck=character[a].isSelected();
			
			//if the player has chosen a character
			if (charcheck==true)
			{
				//change data to true and break the loop (valid input has been found)
				chardata=true;
				break;
			}
			
			//valid input was no found
			else 
			{
				chardata=false;
			}
		}
		
		//for loop that goes through the weapon radiobuttons checking if any one is selected
		for (int a=0;a<4;a++)
		{
			weaponcheck=weapon[a].isSelected();		
			
			//if the player has chosen a weapon 
			if (weaponcheck==true)
			{
				//change data to true and break the loop (valid input has been found)
				weapondata=true;
				break;
			}
			
			//valid input was no found
			else 
			{
				weapondata=false;
			}
		}
		
		//checking to see if an area in which they want to play has been selected
		if (background.getValue()!=null)
		{
			//change the value of the boolean variable to false (valid input)
			backdata=true;
		}
		//else change it to false (invalid input)
		else
		{
			backdata=false;
		}
		
		//if valid input was found (data is true)
		if (chardata==true && weapondata==true && backdata==true)
		{
			//call level 1 and the constructor of the player class passing in the chosen player and the x and y position
			player = new Player(0, 50, chosenPlayer); 
			level1(); 
		}
		
		//if data is found to be false (valid input was not there)
		else
		{
			//display an alert telling them about the wrong input
			Alert wrong = new Alert(AlertType.ERROR);
			wrong.setContentText("You need to select a minimum of one character, weapon AND area to start playing");
			wrong.setTitle("Error");
			wrong.setHeaderText(null);
			wrong.setGraphic(icross);
			wrong.showAndWait();
			
		}	
	}
	
	//method for the first level 
	public void level1() 
	{
		//boolean variable checklevel is used for displaying the high scores before and after playing
		checklevel++;
		
		//initializes the number of potions collected to 0 and removes the grid pane
		collected =0; 
		root.getChildren().removeAll(groot); 
		
		//image for the alert
		ImageView lblView = new ImageView(potionImg); 
		ImageView timerView = new ImageView(hourglass); 
		
		//label for displaying the number of potions collected
		potionCollect = new Label("0"); 
		potionCollect.setGraphic(lblView); 
		potionCollect.setFont(regular);
		potionCollect.setTextFill(Color.WHITESMOKE); 
		potionCollect.setLayoutX(scene.getWidth() - 20);
		
		//label for displaying how much time has elapsed
		timer = new Label("60"); 
		timer.setGraphic(timerView); 
		timer.setFont(regular);
		timer.setTextFill(Color.WHITESMOKE); 
		timer.setLayoutX(10);
		
		//resetting the stage height and width
		stage.setWidth(800);
		stage.setHeight(600);
		
		//setting the background based on the background image chosen by the user
		iviewBack.setImage(new Image("file:" + chosenBack + ".jpg"));

		//image for the alert
		Image imgpotion= new Image ("file:potionYellow.gif", 100, 130, true, true);
		ImageView iviewPotion= new ImageView (imgpotion);
		
		//label showing instructions for level 1
		Alert level1Alert= new Alert(AlertType.INFORMATION);
		level1Alert.setHeaderText(null);
		level1Alert.setTitle("Welcome to Level 1");
		level1Alert.setWidth(350);
		level1Alert.setHeight(500);
		level1Alert.setGraphic(iviewPotion);
		level1Alert.setContentText("Welcome to Level 1 "+chosenPlayer+ " ! You are stuck here for 60 seconds"
				+ " and you must dodge your way around the zombies since you have not unlocked your powers yet which"
				+ " can help you kill them. Potions and poisons will randomly spawn across the room. Poisons are red-colored"
				+ " vials and potions are blue. If you collide with a a poison you die so be safe around them. Make sure to "
				+ "collect as many potions as you can since they are an important asset later on. Don't complain we "
				+ "didn't warn you of all the dangers you are about to encounter. Good luck and collect those potions!!!!");	
		level1Alert.showAndWait();
		
		//initializing a key frame object for the potion (a potion will be added every 4 seconds to the room)
		KeyFrame kfPotion = new KeyFrame(Duration.millis(4000), new
				EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) 
					{
						//generating a random x and y position for the placement of the potion on the screen
						potionview = new ImageView(potionImg); 
						int x = rnd.nextInt((int) (scene.getWidth() - potionImg.getWidth())); 
						int y = rnd.nextInt((int) (scene.getHeight() - potionImg.getHeight() - 20)) + 35; 
						
						//increasing the index count and adding the potion to the arrayList
						potionCount++; 
						potion.add(potionCount, potionview);
						
						//setting the location of the potion and displaying it on the pane
						potion.get(potionCount).setX(x); 
						potion.get(potionCount).setY(y); 
						root.getChildren().add(potion.get(potionCount));
					}
		}); 
		
		//initializing a timeline object for the potion and playing the timer
		potionTimer = new Timeline(kfPotion); 
		potionTimer.setCycleCount(Timeline.INDEFINITE);
		potionTimer.play();
		
		//initializing a key frame object for the poison (a poison will be added every 7 seconds to the room)
		KeyFrame kfPoison = new KeyFrame(Duration.millis(7000), new
				EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) 
					{
						//changing the poscheck to true (this boolean variable helps check the position of the poison
						//and compare it to that of the player so that it is not generated on top of it leading to
						//the player dying).
						poscheck=true;
						poisonview = new ImageView(poisonImg); 
						
						//till poscheck is true
						while (poscheck==true)
						{
							//generate a random x and y position for the placement
							x = rnd.nextInt((int) (scene.getWidth() - poisonImg.getWidth())); 
							y = rnd.nextInt((int) (scene.getHeight() - poisonImg.getHeight() - 20)) + 35; 
							
							//if its not within the bounds of the player's
							if (!(x>=player.getX()&& x<=player.getX()+player.getWidth() && 
									y>=player.getY()&& y<=player.getY()+player.getHeight()))
							{	
								//change poscheck to false (Don't run the loop again)
								poscheck=false;
							}
						}
						
						//run this only if poscheck is false (a vlid x and y coordinate were generated)
						while(poscheck==false)
						{
							//increase the index count, add the image and set its location on the pane
							poisonCount++; 
							poison.add(poisonCount, poisonview);
							poison.get(poisonCount).setX(x); 
							poison.get(poisonCount).setY(y); 
							
							//change poscheck to true (this ensures that the next poison can be generated at a different location)
							poscheck=true;
						}
						
						//add the poison to the pane
						root.getChildren().add(poison.get(poisonCount));
					}
		}); 
		
		//initializing a timeline object for the poison and playing the timer
		poisonTimer = new Timeline(kfPoison); 
		poisonTimer.setCycleCount(Timeline.INDEFINITE);
		poisonTimer.play();

		
		//keyframe to generate the zombies
		KeyFrame kfZombie = new KeyFrame(Duration.millis(1000), new
				EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
					
						//reduce the time count and update the label text
						timerCount--; 
						timer.setText(Integer.toString(timerCount));
						
						//if they have survived 60 seconds
						if(timerCount == 59)
						{
							//call the end1 method
							end1(); 
							
						}
		
						//every second a new zombie class is added to the zombie arrayList. 
						zombieCount++; 
						zombie.add(zombieCount, new Zombie()); 
						
						//calling the ste location method which sets it to a random location 
						zombie.get(zombieCount).setLocation((int) scene.getWidth(), (int)scene.getHeight());
						
						//adding each zombie generated to the pane 
						root.getChildren().addAll(zombie.get(zombieCount).getNode(Level1));
					}
		}); 
		//adding the keyframe to the zombie timer and playing it. 
		zombieTimer = new Timeline(kfZombie); 
		zombieTimer.setCycleCount(Timeline.INDEFINITE);
		zombieTimer.play();

		//strat the player animation and add different nodes to the pane
		playerAni.start();
		
		root.getChildren().addAll(player, potionCollect, timer); 
				
	}
	
	//lose method for whenever the player collides with the zombie/smoke/red balls/poison
	public void lose()
	{
		Platform.runLater(new Runnable() {
			public void run()
			{	
					//stop the timers
					zombieTimer.stop();
					playerAni.stop(); 
					poisonTimer.stop(); 
					potionTimer.stop();
					
					//only access these timers if level 1 is false (implying that we have crossed level 1)
					//helps avoid the null pointer exception error
					if (Level1==false)
					{
						smokeTimer.stop();
						weaponTimer.stop();
						
					}
				
					//only access these timers if the user is on level 3
					//helps avoid the null pointer exception error
					if (level3==true)
					{
						ani3.stop();
					}
					
					//reset the index for the zombie arraylist
					zombieCount=-1;
					
					//display the alert telling them they lost
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Game Over");
					alert.setGraphic(ilose);
					alert.setHeight(90);
					
					//if the player is on level 3
					if(level3 == true)
					{
						//set the text in the alert such that they can see how many people they saved
						alert.setContentText("You LOSE!\nYou were able to save " + pplsave + " people!\nYou did not have enough potions left to defeat the head Zombie!");
					}
					
					//if on levels 1 or 2
					else 
					{
						//set a common text showing that they lost
						alert.setContentText("You LOSE. Game Over!\nBetter luck next time...");
					}
				
					//show the alert and exit the game
					alert.showAndWait();
					System.exit(0);
			}
			});
	}
	
	//method for making a rectangle box outside the player to ensure for better collision
	public Bounds getPlayerBounds() 
	{	
		Rectangle rect = new Rectangle(player.getX() + 15, player.getY(), player.getWidth() -30, player.getHeight() -10); 
		return rect.getBoundsInParent(); 
	}

	//method for making a rectangle box outside the zombie to ensure for better collision
	public Bounds getZombieBounds(int i) 
	{
		Rectangle rect = new Rectangle(zombie.get(i).getX() + 15, zombie.get(i).getY() - 10, zombie.get(i).getWidth() -30, zombie.get(i).getHeight() - 15); 
		return rect.getBoundsInParent(); 
	}
	
	//method for ending level 1
	public void end1()
	{
		Platform.runLater(new Runnable() {
			public void run()
			{
				//change all the movement variables to false
				moveUp = false; 
				moveDown = false; 
				moveRight = false; 
				moveLeft = false; 
				
				//stop the timers
				zombieTimer.stop();
				playerAni.stop(); 
				poisonTimer.stop(); 
				potionTimer.stop(); 

				//display an alert to congratulate them for passing level 1
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Level 1 Over");
				alert.setContentText("Congratulations for passing the first level!\nYou have collected " + collected + " potions!");
				alert.setGraphic(iwin);
				Optional<ButtonType> result = alert.showAndWait();
				
				// if the user clicks yes
				if (result.get() == ButtonType.OK)
				{
					//remove poisons and potions
					root.getChildren().removeAll(poison);
					root.getChildren().removeAll(potion);
							
					//go through the zombie list and remove all from the room
					for (int i=0;i<zombie.size();i++)
					{
						root.getChildren().remove(zombie.get(i).getNode(Level1));
						zombie.remove(i);
						zombieCount--;
						i=-1;
					}
					
					//remove other components from level 1 change the boolean variable to false and call
					//the method for playing level 2
					root.getChildren().removeAll(timer, potionCollect, player);
					Level1=false;
					level2();	
					
				}
			}
			});
	}
	
	
	//method for level 2
	public void level2()
	{	
		//image for alert
		Image imgsmoke= new Image ("file:greenCloud.png", 140, 130, false, false);
		ImageView iviewsmoke= new ImageView (imgsmoke);
		
		//alert displaying instructions for how to play level 2
		Alert level2Alert= new Alert(AlertType.INFORMATION);
		level2Alert.setHeaderText(null);
		level2Alert.setTitle("Welcome to Level 2");
		level2Alert.setWidth(350);
		level2Alert.setHeight(375);
		level2Alert.setGraphic(iviewsmoke);
		level2Alert.setContentText("You are now on Level 2 "+chosenPlayer+ " !. Zombies appear from the right side of the room"
				+ " but there is also toxic smoke coming from the left. If you collide with either of them, you die so watch "
				+ "where you go. Thankfully, you have unlocked your powers to kill the zombies with the weapon you"
				+ " selected. It takes 50 seconds for the smoke to poison the whole room and your goal is to kill 15 zombies before"
				+ " time elapses. Be mindful of the fact that once the zombies cross you and are to your left, they will not be counted as a "
				+ " part of your 15 zombies. There is then a  scroll unlocked which you will have to collect to advance to the next and final level."
				+ " Good luck and be safe. Interesting encounters are waiting for you!");		
		level2Alert.showAndWait();
		
		//start the player animation
		playerAni.start();
		
		//image for how many zombies have been killed
		iKilled = new ImageView(killedImg); 
		
		//label displaying the number of zombies killed
		zombieKilled = new Label();
		zombieKilled.setText(Integer.toString(zkilled));
		zombieKilled.setFont(regular);
		zombieKilled.setLayoutX(scene.getWidth() - 100);
		zombieKilled.setGraphic(iKilled);
		zombieKilled.setTextFill(Color.WHITESMOKE); 
		zombieKilled.setLayoutX(scene.getWidth() - 120);
		
		//image of the smoke coming in from the left and setting its properties
		smoke= new Image ("file:greenCloud.png");
		ismoke= new ImageView (smoke);
		ismoke.setX(-1100);
		ismoke.setY(-80);
		ismoke.setOpacity(0.5);
		root.getChildren().add(ismoke);	
		
		//initializing a key frame object for moving the weapon
		KeyFrame kfWeapon = new KeyFrame(Duration.millis(10), new
				EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
					
					//for loop going through the weapon list
					for (int i=0; i<weaponshoot.size(); i++)
					{
						//calling the move method and getNode to move the weapon right or left and draw the image
						weaponshoot.get(i).move(); 
						weaponshoot.get(i).getNode(); 
						
						//for loop going through the weaponshoot list
						for (int j=0; j<weaponshoot.size(); j++)
						{
							//for loop going through the zombie list
							for (int m=0; m<zombie.size(); m++)
							{
								// if the weapon collides with any of the zombies on the pane
								if(weaponshoot.get(j).getNode().getBoundsInParent().intersects(zombie.get(m).getNode(false).getBoundsInParent()))
								{
									//check for the position if its left of the player and on the pane
									//this prevents the player from just spamming the keyboard and killing the zombies before they appear on the pane
									if (!(zombie.get(m).getX() + zombie.get(m).getWidth() <=player.getX()) )
									{
										if( (zombie.get(m).getX()  + zombie.get(m).getWidth()  < scene.getWidth()))
										{
											
											//increase the variable storing the number of zombies killed
											zkilled++;
											
											//remove the weapon and zombie from the pane and from the lists
											root.getChildren().removeAll(weaponshoot.get(j).getNode(), zombie.get(m).getNode(false)); 
											zombie.remove(m);
											weaponshoot.remove(j);
											zombieCount--; 
											weaponCount--;      
											

											//update the label text showing the zombies killed
											zombieKilled.setText(Integer.toString(zkilled));
										}
										
									}
									
									
									//if they have killed 15 zombies
									if (zkilled==10)
									{
										//stop the timers 
										zombieTimer.stop();
										weaponTimer.stop();
										smokeani.stop();
										smokeTimer.stop();
										weaponTimer.stop();
										
										//go through the zombie list and remove it from the pane and the list
										for (int k=0;k<zombie.size();k++)
										{
											root.getChildren().remove(zombie.get(k).getNode(Level1));
											zombie.remove(k);
											zombieCount--;
											k=-1;
										}
										
										//call the key method to reveal the scroll with which they have to collide to go to the next level
										key();
										
									}
								}
							}
							}
						}
					}
		}); 
		
		//initialize a timeline object
		weaponTimer = new Timeline(kfWeapon); 
		weaponTimer.setCycleCount(Timeline.INDEFINITE);
	
		//label showing how much time till the smoke poisons the whole room
		smoketime = new Label("50"); 
		smoketime.setFont(regular);
		smoketime.setTextFill(Color.WHITESMOKE); 
		smoketime.setLayoutX(20);
		
		//declaring a key frame class for the smoke
		KeyFrame kfSmoke = new KeyFrame(Duration.millis(1000), new
				EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) 
					{
						
						//decrease smokesec (variable storing the 50 seconds) and update the label
						smokesec--;
						smoketime.setText(Integer.toString(smokesec));
						
						//if 50 seconds have been elapsed
						if (smokesec==0)
						{
							//stop the timer (it will automatically lead to a collision)
							smokeTimer.stop();
						}
						
					}
		});
		
		//initialize a timeline object
		smokeTimer= new Timeline(kfSmoke);
		smokeTimer.setCycleCount(Timeline.INDEFINITE);
		
		//animation for smoke
		smokeani= new AnimationTimer() 
		{
			public void handle (long now)
			{
				//used to move the smoke right by 0.25
				ismoke.setX(ismoke.getX() + 0.25);	
				
				//if the smoke collides with the player
				if (getSmokeBounds().intersects(player.getBoundsInParent()))
				{
					//remove the player from the room
					root.getChildren().remove(player); 
					
					Platform.runLater(new Runnable() 
					{
						public void run()
						{
							//stop the timers and call the lose method
							smokeani.stop(); 
							smokeTimer.stop();
							lose();
						}
						});
				}
				
			}
		};
		
		//start all the animations and timers
		smokeani.start();
		smokeTimer.play();
		zombieTimer.play();

		//add components needed for level 2 to the pane
		root.getChildren().addAll(player, zombieKilled, smoketime);
	}
	
	//method to end level 2
	public void end2()
	{
		Platform.runLater(new Runnable() {
			public void run()
			{
				//change all the movement variables to false
				moveUp = false; 
				moveDown = false; 
				moveRight = false; 
				moveLeft = false; 
				
				//stop the timers
				zombieTimer.stop();
				playerAni.stop(); 
				smokeani.stop();
				weaponTimer.stop();
				keyTimer.stop();
				
				//display an alert to congratulate them for passing level 2
				Alert level2over = new Alert(AlertType.INFORMATION);
				level2over.setHeaderText(null);
				level2over.setTitle("Level 2 over");
				level2over.setContentText("Congratulations!! You passed the second level! It is time for your biggest challenge.");
				level2over.setGraphic(iwin);
				Optional<ButtonType> level2result = level2over.showAndWait();
				
				// if the user clicks yes
				if (level2result.get() == ButtonType.OK)
				{
					//remove all the components from the pane
					root.getChildren().remove(zombieKilled);
					root.getChildren().remove(ismoke);
					root.getChildren().removeAll(player);
					
					//go through the weapon list and remove all from the pane
					for (int i=0;i<weaponshoot.size();i++)
					{
						root.getChildren().remove(weaponshoot.get(i).getNode());
						weaponshoot.remove(i);
						weaponCount--;
						i=-1;
					}
					
					//go through the zombie list and remove all from the pane
					for (int i=0;i<zombie.size();i++)
					{
						root.getChildren().remove(zombie.get(i).getNode(Level1));
						zombie.remove(i);
						zombieCount--;
						i=-1;
					}
					
					//change level 1 to false again and call the level 3 method
					Level1=false;
					level3();
				}
				
			}
		});
		
		
	}
	
	//method to create a rectangle around the smoke for better collision
	public Bounds getSmokeBounds()
	{
		Rectangle rectsmoke= new Rectangle(ismoke.getX() , ismoke.getY(), ismoke.getImage().getWidth()-120, ismoke.getImage().getHeight()); 
		return rectsmoke.getBoundsInParent();
	}
	
	//key method for if they win level 2
	public void key()
	{
		//image and imageview for the scroll
		key= new Image ("file:scroll.gif", 50, 50, true, true);
		ikey= new ImageView (key);
		
		//add it to the pane
		root.getChildren().add(ikey);
		
		//setting the position for the scroll
		ikey.setLayoutX(root.getWidth()-125);
		ikey.setLayoutY(root.getHeight()/2-150);
		
		//timer for animation
		keyTimer= new AnimationTimer() 
		{
			public void handle (long now)
			{
				//if the player collides with the key
				if (ikey.getBoundsInParent().intersects(getPlayerBounds()))
				{
					//remove the key and call the method to end level 2
					root.getChildren().remove(ikey);
					smokeTimer.stop();
					root.getChildren().remove(smoketime);
					end2();
				
				}
			}
		};
		
		//start the timer
		keyTimer.start();
	}
	
	//method for level 3
	public void level3()
	{	
			level3 = true; 
			
			//number of injured people to be generated in the room
			int  x, y; 
		
			stage.setWidth(900);
			stage.setHeight(600);
		
			iviewBack.setImage(level3Back); 
			
			Alert level3Alert= new Alert(AlertType.INFORMATION);
			level3Alert.setHeaderText(null);
			level3Alert.setTitle("Welcome to Level 3");
			level3Alert.setWidth(350);
			level3Alert.setHeight(500);
			level3Alert.setGraphic(iKilled);
			level3Alert.setContentText("You are now on the last level "+chosenPlayer+ " ! Poisoned people anywhere from 5 to 8 in number are lying all over the street"
					+ " and it is in your hands to save them by giving them a potion. Each person you save costs you one potion. In order"
					+ " to be saved yourself, you need to give 5 potions to the head zombie by colliding with him since potions are lethal for them."
					+ " Watch for the red deadly balls that the head zombie is throwing horizontally across the room while he is traveling up and "
					+ " down himself. The dilemma here is when you have 5 potions left, would you want to save as many people as you can or would you "
					+ "rather keep them for yourself since once your potions go below 5, you are destined to lose and be captured. Good luck and we "
					+ "hope you make the right decision.");
				
			level3Alert.showAndWait();
			
			player.setX(0); 
			
			pplCount = rnd.nextInt(4) + 5;
			System.out.print(pplCount);
			for(int j = 0; j<pplCount; j++)
			{
				
				imgppl = new Image("file:dead" + (rnd.nextInt(3)+1) +".png"); 
				
				iviewppl = new ImageView(imgppl);  
				
				x = (rnd.nextInt((int) (scene.getWidth() - imgppl.getWidth() - 50))); 
				y = (rnd.nextInt((int) (scene.getHeight() - imgppl.getHeight() - 20)) + 35);
				
				iviewppl = new ImageView(imgppl); 
				
				ppl.add(j, iviewppl);
				ppl.get(j).setX(x); 
				ppl.get(j).setY(y); 
				root.getChildren().add(ppl.get(j));
			}
			
			KeyFrame kfAttack = new KeyFrame(Duration.millis(850), new
					EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) 
						{
							//every second a new zombie class is added to the zombie arrayList. 
							attackCount++; 
							zAttack.add(attackCount, new Attack()); 
							
							//calling the ste location method which sets it to a random location 
							zAttack.get(attackCount).setLocation((int)ivZombie.getX(), (int)ivZombie.getY());
							
							//adding each zombie generated to the pane 
							root.getChildren().addAll(zAttack.get(attackCount).getNode());

							
						}
			}); 
			attackTimer = new Timeline(kfAttack); 
			attackTimer.setCycleCount(Timeline.INDEFINITE);
			attackTimer.play();
			
			ani3 = new AnimationTimer()
			{
				public void handle(long val) 
				{
					if(zombieUp == true)
					{
						yPosZombie -= 6; 
					}
					else 
					{
						yPosZombie += 6; 
					}
					if (ivZombie.getY()<0)
					{
						zombieUp = false; 
					}
					else if (ivZombie.getY() + ivZombie.getImage().getHeight() >= scene.getHeight())
					{
						zombieUp = true; 
					}
					
					ivZombie.setY(yPosZombie);
					
					for (int i=0; i<zAttack.size(); i++)
					{
						zAttack.get(i).move(5);
						
						if(player.getBoundsInParent().intersects(zAttack.get(i).getNode().getBoundsInParent()))
						{					
							root.getChildren().remove(zAttack.get(i).getNode());
							zAttack.remove(i); 
							attackCount--; 
							lose(); 
							
						}
					}
					for(int m = 0; m<ppl.size(); m++)
					{
						if(getPlayerBounds().intersects(ppl.get(m).getBoundsInParent()))
						{
							root.getChildren().remove(ppl.get(m));
							collected--; 
							pplsave++;
							potionCollect.setText(Integer.toString(collected)); 
							ppl.remove(m); 
							pplCount--;
							
						}
					}
					
					if(getPlayerBounds().intersects(ivZombie.getBoundsInParent()))
					{
						root.getChildren().remove(ivZombie);
						
						potionCollect.setText(Integer.toString(collected -5)); 
					
						if(collected>=5)
						{
							collected = collected -5; 
							end3(); 
						}
						else if(collected<5)
						{ 
							collected = collected -5; 
							lose(); 
						}
					}
					
				}
			}; 
			ani3.start();
			playerAni.start();
			root.getChildren().addAll(player, ivZombie, potionCollect); 
			
	}
	
	//method to end level 3
	public void end3()
	{
		Platform.runLater(new Runnable() {
			public void run()
			{
				//stop all the timers and change the movement variables to false
				moveUp = false; 
				moveDown = false; 
				moveRight = false; 
				moveLeft = false; 
				zombieTimer.stop();
				playerAni.stop(); 
				ani3.stop(); 
				
				//custom button for if they want to see their high score
				ButtonType highscore = new ButtonType("High Scores");
				
				//display an alert telling them that they were able to save the town and themselves from the zombie apocalypse
				Alert level3over = new Alert(AlertType.INFORMATION);
				level3over.setHeaderText(null);
				level3over.setTitle("Mission Acomplished");
				level3over.setGraphic(iwin);
				level3over.setWidth(350);
				level3over.setHeight(250);
				level3over.setContentText("Congratulations!! You were able to save yourself from the zombie and do good! You have " + collected + " potions left."
						+ " You were able to save " + pplsave + " people from the zombie apocalyse and defeat the monster zombie. Click the high scores button if you wish "
								+ "to see where you stand in the line of heroes or press exit to exit the game!");
				
				//adding custom buttons to the alert
				level3over.getButtonTypes().clear();
				level3over.getButtonTypes().addAll(highscore, ButtonType.CLOSE);
				
				Optional<ButtonType> level3result = level3over.showAndWait();
				
				// if the user clicks close
				if (level3result.get() ==ButtonType.CLOSE)
				{
					//display an alert thanking them for playing and exit the game
					Alert thanks= new Alert (AlertType.INFORMATION);
					thanks.setHeaderText(null);
					thanks.setTitle("Thank You");
					thanks.setGraphic(igraphic);
					thanks.setContentText("Thank you for playing!");
					thanks.showAndWait();
					
					System.exit(0);
					Platform.exit();
				}
				
				//if they press high score
				else if (level3result.get()==highscore)
				{
					//call the highscore method to show them where they stand
					highscore();
				}
				
				
			}});
	}

	//high score method
	public void highscore()
	{
		//if they have not yet started playing
		if (checklevel==0)
		{
			//reove the grid pane 
			root.getChildren().removeAll(groot, iviewBack);
		}
		
		//if they have started playing
		else
		{
			//reset the stage width and height to the original size
			stage.setWidth(650);
			stage.setHeight(585);
			
			//go through the dead people list and remove all from the pane
			for (int i=0;i<ppl.size();i++)
			{
				root.getChildren().remove(ppl.get(i));
				ppl.remove(i);
				pplCount--;
				i=-1;
				
			}
				
			//go through the red balls list and remove all from the pane
			for (int j=0;j<zAttack.size();j++)
			{
				root.getChildren().remove(zAttack.get(j).getNode());
				zAttack.remove(j);
				attackCount--;
				j=-1;
			}
			
			//stop the animation and remove other components from level 3
			ani3.stop();
			root.getChildren().removeAll(iviewBack, ivZombie, player, potionCollect);
		}
		
		//set the background
		imgBack= new Image("file:spooky.jpg", 650, 585, false, false);
		iviewBack.setImage(imgBack);
		iviewBack.setX(0);
		iviewBack.setY(0);
		root.getChildren().add(iviewBack);
		
		//label for displaying the title
		lblhighscore=new Label("HIGH SCORES");
		lblhighscore.setTextAlignment(TextAlignment.CENTER);
		lblhighscore.setAlignment(Pos.CENTER);
		lblhighscore.setPrefSize(480, 50);
		lblhighscore.setTextFill(Color.WHITE);
		lblhighscore.setLayoutX(stage.getWidth()/2-230);
		lblhighscore.setLayoutY(stage.getHeight()-535);
		lblhighscore.setFont(regular);
		
		Scanner scan = new Scanner (System.in);
		String line;
		
		ArrayList<Integer> sort= new ArrayList <Integer>();
	
		//add the potions collected by the player to the list
		sort.add(initialcollected);
		
		try
		{
			// Creates a file object
			File readscore = new File("highScore.txt");  
			FileReader in=new FileReader (readscore);
			BufferedReader readFile = new BufferedReader(in);
			
			// reads each line until the end of the file 
			while (((line=readFile.readLine())!=null))
			{
				//adds the line that was read to the arrayList as an integer
				int num=Integer.parseInt(line);
				sort.add(num);
			}
			readFile.close(); //Close BufferedReader
			in.close(); // Close the FileReader
		}
		
		//code to execute if exception thrown
		catch (Exception e)
		{
			System.out.print("ERROR");
		}
		
		//sort the contents of the list in reverse order
		Collections.sort(sort, Collections.reverseOrder());
		
		//create a text area to store the information
		txtArea= new TextArea();
		txtArea.setPrefSize(350, 360);
		txtArea.setLayoutX(stage.getWidth()/2-175);
		txtArea.setLayoutY(stage.getHeight()/2-180);
		txtArea.setStyle("-fx-background-color: black");
		txtArea.setStyle("-fx-control-inner-background: black");
		txtArea.setFont(regularbutton);
		
		//creating the close button which will take them back to the previous screen or ask them if they are ready to
		//quit depending upon where they are
		close= new Button("CLOSE");
		close.setPrefSize(120, 40);
		close.setFont(regularbutton);
		close.setLayoutX(stage.getWidth()/2-65);
		close.setLayoutY(stage.getHeight()/2+120);
		close.setTextFill(Color.BLACK);
		close.setStyle("-fx-background-color: white");
		close.setOnAction(e->close());
	
		//add a title to the textare
		txtArea.appendText("Number of potions initially collected:" + "\n");
		
		//variable to ensure the potions collected by the user only come up once
		boolean check1=true;
		
		//for loop to show the top 10 high scores
		for (int i=0;i<10;i++)
		{
			//if the player's number falls in top 10 and its the first time it is occurring
			if (sort.get(i)==collected &&check1==true)
			{
				//append the score and the name of the player
				txtArea.appendText(chosenPlayer + ": " + Integer.toString(sort.get(i)) + "\n");
				
				//change it to false so that if there are same number of potions collected, the user's name is shown only once
				check1=false;
			}
			
			//else append text as game player and the score
			else
			{
				txtArea.appendText("Game Player " + ": " + Integer.toString(sort.get(i))+ "\n");
			}
			
			//set editable to false so that the user cannot edit the area
			txtArea.setEditable(false);
		}
			
		//add the required components to the pane and close the scanner
		root.getChildren().addAll(txtArea, close, lblhighscore);
		scan.close();
	}
	
	
	//close method for the close button under high scores
	public void close()
	{
		//if the player hasn't started playing yet
		if (checklevel==0)
		{
			//remove all the components of the high scores scene
			root.getChildren().removeAll(close, txtArea, lblhighscore, iviewBack);
			
			//change the background
			imgBack= new Image("file:highscoreback.jpg", 650, 585, false, false);
			iviewBack.setImage(imgBack);
			iviewBack.setX(0);
			iviewBack.setY(0);
		
			//add the gridpane and the new background
			root.getChildren().addAll(iviewBack, groot);
		}
		
		//else if the player has started playing and won
		else
		{
			//display an alert thanking them for playing and exit the game
			Alert closethanks= new Alert (AlertType.INFORMATION);
			closethanks.setHeaderText(null);
			closethanks.setTitle("Thank You");
			closethanks.setContentText("Thank you for playing!");
			closethanks.setGraphic(igraphic);
			closethanks.showAndWait();
			
			System.exit(0);
			Platform.exit();
		}
	}
	
	
}
