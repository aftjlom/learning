public class 检查数组是否存在有效划分 {
    public boolean validPartition(int[] nums) {
        int length = nums.length;
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;
        for (int i = 1; i <= length; i++) {
            if (i >= 2) {
                dp[i] = dp[i - 2] && validTwo(nums[i - 2], nums[i - 1]);
            }
            if (i >= 3) {
                dp[i] = dp[i] || (dp[i - 3] && validThree(nums[i - 3], nums[i - 2], nums[i - 1]));
            }
        }
        return dp[length];
    }

    public boolean validTwo(int num1, int num2) {
        return num1 == num2;
    }

    public boolean validThree(int num1, int num2, int num3) {
        return (num1 == num2 && num1 == num3) || (num1 + 1 == num2 && num2 + 1 == num3);
    }

    public static void main(String[] args) {
        检查数组是否存在有效划分 solution = new 检查数组是否存在有效划分();
        System.out.println(solution.validPartition(new int[]{4, 4, 4, 5, 6}));
        System.out.println(solution.validPartition(new int[]{1, 1, 1, 2}));
    }
}
