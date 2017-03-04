import java.util.Random;

/**
 Class: CardSquare
  Description: holds all the information of a CardSquare
**/

public class CardSquare extends BoardLocation{
 
 private int amount;  // rent of the BoardLocation

 public CardSquare()
    // POST: A default CardSquare object is created with a name as Chance Card, 
 // location, xCoord/yCoord and amount set to 0 
 {
  this("Chance Card", 0, 0, 0, 0);
 }
 
 public CardSquare(String name, int location, int xCoord, int yCoord, int amount)
    // PRE:  name is valid and location, xCoord/yCoord and rent is >= 0
    // POST: A CardSquare object is created with its class members set to their respective parameters
 {
  super(name,location, xCoord, yCoord); // match up private variables with parameters
  this.amount = amount;
 }
 
 public void setAmount()
    // PRE:  player is initialized
    // POST: set amount to a random number from -200 to 200
    {
  Random generator = new Random();
  this.amount = 200 - generator.nextInt(401);
    }
 
 public int getAmount()
    // POST: FCTVAL == return the amount of money the player gains/loss
 { 
  Random generator = new Random();
  amount = 200 - generator.nextInt(401);
  return amount;
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
    // POST: FCTVAL == return a string for the CardSquare's information
    {
  return super.getName() + "\n\n";
 }
}
