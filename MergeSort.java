/**
 * Jiating Su
 * SBUID: 111665989
 */

package homework4;

import java.util.Random;
import java.util.stream.IntStream;

public class MergeSort {
	private static final Random RNG    = new Random(10982755L);
    private static final int    LENGTH = 1024000;

    public static void main(String... args) {
        int[] arr = randomIntArray();
        long start = System.currentTimeMillis();
        concurrentMergeSort(arr);
        long end = System.currentTimeMillis();
        if (!sorted(arr)) {
            System.err.println("The final array is not sorted");
            System.exit(0);
        }
        System.out.printf("%10d numbers: %6d ms%n", LENGTH, end - start);
    }

    private static int[] randomIntArray() {
        int[] arr = new int[LENGTH];
        for (int i = 0; i < arr.length; i++)
            arr[i] = RNG.nextInt(LENGTH * 10);
        return arr;
    }

    public static boolean sorted(int[] arr) {
        return !IntStream.range(1, arr.length)
                         .mapToObj(i -> arr[i - 1] > arr[i])
                         .anyMatch(i -> i.booleanValue());
    }
    
    /**
     * concurrentMergeSort that accepts only array
     * @param arr
     */
    public static void concurrentMergeSort(int[] arr) {
    	concurrentMergeSort(arr, Runtime.getRuntime().availableProcessors());
    }
    
    /**
     * concurrentMergeSort accepts both array and threadcount
     * @param arr
     * @param threadCount
     */
    public static void concurrentMergeSort(int[] arr, int threadCount) {
    	if (threadCount == 1 ) {
    		mergeSort(arr);
    	}
    	else {
    		int[] firstHalf = new int[arr.length / 2];
    		int[] secondHalf = new int[arr.length - arr.length / 2];
    		System.arraycopy(arr, 0, firstHalf, 0, arr.length / 2);
    		System.arraycopy(arr, arr.length / 2, secondHalf, 0, arr.length - arr.length / 2);
    		
    		/**
    		 * create two subthread to run the merge sort
    		 */
    		Sorting sub1 = new Sorting(firstHalf, threadCount / 2);
    		Sorting sub2 = new Sorting(secondHalf, threadCount / 2);
    		Thread t1 = new Thread(sub1);
    		Thread t2 = new Thread(sub2);
    		
    		t1.start();
    		t2.start();
    		try {
				t1.join();
	    		t2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		merge(arr, firstHalf, secondHalf);
    	}
    }
    
    /**
     * merge sort
     * @param arr
     */
    public static void mergeSort(int[] arr) {
		if (arr.length > 1) {
			int[] firstHalf = new int[arr.length / 2];
    		int[] secondHalf = new int[arr.length - arr.length / 2];
    		System.arraycopy(arr, 0, firstHalf, 0, arr.length / 2);
    		System.arraycopy(arr, arr.length / 2, secondHalf, 0, arr.length - arr.length / 2);
    		
    		mergeSort(firstHalf);
    		mergeSort(secondHalf);
    		
    		merge(arr, firstHalf, secondHalf);
		}
	}
	
	/**
	 * merge sort part
	 * @param arr
	 * @param bot
	 * @param top
	 */
	public static void merge(int[] arr, int[] firstHalf, int[] secondHalf) {
		int bot = 0;
		int top = 0;
		
		for (int i = 0; i < arr.length; i++) {
			if (bot >= firstHalf.length || (top < secondHalf.length && secondHalf[top] <= firstHalf[bot])) {
				arr[i] = secondHalf[top];
				top++;
			}
			else {
				arr[i] = firstHalf[bot];
				bot++;
			}
		}
	}
}

