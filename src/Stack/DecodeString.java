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

/*  Stack

 */
public class DecodeString {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return "";

        // 专门存次数
        Stack<Integer> countStack = new Stack<>();
        // 存上一层的字符串
        Stack<String> strStack = new Stack<>();
        // tail 存的是当前层的字符串
        StringBuilder tail = new StringBuilder();

        // 3[a2[c]]
        int n = s.length();
        int idx = 0;
        while (idx < n) {
            char c = s.charAt(idx);
            if (Character.isDigit(c)) {
                int count = 0;
                // 由高位到低位
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);         // 3; 2
            } else if (c == '[') {
                // 存储之前的结果
                strStack.push(tail.toString());   // null; a
                // 重置 sb
                tail = new StringBuilder();
                idx++;
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder(strStack.pop());     // temp = a; null
                int repeatTimes = countStack.pop();     // 2; 3
                while (repeatTimes-- > 0) {
                    temp.append(tail);                // acc; accaccacc
                }
                tail = temp;                  // sb = acc; accaccacc
                idx++;
            } else {
                tail.append(c);       // a; c
                idx++;
            }
        }
        return tail.toString();
    }
}

/*  DFS

    int i = 0;
    public String decodeString(String s) {
        int len = s.length();
        if (len < 1) return "";
        char[] cs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        while (i < len) {
            int times = 0;
            // get times
            while (i < len && cs[i] <= '9' && cs[i] >= '0') {
                times = times * 10 + cs[i] - '0';
                i++;
            }
            // get word
            if (cs[i] == '[') {
                i++;
                String word = decodeString(s);
                while (times-- > 0) {
                    sb.append(word);
                }
            } else if (cs[i] == ']') {
                i++;
                return sb.toString();
            } else {
                sb.append(cs[i]);
                i++;
            }
        }
        return sb.toString();
    }
 */
