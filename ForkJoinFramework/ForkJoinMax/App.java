package ForkJoinFramework.ForkJoinMax;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by tianbingleng on 2/12/2017.
 */
public class App {
    public static int THRESHOLD = 0;

    public static void main(String[] args) {
        // in this example, when the array length are in very large size, parallel will be faster.
        int[] nums = initializeNums();

        THRESHOLD = nums.length / Runtime.getRuntime().availableProcessors();

        SequentialMaxFinding sequentialMaxFinding = new SequentialMaxFinding();

        long start = System.currentTimeMillis();
        System.out.println("Max: "+sequentialMaxFinding.SequentialMaxFind(nums, nums.length));
        long end = System.currentTimeMillis();
        System.out.println("Time taken for Sequential maxFinding: "+ (end - start) + " ms");

        System.out.println();

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ParallelMaxFinding parallelMaxFinding = new ParallelMaxFinding(nums, 0, nums.length);

        start = System.currentTimeMillis();
        System.out.println("Max: "+pool.invoke(parallelMaxFinding));
        end = System.currentTimeMillis();
        System.out.println("Time taken for Parallel maxFinding: "+ (end - start) + " ms");

    }

    private static int[] initializeNums() {
        Random random = new Random();

        int length = 300000000;

        int[] nums = new int[length];

        for (int i = 0; i < length; i++) {
            nums[i] = random.nextInt(1000);
        }

        return nums;
    }
}
