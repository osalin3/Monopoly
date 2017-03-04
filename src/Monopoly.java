import java.awt.Color;
import java.util.Random;

/**
Class: Player
 Description: holds all the information of a player
**/

public class Monopoly {
	
	public static void main(String[] args){
		
		Player p1 = new Player();
		Player p2 = new Player(2000,0);
		BoardLocation[] Board = new BoardLocation[40];	
		
		Board[0] = new CornerSquare("GO", 0, 0, 0);
		Board[1] = new Lot("OLD KENT ROAD", 1, 0, 0, 60, 30, 200, 0, 0, Color.BLACK, null);
		
		Random generator = new Random();
		int val = 200 - generator.nextInt(401);
		
		Board[2] = new CardSquare("CHANCE", 2, 0, 0, val); // RNG here
		Board[3] = new Lot("WHITECHAPEL ROAD", 3, 60, 30, 200, 0, 0,0, 0, Color.BLACK, null);
		Board[4] = new TaxSquare("INCOME TAX", 4, 0, 0,75);
		
		RailRoad r5 = new RailRoad("KINGS CROSS STATION", 5,0, 0, 200, 0, null);
		Board[5] = r5;
		
		Board[6] = new Lot("THE ANGEL ISLINGTON", 6, 0, 0, 100, 50, 200, 0, 0, Color.BLUE, null);
		
		generator = new Random();
		val = 200 - generator.nextInt(401);
		
		Board[7] = new CardSquare("CHANCE", 7,0, 0, val); // RNG here
		Board[8] = new Lot("EUSTON ROAD", 8, 0, 0, 100, 10, 100, 0, 0, Color.BLUE, null);
		Board[9] = new Lot("PENTONVILLE ROAD", 9, 0, 0,120, 12, 100, 0, 0, Color.BLUE, null);	
		Board[10] = new CornerSquare("JAIL",0, 0, 10);
		Board[11] = new Lot("PALL MALL", 11,0, 0, 140, 14, 100, 0, 0, Color.MAGENTA, null); 
		
		Utility u12 = new Utility("ELECTRIC COMPANY", 12,0, 0, 150, 15, null);
		Board[12] = u12;
		
		Lot a13 = new Lot("WHITEHALL", 13, 0, 0,140, 14, 100, 0, 0, Color.MAGENTA, null);
		Board[13] = a13; 
		
		Lot a14  = new Lot("NORTHHUMRL'D AVENUE", 14, 0, 0,160, 16, 100, 0, 0, Color.MAGENTA, null);
		Board[14] = a14;
		
		System.out.print("---PLAYER TEST---\n");
		//p1.move(5);
		//p2.move(10);
		p1.gain(1000);
		p2.loss(300);
		System.out.print("Number of players: " + Player.getNumPlayers() + "\n");
		
		p1.addProperty(a13);
		p1.addProperty(a14);
		p2.addProperty(r5);
		p2.addProperty(u12);

		System.out.print(p1.toString());
		System.out.print(p2.toString());
		
		System.out.print("P1 Loc: " + p1.getLocation()+"\n");
		System.out.print("P1 Loc: " + p2.getLocation()+"\n");	
		System.out.print("P1 util: " +p1.numUtility()+"\n");
		System.out.print("P1 rail: " +p1.numRailRoad()+"\n");
		System.out.print("P2 util: " +p2.numUtility()+"\n");	
		System.out.print("P2 rail: " +p2.numRailRoad()+"\n");	
	
		System.out.print("\n---BOARDLOCATION TEST---\n");
		System.out.print("Board 5: " + r5.getLoc()+"\n");
		System.out.print("Board 14: " + a14.getLoc()+"\n");	
		System.out.print(a14.toString()+"\n");
		
		System.out.print("\n---PROPERTY TEST---\n");
		System.out.print(a13.toString());	
		System.out.print("testProp getCost test: " + a13.getCost() + "\n");
		
		System.out.print("\n---LOT TEST---\n");
		a14.setNHouse(5);
		a14.setNHotel(5);
		a14.setColor(Color.BLACK);
		System.out.print("testLot toString test: " + a14.toString() + "\n");;
		System.out.print("testLot getCost test: " + a14.getCost() + "\n");
		System.out.print("testLot setNHouse test: " + a14.getNHouse() + "\n");
		System.out.print("testLot setNHotel test: " + a14.getNHotel() + "\n");
		System.out.print("testLot setColor test: " + a14.getColor() + "\n");
		System.out.print("testLot toString test: " + a14.toString() + "\n");

		System.out.print("\n---RAILROAD TEST---\n");
		r5.calcRent(p1);
		System.out.print(r5.toString());
		r5.calcRent(p2);
		System.out.print(r5.toString());
		System.out.print(p1.toString());
		System.out.print(p2.toString());
		
		System.out.print("\n---UTILITY TEST---\n");
		p1.setRoll(50);
		u12.calcRent(p1);
		System.out.print(u12.toString());
		p1.setRoll(60);
		u12.calcRent(p2);
		System.out.print(u12.toString());
		System.out.print(p1.toString());
		System.out.print(p2.toString());
	
		System.out.print("\n---CORNERSQUARE TEST---\n");
		System.out.print("Corner Square to string test: " + (Board[0].toString()) + "\n");
		
		System.out.print("\n---TAX TEST---\n");
		TaxSquare testCardTax = new TaxSquare("INCOME TAX", 4, 0, 0,75);
		System.out.print("TaxSquare getTax test: " + (testCardTax.getTax()) + "\n");
		testCardTax.setTax(300);
		System.out.print("TaxSquare getTax test: " + (testCardTax.getTax()) + "\n");
		System.out.print("TaxSquare toString test: " + (testCardTax.toString()) + "\n");
		
		System.out.print("\n---CARDSQUARE TEST---\n");
		System.out.print("CardSquare tostring test: " + (Board[2].toString()) + "\n");  
		CardSquare testSquareCard = new CardSquare("CHANCE", 2, 0, 0,val); // RNG here
		System.out.print("CardSquare getAmount test: " + (testSquareCard.getAmount()) + "\n");
		testSquareCard.setAmount();
		System.out.print("CardSquare getAmount test: " + (testSquareCard.getAmount()) + "\n");
	}	
}
