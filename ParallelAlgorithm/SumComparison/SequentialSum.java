package ParallelAlgorithm.SumComparison;

/**
 * Created by tianbingleng on 2/12/2017.
 */
public class SequentialSum {

    public int sum(int[] nums) {
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        return total;
    }
}
