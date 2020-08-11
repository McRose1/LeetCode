package DP;

/*  91. Decode Ways
    A message containing letters from A-Z is being encoded to numbers using the following mapping:
    'A' -> 1
    'B' -> 2
    ...
    'Z' -> 26
    Given a non-empty string containing only digits, determine the total number of ways to decode it.

    Example 1:
    Input: "12"
    Output: 2
    Explanation: It could be decoded as "AB" (1 2) or "L" (12).

    Example 2:
    Input: "226"
    Output: 3
    Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */

/*  DP (Bottom-Up): Time = O(n) Space = O(1)
    dp[i] to decode s[0] -> s[i]
 */
public class DecodeWays {
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        // 第一个元素不能为 0
        if (s.charAt(0) == '0') {
            return 0;
        }
        dp[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            // 1-9
            if (oneDigit >= 1) {
                dp[i] += dp[i - 1];
            }
            // 10-26
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }
}

/*  Recursion with Memoization (Top-Down DP): Time = O(n) Space = O(n)

    public int numDecodings(String s) {
        int[] memo = new int[s.length() + 1];
        Arrays.fill(memo, -1);
        return helper(s.toCharArray(), 0, memo);
    }

    private int helper(char[] arr, int idx, int[] memo) {
        // 记忆化
        if (memo[idx] != -1) {
            return memo[idx];
        }
        // 遍历完毕
        if (idx == arr.length) {
            memo[idx] = 1;
            return 1;
        }
        int ways = 0;
        // 取前面一个
        // 1 - 9
        if (arr[idx] != '0') {
            ways += helper(arr, idx + 1, memo);
        }
        // 取前面两个
        if (isValid(arr, idx)) {
            ways += helper(arr, idx + 2, memo);
        }
        memo[idx] = ways;
        return ways;
    }

    // check if array[start] and array[start + 1] forms a valid encoding
    private boolean isValid(char[] arr, int start) {
        if (start + 1 >= arr.length) return false;

        // 10 - 19
        if (arr[start] == '1') {
            return true;
        }

        // 21 - 26
        if (arr[start] == '2' && arr[start + 1] - '6' <= 0) {
            return true;
        }
        return false;
    }
 */
