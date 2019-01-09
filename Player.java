/*
Version 1.0.2

Functionality: Sets atributes for the player class which can be changed
* This class is the child class of Components
*
**/

//The attributes of the Player
public class Player extends Components{



	private final int STARTING_HEALTH=30;
	private final int STARTING_ENERGY=30;
	private final int ENERGY_RATE=1;
	private final int HEALTH_RATE=10;

	private int score;
	private int health;
	private int energy;
	public int turnCounter=0;

//sets the attributes
	public Player(){
		appearance="|P|";

		setHealth(STARTING_HEALTH);
		setEnergy(STARTING_ENERGY);
	}


	//method used for returning health
	public int getHealth(){
		return health;
	}
//method for returning energy
	public int getEnergy(){
		return energy;
	}


//method for setting health
	public void setHealth(int health){
		this.health=health;
	}
//method for setting energy
	public void setEnergy(int energy){
		this.energy=energy;

	}
//method for using health
	public void useHealth(){
		int tempHealth = health-HEALTH_RATE;
		if (tempHealth<0){
			setHealth(0);
		}
		else{
			setHealth(tempHealth);
		}

	}
//method for using energy
	public void useEnergy(){
		int tempEnergy=energy-ENERGY_RATE;
		if (tempEnergy<0){
			setEnergy(0);
		}
		else{
			setEnergy(tempEnergy);
		}

	}
	//this method returns the String of final stats
	public String toString() {
       return ("Your final stats are as Follows: \n"+"Energy: "+getEnergy()+" "+ "Health: "+getHealth());

	}

	public String processMove(Player P, char move, GameGrid gg)
	{
		String moveString=null;
		turnCounter++;//Updates the turn
		useEnergy();//calls the useEnergy method in Player to spend energy every turn/movement
		//System.out.println("Turn Counter: "+turnCounter);//prints the turn for grid display


		try
		{
			//checks for the current player grid.row and grid.col before moving
			boolean done = false;
			gg.setMoveRow(0);
			gg.setMoveCol(0);
			for (gg.col = 0; gg.col < 10; gg.col++)
			{
				for (gg.row = 0; gg.row < 10; gg.row++)
				{
					if ((gg.grid[gg.row][gg.col]==P))
						{

						switch (move)//takes move char from the input from UserInterface
						{

							case 'd':
							case 'D':

								gg.setMoveCol(1);
								gg.PlayerCollision();
								//moves P and sets the past location to null
								gg.grid[gg.row][gg.col+1] = P;
								gg.grid[gg.row][gg.col] = null;
							return moveString =("Moving right...\n");

							case 'a':
							case 'A':

								gg.setMoveCol(-1);
								gg.PlayerCollision();
								//moves P and sets the past location to null
								gg.grid[gg.row][gg.col-1] = P;
								gg.grid[gg.row][gg.col] = null;
							return moveString =("Moving left...\n");

							case 's':
							case 'S':

								gg.setMoveRow(1);
								gg.PlayerCollision();
								//moves P and sets the past location to null
								gg.grid[gg.row+1][gg.col] = P;
								gg.grid[gg.row][gg.col] = null;
							return moveString =("Moving down...\n");

							case 'w':
							case 'W':

								gg.setMoveRow(-1);
								gg.PlayerCollision();
								//moves P and sets the past location to null
								gg.grid[gg.row-1][gg.col] = P;
								gg.grid[gg.row][gg.col] = null;
							return moveString =("Moving up...\n");

							case 'x':
							case 'X':

								gg.grid[gg.row][gg.col]=P;

							return moveString =("Staying stationary...\n");
						}


					}
				}
			}
		}

			catch(ArrayIndexOutOfBoundsException e){
				return moveString =("Moving out of the grid is not permitted!\n");}

	return moveString;}



}
