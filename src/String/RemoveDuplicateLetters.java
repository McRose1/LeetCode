package String;

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

//  Stack
import java.util.Stack;

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        Stack<Integer> stack = new Stack<>();
        // contains number of occurrence of character
        int nums[] = new int[26];
        // contains if character is present in current result stack
        boolean visit[] = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            nums[index]--;
            if (visit[index]) {
                continue;
            }
            // if current character is smaller than last character in stack which occurs later in the string again
            // it can be removed and added later
            while (!stack.isEmpty() && stack.peek() > index && nums[stack.peek()] > 0) {
                visit[stack.peek()] = false;
                stack.pop();
            }
            // add current character and mark it as visited
            stack.push(index);
            visit[index] = true;
        }
        String res = "";
        for (int x : stack) {
            res += (char)('a' + x);
        }
        return res;
    }
}
