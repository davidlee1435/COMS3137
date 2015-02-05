package part_2;
import java.util.Random;

/**
 * @author David Lee<br>
 * UNI: jl4397<br>
 * Class for the algorithms listed in the Weisz text to generate a random permutation of the first N integers.<br>
 */
public class Algorithm 
{
	/**
	 * Instantiates the Algorithm class with a random number generator<br>
	 */
	public Algorithm()
	{
		randGenerator = new Random();
	}
	
	/**
	 * Generating random numbers by checking every new random number with all the other numbers already in the array<br>
	 * @param upperLimit the upperLimit from which to choose random integers
	 */
	public void algorithmOne(int upperLimit)
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
	}
	
	/**
	 * Generating random numbers by checking an array of booleans called used that indicate whether the number has already been put in the array.<br>
	 * @param upperLimit the upperLimit from which to choose random integers
	 */
	public void algorithmTwo(int upperLimit)
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
	}
	
	/**
	 * Generating random numbers by checking. <br>
	 * @param upperLimit the upperLimit from which to choose random integers
	 */
	public void algorithmThree(int upperLimit)
	{
		int[] randomArray = new int[upperLimit];
		for(int i=0; i<upperLimit; i++){
			randomArray[i]=i+1;
		}
		for(int i=0; i<randomArray.length; i++){
			swapReferences(randomArray, i, randGenerator.nextInt(upperLimit));
		}
	}
	
	/**
	 * Swaps the references in a particular integer array <br>
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
