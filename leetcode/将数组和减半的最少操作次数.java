import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个正整数数组 nums 。每一次操作中，你可以从 nums 中选择 任意 一个数并将它减小到 恰好 一半。
 * （注意，在后续操作中你可以对减半过的数继续执行操作）
 * 请你返回将 nums 数组和 至少 减少一半的 最少 操作数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,19,8,1]
 * 输出：3
 * 解释：初始 nums 的和为 5 + 19 + 8 + 1 = 33 。
 * 以下是将数组和减少至少一半的一种方法：
 * 选择数字 19 并减小为 9.5 。
 * 选择数字 9.5 并减小为 4.75 。
 * 选择数字 8 并减小为 4 。
 * 最终数组为 [5, 4.75, 4, 1] ，和为 5 + 4.75 + 4 + 1 = 14.75 。
 * nums 的和减小了 33 - 14.75 = 18.25 ，减小的部分超过了初始数组和的一半，18.25 >= 33/2 = 16.5 。
 * 我们需要 3 个操作实现题目要求，所以返回 3 。
 * 可以证明，无法通过少于 3 个操作使数组和减少至少一半。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [3,8,20]
 * 输出：3
 * 解释：初始 nums 的和为 3 + 8 + 20 = 31 。
 * 以下是将数组和减少至少一半的一种方法：
 * 选择数字 20 并减小为 10 。
 * 选择数字 10 并减小为 5 。
 * 选择数字 3 并减小为 1.5 。
 * 最终数组为 [1.5, 8, 5] ，和为 1.5 + 8 + 5 = 14.5 。
 * nums 的和减小了 31 - 14.5 = 16.5 ，减小的部分超过了初始数组和的一半， 16.5 >= 31/2 = 16.5 。
 * 我们需要 3 个操作实现题目要求，所以返回 3 。
 * 可以证明，无法通过少于 3 个操作使数组和减少至少一半。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^7
 */
public class 将数组和减半的最少操作次数 {
    /**
     * 方法一：贪心 + 优先队列（大根堆）
     * 根据题目描述，每一次操作，都会将数组中的一个数减半。要使得数组和至少减少一半的操作次数最少，那么每一次操作都应该选择当前数组中的最大值进行减半。
     * 因此，我们先算出数组要减少的总和 sss，然后用一个优先队列（大根堆）维护数组中的所有数，每次从优先队列中取出最大值 ttt，将其减半，
     * 然后将减半后的数重新放入优先队列中，同时更新 sss，直到 s≤0s \le 0s≤0 为止。那么此时的操作次数就是答案。
     * <p>
     * 时间复杂度 O(n×logn)，空间复杂度 O(n)。其中 n 是数组的长度。
     */
    public int halveArray(int[] nums) {
        // 优先队列，最大值放在队头
        PriorityQueue<Double> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        int result = 0;
        double sum = 0, sum2 = 0.0;
        for (int num : nums) {
            // 将数组所有元素推入优先队列
            priorityQueue.offer((double) num);
            // 计算累加和
            sum += num;
        }
        while (sum2 < sum / 2) {
            // 从优先队列中取出最大元素 x
            double x = priorityQueue.poll();
            // 令 sum2=sum2+x/2
            sum2 += x / 2;
            // 将 x/2放入优先队列中
            priorityQueue.offer(x / 2);
            result++;
        }
        return result;
    }

    public static void main(String[] args) {
        将数组和减半的最少操作次数 solution = new 将数组和减半的最少操作次数();
        System.out.println(solution.halveArray(new int[]{5, 19, 8, 1}));
        System.out.println(solution.halveArray(new int[]{3, 8, 20}));
    }
}
