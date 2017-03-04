import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JOptionPane;

/**
	Class: Lot
 	Description: holds all the information of a lot
**/

public class Lot extends Property 
{
	private int hCost;    	 // cost of a house
	private int nHouse;   	 // number of houses
	private int nHotel;   	 // number of hotels
	private Color color;  	 // color of the square

	public Lot()
    // POST: A default Lot object is created with a blank name, location, xCoord/yCoord, cost, 
	// rent, hCost, nHouse and nHotel set to 0 with player set to null and color set to black
	{
		this(" ", 0, 0, 0, 0, 0, 0, 0, 0, Color.BLACK, null);
	}
	
	public Lot(String name, int location, int xCoord, int yCoord, int cost, int rent,  
			int hCost, int nHouse, int nHotel, Color color, Player player)
    // PRE:  name is valid and player and color are initialized. 
	//        location, xCoord/yCoord, cost, rent, hCost, nHouse and nHotel are >= 0 
    // POST: A Lot object is created with its class members set to their respective parameters
	{
		super(name,location, xCoord, yCoord, cost, rent, player);  // match up private variables with parameters
		this.hCost = hCost;
		this.nHouse = nHouse;
		this.nHotel = nHotel;	
		this.color = color;
	}	
	
	public void updateRent()
	{
	  rent = rent + (nHouse*50);
	  rent = rent + (nHotel*500);
	}
	
	public void setRent(int rent)
	// PRE:  rent >= 0
	// POST: set rent to the class member rent
	{
		this.rent = rent;
	}
	
	public void setHCost(int hCost)
	// PRE:  hCost > 0
	// POST: set the cost to the class member cost
	{
		this.hCost = hCost;
	}
	
	public void setNHouse(int nHouse)
	// PRE:  nHouse >= 0
	// POST: set the cost to the class member cost
	{
		this.nHouse = nHouse;
	}
	
	public void setNHotel(int nHotel)
	// PRE:  nHotel >= 0
	// POST: set the nHotel to the class member nHotel
	{
		this.nHotel = nHotel;
	}
	
	public void setColor(Color color)
	// PRE:  color is initialized
	// POST: set color to the class member color
	{
		this.color = color;
	}
	
	public int getHCost()
    // POST: FCTVAL == return cost of a house of the lot as an int
	{
		return hCost;
	}
	
	public int getNHouse()
    // POST: FCTVAL == return the number of house of the lot as an int
	{
		return nHouse;
	}
	
	public int getNHotel()
    // POST: FCTVAL == return number of hotels of the lot as an int
	{
		return nHotel;
	}
	
	public Color getColor()
    // POST: FCTVAL == return color of the lot
	{
		return color;
	}
	
	@Override
	public String[] getPossibleActions(Player player)
	// PRE:  player is initialized
	// POST: FCTVAL == array of options player has upon landing on
	//       this space, to be used in a menu in a user interface
	{
		//((Lot)board[currPlayer.getLocation()]).updateRent();
		LinkedList<String> list = new LinkedList<String>();
		updateRent();
		if(owner != null)
		{
			if(player.equals(getOwner()))
			{
				if(player.getMoney() > this.hCost)
					list.add("Buy House");
				if(nHouse >= 4 && player.getMoney() > (this.hCost * 4))
				{
					list.add("Buy Hotel");
				}
				if(nHouse > 0)
				{
					list.add("Sell House");
				}
				if(nHotel > 0)
				{
					list.add("Sell Hotel");
				}
			}
			else
			{
				owner.gain(this.rent);
				player.loss(this.rent);
				JOptionPane.showMessageDialog(null, "RENT: " + rent);
			}
		}
		else
		{
			if(player.getMoney() >= cost)
				list.add("Buy Lot");
		}
		
		String[] arrayOfOptions = new String[list.size()];
		list.toArray(arrayOfOptions);		
		return arrayOfOptions;
	}
		
	public String toString()
	// POST: FCTVAL == return a string for lot's information
	{
		return super.toString() + "number of houses: " + nHouse +  "\n" + "number of hotels: " + nHotel + "\n\n"; 
	}
}

