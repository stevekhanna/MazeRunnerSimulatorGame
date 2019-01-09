/*Date 15/06/2017
 * This class is responsible for creating the grid and initializing it
 *
 */
import java.io.*;
public class GameGrid {

    public Components [][] grid;//defines a 2D grid of type object(Components)
    private final int MAX_ROW=10;
    private final int MAX_COL=10;
    public Components F= new Components();
    public Player P = new Player();//creates new player
    public Obstacles O = new Obstacles();//creates new stationary obstacle which is reused multiple times in the grid
    public MovingObstacle H = new MovingObstacle();//creates new moving obstacle with appearance of H
    public MovingObstacle V = new MovingObstacle();//creates new moving obstacle with appearance of V
    public int row;
    public int col;
    private int moveCol;
  	private int moveRow;
  	private boolean horzEnds = false;
  	private boolean vertEnds = false;
  	private boolean playEnd = false;
  	private String collision= "\n";

//Constructor initializes the game grid
    public GameGrid(char choice){


        grid = new Components[MAX_ROW][MAX_COL];//creates the new grid of a specific size
        wipe();//makes sure that each element is set to null




       String fileName = "Obstacles.txt";
        String line = null;

        try{
          FileReader fileReader = new FileReader(fileName);
          BufferedReader bufferedReader = new BufferedReader(fileReader);
          String[] gridComponents;
          while((line = bufferedReader.readLine()) != null){
            gridComponents = line.split(",");
            //row = Integer.parseInt(gridComponents[0]);
            //col=Integer.parseInt(gridComponents[1]);
            grid[Integer.parseInt(gridComponents[0])][Integer.parseInt(gridComponents[1])]=O;
          }
          bufferedReader.close();
        }
        catch(FileNotFoundException ex){
          System.out.println("Obstacles.txt was not found.");
          System.exit(0);
        }
        catch(IOException ex){
          ex.printStackTrace();
        }
        grid[0][0]=P;

		    grid[7][7]=H;
		    grid[4][4]=V;



		    grid[9][9]=F;

		if (choice== 't'){
			display();}
        else if(choice=='g'){
          //do nothing
        }
		else{
			System.out.println("Game grid could not be created.");}

		grid[0][0]=P;

		grid[7][7]=H;
		grid[4][4]=V;

		grid[9][9]=F;


    }

//Displays the grid
    public void display()
    {


        for (row=0; row<MAX_ROW; row++)
        {
            for (col =0; col<MAX_COL; col++)
            {
                if (grid[row][col]==null)
                {
                System.out.print("|_|");
                }
                else
                {
                    System.out.print(grid[row][col].getAppearance());
                }
            }
            System.out.println();
        }
    }
    //Wipes the entire grid. Makes sure each element is null.
    public void wipe()
    {

	   for (row = 0; row < MAX_ROW; row++)
        {
	       for (col = 0; col < MAX_COL; col++)
	       {
		     grid[row][col] = null;
	       }
	    }
    }

    public int getMoveRow(){
  		return moveRow;
  	}

    public int getMoveCol(){
  		return moveCol;
  	}

    public void setMoveRow(int moveRow){
  		this.moveRow=moveRow;
  	}

    public void setMoveCol(int moveCol){
  		this.moveCol=moveCol;
  	}
//you run into guard
    public boolean PlayerCollision()
    {
      for (col = 0; col < 10; col++)
  		{
  			for (row = 0; row < 10; row++)
  			{
  				if ((grid[row][col]==P))
					{
					  if(grid[row + moveRow][col + moveCol] == O)
					  {
						//if Player moves into "O" then calls the getHealth and useHealth to update the new health
						P.getHealth();
						P.useHealth();
						collision=("Ouch I'm on fire");
						return playEnd = false;
					  }//ends inner if
					  //checks the desired element the player wants to move to and sees if there is a moving obstacle there
					  else if(grid[row + moveRow][col + moveCol] == V || grid[row + moveRow][col + moveCol] == H)
					  {
						return playEnd = true;
					  }//ends else if
					  else{
						  collision="\n";
						return playEnd = false;}

					}//ends outer if
			}//ends inner for
        }//ends outer for
        return playEnd;
    }//ends method


    public String getCollisonString(){
		return collision;
	}
	public boolean getPlayEnd(){
		return playEnd;
	}

	public boolean getHorzEnd(){
		return horzEnds;

	}

	public boolean getVertEnd(){
		return vertEnds;
	}






    //guard runs into you
    public boolean ObstacleCollisionH()
    {
      for (col = 0; col < 10; col++)
			{
				for (row = 0; row < 10; row++)
				{
				  if (grid[row][col]== H)
				  {
					  //checks if the moving obstacle is moving into the Player coordinates
					  if(grid[row + getMoveRow()][col + getMoveCol()] == P)
					  {
						  //if moving obstacle is in the Player coordinates then prints the toString method from Player class and exits the game
					      return horzEnds = true;
					  }//ends inner if
                 else
					return horzEnds = false;
				  }//ends outer if
				}//ends inner for
		    }//ends outer for
		    return horzEnds;
    }//ends method

    //guard runs into you
    public boolean ObstacleCollisionV()
    {
      for (col = 0; col < 10; col++)
			{
				for (row = 0; row < 10; row++)
				{
				  if (grid[row][col]==V)
				  {
					  //checks if the moving obstacle is moving into the Player coordinates
					  if(grid[row + getMoveRow()][col + getMoveCol()] == P)
					  {
						  //if moving obstacle is in the Player coordinates then prints the toString method from Player class and exits the game
					      return vertEnds = true;
					  }//ends inner if
                  else
					  return vertEnds = false;
				  }//ends outer if
				}//ends inner for
		    }//ends outer for
		    return vertEnds;
    }//ends method

}
