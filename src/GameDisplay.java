import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;

import javax.swing.*;

import java.util.Random;

/**
Class: MGUI 
 Description: holds and display an instance of the monopoly game 
**/

public class GameDisplay extends JApplet implements ActionListener, ItemListener
{
	private static final int SIZE = 40;                        // size of the board
	private static final Color BG = new Color(205,230,208);    // Color constants
	private static final Color BROWN = new Color(149,84,54);   
	private static final Color LBLUE = new Color(170,224,250);
	private static final Color MAGENTA = new Color(217,58,150);
	private static final Color ORANGE = new Color(247,148,29);	
	private static final Color RED = new Color(237,27,29);	
	private static final Color YELLOW = new Color(254,242,0);   
	private static final Color GREEN = new Color(31,178,90);   
	private static final Color DBLUE = new Color(0,114,187); 
	private static final Color CHANCE = new Color(138,43,240);
	private int aWidth;       		 			 // dimensions of the applet
	private int aHeight;
	private JButton rules;                       // button for rules of the game
	private JButton endTurn;                     // button for player to end turn
	private JButton stats;
	private JButton rollDie;                     // button for player to roll die
	private JButton confirm;
	private JComboBox action;                    // list of player actions during the game
	private Player currPlayer;                   // the current player
	private int currDieVal1;                     // the current die value 1
	private int currDieVal2;                     // the current die value 2
	private int numPlayers;                      // the number of players 
	private Player[] players;          			 // a containing all the players
	private BoardLocation [] board;              // array containing the monopoly board
	private boolean activePlayers;   			 // make sure this is placed in the right place
	private int currPlayerIndex;				 // index of the current player
	private String [] PA = {"", ""};             // array for the action combobox
	private boolean showDie;                     // true if die is displayed false otherwise
	private boolean demo;                        // true to play demo mode
	private boolean isSameDieRoll;               // true if the rolls are the same 
	private String numPlayersString;             // string that holds the number of players

	public void init() // Set up GUI, initialize variables
	{
		demo = false;
		//demo = true;
		setSize(1700,900);
		showDie = true;
		isSameDieRoll = false;
		aWidth = getWidth();
		aHeight = getHeight();
		currPlayerIndex = 0;
		
		if(!demo)
		{
		   // prompt how many players
		   numPlayersString = JOptionPane.showInputDialog("Please enter the number of players: ");
		   numPlayers = Integer.parseInt(numPlayersString);
		}
		
		if(!demo) // demo
			players = new Player [numPlayers];
		else
			players = new Player [3];
		board = new BoardLocation[40];
		
		if(!demo)
			for(int i = 0; i < numPlayers; i++)
			{
				players[i] = new Player(); //put this new variable declaration in its space		
			}
		else
		{
			players[0] = new Player(200,16);
			players[1] = new Player(1500, 2);
			players[2] = new Player(100,34);
		}
		activePlayers = true;
		
		// set JComponent and at them to listener and layout
		setLayout(null);
		endTurn = new JButton("END TURN");
		endTurn.addActionListener(this);
		rules = new JButton("RULES");
		rules.addActionListener(this); 
		rollDie = new JButton("ROLL DIE");
		rollDie.addActionListener(this);
		action = new JComboBox(PA);
		action.addActionListener(this);
		stats = new JButton("Stats");
		stats.addActionListener(this);
		confirm = new JButton("Confirm");
		confirm.addActionListener(this);
	
		this.add(stats);
		this.add(rollDie);
		this.add(endTurn);		
		this.add(rules);
		this.add(action);
		this.add(confirm);
		
		action.show(false); // hide the combobox initially
		
		currPlayer = players[currPlayerIndex];	// initialize current player
		defineBoard();  // define the board
		
		if(demo)
		{
 		   ((Lot)board[39]).setOwner(players[1]);
 		   players[1].addProperty((Lot)board[39]);
 		   
		   ((Lot)board[37]).setOwner(players[1]);
 		   players[1].addProperty((Lot)board[37]);
		   
		   ((Lot)board[3]).setOwner(players[1]);
 		   players[2].addProperty((Lot)board[3]);
 		   
 		   ((Lot)board[21]).setOwner(players[0]);
 		   players[0].addProperty((Lot)board[21]);
 		   
		   ((Lot)board[1]).setOwner(players[2]);
 		   players[2].addProperty((Lot)board[1]); 
		}
	}
	@Override
	public void paint(Graphics g)  // Display board
	{    	
		super.paint(g);
		
		aWidth = getWidth();   // dimensions of the applet
		aHeight = getHeight();
		int fWidth;                // dimensions of a string
		int fHeight;
		FontMetrics metrics;
		
		// set locations of the JComponents on the layout
		endTurn.setBounds(aWidth/2-aWidth/14, 8*aHeight/11, aWidth/7, aHeight/22);
		rules.setBounds(aWidth/2-aWidth/20, 9*aHeight/11, aWidth/10, aHeight/30);
		rollDie.setBounds(2*aWidth/11 + aWidth/22-aWidth/20, 7*aHeight/11, aWidth/10, aHeight/30);
		action.setBounds(8*aWidth/11 + aWidth/22 - aWidth/16, 3*aHeight/11, aWidth/8, aHeight/30);		
		stats.setBounds(2*aWidth/11 + aWidth/22-aWidth/20, 3*aHeight/11, aWidth/10, aHeight/30);
		confirm.setBounds(8*aWidth/11 + aWidth/22-aWidth/20, 5*aHeight/11, aWidth/10, aHeight/30);
		
		// Board Color
		g.setColor(BG);
		g.fillRect(0 , 0 , 1*aWidth/11, aHeight);
		g.fillRect(0 , 0 , aWidth, 1*aHeight/11);
		g.fillRect(10*aWidth/11 , 0 , 1*aWidth/11, aHeight);
		g.fillRect(0, 10*aHeight/11 , aWidth, 1*aHeight/11);
		
		// corner square display
		g.setFont(new Font("Courier New", Font.BOLD, aHeight*aWidth/57500));
	    metrics = g.getFontMetrics();
		fHeight = metrics.getHeight();
		
	    // GO 
		fWidth = metrics.stringWidth("GO");
		g.setColor(Color.BLACK);
		g.drawString("GO", 10*aWidth/11 + aWidth/22 - fWidth/2, 10*aHeight/11 + aHeight/22 + fHeight/2);
		
		// FREE PARK
		g.setColor(new Color(237,27,29));                   
		g.fillRect(1, 1, aWidth/11 - 1, aHeight/11 - 1 ); 
		fWidth = metrics.stringWidth("PARK");
		g.setColor(Color.BLACK);
		g.drawString("PARK", aWidth/22 - fWidth/2, aHeight/22 + fHeight/2);
		
		// JAIL
		g.setColor(new Color(247,148,29));
		g.fillRect(1, 10*aHeight/11+1, aWidth/11 - 1, aHeight/11 + fHeight/2 );
		fWidth = metrics.stringWidth("JAIL");
		g.setColor(Color.BLACK);
		g.drawString("JAIL", 0*aWidth/11 + aWidth/22 - fWidth/2, 10*aHeight/11 + aHeight/22 + fHeight/2);
		
		// TO JAIL
		g.setColor(new Color(0,113,193)); 
		g.fillRect(10*aWidth/11 , 0 , aWidth/11+1, aHeight/11+1);
		fWidth = metrics.stringWidth("TO JAIL");
		fHeight = metrics.getHeight();
		g.setColor(Color.BLACK);
		g.drawString("TO JAIL", 10*aWidth/11 + aWidth/22 - fWidth/2, aHeight/22 + fHeight/2);
		
		// different lots
		g.setColor(BROWN);    // brown
		g.fillRect(7*aWidth/11, 10*aHeight/11, 3*aWidth/11 + 1, aHeight/35);
		g.setColor(LBLUE);	 // light blue
		g.fillRect(aWidth/11, 10*aHeight/11, 4*aWidth/11 + 1, aHeight/35);
		g.setColor(MAGENTA);   // magenta
		g.fillRect(aWidth/11- aWidth/35, 6*aHeight/11, aWidth/35 , 4*aHeight/11 + 1);
		g.setColor(ORANGE);   // orange
		g.fillRect(aWidth/11- aWidth/35, aHeight/11, aWidth/35 , 4*aHeight/11 + 1);
		g.setColor(RED);    // red
		g.fillRect(aWidth/11 + 1, aHeight/11 - aHeight/35, 4*aWidth/11 , aHeight/35); 
		g.setColor(YELLOW);    // yellow
		g.fillRect(6*aWidth/11 + 1, aHeight/11 - aHeight/35, 4*aWidth/11, aHeight/35); 
		g.setColor(GREEN);    // green
		g.fillRect(10*aWidth/11 + 1, aHeight/11, aWidth/35 , 4*aHeight/11 + 1);
		g.setColor(DBLUE);    // dark blue
		g.fillRect(10*aWidth/11 + 1, 7*aHeight/11, aWidth/35 , 3*aHeight/11 + 1);
		
		// railroad
		g.setColor(Color.GRAY);
		g.fillRect(5*aWidth/11, 10*aHeight/11, aWidth/11 +1 , aHeight/35);
		g.fillRect(aWidth/11 - aWidth/35, 5*aHeight/11, aWidth/35, aHeight/11 + 1);
		g.fillRect(5*aWidth/11 + 1, aHeight/11 - aHeight/35, aWidth/11, aHeight/35 );
		g.fillRect(10*aWidth/11 + 1,  5*aHeight/11, aWidth/35 , aHeight/11 + 1);
		
		// utility
		g.setColor(Color.WHITE);
		g.fillRect(aWidth/11 - aWidth/35, 8*aHeight/11, aWidth/35 , aHeight/11 + 1);
		g.fillRect(8*aWidth/11, aHeight/11 - aHeight/35, aWidth/11, aHeight/35 ); 	
		
		// tax
		g.setColor(Color.BLACK);
		g.fillRect(6*aWidth/11, 10*aHeight/11, aWidth/11 +1 , aHeight/35);
		g.fillRect(10*aWidth/11 + 1, 8*aHeight/11, aWidth/35 , aHeight/11 +1);
		
		// chance
		g.setColor(CHANCE);
		g.fillRect(8*aWidth/11, 10*aHeight/11, aWidth/11 +1 , aHeight/35);
		g.fillRect(3*aWidth/11, 10*aHeight/11, aWidth/11 +1 , aHeight/35);		
		g.fillRect(aWidth/11 - aWidth/35, 3*aHeight/11, aWidth/35 , aHeight/11 + 1);
		
		g.fillRect(2*aWidth/11, aHeight/11 - aHeight/35, aWidth/11 , aHeight/35); 
		g.fillRect(10*aWidth/11 + 1, 3*aHeight/11, aWidth/35 , aHeight/11 + 1);
		g.fillRect(10*aWidth/11 + 1, 6*aHeight/11, aWidth/35 , aHeight/11 + 1);
		
		// lines
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, aWidth, aHeight-1);
		
		g.drawLine(1*aWidth/11, 0*aHeight/11, 1*aWidth/11, 11*aHeight/11);
		g.drawLine(1*aWidth/11-aWidth/35, 1*aHeight/11, 1*aWidth/11-aWidth/35, 10*aHeight/11);
		g.drawLine(10*aWidth/11, 0*aHeight/11, 10*aWidth/11, 11*aHeight/11);
		g.drawLine(10*aWidth/11+aWidth/35, 1*aHeight/11, 10*aWidth/11+aWidth/35, 10*aHeight/11);
		
		g.drawLine(0*aWidth/11, 1*aHeight/11, 11*aWidth/11, 1*aHeight/11);
		g.drawLine(1*aWidth/11, 1*aHeight/11-aHeight/35, 10*aWidth/11, 1*aHeight/11-aHeight/35);
		g.drawLine(0*aWidth/11, 10*aHeight/11, 11*aWidth/11, 10*aHeight/11);
		g.drawLine(1*aWidth/11, 10*aHeight/11+aHeight/35, 10*aWidth/11, 10*aHeight/11+aHeight/35);
		
		for(int i = 1; i< 11; i++){
			g.drawLine(i*aWidth/11, 0*aHeight/11, i*aWidth/11, 1*aHeight/11);
			g.drawLine(i*aWidth/11, 10*aHeight/11, i*aWidth/11, 11*aHeight/11);	
		}
		for(int i = 1; i< 11; i++){
			g.drawLine(0*aWidth/11, i*aHeight/11, 1*aWidth/11, i*aHeight/11);
			g.drawLine(10*aWidth/11, i*aHeight/11, 11*aWidth/11, i*aHeight/11);	
		}
		
		// display player turn icon
		g.setFont(new Font("Courier New", Font.BOLD, aWidth/50));                    // set font
	    metrics = g.getFontMetrics();                                                // get metric
		fWidth = metrics.stringWidth("TURN: PLAYER " + " " + currPlayerIndex);       // get width
		g.setColor(Color.BLACK);
		g.drawString("TURN: PLAYER " + " " + (currPlayerIndex+1), aWidth/2 - fWidth/2, 2*aHeight/11);
		
		// display stats icon
		g.setFont(new Font("Courier New", Font.BOLD, aWidth/80));
	    metrics = g.getFontMetrics();
		fWidth = metrics.stringWidth("STATS");
		fHeight = metrics.getHeight();
		g.setColor(Color.BLACK);
		g.drawString("STATS", 2*aWidth/11 + aWidth/22 - fWidth/2, 3*aHeight/11 - fHeight/2);
		
		// display action icon
		fWidth = metrics.stringWidth("ACTIONS");
		fHeight = metrics.getHeight();
		g.setColor(Color.BLACK);
		g.drawString("ACTIONS", 8*aWidth/11 + aWidth/22 - fWidth/2, 3*aHeight/11 - fHeight/2);
		
		// display logo
		g.setColor(new Color(237,27,29));
		g.fillRect(aWidth/2 - aWidth/6, aHeight/2 - aHeight/6, aWidth/3, aHeight/3); 
		g.setColor(Color.BLACK);
		g.drawRect(aWidth/2 - aWidth/6, aHeight/2 - aHeight/6, aWidth/3, aHeight/3); 
		g.setFont(new Font("Courier New", Font.BOLD, aHeight*aWidth/30000));
	    metrics = g.getFontMetrics();
		metrics.stringWidth("MONOPOLY");
		fWidth = metrics.stringWidth("MONOPOLY");
		g.setColor(Color.WHITE);
		g.drawString("MONOPOLY", aWidth/2 - fWidth/2 , aHeight/2);
		
		for (Player p : players) // print all playerspay
		{
			if(p.getMoney() > 0) // if they have money
			{
				g.setFont(new Font("Courier New", Font.BOLD, aWidth/50));
				metrics = g.getFontMetrics();
				fWidth = metrics.stringWidth("5");
				fHeight = metrics.getHeight();
				g.setColor(Color.BLUE);  // token color
				g.drawString(p.getToken()+"", board[p.getLocation()].getX()+(aWidth/85*p.getToken()), board[p.getLocation()].getY()+(aHeight/20));
			}
		}
		if(showDie)
		{		
			drawDie(g,2*aWidth/11 + aWidth/22-aWidth/20, 8*aHeight/11, aWidth/20,Color.BLACK,currDieVal1);	
			drawDie(g,2*aWidth/11 + aWidth/22-aWidth/20 + aWidth/20, 8*aHeight/11, aWidth/20,Color.BLACK,currDieVal2);
		}
	}
	
	public void defineBoard()
	// POST: defines all the squares of the board
	{
		board = new BoardLocation[40];	
		// corner
		board[0] = new CornerSquare("GO", 0, 10*aWidth/11, 10*aHeight/11);
		board[10] = new CornerSquare("IN JAIL", 10, 0*aWidth/11, 10*aHeight/11);
		board[20] = new CornerSquare("FREE PARKING", 20, 0*aWidth/11, 0*aHeight/11);
		board[30] = new CornerSquare("GO TO JAIL", 30, 10*aWidth/11, 0*aHeight/11);
		// card square/tax square
		board[2] = new CardSquare("CHANCE", 2, 8*aWidth/11, 10*aHeight/11, 0);
		board[7] = new CardSquare("CHANCE", 7, 3*aWidth/11, 10*aHeight/11, 0);
		board[17] = new CardSquare("CHANCE", 17, 0*aWidth/11, 3*aHeight/11, 0);
		board[22] = new CardSquare("CHANCE", 22, 2*aWidth/11, 0*aHeight/11, 0);
		board[33] = new CardSquare("CHANCE", 33, 10*aWidth/11, 3*aHeight/11, 0);
		board[36] = new CardSquare("CHANCE", 36, 10*aWidth/11, 6*aHeight/11, 0);
		board[4] = new TaxSquare("INCOME TAX", 4, 6*aWidth/11, 10*aHeight/11, 200);
		board[38] = new TaxSquare("LUXURY TAX", 38, 10*aWidth/11, 8*aHeight/11, 75);
		// railroad/utility
		RailRoad rail1 = new RailRoad("READING RAILROAD", 5, 5*aWidth/11, 10*aHeight/11, 200, 0, null);
		board[5] = rail1;
		RailRoad rail2 = new RailRoad("PENNSYLVANIA RAILROAD", 15, 0*aWidth/11, 5*aHeight/11, 200, 0, null);
		board[15] = rail2;
		RailRoad rail3 = new RailRoad("B & O RAILROAD", 25, 5*aWidth/11, 0*aHeight/11, 200, 0, null);
		board[25] = rail3;
		RailRoad rail4 = new RailRoad("SHORT LINE", 35, 10*aWidth/11, 5*aHeight/11, 200, 0, null);
		board[35] = rail4;
		Utility util1 = new Utility("ELECTRIC COMPANY", 12, 0*aWidth/11, 8*aHeight/11, 200, 0, null);
		board[12] = util1;
		Utility util2 = new Utility("WATER WORKS", 28, 8*aWidth/11, 0*aHeight/11, 200, 0, null);
		board[28] = util2;
		// brown lot
		Lot brown1 = new Lot("MEDITERRANEAN AVENUE", 1, 9*aWidth/11, 10*aHeight/11, 60, 30, 60, 0, 0, BROWN, null);
		board[1] = brown1;
		Lot brown2 = new Lot("BALTIC AVENUE", 3, 7*aWidth/11, 10*aHeight/11, 60, 30, 60, 0, 0, BROWN, null);
		board[3] = brown2;
		// light blue lot
		Lot lBlue1 = new Lot("ORIENTAL AVENUE", 6, 4*aWidth/11, 10*aHeight/11, 100, 50, 100, 0, 0, LBLUE, null);
		board[6] = lBlue1;
		Lot lBlue2 = new Lot("VERMONT AVENUE", 8, 2*aWidth/11, 10*aHeight/11, 100, 50, 100, 0, 0, LBLUE, null);
		board[8] = lBlue2;
		Lot lBlue3 = new Lot("CONNECTICUT AVENUE", 9, 1*aWidth/11, 10*aHeight/11, 120, 60, 120, 0, 0, LBLUE, null);
		board[9] = lBlue3;
		// magenta lot
		Lot magenta1 = new Lot("ST. CHARLES PLACE", 11, 0*aWidth/11, 9*aHeight/11, 140, 70, 140, 0, 0, MAGENTA, null);
		board[11] = magenta1;
		Lot magenta2 = new Lot("STATES AVENUE", 13, 0*aWidth/11, 7*aHeight/11, 140, 70, 140, 0, 0, MAGENTA, null);
		board[13] = magenta2;
		Lot magenta3 = new Lot("VIRGINIA AVENUE", 14, 0*aWidth/11, 6*aHeight/11, 160, 80, 160, 0, 0, MAGENTA, null);
		board[14] = magenta3;
		// orange lot
		Lot orange1 = new Lot("ST. JAMES PLACE", 16, 0*aWidth/11, 4*aHeight/11, 180, 90, 180, 0, 0, ORANGE, null);
		board[16] = orange1;
		Lot orange2 = new Lot("TENNESSEE AVENUE", 18, 0*aWidth/11, 2*aHeight/11, 180, 90, 180, 0, 0, ORANGE, null);
		board[18] = orange2;
		Lot orange3 = new Lot("NEW YORK AVENUE", 19, 0*aWidth/11, 1*aHeight/11, 200, 100, 200, 0, 0, ORANGE, null);
		board[19] = orange3;		
		// red lot
		Lot red1 = new Lot("KENTUCKY AVENUE", 21, 1*aWidth/11, 0*aHeight/11, 220, 110, 220, 0, 0, RED, null);
		board[21] = red1;
		Lot red2 = new Lot("INDIANA AVENUE", 23, 3*aWidth/11, 0*aHeight/11, 220, 110, 220, 0, 0, RED, null);
		board[23] = red2;
		Lot red3 = new Lot("ILLINOIS AVENUE", 24, 4*aWidth/11, 0*aHeight/11, 240, 120, 240, 0, 0, RED, null);
		board[24] = red3;	
		// yellow lot
		Lot yellow1 = new Lot("ATLANTIC AVENUE", 26, 6*aWidth/11, 0*aHeight/11, 260, 130, 260, 0, 0, YELLOW, null);
		board[26] = yellow1;
		Lot yellow2 = new Lot("VENTNOR AVENUE", 27, 7*aWidth/11, 0*aHeight/11, 260, 130, 260, 0, 0, YELLOW, null);
		board[27] = yellow2;
		Lot yellow3 = new Lot("MARVIN GARDENS", 29, 9*aWidth/11, 0*aHeight/11, 280, 140, 280, 0, 0, YELLOW, null);
		board[29] = yellow3;			
		// green lot
		Lot green1 = new Lot("PACIFIC AVENUE", 31, 10*aWidth/11, 1*aHeight/11, 300, 150, 300, 0, 0, GREEN, null);
		board[31] = green1;
		Lot green2 = new Lot("NORTH CAROLINA AVENUE", 32, 10*aWidth/11, 2*aHeight/11, 300, 150, 300, 0, 0, GREEN, null);
		board[32] = green2;
		Lot green3 = new Lot("PENNSYLVANIA AVENUE", 34, 10*aWidth/11, 4*aHeight/11, 320, 160, 320, 0, 0, GREEN, null);
		board[34] = green3;
		// dark blue lot
		Lot dblue1 = new Lot("PARK PLACE", 31, 10*aWidth/11, 7*aHeight/11, 350, 175, 350, 0, 0, DBLUE, null);
		board[37] = dblue1;
		Lot dblue2 = new Lot("BOARDWALK", 32, 10*aWidth/11, 9*aHeight/11, 400, 200, 400, 0, 0, DBLUE, null);
		board[39] = dblue2;	
	}
	
	public void roll()
	// POST: regenerate die values and display the die 
	{		
		Random generator = new Random();
		this.currDieVal1 = generator.nextInt(5)+1; // RNG dice roll
		this.currDieVal2 = generator.nextInt(5)+1; // RNG dice roll
		currPlayer.setRoll(currDieVal1+currDieVal2);
		if(currDieVal1 != currDieVal2)
		{
			rollDie.setEnabled(false);
		}
		currPlayer.move(currDieVal1 + currDieVal2); 

		updateActions();
		super.repaint();
	}
	
	public void updateActions()
	// POST: update the action combobox to represent the actions available at each location
	{
		PA =  board[currPlayer.getLocation()].getPossibleActions(currPlayer);
		if(PA != null) // if there is an action
		{
			action.removeAllItems();
			action.addItem("Choose Action"); // default action
			for (String string : PA) 
			{
				action.addItem(string); // add possible actions
			}
		}
		else
		{
			action.removeAllItems();          // set combobox to default
			action.addItem("Choose Action");
		}
		action.addItem("Quit");
		super.repaint();
	}
	
	public void printBoard()
	// POST: prints out all information about all spaces on the board in order.
	{
		for(int i = 0; i<SIZE; i++)
			System.out.print(board[i].toString());
	}
	
	public void drawDie(Graphics g, int xCoord, int yCoord, int length, Color color, int number)
	/* PRE: 
	    *		g is initialized
	    *		xCoord is greater or equal to 0 and xCoord + length is less than applet width
	    *		yCoord is greater or equal to 0 and yCoord + length is less than applet height
	    *		length is greater than 0 and 
	    *			   (xCoord + length)/(yCoord + length) is less than applet width/height
	    * 		color is  initialized and is a valid color
	    * 		number is between 1 and 6 inclusive
	    *	POST:
	    *		displays a length x length die with the starting coordinate xCoord x yCoord
	    *       with the side number color dots on the applet screen
	    *	FCTVAL: N/A
	    */
	    {
			g.setColor(Color.WHITE);
	    	g.fillRect(xCoord, yCoord, length, length);
			g.setColor(Color.BLACK);
	    	
	    	final int DIAMETER = length/10; // diameter of the dots is 1/10 of the die length    
	    	final int lefX = (xCoord+DIAMETER);			 // calculate the left x coordinate of 
			 											 //  the die with adjustment for the dot
	    	final int rigX = (xCoord+length-2*DIAMETER); // calculate the right x coordinate of 
			 											 //  the die with adjustment for the dot    	
	    	final int topY = (yCoord+DIAMETER);    		 // calculate the top y coordinate of 
			 											 //  the die with adjustment for the dot
	    	final int botY = (yCoord+length-2*DIAMETER); // calculate the bot y coordinate of 
														 //  the die with adjustment for the dot      	
	    	final int midX = xCoord+(length-DIAMETER)/2; // calculate the middle x coordinate of 
	    					                             //  the die with adjustment for the dot
	    	final int midY = yCoord+(length-DIAMETER)/2; // calculate the middle y coordinate of 
	    					                   	         //  the die with adjustment for the dot
	    	
			g.drawRect(xCoord, yCoord, length, length);  // draw the die
	    	g.setColor(color);                           // set the dot(s) color 
			
	    	if(number == 1 || number == 3 || number == 5) // if one, three or five sided
	    	{
	    		// Draw Center Dot
	    		g.fillOval(midX, midY, DIAMETER, DIAMETER); // draw the dot
	    	}
	       
	    	if(number == 2 || number == 3 ||            // if two, three, four
	    	number == 4 || number == 5 || number == 6)	//   five or six sided
	    	{
	        	// Draw Top Left Dot 
	      		g.fillOval(lefX, topY, DIAMETER, DIAMETER); // draw the dot
	        	// Draw Bottom Right Dot
	      		g.fillOval(rigX, botY, DIAMETER, DIAMETER); // draw the dot
	        }
	        
	    	if(number == 4 || number == 5 || number == 6) // if four, five or six sided
	        {
	        	// Draw Top Right Dot
	      		g.fillOval(rigX, topY, DIAMETER, DIAMETER); // draw the dot
	        	// Draw Bottom Left Dot   	
	      		g.fillOval(lefX, botY, DIAMETER, DIAMETER); // draw the dot
	        }

	       	if(number == 6)	// if six sided 
	       	{
	        	// Draw Middle Left Dot 
	      		g.fillOval(lefX, midY, DIAMETER, DIAMETER); // draw the dot
	        	// Draw Middle Right Dot
	      		g.fillOval(rigX, midY, DIAMETER, DIAMETER); // draw the dot
	       	}
	    }
	
	public void updateStats()
	// POST: displays the stat gui
	{
		String statsString = "<html><body width='600'><center><h1>Monopoly: Stats</h1></center>";
		for (Player p : players) 
		{
			statsString += p.toString() + "\n";
		}
		JPanel p = new JPanel( new BorderLayout() );
		JOptionPane.showMessageDialog(null, statsString);  
	}
	  
	@Override
	public void itemStateChanged(ItemEvent e) 
	{
	
	}

	@Override
	public void actionPerformed(ActionEvent e) // handles event actions 
	{
	  if(e.getSource() == confirm)
		{
		   String selectAction = (String) action.getSelectedItem();
		   if(selectAction.equals("Choose Action"))
		   {
		      JPanel p = new JPanel( new BorderLayout() );
		      JOptionPane.showMessageDialog(null, "Please made a valid choice!"); 
		      return;
		   }
		   else
		   {
		     switch(selectAction){ 
		       case "Quit":
		    	   players[currPlayerIndex].playerQuits();
		    	   Player.decrementPlayers();
		    	   endTurn.doClick();
		    	   break;
		       case "Buy Lot": // buy lot
		    	   // if no owner
		    	   if(((Lot)(board[players[currPlayerIndex].getLocation()])).getOwner() == null)
		    	   {
		    		   // set owner
		    		   ((Lot)board[players[currPlayerIndex].getLocation()]).setOwner(players[currPlayerIndex]);
		    		   // add to player property
		    		   players[currPlayerIndex].addProperty((Lot)board[players[currPlayerIndex].getLocation()]);
		    		   // deduct the cost
		    		   players[currPlayerIndex].loss(((Lot) board[players[currPlayerIndex].getLocation()]).getCost());
		    	   }
		    	   break;
		       case "Buy Railroad":
		       case "Buy Utility":
		    	   // if no owner
		    	   if(((Property)(board[players[currPlayerIndex].getLocation()])).getOwner() == null)
		    	   {
		    		   // set owner
		    		   ((Property)board[players[currPlayerIndex].getLocation()]).setOwner(players[currPlayerIndex]);
		    		   // add to player property
		    		   players[currPlayerIndex].addProperty((Property)board[players[currPlayerIndex].getLocation()]);
		    		   // deduct the cost
		    		   players[currPlayerIndex].loss(((Property) board[players[currPlayerIndex].getLocation()]).getCost());
		    	   }
		    	   break;
		       case "Buy House": // buy house
		    	   // warning message
		    	   JOptionPane.showMessageDialog(null, "Proceeding to buy house assuming all color properties are owned");
		    	   Lot tHouse = new Lot(); // set temp
		    	   tHouse = (Lot) board[players[currPlayerIndex].getLocation()];
		    	   tHouse.setNHouse(tHouse.getNHouse()+1); // increase house count
		    	   players[currPlayerIndex].loss(tHouse.getHCost());
		    	   board[players[currPlayerIndex].getLocation()] = tHouse;
		    	   break;
		       	case "Buy Hotel":                     // buy hotel
		       	   Lot tHotel = new Lot();                                        // setup temp
		       	   tHotel = (Lot) board[players[currPlayerIndex].getLocation()]; 
		       	   tHotel.setNHotel(tHotel.getNHotel()+1); // increase hotel count
		       	   tHotel.setNHouse(tHotel.getNHouse()-4); // deduct four house
		       	   players[currPlayerIndex].loss(tHotel.getHCost()); // hotel cost
		       	   board[players[currPlayerIndex].getLocation()] = tHotel; // restore back to array
		       	   break;
		       	case "Sell House":
		       		//
		       		Lot tHouseSell = new Lot(); // set temp
		       		tHouseSell = (Lot) board[players[currPlayerIndex].getLocation()];
		       		tHouseSell.setNHouse(tHouseSell.getNHouse()-1); // decrease house count
		       		players[currPlayerIndex].gain((tHouseSell.getHCost()/2));
		       		board[players[currPlayerIndex].getLocation()] = tHouseSell;
		       		JOptionPane.showMessageDialog(null, "houses: " + tHouseSell.getNHouse());
		       		JOptionPane.showMessageDialog(null, "House cost: " + tHouseSell.getHCost() + " | Bank buy-back price: " + tHouseSell.getHCost()/2);
		       		break;
		       	case "Sell Hotel":
		       		Lot tHotelSell = new Lot();                                        // setup temp
			       	tHotelSell = (Lot) board[players[currPlayerIndex].getLocation()]; 
			       	tHotelSell.setNHotel(tHotelSell.getNHotel()-1); // decrease hotel count
			       	players[currPlayerIndex].gain((tHotelSell.getHCost()/2)); // hotel cost
			       	board[players[currPlayerIndex].getLocation()] = tHotelSell; // restore back to array
			       	JOptionPane.showMessageDialog(null, "hotels: " +  tHotelSell.getNHotel());
			       	JOptionPane.showMessageDialog(null, "Hotel cost: " + tHotelSell.getHCost()*4 + " | Bank buy-back price: " + tHotelSell.getHCost()*2);
			       	break;
		       }
		   }
		   updateActions(); // update action
		}
		if(e.getSource() == rollDie)
		{
			action.show(true);
			showDie = true;
			roll();
		}
		if(e.getSource() == endTurn)
		{	
			if(Player.getNumPlayers() == 1)
			{
				int playerWonToken = -1;
				for (Player p : players) 
				{
					if(p.getMoney() > 0)
					{
						playerWonToken = p.getToken();
					}
				}
				JPanel p = new JPanel( new BorderLayout() );
				JOptionPane.showMessageDialog(null, "Player " + playerWonToken + " win!"); 
	    		System.exit(ABORT);
	    		return;
			}
			
			for (Player p : players) 
            {
                if(p.getMoney() <= 0)
                {
                    for (Property prop : p.GetProperties()) 
                    {
                        prop.setOwner(null);
                    }
                }
            }
			
			if(currPlayerIndex == players.length-1 && players[0].getMoney() > 0)
			{
				currPlayerIndex = 0;
				currPlayer = players[currPlayerIndex];
			}
			else
			{
				for (int i = currPlayerIndex; i < players.length; i++) 
				{
					if(i+1 >= players.length)
					{
						i = 0;
					}
					if(players[i+1].getMoney() > 0)
					{
						currPlayerIndex = i+1;
						break;
					}
				}
			}
			currPlayer = players[currPlayerIndex];
			rollDie.setEnabled(true);
		}
		if(e.getSource() == stats) // stat button
		{
			updateStats(); // display stats box
		}
		 if(e.getSource() == rules) // rule button
		 {
		   String message = "<html><body width='600'><h1>Monopoly: Edited Rules</h1>" +
		                    "The object of the game is to become the wealthiest player through buying" +
		                    " and renting property. The equipment consists of a board, 2 dice, tokens" + 
		                    ", houses, and hotels. There is a Title Deed card for each property and play " + 
		                    "money. Each player is given $1500. All remaining money and equipment go to" +
		                    " the Bank.<br><br> The bank owns properties and houses and hotels prior to " +
		                    "purchase and use by players. The bank pays salaries and sells properties. " +
		                    " Additonally, the bank never goes broke. <br><br> Players start at GO, " +
		                    "roll a dice, and move the selected spaces indicated by the dice. According to" +
		                    "the space a player a player reaches, may be entitled to buy properties or be "+
		                    " obliged to pay rent, taxes, ect. If the player rolls doubles, they receive " +
		                    " another turn. <br><br> GO:  Each time a player's token lands on or passes " +
		                    " over GO, the Bank pays them a $200 salary. <br><br> Buying Property: Whenever "+
		                    " you land on an unowned property,  you may buy that property from the Bank at" +
		                    " its printed price. You may otherwise pass. " +
		                    " Paying Rent: When you land on property owned by another player, the owner " + 
		                    " collects rent from you in accordance with the rents on the Title Deed. " +
		                    " Rents are higher when properties have houses and hotels.<br><br>" +
		                    "Chance and Community Chest: When the player lands on these spaces in our form " + 
		                    "of the game, they will win or lose up to $200. <br><br>" +
		                    "Income Tax: If the player lands here, they pay $200 to the bank."+
		                    "Luxury Tax: If the player lands here, they pay $75 to the bank."+
		                    "Free Parking: If a player lands here, a free resting place is provided<br><br>"+
		                    "Houses: When a player owns all the properties in a color group, he may buy"+
		                    " houses from the Bank and erect them on these properties. Houses must be " +
		                    "built and sold evenly, meaning they must be sold and bought one at a time. " +
		                    "<br><br> Hotels: When a player has four houses on each property of a complete "+
		                    " color-group, they can buy a hotel and ereect it on the property. They player" +
		                    " returns the four houses from that property to the Bank and pays the same " + 
		                    " price for the hotel as he/she would otherwise pay to build a fifth house."+
		                    "<br><br> Selling property:  Houses and hotels may be sold back to the bank" + 
		                    "at one-half the price paid for them. <br><br>" +
		                    "Bankruptcy: You are declared bankrupt if you owe more than you can pay "+
		                    "either to another player or to the bank.<br><br>" +
		                    "Railroads: If the player who owns the railroad only owns that railroad," +
		                    " the rent is $25. If the player owns two railroads, the rent is $50. If the" +
		                    " player owns three railroads, the rent is $100. If the player owns all four " +
		                    "railroads, the rent is $200.<br><br>" +
		                    "Utilities: If the player who owns the utility owns one utility, the rent is 4"+
		                    "times the amount shown on the dice. If the player who owns the utility owns "+
		                    "both utilities, the rent is 10 times the amount shown on the dice.<br><br>" +
		                    "Lots: There is a rent for unimproved lots; a separate rent for having 1 " +
		                    "house, 2 houses, etc. and a separate rent for having a house. Rents " +
		                    "vary based on properties. <br><br>" +
		                    "Black squares denote taxes, gray denote rail roads " +
		                    ", purple boxes denote chance cards, and white denote utility.";

		   JPanel p = new JPanel( new BorderLayout() );
		   JOptionPane.showMessageDialog(null, message);  

		 }
		repaint();
	}
}

