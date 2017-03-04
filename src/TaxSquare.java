import javax.swing.JOptionPane;

/**
	Class: TaxSquare
 	Description: holds all the information of a TaxSquare
**/

public class TaxSquare extends BoardLocation{
	
	private int tax;        // rent of the BoardLocation

	public TaxSquare()
    // POST: A default TaxSquare object is created with a blank name, 
	// location, xCoord/yCoord and tax set to 0 
	{
		this(" ", 0, 0, 0, 0);
	}
	
	public TaxSquare(String name, int location, int xCoord, int yCoord, int tax)
    // PRE:  name is valid, location xCoord/yCoord and tax is >= 0
    // POST: A TaxSquare object is created with its class members set to their respective parameters
	{
		super(name,location, xCoord, yCoord);	// match up private variables with parameters
		this.tax = tax;
	}
	
	public void setTax(int tax)
    // PRE: player is initialized
    // POST: set tax to the class member tax
    {
		this.tax = tax;
    }
	
	public int getTax()
    // POST: FCTVAL == return the tax of the player plays
	{
		return tax;
	}

	@Override
	public String[] getPossibleActions(Player player)
	// PRE:  player is initialized
	// POST: FCTVAL == array of options player has upon landing on
	//       this space, to be used in a menu in a user interface
	{
		JOptionPane.showMessageDialog(null, "-" + tax + " dollars");
		player.loss(tax);	//Subtract taxes from player.
		return null;
	}
	
	public String toString()
    // POST: FCTVAL == return a string for the TaxSquare's information
    {
		return super.getName() + "\n";
	}
}
