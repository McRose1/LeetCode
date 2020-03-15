package Stack;

/*  32. Longest Valid Parentheses
    Given a string containing just the characters '(' and ')',
    find the length of the longest valid (well-formed) parentheses substring.

    Example 1:
    Input: "(()"
    Output: 2
    Explanation: The longest valid parentheses substring is "()"

    Example 2:
    Input: ")()())"
    Output: 4
    Explanation: The longest valid parentheses substring is "()()"
 */

import java.util.Stack;

/*  Stack: Time = O(n) Space = O(n)
    start by pushing -1 onto the stack
    for every '(' encountered, we push its index onto the stack.
    for every '(' encountered, we pop the top and subtract the current element's index from the top,
    which gives the length of the currently encountered valid string of parentheses.
    If while popping the element, the stack becomes empty, we push the current element's index onto the stack.
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {  // ")()())"
        int maxlen = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {  // ); (; ); (; ); )
            if (s.charAt(i) == '(') {
                stack.push(i);                  // 0; 1; 3
            } else {
                stack.pop();            // -1; 1; 3
                if (stack.isEmpty()) {
                    stack.push(i);      // 0
                } else {
                    maxlen = Math.max(maxlen, i - stack.peek());    // 2-0=2; 4-0=4
                }
            }
        }
        return maxlen;
    }
}

/*  Without extra space: Time = O(n) Space = O(1)
    left to right traverse, for every '(' encountered, increment the left and for every ')', increment the right
    Whenever left becomes equal to right, we calculate the length of the current valid string and max
    If right becomes greater than left, reset left and right to 0
    right to left traverse with similar procedure

        int left = 0, right = 0, maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlen = Math.max(maxlen, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlen = Math.max(maxlen, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlen;
 */

/*  DP: Time = O(n) Space = O(n)
    dp array where ith element represents the length of the longest valid substring ending at ith index.
    It's obvious that the valid substring must end with ')'
    the substrings ending with '(' will always contain '0' at their corresponding dp indices.
    To fill dp array we will check every two consecutive characters of the string and if
    1. s[i] = ')' and s[i - 1] = '(': "......()" => dp[i] = dp[i - 2] + 2
    2. s[i] = ')' and s[i - 1] = ')': "......))" => if s[i - dp[i - 1] - 1] == '(' then
    dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2

        int maxlen = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxlen = Math.max(maxlen, dp[i]);
            }
        }
        return maxlen;
 */


/*  Brute Force: Time = O(n^3) Space = O(n) TLE

    public int longestValidParentheses(String s) {  // ")()())"
        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            // 每次拿两个
            for (int j = i + 2; j <= s.length(); j += 2) {
                if (isValid(s.substring(i, j))) {   // ")("; ")()("; ")()())"; "()"; "()()"
                    maxlen = Math.max(maxlen, j - i);   // 2; 4
                }
            }
        }
        return maxlen;
    }

    // If '(' isn't available on the stack for popping at anytime or
    // if stack contains some elements after processing complete substring -> invalid
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {            // ")("; ")()("; ")()())"
                return false;
            }
        }
        return stack.isEmpty();
    }
 */

