package Greedy;

/*  316. Remove Duplicate Letters
    Given a string which contains only lowercase letters,
    remove duplicate letters so that every letter appears once and only once.
    You must make sure your result is the smallest in lexicographical order among all possible results.

    Example 1:
    Input: "bcabc"
    Output: "abc"

    Example 2:
    Input: "cbacdcbc"
    Output: "acdb"
 */

//  Greedy (No Stack)
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        char[] chs = s.toCharArray();
        int[] count = new int[26];
        for (char c : chs) {
            count[c - 'a']++;
        }
        boolean[] used = new boolean[26];
        StringBuilder sb = new StringBuilder();
        for (char c : chs) {
            count[c - 'a']--;

            if (used[c - 'a']) continue;
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) > c && count[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                used[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(c);
            used[c - 'a'] = true;
        }
        return sb.toString();
    }
}

/*  Greedy + Stack: Time = O(n) Space = O(1)
    用 Stack 来存储最终返回的字符串，并维持字符串的最小字典序。
    每遇到一个字符，如果这个字符不存在与栈中，就需要将该字符压入栈中。
    但在压入之前，需要先将之后还会出现，并且字典序比当前字符大的栈顶字符移除，然后再将当前字符压入。

        Stack<Character> stack = new Stack<>();

        // contains number of occurrence of character
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        // contains if character is present in current result stack
        boolean[] visit = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c - 'a']--;
            if (visit[c - 'a']) {
                continue;
            }
            // if current character is smaller than last character in stack which occurs later in the string again
            // it can be removed and added later
            while (!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] > 0) {
                visit[stack.peek() - 'a'] = false;
                stack.pop();
            }
            // add current character and mark it as visited
            stack.push(c);
            visit[c - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        return sb.toString();
 */

/*  Greedy（一个字符一个字符处理）: Time = O(n) Space = O(n)
    每次递归中，在保证其他字符至少出现一次的情况下，确定最小左侧字符。之后再将未处理的后缀字符串继续递归。

    public String removeDuplicateLetters(String s) {
        // find pos - the index o the leftmost letter
        // we create a counter and end the iteration once the suffix doesn't have each unique character
        // pos will be the index of the smallest character we encounter before the iteration ends
        int[] count = new int[26];
        int pos = 0;
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) {
                pos = i;
            }
            if (--count[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }
        // our answer is the leftmost letter plus the recursive call on the remainder of the string
        // note that we have to get rid of further occurrences of s[pos] to ensure that there are no duplicates
        // "" + s.charAt(pos): char -> String
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }
 */