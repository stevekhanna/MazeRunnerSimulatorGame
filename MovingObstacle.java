
public class MovingObstacle extends Obstacles{
    public MovingObstacle(){
      appearance= "|G|";

  }

  public void moveObstacleV(GameGrid gg)
  {
		//checks moving obstacle's current location

		for (gg.col = 0; gg.col < 10; gg.col++)
		{
				for (gg.row = 0; gg.row < 10; gg.row++)
				{
					  if ((gg.grid[gg.row][gg.col]== gg.V))
					  {
						  gg.setMoveCol(0);
						  if(gg.P.turnCounter%4==0||gg.P.turnCounter%4==1)
						  {
							  gg.setMoveRow(-1);
							  gg.ObstacleCollisionV();
							  gg.grid[gg.row-1][gg.col]=gg.V;
							  gg.grid[gg.row][gg.col]=null;
							  return;
						  }

						  else if(gg.P.turnCounter%4==3||gg.P.turnCounter%4==2)
						  {
							  gg.setMoveRow(1);
							  gg.ObstacleCollisionV();
							  gg.grid[gg.row+1][gg.col]=gg.V;
							  gg.grid[gg.row][gg.col]=null;
							  return;
						  }

					  }
				}

		}

  }

    public void moveObstacleH(GameGrid gg)
	{
		//checks moving obstacle's current location

		for (gg.col = 0; gg.col < 10; gg.col++)
		{
			for (gg.row = 0; gg.row < 10; gg.row++)
			{
				if ((gg.grid[gg.row][gg.col]== gg.H))
				{
					gg.setMoveRow(0);
					if(gg.P.turnCounter%4==0||gg.P.turnCounter%4==1)
					{
						gg.setMoveCol(-1);
						gg.ObstacleCollisionH();
						gg.grid[gg.row][gg.col-1]=gg.H;
						gg.grid[gg.row][gg.col]=null;
						return;
					}

					else if(gg.P.turnCounter%4==3||gg.P.turnCounter%4==2)
					{
						gg.setMoveCol(1);
						gg.ObstacleCollisionH();
						gg.grid[gg.row][gg.col+1]=gg.H;
						gg.grid[gg.row][gg.col]=null;
						return;
					}
     		    }
			}

		}
    }


}
