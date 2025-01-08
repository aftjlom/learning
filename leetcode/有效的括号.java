import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class 有效的括号 {
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        Deque<Character> stack = new LinkedList<>();
        for (Character str : s.toCharArray()) {
            if (map.containsKey(str)) {
                if (stack.isEmpty() || stack.peek() != map.get(str)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(str);
            }
        }
        return stack.isEmpty();
    }
}
