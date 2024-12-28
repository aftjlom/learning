public class Z字形变换 {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder[] lineString = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            lineString[i] = new StringBuilder();
        }
        boolean flag = true;
        int lineNum = 1;
        for (Character sAt : s.toCharArray()) {
            lineString[lineNum - 1].append(sAt);
            if (lineNum == numRows) {
                flag = false;
            } else if (lineNum == 1) {
                flag = true;
            }
            if (flag) {
                lineNum ++;
            } else {
                lineNum --;
            }
        }
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            resultString.append(lineString[i]);
        }
        return resultString.toString();
    }

    public static void main(String[] args) {
        Z字形变换 solution = new Z字形变换();
        System.out.println(solution.convert("PAYPALISHIRING", 3));
        System.out.println(solution.convert("PAYPALISHIRING", 4));
        System.out.println(solution.convert("A", 1));
        System.out.println(solution.convert("AB", 1));
    }
}


