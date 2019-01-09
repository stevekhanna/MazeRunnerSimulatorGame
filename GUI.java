import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class GUI extends JComponent implements KeyListener {
	private Image playerImage = new ImageIcon("Player.png").getImage();
	private Image grass = new ImageIcon("grass.png").getImage();
	private Image fireImage = new ImageIcon("fire.png").getImage();
	private Image guardImage = new ImageIcon("guard.png").getImage();
	private Image flagImage = new ImageIcon("flag.png").getImage();
	private boolean done;
	private String endString;
	private GameGrid gg = new GameGrid('g');
	private JFrame project = new JFrame("CPSC 233 project");
	private JFrame frame = new JFrame("Maze Runner Simulator");
	private JTextArea startMessage = new JTextArea();
	
	
	public GUI(){
		addKeyListener(this);
		
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(500,537);
	    setFocusable(true);
	    frame.getContentPane().add(this);
	    frame.setResizable(false);
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
			  project.setSize(400,75);
			  project.setTitle("Options");
			  startButton.setVisible(false);
			  startMessage.setText("Use arrow keys for movement");
			  project.setLocation(300,500);
			  frame.setVisible(true);
		  }
	    });
	    
	    frame.setLocationRelativeTo(null);
	    
	    JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
			  endScreen("You ended the game!");
          }
        });
        project.setLayout(new FlowLayout());
        startMessage.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        startMessage.setSize(400,300);
        startMessage.setEditable(false);
        project.getContentPane().add(startMessage);
		project.setResizable(false);
        project.getContentPane().add(startButton);
        project.getContentPane().add(quitButton);


        project.setTitle("CPSC 233 Project");
        project.setSize(1000,370);
        project.setLocationRelativeTo(null);
        project.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        project.setVisible(true);
       
        //System.out.println();
        startMessage.setText(UserInterface.rules());
        
	    
	  
	}
	
	
	public void paint(Graphics g){
	
		for(int vertical = 0; vertical<500; vertical= vertical+50){
			for(int horizontal=0; horizontal<500; horizontal = horizontal+50){
				 for (gg.row=0; gg.row<10; gg.row++){
					for (gg.col =0; gg.col<10; gg.col++){
						if(gg.grid[gg.row][gg.col]==null){
							g.drawImage(grass,gg.col*50,gg.row*50,null);
							}
						if(gg.grid[gg.row][gg.col]==gg.O){
							g.drawImage(fireImage,gg.col*50,gg.row*50,null);
							}
						if((gg.grid[gg.row][gg.col]==gg.V)||(gg.grid[gg.row][gg.col]==gg.H)){
								g.drawImage(guardImage,gg.col*50,gg.row*50,null);
						}
						if(gg.grid[gg.row][gg.col]==gg.P){
								g.drawImage(playerImage,gg.col*50,gg.row*50,null);
						}
						if(gg.grid[gg.row][gg.col]==gg.F){
								g.drawImage(flagImage,gg.col*50,gg.row*50,null);
						}
					}
				}
			}
		}
		for(int vertical = 50; vertical<500; vertical= vertical+50){
			g.drawLine(vertical,0,vertical,500);
		}
		for(int horizontal=50; horizontal<500; horizontal = horizontal+50){
				g.drawLine(0,horizontal, 500,horizontal);
		}
	}
	
    public void keyTyped(KeyEvent ke){}
    public void keyReleased(KeyEvent ke) {}
    public void keyPressed(KeyEvent ke) {
		project.setSize(200,100);



		int id = ke.getKeyCode();

		if (id == KeyEvent.VK_LEFT||id == KeyEvent.VK_A){
			
			frame.setTitle(gg.P.processMove(gg.P, 'a', gg));//calls the processMove method from GameGrid
			gg.H.moveObstacleH(gg);//calls the moveObstacleVertically method
			gg.V.moveObstacleV(gg);
		}

		if(id == KeyEvent.VK_RIGHT||id == KeyEvent.VK_D){
			frame.setTitle(gg.P.processMove(gg.P, 'd', gg));//calls the processMove method from GameGrid
			gg.H.moveObstacleH(gg);//calls the moveObstacleVertically method
			gg.V.moveObstacleV(gg);
		 
		}

		if(id == KeyEvent.VK_UP||id == KeyEvent.VK_W){
			frame.setTitle(gg.P.processMove(gg.P, 'w', gg));//calls the processMove method from GameGrid
			gg.H.moveObstacleH(gg);//calls the moveObstacleVertically method
			gg.V.moveObstacleV(gg);
		}

		if(id == KeyEvent.VK_DOWN||id == KeyEvent.VK_S){
			frame.setTitle(gg.P.processMove(gg.P, 's', gg));//calls the processMove method from GameGrid
			gg.H.moveObstacleH(gg);//calls the moveObstacleVertically method
			gg.V.moveObstacleV(gg);
		}

		if(id==KeyEvent.VK_SPACE||id == KeyEvent.VK_X){
			
			frame.setTitle(gg.P.processMove(gg.P, 'x', gg));//calls the processMove method from GameGrid
			gg.H.moveObstacleH(gg);//calls the moveObstacleVertically method
			gg.V.moveObstacleV(gg);
		}
		if(id==KeyEvent.VK_ESCAPE||id == KeyEvent.VK_Q){
			endScreen("You have ended the game");
			
			
		}
		project.setTitle("Stats");
		project.setLocation(400,500);
		startMessage.setText("Health: "+ gg.P.getHealth() + "\nEnergy: "+ gg.P.getEnergy());
		if (gg.getCollisonString().equals("Ouch I'm on fire")){
			frame.setTitle(gg.getCollisonString());
		}
		repaint();
		endGame();
		
    }
  
	public void endGame()
	{
		if(gg.grid[9][9]==gg.P){
			endScreen("Congrats! You win!");}

		else if(gg.P.getEnergy() <= 0){
			endScreen("You ran out of energy and died");}

		else if(gg.P.getHealth() <= 0){
			endScreen("You ran out of health and died");}
		  
		else if((gg.getHorzEnd() == true)||(gg.getVertEnd() == true)){
			endScreen("The guard ran into you!");}
			 
		else if(gg.getPlayEnd() == true){
			endScreen("You ran into a Guard!");}

	}
  
    public void endScreen(String endString){
	  
		frame.setVisible(false);
		project.setVisible(false);
		JFrame end = new JFrame("The end results");
		JTextArea endMessage = new JTextArea();
		JButton restartButton = new JButton("Play Again");
        restartButton.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
			  GUI g = new GUI();
			  end.setVisible(false);
          }
        });
        JButton endQuit = new JButton("Close");
		endQuit.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
			  System.exit(0);
          }
        });
        endMessage.setBackground(Color.RED);
		endMessage.setFont(new Font(Font.DIALOG, Font.BOLD, 28));
		endMessage.setForeground(Color.ORANGE);
		endMessage.setText(endString + "\n" + gg.P.toString());
		end.setLocationRelativeTo(null);
		end.setSize(400,150);
		end.setLayout(new FlowLayout());
		endMessage.setFont(new Font(Font.DIALOG,Font.PLAIN,20));
		endMessage.setEditable(false);
		end.getContentPane().add(endMessage);
		end.getContentPane().add(restartButton);
		end.getContentPane().add(endQuit);
		end.setResizable(false);
		end.setLocationRelativeTo(null);
		end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		end.setVisible(true);
		
	}	
	
	
}
