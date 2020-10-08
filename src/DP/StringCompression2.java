package DP;

/*  1531. String Compression 2
    Run-length encoding is a string compression method that works by replacing consecutive identical characters
    (repeated 2 or more times) with the concatenation of the character and the number making the count of the characters (length of the run).
    For example, to compress the string "aabccc" we replace "a" by "a2" and replace "ccc" by "c3".
    Thus the compressed string becomes "a2bc3".
    Notice that in this problem, we are not adding '1' after single characters.
    Given a string s and an integer k.
    You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.
    Find the minimum length of the run-length encoded version of s after deleting at most k characters.

    Example 1:
    Input: s = "aaabcccd", k = 2
    Output: 4
    Explanation: Compressing s without deleting anything will give us "a3bc3d" of length 6.
    Deleting any of the characters 'a' or 'c' would at most decrease the length of the compressed string to 5,
    for instance delete 2 'a' then we will have s = "abcccd" which compressed is abc3d.
    Therefore, the optimal way is to delete 'b' and 'd',
    then the compressed version of s will be "a3c3" of length 4.

    Example 2:
    Input: s = "aabbaa", k = 2
    Output: 2
    Explanation: If we delete both 'b' characters, the resulting compressed string would be "a4" of length 2.

    Example 3:
    Input: s = "aaaaaaaaaaa", k = 0
    Output: 3
    Explanation: Since k is zero, we cannot delete anything. The compressed string is "a11" of length 3.

    Constraints:
        o 1 <= s.length <= 100
        o 0 <= k <= s.length
        o s contains only lowercase English letters.

    Hint:
    1. Use dp.
    2. The state of the DP can be the current index and the remaining characters to delete.
    3. Having a prefix sum for each character can help you determine for a certain character c in some specific range,
    how many characters you need to delete to merge all occurrences of c in that range.
 */

import java.util.Arrays;

/*  二维 DP: Time = O(n^2k) Space = O(n*k)
    Can we do better? Do we really need a 4 dimensional state?
    We need last/running length because the unsolved subproblem rely on that.
    What if we remove the dependency by making one group of same characters at a time?
    dp(i, k) := min length to encode s[i:] with at most k deletions.
    Transitions: keep / delete
    delete: dp(i, k) = dp(i+1, k-1)
    keep: use s[i] as an anchor, scan from i to n, for s[i~j], we need to remove all other characters other than s[i] to form a group.
    dp(i, k) = encode_len(sum(s[i~j] == s[i])) + dp(j+1, k - (s[i~j] != s[i]))
    Subproblems: O(n*k), each takes O(n) time
 */
public class StringCompression2 {
    public int getLengthOfOptimalCompression(String s, int k) {
        chars = s.toCharArray();
        n = s.length();
        memo = new int[n][k + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return helper(0, k);
    }

    private int[][] memo;
    private char[] chars;
    private int n;

    private int helper(int i, int k) {
        if (k < 0) {
            return n;
        }
        // done or delete all
        if (i + k >= n) {
            return 0;
        }
        int res = memo[i][k];
        if (res != -1) {
            return res;
        }
        // delete chars[i]
        res = helper(i + 1, k - 1);

        int len = 0;
        int same = 0;
        int diff = 0;
        for (int j = i; j < n && diff <= k; j++) {
            if (chars[j] == chars[i]) {
                same++;
                // 处理进位
                if (same <= 2 || same == 10 || same == 100) {
                    len++;
                }
            } else {
                diff++;
            }
            res = Math.min(res, len + helper(j + 1, k - diff));
        }
        memo[i][k] = res;
        return res;
    }
}

/*  四维 DP
    Optimal decision problem -> DP
    Similar to LC 546. Remove Boxes State
    State:
    1. i: current index
    2. k: # of chars to remove
    3. p: previous character
       l: Running length of the last character
    dp(state) := min len of s[i:] encoded, given the current state.

    Transition for dp(i, k, p, l)
    1. Delete = dp(i+1, k-1, p, l)
    2. Keep
       if s[i] == p := carry + dp(i+1, k, p, l+1)
       else = 1 + dp(i+1, k, s[i], l)
 */