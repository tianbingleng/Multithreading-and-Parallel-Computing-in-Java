package ParallelAlgorithm.MergeSortComparison;

import java.util.Random;

/**
 * Created by tianbingleng on 2/12/2017.
 */
public class MergeSortApp {
    // NOTICE: in this test, sequential has better performance than parallel.
    // BUT in big data > 10M, the parallel sorting is much faster than sequential way.
    public static final int numOfElements = 100000000;

    public static void main(String[] args) {

        int numOfThreads = Runtime.getRuntime().availableProcessors();

        //int[] nums = {4,2,6,5,44,78,-4,0,1};

        System.out.println("Number of threads/cores: " + numOfThreads);
        System.out.println("");

        int[] numbers = createRandomArray();
        MergeSort mergeSort = new MergeSort(numbers);

        // run the algorithm and time how long it takes
        long startTime1 = System.currentTimeMillis();
        mergeSort.parallelMergeSort(0, numbers.length-1, numOfThreads);
        //mergeSort.showResult();
        long endTime1 = System.currentTimeMillis();

        System.out.println("");
        System.out.printf("Time taken for "+numOfElements+" elements parallel =>  %6d ms \n", endTime1 - startTime1);
        System.out.println("");

        startTime1 = System.currentTimeMillis();
        mergeSort.mergeSort(0,numbers.length-1);
        endTime1 = System.currentTimeMillis();


        // System.out.println("Array("+LENGTH+") elements after sort: ");
        // Print first 10 elements
        //printIntArray(a);


        System.out.printf("Time taken for "+numOfElements+" elements sequential =>  %6d ms \n", endTime1 - startTime1);
        System.out.println("");

        //MergeSort mergeSort = new MergeSort(nums);
        //mergeSort.parallelMergeSort(0, nums.length-1, numOfThreads);
        //mergeSort.showResult();


    }

    public static int[] createRandomArray() {
        Random random = new Random();
        int[] a = new int[numOfElements];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(10000);
        }
        return a;
    }
}
