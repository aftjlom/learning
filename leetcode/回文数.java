public class 回文数 {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        int begin = x;
        int result = 0;
        while (x > 0) {
            int endNum = x % 10;
            result = result * 10 + endNum;
            x /= 10;
        }
        return begin - result == 0;
    }

    public static void main(String[] args) {
        回文数 solution = new 回文数();
        System.out.println(solution.isPalindrome(121));
        System.out.println(solution.isPalindrome(-121));
        System.out.println(solution.isPalindrome(10));
    }
}
