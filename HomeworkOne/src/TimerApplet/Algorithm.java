package TimerApplet;
import java.util.Random;

/**
 * @author David Lee
 * UNI: jl4397
 * Class for the algorithms listed in the Weisz text to generate a random permutation of the first N integers.
 */
public class Algorithm 
{
	/**
	 * Constructor that creates a random number generator using Java's Random class
	 */
	public Algorithm()
	{
		randGenerator = new Random();
	}
	
	/**
	 * Generating random numbers by checking every new random number with all the other numbers already in the array
	 * 
	 * @param upperLimit the upperLimit from which to choose random integers
	 * @return the array of random integers
	 */
	public int[] algorithmOne(int upperLimit)
	{
		int[] randomArray = new int[upperLimit];
		for(int i=0; i<randomArray.length; i++){
			int randomInt = randGenerator.nextInt(upperLimit) + 1;
			int k = 0;
			while(k<i){
				if(randomInt==randomArray[k]){
					k=0;
					randomInt = randGenerator.nextInt(upperLimit) + 1;
				}
				else
					k++;
			}
			randomArray[i]=randomInt;
		}
		return randomArray;
	}
	
	/**
	 * Generating random numbers by checking an array of booleans called used that indicate whether the number has already been put in the array
	 * 
	 * @param upperLimit the upperLimit from which to choose random integers
	 * @return the array of random integers
	 */
	public int[] algorithmTwo(int upperLimit)
	{
		int[] randomArray = new int[upperLimit];
		boolean[] used = new boolean[upperLimit];

		//Populate the used array
		for(int i = 0; i<used.length; i++){
			used[i]=false;
		}
		
		for(int i=0; i<randomArray.length; i++){
			int randomInt = randGenerator.nextInt(upperLimit) + 1;
			while(used[randomInt-1]){
				randomInt = randGenerator.nextInt(upperLimit) + 1;
			}
			randomArray[i] = randomInt;
			used[randomInt-1] = true;
		}
		return randomArray;
	}
	/**
	 * Generating random numbers by checking 
	 * @param upperLimit the upperLimit from which to choose random integers
	 * @return the array of random integers
	 */
	public int[] algorithmThree(int upperLimit)
	{
		int[] randomArray = new int[upperLimit];
		for(int i=0; i<upperLimit; i++){
			randomArray[i]=i+1;
		}
		for(int i=0; i<randomArray.length; i++){
			swapReferences(randomArray, i, randGenerator.nextInt(upperLimit));
			System.out.println(randomArray[i]);
		}
		return randomArray;
	}
	
	/**
	 * Swaps the references in a particular integer array
	 * @param arr an array of integers
	 * @param pos1 an integer in the array to switch
	 * @param pos2 an integer in the array to switch
	 */
	private void swapReferences(int[] arr, int pos1, int pos2){
		int temp=arr[pos1];
		arr[pos1]=arr[pos2];
		arr[pos2]=temp;
	}

	private Random randGenerator;
}
