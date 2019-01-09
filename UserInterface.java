/*Date: 15/06/2017
 *
 * Has the main method.
 * Displays the start message with rules and main menu.
 * Takes in user input and quits if user presses q or Q
 * Starts if user presses S or s
 *
 * */
import java.util.Scanner;

public class UserInterface{
  private Scanner in= new Scanner(System.in);
  private boolean done;
  private GameGrid grid;
  private String ruleString=rules();

//Constructor sets done variable to false and displays the initials message
  public UserInterface(){

	  done = false;
	  System.out.println(ruleString);
    
//Calls the start method
	  start();
  }
  

  public static String rules(){
    return("\n\tWELCOME TO MAZE RUNNER SIMULATOR CPSC 233!\n\n"
    +"RULES OF THE GAME ARE AS FOLLOWS:\n"
    +"\nThe aim of the game is for the (P)layer to get to the (F)lag with the highest Energy and Health remaining.\n"
    +"Initial Health and Initial Energy is 30 points.\n"
    +"No diagonal movement is allowed.\n"
    +"Running into a stationary (O)bstacle will reduce your Health by 10 points.\n"
    +"Running into (G)uards will get you captured.\n"
    +"Each turn uses up one unit of energy and moving out of the grid is not permitted.\n"
    +"Good Luck!\n");

  }

  //Prints start options. This method is called in the start method
  public void startOptions(){

	  //The options the user has when they start the game
	  System.out.println("Options");
	  System.out.println("(S)tart game");
	  System.out.println("(Q)uit game");
	  System.out.print("Selection:");
  }
//Takes in user input and validates it
  public void start(){
	  while(!done){

		  try{startOptions();

		  final int FIRST =0;
		  String line = null;
		  char selection= ' ';
		  line = in.nextLine();
		  selection= line.charAt(FIRST);

		  while ((selection != 's' &&
				  selection != 'S' &&
				  selection != 'q' &&
				  selection != 'Q' ) || done ==true){

				System.out.println("Please enter a valid input");
				System.out.println("Enter a selection: ");
				line = in.nextLine();
				selection = line.charAt(FIRST);
		  }

		  System.out.println();

		  switch (selection){

				case 'S' :
				case 's' :
					System.out.println("Starting Game...\n");
					grid = new GameGrid('t');
					movementValidation();
				break;

				case 'Q':
				case 'q':
					System.out.println("Thank you for using Maze Runner Simulator CPSC 233!");
					done= true;
					System.exit(0);
				break;
			}
		}
		catch(StringIndexOutOfBoundsException e)
		{
			System.out.println("That is not a valid input");
		}
	}
}

//once game has started takes in input for movement and validates that.
 public void movementValidation(){


		 while(!done){
			 try{

				 System.out.print("\nEnter your move(w/a/s/d/x): ");
				 String line = in.nextLine();
				 final int FIRST= 0;
				 char move = line.charAt(FIRST);

				 while ((move != 'w' &&
						 move != 'a' &&
						 move != 's' &&
						 move != 'd' &&
						 move != 'x' &&
						 move != 'q' &&
						 move != 'W' &&
						 move != 'A' &&
						 move != 'S' &&
						 move != 'D' &&
						 move != 'X' &&
						 move != 'Q')){
						System.out.println("Please enter a valid input");
						System.out.print("\nEnter your move(w/a/s/d/x): ");
						line = in.nextLine();
						move = line.charAt(0);
					}

					if (move== 'q' ||move =='Q'){
						System.out.println("Thank you for using Maze Runner Simulator CPSC 233!");
						done=true;
						System.exit(0);
					}

					System.out.println();
					System.out.println(grid.P.processMove(grid.P, move, grid));//calls the processMove method from GameGrid
					System.out.println(grid.getCollisonString());
					grid.V.moveObstacleV(grid);//calls the moveObstacleVertically method
					grid.H.moveObstacleH(grid);//calls the moveObstacleHorizontally method
					System.out.println("Health: "+grid.P.getHealth() + "\t" + "Energy: "+grid.P.getEnergy());
					grid.display();//Displays the grid each time its updated
					endGame();//calls endGame method

			}
			catch(StringIndexOutOfBoundsException e){
				System.out.println("That is not a valid input");
			}
		}
	}



//Checks the three cases when the game has ended and ends it if cases are met
//Creator Jun Young Won
  public void endGame()
  {
    if(grid.grid[9][9]==grid.P){
      System.out.println("Congrats! You win!");
      System.out.println(grid.P.toString());
      System.exit(0);}

    else if(grid.P.getEnergy() <= 0){
      System.out.println("You run out of energy and drop dead");
      System.out.println(grid.P.toString());
      System.exit(0);}

    else if(grid.P.getHealth() <= 0){
      System.out.println("You run out of health and drop dead");
      System.out.println(grid.P.toString());
      System.exit(0);}
    
    else if((grid.getHorzEnd()== true)||(grid.getVertEnd()==true)){
	  System.out.println("The guard ran into you!");
      System.out.println(grid.P.toString());
      System.exit(0);}
    else if(grid.getPlayEnd()==true){
		System.out.println("You ran into the guard");
		System.out.println(grid.P.toString());
		System.exit(0);}



  }

//Main method: start point of program creates the instance of UserInterface
  public static void main(String []args){
	  boolean quit=false;
	  Scanner key = new Scanner(System.in);
		while(!quit){  
		  try{
			  System.out.print("Enter T for text based or G for Graphical: ");
			  String choiceLine = key.nextLine();
			  final int FIRST=0;
			  char choice = choiceLine.charAt(FIRST);
			  
				switch(choice){
					
					case 't':
					case 'T':
						System.out.println("Running text based version");
						UserInterface UI = new UserInterface();
					return;
					
					case 'g':
					case 'G':
						System.out.println("Running Graphical version");
						GUI gui = new GUI();
					return;
					
					case 'q':
					case 'Q':
						System.out.println("Thank you for using Maze Runner Simulator CPSC 233!");
						quit=true;
						System.exit(0);
					return;
					
					default:
						System.out.println("Please enter a valid input");
						break;
				}
				
			  
		  }
		  catch(StringIndexOutOfBoundsException e){
			  System.out.println("That is not a valid input");
		  }
	  }
  }
}
