package ForkJoinFramework.ForkJoinMergeSort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by tianbingleng on 2/12/2017.
 */
public class App {
    public static void main(String[] args) {
        // In this example, Parallel sorting is faster than sequential one.

        int[] nums = initializeNums();

        SequentialMergeSort sequentialMergeSort = new SequentialMergeSort();
        long start = System.currentTimeMillis();
        sequentialMergeSort.mergeSort(nums);
        long end = System.currentTimeMillis();
        System.out.println("Sequential algorithms: "+ (end - start) + " ms");
        System.out.println();


        start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ParallelMergeSortTask parallelMergeSortTask = new ParallelMergeSortTask(nums);
        pool.invoke(parallelMergeSortTask);
        end = System.currentTimeMillis();
        System.out.println("Parallel algorithms: "+ (end - start) + " ms");

        //System.out.println(Arrays.toString(nums));
    }

    public static int[] initializeNums() {
        Random random = new Random();
        int length = 10000000;
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = random.nextInt(1000);
        }
        return nums;
    }
}
