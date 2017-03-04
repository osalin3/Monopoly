/**
 Class: BoardLocation
  Description: holds all the information of one square on the monopoly board
**/

public abstract class BoardLocation 
{
	private int xCoord;       // the xCoord of the BoardLocation on the board
	private int yCoord;       // the yCoord of the BoardLocation on the board
    protected int location;   // location of the square on the board
    protected int fromGo;     // number of spaces counting from "GO"
    protected String name;    // name of the square
    protected String[] action; // actions available on the square
  
    public BoardLocation()
    // POST: A default BoardLoaction object is created with name set to blank 
    //       and location/xCoord/yCoord set to 0 
    {
       this(" ", 0, 0, 0);
    }
 
    public BoardLocation(String name, int location, int xCoord, int yCoord)
    // PRE:  name is valid and location/xCoord/yCoord >= 0
    // POST: A BoardLocation object is created with its class members set to their respective parameters
    {
       this.name = name;         // match up private variables with parameters
       this.location = location;
       this.xCoord = xCoord;
       this.yCoord = yCoord;
    }
 
    public String getName()
    // POST: FCTVAL = return the name of the BoardLocation
    {
      return this.name;
    }
 
    public int getLoc()
    // POST: FCTVAL = return the location of the BoardLocation
    {
       return this.location;
    }
    
    public int getX()
    // POST: FCTVAL = return the xcoord of the BoardLocation
    {
       return this.xCoord;
    }
    
    public int getY()
    // POST: FCTVAL = return the ycoord of the BoardLocation
    {
       return this.yCoord;
    }
 
    public abstract String[] getPossibleActions(Player player);
    // PRE:  player is initialized
    // POST: FCTVAL == array of options player has upon landing on
    //       this space, to be used in a menu in a user interface
   
    public abstract String toString();
    // POST: FCTVAL == return a string for the player's statistics 
}
