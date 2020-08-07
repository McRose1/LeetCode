package DP;

/*  718. Maximum Length of Repeated Subarray
    Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

    Example:
    Input:
    A: [1,2,3,2,1]
    B: [3,2,1,4,7]
    Output: 3
    Explanation:
    The repeated subarray with maximum length is [3, 2, 1].

    Note:
    1. 1 <= len(A), len(B) <= 1000
    2. 0 <= A[i], B[i] < 100

    Hint:
    Use DP. dp[i][j] will be the answer for inputs A[i:], B[j:].
 */

/*  DP: Time = O(n^2) Space = O(n^2)

 */
public class MaximumLengthofRepeatedSubarray {
    public int findLength(int[] A, int[] B) {
        int a = A.length;
        int b = B.length;
        int[][] dp = new int[a + 1][b + 1];
        int res = 0;
        for (int i = a - 1; i >= 0; i--) {
            for (int j = b - 1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
