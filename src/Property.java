/**
	Class: Property
 	Description: holds all the information of a property
**/

public class Property extends BoardLocation{
	protected int cost;         // cost of the property for purchase
	protected int rent;         // rent of the property
	protected Player owner;     // owner of the property
	protected String actions[]; // possible player actions on the property
	
	public Property()
    // POST: A default Property object is created with a blank name, 
	// location, xCoord/yCoord and cost set to 0 with owned set to false
	{
		this(" ", 0, 0, 0, 0, 0, null);
	}
	
	public Property(String name, int location, int xCoord, int yCoord, int cost, int rent, Player owner)
    // PRE:  name and owner are initialized. location, xCoord/yCoord, cost and rent are >= 0
    // POST: A Property object is created with its class members set to their respective parameters
	{
		super(name,location,xCoord,yCoord);		  // match up private variables with parameters
		this.cost = cost;
		this.rent = rent;
		this.owner = owner;	
	}
	
	public void setOwner(Player player)
	// PRE:  player is initialized
	// POST: set owned as true and the player name to the class member owner
	{
		owner = player;
	}
	
	public void setCost(int cost)
	// PRE:  cost > 0
	// POST: set the cost to the class member cost
	{
		this.cost = cost;
	}
	
	public void setRent(int rent)
	// PRE:  rent > 0
	// POST: set the rent to the class member rent
	{
		this.rent = rent;
	}
	
	public String getName()
    // POST: FCTVAL == return the name of the property as a string
	{
		return name;
	}
	
	public int getCost()
    // POST: FCTVAL == return the cost of the property as an int
	{
		return cost;
	}
	
	public Player getOwner()
    // POST: FCTVAL == return the owner of the property as a string
	{
		return owner;
	}
	
	public int getRent()
    // POST: FCTVAL == return the rent of the property as an int
	{
		return rent;
	}
	
	@Override
	public String[] getPossibleActions(Player player)
	// PRE:  player is initialized
	// POST: FCTVAL == array of options player has upon landing on
	//       this space, to be used in a menu in a user interface
	{
		return null;
	}
	
	public String toString()
    // POST: FCTVAL == return a string for the property's information
    {
		return "Property: " + name + "\n" +"Cost: " + cost  + "\n" + "Rent: " + rent + "\n" + "Location on board: " + location + "\n";
	}
}

