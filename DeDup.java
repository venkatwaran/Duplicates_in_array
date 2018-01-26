package sei_investments;

/*Author: Venkateshwaran Subbusamy
 * Email: venki.nh@gmail.com
 * */

import java.util.Arrays;
import java.util.function.Consumer;

public class DeDup {

	public int[] randomIntegers = { 1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3,
            20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,16,3,6,19,
            13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11 };

	public static void main(String[] args) {
		new DeDup().removeDuplicates1();
		new DeDup().removeDuplicates2();
		new DeDup().removeDuplicates3();
	}

	// by replacing the duplicate number with the last number, avoiding moving the array to fill the removed index
	//POSITIVE - Best Big O measure compared to method 2 and 3
	//NEGATIVE - Not in given order
	public int[] removeDuplicates1() {
		int arrLength = randomIntegers.length;
		for (int i = 0; i < arrLength; i++) {
			for (int j = i + 1; j < arrLength; j++) {
				if (randomIntegers[i] == randomIntegers[j]) {
					randomIntegers[j] = randomIntegers[arrLength - 1];
					arrLength--;
					j--;
				}
			}
		}
		int[] arrNoDuplicates = Arrays.copyOf(randomIntegers, arrLength);

		for (int i = 0; i < arrNoDuplicates.length; i++) {
			System.out.print(arrNoDuplicates[i] + ",");
		}
		System.out.println();
		return arrNoDuplicates;
	}

	//Will be in given order
	
	//POSTITIVE - ordered array 
	//NEGATIVE - since multiple array objects getting referenced, will use JVM heavily but Garbage collection is efficient then good. 
	public int[] removeDuplicates2() {
		int arrLength = randomIntegers.length;
		int[] randomIntegersNoDup = new int[arrLength];

		int randomIntegersNoDupIndex =0;
		for (int i = 0; i < arrLength; i++) {//master loop
			for (randomIntegersNoDupIndex = 0; randomIntegersNoDupIndex <= i; randomIntegersNoDupIndex++) {
				randomIntegersNoDup[randomIntegersNoDupIndex] = randomIntegers[randomIntegersNoDupIndex];
			}//to read and store the numbers which are already sorted
			for (int j = i + 1; j < arrLength; j++) {
				if (randomIntegers[i] != randomIntegers[j]) {//if the numbers are not equal to the master loop number, then store, others ignore
					randomIntegersNoDup[randomIntegersNoDupIndex] = randomIntegers[j];
					randomIntegersNoDupIndex++;
				}
				
			}
			
			randomIntegers = Arrays.copyOf(randomIntegersNoDup,randomIntegersNoDupIndex);//copying the derived array to the original array
			arrLength = randomIntegers.length;
			randomIntegersNoDup = new int[arrLength];
		}
		
		for (int i = 0; i < randomIntegers.length; i++) {
			System.out.print(randomIntegers[i] + ",");
		}
		System.out.println();
		return randomIntegers;
	}

	//identify the index of the duplicate values in the original given array, and store those index in another array, and except index 0 if all other indexes has non zero value for the index where duplicate value in the original array, at last iterate through the original array and then extract values of index of another array, where the value is zero
	//POSTITIVE - ordered array and better big O measure than method 2
	//NEGATIVE - big O measure is higher than method 1
	public int[] removeDuplicates3() {
		int arrLength = randomIntegers.length;
		int[] randomIntegersDupIndexArr = new int[arrLength];
		
		for (int i = 0; i < arrLength; i++) {
			for (int j = i + 1; j < arrLength; j++) {

				if (randomIntegersDupIndexArr[j] ==0 && randomIntegers[i] == randomIntegers[j]) {
					randomIntegersDupIndexArr[j] = j;
				}
			}
		}
		
		int[] randomIntegersNoDup = new int[arrLength];
		int randomIntegersNoDupIndex = 0;
		for (int i = 0; i < arrLength; i++) {
			
				if(i==0 || randomIntegersDupIndexArr[i]==0) {
					randomIntegersNoDup[randomIntegersNoDupIndex] = randomIntegers[i];
					randomIntegersNoDupIndex++;
					
				}
			
		}
		
		randomIntegers = Arrays.copyOf(randomIntegersNoDup,randomIntegersNoDupIndex);
		for (int i = 0; i < randomIntegers.length; i++) {
			System.out.print(randomIntegers[i] + ",");
		}
		System.out.println(); 
		return randomIntegers;
	}
}
