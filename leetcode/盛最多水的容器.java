public class 盛最多水的容器 {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int result = 0;
        while (left < right) {
            int arex = Math.min(height[left], height[right]) * (right - left);
            result = Math.max(result, arex);
            if (height[left] < height[right]) left++;
            else right--;
        }
        return result;
    }

    public static void main(String[] args) {
        盛最多水的容器 solution = new 盛最多水的容器();
        System.out.println(solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(solution.maxArea(new int[]{1, 1}));
    }
}
