import sun.misc.Unsafe;

public class 任意子数组和的绝对值的最大值 {
    public int maxAbsoluteSum(int[] nums) {
        int sum = 0, max = 0, min = 0;
        for (int num : nums) {
            max = Math.max(max, 0) + num;
            min = Math.min(min, 0) + num;
            sum = Math.max(Math.max(max, -min), sum);
        }
        return sum;
    }

    public static void main(String[] args) {
        任意子数组和的绝对值的最大值 solution = new 任意子数组和的绝对值的最大值();
        System.out.println(solution.maxAbsoluteSum(new int[]{1, -3, 2, 3, -4}));
        System.out.println(solution.maxAbsoluteSum(new int[]{2, -5, 1, -4, 3, -2}));
    }
}
