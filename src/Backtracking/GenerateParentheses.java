package Backtracking;

/*  22. Generate Parentheses
    Given n pairs of parentheses,
    write a function to generate all combinations of well-formed parentheses.
    For example, given n = 3, a solution set is:
    [
      "((()))",
      "(()())",
      "(())()",
      "()(())",
      "()()()"
    ]
 */
import java.util.ArrayList;
import java.util.List;
/*  Backtracking
    Instead of adding '(' or ')' every time as in Brute Force,
    we can only add them when we know it will remain a valid sequence.
    We can do this by keeping track of the number of opening and closing brackets we have placed so far.
    We can start an opening bracket if we still have one (of n) left to place,
    we can start a closing bracket if it would not exceed the number of opening brackets.
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        // 从 0 -> n
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder sb, int open, int close, int target) {
        if (sb.length() == target * 2) {
            ans.add(sb.toString());
            return;
        }
        if (open < target) {
            sb.append("(");
            backtrack(ans, sb, open + 1, close, target);
            // backtrack
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(")");
            backtrack(ans, sb, open, close + 1, target);
            // backtrack
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

/*  Backtracking

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        // 从 n -> 0
        backtrack(n, n, "", ans);
        return ans;
    }

    public void backtrack(int left, int right, String s, List<String> ans) {
        if (left == 0 && right == 0) {
            ans.add(s);
            return;
        }
        if (left > 0) {
            backtrack(left - 1, right, s + "(", ans);
        }
        if (right > 0 && left < right) {
            backtrack(left, right - 1, s + ")", ans);
        }
    }
 */

/*  Brute Force: Time = O(2^2n * n) Space = O(2^2n * n)

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateAll(new char[2 * n], 0, res);
        return res;
    }

    public void generateAll(char[] current, int pos, List<String> res) {
        if (pos == current.length) {
            if (valid(current)) {
                res.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, res);
            current[pos] = ')';
            generateAll(current, pos + 1, res);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return (balance == 0);
    }
 */

