/**
 * Jiating Su
 * SBUID: 111665989
 */

package homework4;

public class Sorting implements Runnable {
	private int[] arr;
	private int threadCount;
	
	public Sorting(int[] arr, int threadCount) {
		this.arr = arr;
		this.threadCount = threadCount;
	}
	
	public void run() {
		MergeSort.concurrentMergeSort(arr, threadCount);
	}
}
