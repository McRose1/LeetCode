package Stack;

/*  678. Valid Parenthesis String
    Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid.
    We define the validity of a string by these rules:
    1. Any left parenthesis '(' must have a corresponding right parenthesis ')'.
    2. Any right parenthesis ')' must have a corresponding left parenthesis '('.
    3. Left parenthesis '(' must go before the corresponding right parenthesis ')'.
    4. '*' could ve treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
    5. An empty string is also valid.

    Example 1:
    Input: "()"
    Output: True

    Example 2:
    Input: "(*)"
    Output: True

    Example 3:
    Input: "(*))"
    Output: True

    Note: The string size will be in the range [1, 100].
 */
/*  二维 DP (Bottom-up): Time = O(n^3) Space = O(n^2)
    初始化：dp[i][i] = true if s[i] == '*'

    Case 1:
    L = (,*     R = ),*
    L LLLRRR R -> LLLLRRRR
    if dp[i+1][j-1] and s[i] in L and s[j] in R
        dp[i][j] = true

    Case 2: Try every break point
    L....RL....R
         k
    for k in i..j-1
        if dp[i][k] && dp[k+1][j]
            dp[i][j] = true
 */
public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        int n = s.length();
        if (n == 0) return true;
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                dp[i][i] = true;
            }
        }
        for (int size = 2; size <= n; size++) {
            for (int i = 0; i <= n - size; i++) {
                int j = i + size - 1;
                // 如果满足 case 1
                if ((s.charAt(i) == '(' || s.charAt(i) == '*') &&
                        (s.charAt(j) == ')' || s.charAt(j) == '*') &&
                        (size == 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    // 是有可能出现 dp[i + 1][j - 1] == false，但是 dp[i][j] == true 的情况的
                    // )*( -> ()*()，所以还需要考虑 case 2
                    continue;
                }
                // 如果不满足 case 1，还是有可能 valid：)*( -> ()*()
                // 但是满足 case 2：s[i-k] = ()*; s[k+1 - j] = ()
                for (int k = i; k < j; k++) {
                    if (dp[i][k] && dp[k + 1][j]) {
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
}

/*  Greedy: Time = O(n) Space = O(1)
    '*' 的引入让我们对左括号的数量有了一个可能值的区间：
    [lo, hi]:
    "(" -> [1, 1]
    "(*" -> [0, 2]
    "(**" -> [0, 3]
    "(***" -> [0, 4]
    "(***)" -> [0, 3]
    lo, hi 分别为可能的最小和最大左括号数。
    如果我们遇到左括号，则 lo++；否则就是右括号和'*'，右括号铁定 lo--，而遵循 greedy 我们要最小所有遇到'*'也将其转换为右括号处理
    如果我们没有遇到右括号，就是左括号和'*'，左括号铁定 hi++，遇到'*'由于我们要最大所以将其看做左括号；遇到右括号肯定是 hi--
    当 hi < 0，说明此时的右括号总数已经大于（左括号加上*的个数）：(*))) -> 不管后面怎么用左括号弥补都一定是 invalid
    遍历到最后需要检查 lo 是否等于 0，因为如果大于 0 说明有左括号在最右侧无法匹配：(*(

        int lo = 0, hi = 0;
        for (char c : s.toCharArray()) {
            lo += c == '(' ? 1 : -1;
            hi += c != ')' ? 1 : -1;
            if (hi < 0) break;
            lo = Math.max(lo, 0);
        }
        return lo == 0;
 */

/*  Stack

 */

/*  正向扫一遍，反向扫一遍

 */

/*  Brute Force (TLE): Time = O(n * 3^k) Space = O(1)
    For each *
    o delete/ignore it
    o change to (
    o change to )

    boolean ans;
    public boolean checkValidString(String s) {
        solve(new StringBuilder(s), 0);
        return ans;
    }

    public void solve(StringBuilder sb, int i) {
        // 递归的出口，此时的字符串里已经没有 '*'
        if (i == sb.length()) {
            ans |= valid(sb);
        } else if (sb.charAt(i) == '*') {
            // 将 '*' 依次替换成 '(', ')', ' '
            for (char c : "() ".toCharArray()) {
                sb.setCharAt(i, c);
                solve(sb, i + 1);
                if (ans) return;
            }
            // sb.setCharAt(i, '*');
        } else {
            solve(sb, i + 1);
        }
    }

    public boolean valid(StringBuilder sb) {
        int bal = 0;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') bal++;
            if (c == ')') bal--;
            if (bal < 0) break;
        }
        return bal == 0;
    }
 */
