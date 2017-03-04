import java.util.LinkedList;
import java.util.Random;

/**
    Class: Player
     Description: holds all the information of a player
**/

public class Player 
{
	private static int nPlayers = 0;           // the total number of players
	private double money; 	                   // how much money the player has
	private int location;                      // the location of the player
	private int token;		                   // the token representation of the player
	private int nRoll;                         // what the player rolled for the dice
	private LinkedList<Property> properties;   // properties the player owns

	public Player()
    // POST: A default Player object is created with all class members set to 0
	{
		this(2000, 0);
	}
	
	public Player(double money, int location)
    // PRE:  money >= 0.00 in dollars, location, xCoord/yCoord >= 0 and token is initialized 
    // POST: A Player object is created with its class members set to their respective parameters
	{
		this.money = money;		  // match up private variables with parameters
		this.location = location;
		this.token = nPlayers+1;
		properties = new LinkedList<Property>();
		nPlayers++;              // adds one to the total number of players
	}
	
	public static void decrementPlayers()
	{
		nPlayers--;
	}
	
	public static int getNumPlayers()
	// POST: return the total number of players
	{
		return nPlayers;
	}
	
	public void setRoll(int nRoll)
    // POST: set nRoll to the class member nRoll
	{
		this.nRoll = nRoll;
	}
	
	public void gain(int gain)
	// PRE:  gain > 0
	// POST: Increase the player's money by gain
	{
		money+=gain;
	}
	 
	public void loss(int loss)
	// PRE:  loss > 0
	// POST: Decrease the player's money by gain
	{
	  this.money-=loss;
	}
	
	public void move(int space)
	// PRE:  space > 0
    // POST: Moves the player's location by space space(s)
	{
		this.location +=space;
		if(this.location >= 40)
		{
			this.location -= 40;
			this.gain(200);
		}	
	}
	
	public void addProperty(Property p)
    // POST: FCTVAL == return the location of the player as an int
    {
        properties.add(p);
        p.owner = this;
    }
	
    public int numUtility()
    // POST: FCTVAL == return the number of Utility the player owns
    {
    	int numUtil = 0;
    	for (Property prop : properties) 
    	{
    		if(prop instanceof Utility)
    		{
    			numUtil++;
    		}
		}
    	return numUtil;
    }
    
    public int numRailRoad()
    // POST: FCTVAL == return the number of RailRoad the player owns
    {
    	int numRail = 0;
    	for (Property prop : properties) 
    	{
    		if(prop instanceof RailRoad)
    		{
    			numRail++;
    		}
		}
    	return numRail;
    }
	
	public int getToken()
    // POST: FCTVAL == return the player's name as a string
	{
		return token;
	}
	
	public int getRoll()
    // POST: FCTVAL == return the player's name as a string
	{
		return nRoll;
	}
	
	public double getMoney()
    // POST: FCTVAL == return how much money the player has as a double
	{
		return money;
	}
	
	public int getLocation()
    // POST: FCTVAL == return the location of the player as an int
	{
		return location;
	}
	
	public String getPropNames()
	// POST: FCTVAL == return a string containing the player's property(s)
	{
		String props = "";
		for (Property property : properties) 
		{
			props += property.getName() + ", ";
		}
		return props;
	}
	
	public LinkedList<Property> GetProperties()
	// POST: FCTVAL == return a string array that holds player's properties
	{
		return properties;
	}
	
	public String toString()
    // POST: FCTVAL == return a string for the player's statistics
    {
		return "Player token: " + this.getToken() + 
			   "\nPlayer capital: " + this.getMoney() + 
			   "\nPlayer properties: " + this.getPropNames() + "\n";
	}
	public void playerQuits()
	{
		this.money = 0;
	}
}