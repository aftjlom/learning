import java.util.ArrayList;
import java.util.List;

public class 删除注释 {
    public List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        boolean block = false;
        for (String s : source) {
            int length = s.length();
            for (int i = 0; i < length; i++) {
                if (block) {
                    if (i + 1 < length && s.charAt(i) == '*' && s.charAt(i + 1) == '/') {
                        block = false;
                        i++;
                    }
                } else {
                    if (i + 1 < length && s.charAt(i) == '/' && s.charAt(i + 1) == '*') {
                        block = true;
                        i++;
                    } else if (i + 1 < length && s.charAt(i) == '/' && s.charAt(i + 1) == '/') {
                        break;
                    } else {
                        builder.append(s.charAt(i));
                    }
                }
            }
            if (!block && builder.length() > 0) {
                result.add(builder.toString());
                builder.setLength(0);
            }
        }
        return result;
    }
}
