public class 整数反转 {
    public int reverse(int x) {
        long result = 0;
        while(x != 0) {
            int endNum = x % 10;
            result = result * 10 + endNum;
            x /= 10;
        }
        return (result >= 2147483648L || result < -2147483648L) ? 0 : (int) result;
    }

    public static void main(String[] args) {
        整数反转 solution = new 整数反转();
        System.out.println(solution.reverse(123));
        System.out.println(solution.reverse(-123));
        System.out.println(solution.reverse(120));
        System.out.println(solution.reverse(0));
        System.out.println(solution.reverse(1534236469));
    }
}
