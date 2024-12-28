public class 字符串转换整数 {
    public int myAtoi(String s) {
        boolean flag = true;
        boolean beginFlag = true;
        char[] sCharArray = s.trim().toCharArray();
        if (sCharArray.length == 0) {
            return 0;
        } else if (sCharArray[0] == '+') {
            beginFlag = false;
        } else if (sCharArray[0] == '-') {
            beginFlag = false;
            flag = false;
        }
        long result = 0;
        for (char sAt : s.trim().toCharArray()) {
            if (!beginFlag) {
                beginFlag = true;
                continue;
            }
            if (sAt >= '0' && sAt <= '9') {
                result *= 10;
                if (!flag) {
                    result -= sAt - '0';
                } else {
                    result += sAt - '0';
                }
            } else {
                break;
            }
            if (result >= 2147483648L) {
                result = 2147483647L;
                break;
            }
            if (result <= -2147483648L) {
                result = -2147483648L;
                break;
            }
        }
        return (int) result;
    }

    public static void main(String[] args) {
        字符串转换整数 solution = new 字符串转换整数();
        System.out.println(solution.myAtoi("42"));
        System.out.println(solution.myAtoi(" -042"));
        System.out.println(solution.myAtoi("1337c0d3"));
        System.out.println(solution.myAtoi("0-1"));
        System.out.println(solution.myAtoi("words and 987"));
        System.out.println(solution.myAtoi("-91283472332"));
        System.out.println(solution.myAtoi("21474836460"));
        System.out.println(solution.myAtoi("9223372036854775808"));
    }
}
