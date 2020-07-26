package Backtracking;

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

    Hint:
    1. We can use recursion to try out all possibilities for the given expression. For each of the brackets, we have 2 options:
        1. We keep the bracket and add it to the expression that we are building on the fly during recursion.
        2. OR, we can discard the bracket and move on.
    2. For every (, we should have a corresponding ).
       We can make use of two counters which keep track of misplaced ( and ) and in one iteration we can find out these two values.
    3. We found out that the exact number of ( and ) that has to be removed to get a valid expression.
       So, e.g. in a 1000 parentheses string, if there are 2 misplaced ( and 2 misplaced ), after we are done discarding 2 ( and 2 ),
       we will have only one option per remaining character in the expression i.e. to consider them. We can't discard them.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*  Limited Backtracking: Time(worst) = O(n^2) Space = O(n)
    如果我们知道从原始表达式中删除多少左括号和右括号以获得 valid 的表达式，那么我们将减少不需要的递归调用
    假设原始表达式是 1000 个字符，其中只有 3 个放错了位置的 ( 和 2 个放错了位置的 )。
    在前面的解决方案中，我们最终尝试删除左括号和右括号中的每一个，并尝试在末尾达到有效的表达式，但是其实我们只应尝试删除 3 个 ( 和 2 个 )
    所以我们需要不多不少的获取有效表达式必须删除的 ( 和 ) 的确切数目，以避免不必要的递归
 */
public class RemoveInvalidParentheses {
    private HashSet<String> validExpressions = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;

        // First, we find out the number of misplaced left and right parentheses.
        for (int i = 0; i < s.length(); i++) {
            // Simply record the left one.
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                // If we don't have a matching left, then this is a misplaced right, record it.
                right = (left == 0 ? right + 1 : right);

                // Decrement count of left parentheses because we have found a right which CAN be a matching one for a left.
                left = (left > 0 ? left - 1 : left);
            }
        }
        backtrack(s, 0, 0, 0, left, right, new StringBuilder());
        return new ArrayList<>(validExpressions);
    }

    private void backtrack(String s, int index, int lCount, int rCount, int lRemain, int rRemain, StringBuilder sb) {
        // If we reached the end of the string, just check if the resulting expression is valid or not
        // and also if we have removed the total number of left and right parentheses that we should have removed
        if (index == s.length()) {
            if (lRemain == 0 && rRemain == 0) {
                validExpressions.add(sb.toString());
            }
        } else {
            char ch = s.charAt(index);

            // The discard case. Note that here we have our pruning condition.
            // We don't recurse if the remaining count for that parenthesis is == 0.
            if ((ch == '(' && lRemain > 0) || (ch == ')' && rRemain > 0)) {
                backtrack(s, index + 1, lCount, rCount, lRemain - (ch == '(' ? 1 : 0), rRemain - (ch == ')' ? 1 : 0), sb);
            }

            // The Add case.
            sb.append(ch);
            // Simply recurse one step further if the current character is not a parenthesis
            if (ch != '(' && ch != ')') {
                backtrack(s, index + 1, lCount, rCount, lRemain, rRemain, sb);
            } else if (ch == '(') {
                backtrack(s, index + 1, lCount + 1, rCount, lRemain, rRemain, sb);
            } else if (rCount < lCount) {
                backtrack(s, index + 1, lCount, rCount + 1, lRemain, rRemain, sb);
            }
            // backtrack
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

/*  Backtracking: Time = O(n^2) Space = O(n)
    如果我们从左到右查看每个括号，一旦遇到 )，就应该有一个 ( 来匹配它。否则表达式将变为 invalid。如果 ( 的数目大于 ) 的数目则表达式也 invalid
    所以尝试从表达式中删除每个可能的括号，最后我们检查两件事：
        1. 表达式有效或无效
        2. 当前递归中删除的括号总数是否小于当前为止的全局最小值。
    对于每个括号，我们有两个选择：
        o 它可以被视为最终表达式的一部分
        o 它可以被忽略，也就是说，我们可以从最终表达式中删除它。

    private HashSet<String> validExpressions = new HashSet<>();
    private int minRemoved = Integer.MAX_VALUE;

    public List<String> removeInvalidParentheses(String s) {
        backtrack(s, 0, 0, 0, new StringBuilder(), 0);
        return new ArrayList<>(validExpressions);
    }

    private void backtrack(String s, int index, int lCount, int rCount, int lRemain, int rRemain, StringBuilder sb) {
        // If we reached the end of the string, just check if the resulting expression is valid or not
        // and also if we have removed the total number of left and right parentheses that we should have removed
        if (index == s.length()) {
            if (lRemain == 0 && rRemain == 0) {
                validExpression.add(sb.toString());
            }
        } else {
            char ch = s.charAt(index);

            // The discard case. Note that here we have our pruning condition.
            // We don't recurse if the remaining count for that parenthesis is == 0.
            if ((ch == '(' && lRemain > 0) || (ch == ')' && rRemain > 0)) {
                backtrack(s, index + 1, lCount, rCount, lRemain - (ch == '(' ? 1 : 0), rRemain - (ch == ')' ? 1 : 0), sb);
            }

            // The Add case.
            sb.append(ch);
            // Simply recurse one step further if the current character is not a parenthesis
            if (ch != '(' && ch != ')') {
                backtrack(s, index + 1, lCount, rCount, lRemain, rRemain, sb);
            } else if (ch == '(') {
                backtrack(s, index + 1, lCount + 1, rCount, lRemain, rRemain, sb);
            } else if (rCount < lCount) {
                backtrack(s, index + 1, lCount, rCount + 1, lRemain, rRemain, sb);
            }

            // backtrack
            sb.deleteCharAt(sb.length() - 1);
        }

        private void backtrack(String s, int index, int lCount, int rCount, StringBuilder sb, int removedCount) {
        // 递归出口
        if (index == s.length()) {
            // 如果当前表达式 valid
            if (lCount == rCount) {
                // If the current count of removed parentheses is <= the current minimum count
                if (removedCount <= minRemoved) {
                    // Remove the minimum number of invalid parentheses in order to make the input string valid
                    // 实现题目的这个要求，也就是只记录 remove 最少括号的表达式组合
                    if (removedCount < minRemoved) {
                        validExpressions.clear();
                        minRemoved = removedCount;
                    }
                    validExpressions.add(sb.toString());
                }
            }
        } else {
            char curChar = s.charAt(index);
            // 如果既不是 ( 也不是 )，直接加入
            if (curChar != '(' && curChar != ')') {
                sb.append(curChar);
                backtrack(s, index + 1, lCount, rCount, sb, removedCount);
            } else {
                // 1. discard 这个括号
                backtrack(s, index + 1, lCount, rCount, sb, removedCount + 1);
                // 2. 保留（加入）这个括号
                sb.append(curChar);
                // 如果加入的是左括号
                if (curChar == '(') {
                    backtrack(s, index + 1, lCount + 1, rCount, sb, removedCount);
                } else if (rCount < lCount) {
                    // 如果是右括号，只有在右括号数 < 左括号数时才继续 DFS
                    backtrack(s, index + 1, lCount, rCount + 1, sb, removedCount);
                }
            }
            // backtrack
            sb.deleteCharAt(sb.length() - 1);
        }
    }
 */