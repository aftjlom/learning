import java.util.HashSet;
import java.util.Set;

/**
 * 在桌子上有 n 张卡片，每张卡片的正面和背面都写着一个正数（正面与背面上的数有可能不一样）。
 * 我们可以先翻转任意张卡片，然后选择其中一张卡片。
 * 如果选中的那张卡片背面的数字 x 与任意一张卡片的正面的数字都不同，那么这个数字是我们想要的数字。
 * 哪个数是这些想要的数字中最小的数（找到这些数中的最小值）呢？如果没有一个数字符合要求的，输出 0 。
 * 其中, fronts[i] 和 backs[i] 分别代表第 i 张卡片的正面和背面的数字。
 * 如果我们通过翻转卡片来交换正面与背面上的数，那么当初在正面的数就变成背面的数，背面的数就变成正面的数。
 * <p>
 * 示例 1：
 * 输入：fronts = [1,2,4,4,7], backs = [1,3,4,1,3]
 * 输出：2
 * 解释：假设我们翻转第二张卡片，那么在正面的数变成了 [1,3,4,4,7] ， 背面的数变成了 [1,2,4,1,3]。
 * 接着我们选择第二张卡片，因为现在该卡片的背面的数是 2，2 与任意卡片上正面的数都不同，所以 2 就是我们想要的数字。
 * <p>
 * 示例 2：
 * 输入：fronts = [1], backs = [1]
 * 输出：0
 * 解释：
 * 无论如何翻转都无法得到想要的数字，所以返回 0 。
 * <p>
 * 提示：
 * n == fronts.length == backs.length
 * 1 <= n <= 1000
 * 1 <= fronts[i], backs[i] <= 2000
 */
public class 翻转卡片游戏 {
    /**
     方法一：哈希集
     如果一张卡片正反两面有相同的数字，那么这张卡片无论怎么翻转，正面都是这个数字，这个数字即不能是最后所选的数字 x。
     按照这个思路，我们首先遍历所有卡片，如果卡片上的两个数字相同，则加入哈希集合 same 中，除此集合外的所有数字，都可以被选做 x，
     我们只需要再次遍历所有数字，找到最小值即可。
     最后，我们返回找到的最小值，如果没有则返回 0。
     */
    public int flipgame(int[] fronts, int[] backs) {
        Set<Integer> same = new HashSet<>();
        for (int i = 0; i < fronts.length; ++i) {
            if (fronts[i] == backs[i]) {
                same.add(fronts[i]);
            }
        }
        int res = 3000;
        for (int x : fronts) {
            if (x < res && !same.contains(x)) {
                res = x;
            }
        }
        for (int x : backs) {
            if (x < res && !same.contains(x)) {
                res = x;
            }
        }
        return res % 3000;
    }

    public static void main(String[] args) {
        翻转卡片游戏 solution = new 翻转卡片游戏();
        System.out.println(solution.flipgame(new int[]{1, 2, 4, 4, 7}, new int[]{1, 3, 4, 1, 3}));
        System.out.println(solution.flipgame(new int[]{1}, new int[]{1}));
    }
}
