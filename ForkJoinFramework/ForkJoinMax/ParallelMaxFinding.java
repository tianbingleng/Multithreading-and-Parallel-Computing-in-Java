package ForkJoinFramework.ForkJoinMax;

import java.util.concurrent.RecursiveTask;

/**
 * Created by tianbingleng on 2/12/2017.
 */
public class ParallelMaxFinding extends RecursiveTask<Integer>{

    private int[] nums;
    private int lowIndex;
    private int highIndex;

    public ParallelMaxFinding(int[] nums, int lowIndex, int highIndex) {
        this.nums = nums;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }

    @Override
    protected Integer compute() {
        if (highIndex - lowIndex < App.THRESHOLD) {
            return SequentialMaxFind();
        } else {
            int middleIndex = (lowIndex + highIndex) / 2;

            ParallelMaxFinding task1 = new ParallelMaxFinding(nums, lowIndex, middleIndex);
            ParallelMaxFinding task2 = new ParallelMaxFinding(nums, middleIndex + 1, highIndex);

            invokeAll(task1, task2); //fork them and then result

            return Math.max(task1.join(), task2.join());
        }
    }

    private int SequentialMaxFind() {
        int max = nums[lowIndex];
        for (int i = lowIndex + 1; i < highIndex; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
}
