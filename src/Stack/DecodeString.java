package Stack;

/*  394. Decode String
    Given an encoded string, return its decoded string.
    The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
    Note that k is guaranteed to be a positive integer.

    You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

    Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
    For example, there won't be input like 3a or 2[4].

    Example:
    s = "3[a]2[bc]", return "aaabcbc".
    s = "3[a2[c]]", return "accaccacc".
    s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */

import java.util.Stack;

public class DecodeString {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return "";
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                // 由高位到低位
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');     // 3; 2
                    idx++;
                }
                countStack.push(count);     // 3; 2
            } else if (s.charAt(idx) == '[') {
                // 存储之前的结果
                resStack.push(res);         // aaa
                // 重置 res
                res = "";
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder sb = new StringBuilder(resStack.pop());   // aaa
                int repeat = countStack.pop();      // 3; 2
                for (int i = 0; i < repeat; i++) {
                    sb.append(res);
                }
                res = sb.toString();           // aaa; aaabcbc
                idx++;
            } else {
                res += s.charAt(idx++);     // a; bc
            }
        }
        return res;
    }
}
