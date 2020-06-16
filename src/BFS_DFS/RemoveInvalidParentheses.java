package BFS_DFS;

/*  301. Remove Invalid Parentheses
    Remove the minimum number of invalid parentheses in order to make the input string valid.
    Return all possible results.

    Note: The input string may contain letters other than the parentheses ( and ).

    Example 1:
    Input: "()())()"
    Output: ["()()()", "(())()"]

    Example 2:
    Input: "(a)())()"
    Output: ["(a)()()", "(a())()"]

    Example 3:
    Input: ")("
    Output: [""]

    Hint: For every (, we should have a corresponding ).
    We can make use of two counters which keep track of misplaced ( and ) and in one iteration we can find out these two values.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParentheses {
    private Set<String> validExpressions = new HashSet<>();
    private int minRemoved;
    public List<String> removeInvalidParentheses(String s) {
        dfs(s, 0, 0, 0, new StringBuilder(), 0);
        return new ArrayList<>(validExpressions);
    }

    private void dfs(String s, int index, int lCount, int rCount, StringBuilder expr, int removedCount) {
        // If we have reached the end of string
        if (index == s.length()) {
            // If the current expression is valid
            if (lCount == rCount) {
                // If the current count of removed parentheses is <= the current minimum count
                if (removedCount <= minRemoved) {
                    // Convert StringBuilder to a String. This is an expensive operation
                    // So we only perform this when needed
                    String possibleAnswer = expr.toString();

                    // If the current count beats the overall minimum we have till now
                    if (removedCount < minRemoved) {
                        validExpressions.clear();
                        minRemoved = removedCount;
                    }
                    validExpressions.add(possibleAnswer);
                }
            }
        } else {
            char curChar = s.charAt(index);
            int len = expr.length();

            // If the current character is neither an opening bracket not a closing one,
            // simply recurse further by adding it the expression StringBuilder
            if (curChar != '(' && curChar != ')') {
                expr.append(curChar);
                dfs(s, index + 1, lCount, rCount, expr, removedCount);
                expr.deleteCharAt(len);
            } else {
                // Recursion where we delete the current character and move forward
                dfs(s, index + 1, lCount, rCount, expr, removedCount + 1);
                expr.append(curChar);

                // If it's an opening parenthesis, consider it and recurse
                if (curChar == '(') {
                    dfs(s, index + 1, lCount + 1, rCount, expr, removedCount);
                } else if (rCount < lCount) {
                    // For a closing parenthesis, only recurse if right < left
                    dfs(s, index + 1, lCount, rCount + 1, expr, removedCount);
                }

                // Undoing the append operation for other recursions
                expr.deleteCharAt(len);
            }
        }
    }
}
