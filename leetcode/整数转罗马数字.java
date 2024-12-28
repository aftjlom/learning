public class 整数转罗马数字 {
    public String intToRoman(int num) {
        String[] Roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] RomanNum = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder builder = new StringBuilder();
        int index = 0;
        while (num > 0) {
            if (num >= RomanNum[index]) {
                builder.append(Roman[index]);
                num -= RomanNum[index];
            } else {
                index++;
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        整数转罗马数字 solution = new 整数转罗马数字();
        System.out.println(solution.intToRoman(3749));
        System.out.println(solution.intToRoman(58));
        System.out.println(solution.intToRoman(1994));
    }
}
