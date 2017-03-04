/**
	Class: Utility
 	Description: holds all the information of a Utility
**/

public class Utility extends Property{
	
	public Utility()
    // POST: A default Utility object is created with a blank name, 
	// location, xCoord/yCoord set to 0, cost set to 150 and rent set to 0 with player set to NULL
	{
		this(" ", 0,  0, 0, 150, 0, null);
	}
	
	public Utility(String name, int location, int xCoord, int yCoord, int cost, int rent, Player owner)
    // PRE:  name is valid, location, xCoord/yCoord, cost and rent are >= 0 and owner is initialized
    // POST: A Utility object is created with its class members set to their respective parameters
	{
		super(name,location, xCoord, yCoord, cost, rent, owner);	// match up private variables with parameters
	}

    public void calcRent (Player player)
    // PRE: player is initialized
    // POST: calculates the rent based on the given formula and set it to class member rent
    {
    	if(!(player.equals(owner))){      // if not owner
    		if(owner.numUtility() == 1)
    			rent = 4*player.getRoll();
    		if(owner.numUtility() == 2)
    			rent = 10*player.getRoll();
        	player.loss(rent);            // deduct from player
    	}
    }

	@Override
	public String[] getPossibleActions(Player player)
	// PRE:  player is initialized
	// POST: FCTVAL == array of options player has upon landing on
	//       this space, to be used in a menu in a user interface
	{
		if(owner == null && player.getMoney() >= cost)
		{
			actions = new String [] {"Buy Utility"};
			return actions;
		}
		else
		{
			calcRent(player);
			actions =  null;
		}
		return actions;
	}
	
	public String toString()
    // POST: FCTVAL == return a string for the Utility's information
    {
		return super.toString() + "\n";
	}
}