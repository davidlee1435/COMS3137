package part_1;
/**
 * Determines distinct ways to split a value given in cents by
 * quarters, dimes and nickels recursively.<br>
 * 
 * @author David Lee<br>
 * UNI: jl4397<br>
 * 
 */
public class MakeChange {
	
	/**
	 * Main method
	 * @param args the amount of money given in cents
	 */
	public static void main(String[] args){
		int originalValue = Integer.parseInt(args[0]);
		String originalString = "Change for " + originalValue + " =";
		
		//Checks for valid cent value
		if(originalValue%5!=0 || originalValue<=0){
			System.out.println(originalValue + " can't be changed");
		}
		else{
			System.out.println(change(originalValue, QUARTER, originalString));	
		}
	}
	
	/**
	 * Method to recursively split the amount given in cents into nickels, dimes, and quarters
	 * @param centValue the current number of cents
	 * @param lastValue the last denomination (nickel, dime, or quarter) added to the string
	 * @param stringValue the string keeping track of the ways to make change
	 * @return the String of distinct ways to make change using dimes, nickels, and quarters
	 */
	public static String change(int centValue, int lastValue, String stringValue){
		if(centValue==0){
			return stringValue+NEW_LINE;
		}
		else if(centValue-QUARTER>=0 && lastValue>=QUARTER){
			return change(centValue-QUARTER, QUARTER, stringValue+SPACE+QUARTER)+
				   change(centValue-DIME, DIME, stringValue+SPACE+DIME)+
				   change(centValue-NICKEL, NICKEL, stringValue+SPACE+NICKEL);
		}
		else if(centValue-DIME>=0 && lastValue>=DIME){
			return change(centValue-DIME, DIME, stringValue+SPACE+DIME) +
				   change(centValue-NICKEL, NICKEL, stringValue+SPACE+NICKEL);
		}
		else{
			return change(centValue-NICKEL, NICKEL, stringValue+SPACE+NICKEL);
		}
	}
	
	private static final int QUARTER = 25;
	private static final int DIME = 10;
	private static final int NICKEL = 5;
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
}
