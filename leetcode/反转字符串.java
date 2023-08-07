/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 示例 1：
 * 输入：s = ['h','e','l','l','o']
 * 输出：['o','l','l','e','h']
 * 
 * 示例 2：
 * 输入：s = ['H','a','n','n','a','h']
 * 输出：['h','a','n','n','a','H']
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s[i] 都是 ASCII 码表中的可打印字符
 */
public class 反转字符串 {
    public void reverseString(char[] s) {
        int length = s.length;
        for (int i = 0; i < length / 2; i ++) {
            char begin = s[i];
            s[i] = s[length - i - 1];
            s[length - i - 1] = begin;
        }
    }

    public static void main(String[] args) {
        反转字符串 solution = new 反转字符串();
        char[] s = new char[] {'h','e','l','l','o'};
        solution.reverseString(s);
        System.out.println(s);
        s = new char[] {'H','a','n','n','a','h'};
        solution.reverseString(s);
        System.out.println(s);
    }
}
