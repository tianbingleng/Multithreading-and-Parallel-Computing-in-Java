package ForkJoinFramework.ForkJoinMax;

/**
 * Created by tianbingleng on 2/12/2017.
 */
public class SequentialMaxFinding {

    public int SequentialMaxFind(int[] nums, int highIndex) {
        int max = nums[0];
        for (int i = 1; i < highIndex; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
}
