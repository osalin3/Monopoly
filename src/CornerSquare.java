/**
	Class: CornerSquare
 	Description: holds all the information of a CornerSquare
**/

public class CornerSquare extends BoardLocation {
	
	public CornerSquare()
    // POST: A default CornerSquare object is created with name set to blank 
    //       and location/xCoord/yCoord set to 0 
	{
		this(" ", 0, 0, 0);
	}
	
	public CornerSquare(String name, int location, int xCoord, int yCoord)
    // PRE:  name and location are initialize and valid
    // POST: A BoardLocation object is created with its class members set to their respective parameters
	{
		super(name,location, xCoord, yCoord);  // match up private variables with parameters
	}
	
	public String[] getPossibleActions(Player player)
	// PRE:  player is initialized
	// POST: FCTVAL == array of options player has upon landing on
	//       this space, to be used in a menu in a user interface
	{
		return null;
	}
	
	public String toString()
    // POST: FCTVAL == return a string for the player's statistics
    {
		return super.getName() + "\n\n";
		
	}
}
