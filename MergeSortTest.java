/**
 * Jiating Su
 * SBUID: 111665989
 */

package homework4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MergeSortTest {

	@Test
	public void test() {
		/**
		 * test null array in sorted array.
		 */
		int[] nullArray = new int[0];
		assertTrue(MergeSort.sorted(nullArray));
		
		/**
		 * create a random array and print the content of it
		 */
		int[] randomArray = new int[10];
		for (int i = 0; i < randomArray.length; i++) {
			randomArray[i] = (int) (Math.random() * 40);
			System.out.print(randomArray[i] + " ");
		}
		System.out.println();
		
		assertFalse(MergeSort.sorted(randomArray));
		
		/**
		 * test the sorted method after the array is sorted.
		 * then print the content of it.
		 */
		MergeSort.mergeSort(randomArray);
		for (int i = 0; i < randomArray.length; i++) {
			System.out.print(randomArray[i] + " ");
		}
		assertTrue(MergeSort.sorted(randomArray));

		System.out.println();
		int[] randomArray2 = new int[10];
		for (int i = 0; i < randomArray.length; i++) {
			randomArray2[i] = (int) (Math.random() * 40);
			System.out.print(randomArray[i] + " ");
		}
		
		/**
		 * second fail test
		 */
		assertFalse(MergeSort.sorted(randomArray2));
	}

}
