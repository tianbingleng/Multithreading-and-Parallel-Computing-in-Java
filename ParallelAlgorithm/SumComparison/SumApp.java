package ParallelAlgorithm.SumComparison;

import java.util.Random;

/**
 * Created by tianbingleng on 2/12/2017.
 */
public class SumApp {
    public static void main(String[] args) {

        // in this example, the parallel sum is faster than sequential sum.
        // if length = 100000000 (large size)

        Random random = new Random();
        SequentialSum sequentialSum = new SequentialSum();
        int length = 100000000;

        int[] nums = new int[length];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }

        long start = System.currentTimeMillis();
        System.out.println(sequentialSum.sum(nums));
        long end = System.currentTimeMillis();


        System.out.println("Sequential sum takes: "+ (end - start) + " ms");
        System.out.println("");

        int numOfThreads = Runtime.getRuntime().availableProcessors();

        System.out.println("Number of threads/cores: " + numOfThreads);
        System.out.println("");

        start = System.currentTimeMillis();

        ParallelSum parallelSum = new ParallelSum(numOfThreads);
        System.out.println(parallelSum.sum(nums));

        end = System.currentTimeMillis();

        System.out.println("Parallel sum takes: "+ (end - start) + " ms");

    }
}
