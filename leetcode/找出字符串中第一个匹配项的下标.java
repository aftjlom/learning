public class 找出字符串中第一个匹配项的下标 {
    public int strStr(String haystack, String needle) {
        int m = needle.length();
        if (m == 0) {
            return 0;
        }
        int n = haystack.length();
        if (n < m) {
            return -1;
        }
        int i = 0;
        int j = 0;
        while (i < n - m + 1) {
            while (i < n && haystack.charAt(i) != needle.charAt(j)) {
                i++;
            }
            if (i == n) {
                return -1;
            }
            i++;
            j++;
            while (i < n && j < m && haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {
                return i - j;
            } else {
                i -= j - 1;
                j = 0;
            }
        }
        return -1;
    }
}
