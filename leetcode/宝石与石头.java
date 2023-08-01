import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。 stones 中每个字符代表了一种你拥有的石头的类型，
 * 你想知道你拥有的石头中有多少是宝石。字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
 * <p>
 * 示例 1：
 * 输入：jewels = "aA", stones = "aAAbbbb"
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：jewels = "z", stones = "ZZ"
 * 输出：0
 * <p>
 * 提示：
 * 1 <= jewels.length, stones.length <= 50
 * jewels 和 stones 仅由英文字母组成
 * jewels 中的所有字符都是 唯一的
 */
public class 宝石与石头 {

    /**
     * 方法二：哈希集合
     * 思路与算法
     * <p>
     * 方法一中，对于字符串 stones\textit{stones}stones 中的每个字符，都需要遍历一次字符串 jewels\textit{jewels}jewels，
     * 导致时间复杂度较高。如果使用哈希集合存储字符串 jewels\textit{jewels}jewels 中的宝石，则可以降低判断的时间复杂度。
     * <p>
     * 遍历字符串 jewels\textit{jewels}jewels，使用哈希集合存储其中的字符，然后遍历字符串 stones\textit{stones}stones，
     * 对于其中的每个字符，如果其在哈希集合中，则是宝石。
     */
    public int numJewelsInStones2(String jewels, String stones) {
        int result = 0;
        Set<Character> jewelsSet = new HashSet<>();
        for (char c : jewels.toCharArray()) {
            jewelsSet.add(c);
        }
        for (char c : stones.toCharArray()) {
            if (jewelsSet.contains(c)) {
                result++;
            }
        }
        return result;
    }

    /**
     * 方法三：位运算
     * 观察 ASCII 表，可以发现：
     * <p>
     * 大写字母二进制的低 666 位是从 000001000001000001 开始的（对应大写字母 A），一直到 011010011010011010（对应大写字母 Z）。
     * 小写字母二进制的低 666 位是从 100001100001100001 开始的（对应小写字母 a），一直到 111010111010111010（对应小写字母 z），
     * 即十进制的 585858。
     * 所以取字符的的低 666 位，就可以把不同的大小写字母映射到不同的数字上。
     * <p>
     * 所以，可以用一个 646464 位整数 mask\textit{mask}mask 来代替哈希表/布尔数组，压缩存储 jewels\textit{jewels}jewels 中的字母。
     * 然后遍历 stones\textit{stones}stones，判断每个字母是否在 mask\textit{mask}mask 中。
     */
    public int numJewelsInStones3(String jewels, String stones) {
        // 把 jewels 转换成字符集合 mask
        long mask = 0;
        for (char c : jewels.toCharArray())
            mask |= 1L << (c & 63);
        // 统计有多少 stones[i] 在集合 mask 中
        int ans = 0;
        for (char c : stones.toCharArray())
            ans += mask >> (c & 63) & 1;
        return ans;
    }

    public static void main(String[] args) {
        宝石与石头 solution = new 宝石与石头();
        System.out.println(solution.numJewelsInStones2("aA", "aAAbbbb"));
        System.out.println(solution.numJewelsInStones2("z", "ZZ"));
        System.out.println(solution.numJewelsInStones3("aA", "aAAbbbb"));
        System.out.println(solution.numJewelsInStones3("z", "ZZ"));
    }
}
