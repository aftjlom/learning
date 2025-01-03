import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 提示：
 * 0 <= s.length <= 5 * 10^4
 * s 由英文字母、数字、符号和空格组成
 */
public class 无重复字符的最长子串 {
    /**
     * 思路：
     * 这道题主要用到思路是：滑动窗口
     * 什么是滑动窗口？
     * 其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，当再进入 a，队列变成了 abca，这时候不满足要求。
     * 所以，我们要移动这个队列！
     * 我们只要把队列的左边的元素移出就行了，直到满足题目要求！
     * 一直维持这样的队列，找出队列出现最长的长度时候，求出解！
     * 时间复杂度：O(n)
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int leftIndex = 0, max = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char devS = s.charAt(i);
            if (map.containsKey(devS)) {
                leftIndex = Math.max(leftIndex, map.get(devS) + 1);
            }
            map.put(devS, i);
            max = Math.max(max, i - leftIndex + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        无重复字符的最长子串 solution = new 无重复字符的最长子串();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }
}
