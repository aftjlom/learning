
public class 将标题首字母大写 {
    public String capitalizeTitle(String title) {
        String[] words = title.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toLowerCase();
            if (words[i].length() > 2) {
                words[i] = (char) (words[i].charAt(0) - 32) + words[i].substring(1);
            }
        }
        return String.join(" ", words);
    }

    public static void main(String[] args) {
        将标题首字母大写 solution = new 将标题首字母大写();
        System.out.println(solution.capitalizeTitle("capiTalIze tHe titLe"));
        System.out.println(solution.capitalizeTitle("First leTTeR of EACH Word"));
        System.out.println(solution.capitalizeTitle("i lOve leetcode"));
    }
}
