package DP;

/*  546. Remove Boxes
    Given several boxes with different colors represented by different positive numbers.
    You may experience several rounds to remove boxes until there is no box left.
    Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
    Find the maximum points you can get.

    Example:
    Input: boxes = [1,3,2,2,2,3,4,3,1]
    Output: 23
    Explanation:
    [1, 3, 2, 2, 2, 3, 4, 3, 1]
    ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
    ----> [1, 3, 3, 3, 1] (1*1=1 points)
    ----> [1, 1] (3*3=9 points)
    ----> [] (2*2=4 points)

    Constraints:
        o 1 <= boxes.length <= 100
        o 1 <= boxes[i] <= 100
 */

/*  三维 DP: Time = O(n^4) Space = O(n^3)
    dp[i][j][k] := max score of subarray b[i]~b[j] if there are k boxes that have the same color as b[j] following b[j]
    Those k boxes are from box[+1] ~ box[n], to simulate boxes with other colors are removed first.
    "ABACA", dp[0][0][2] = dfs("A|AA")=9 # B,C are removed
    base case: 0, if j > i
    Transition:
    dp[i][j][k] = dp[i][j-1][0] + (k+1)^2               # case 1
                  dp[i][p][k+1] + dp[p+1][j-1][0]       # case 2
    Case 1: drop box[j], remove k+1 boxes.
    Case 2: Try all breakpoints p, attach a[j] to a[p], i <= p < j, box[p] == box[]
    res: dp[0][n-1][0]
 */
public class RemoveBoxes {
    public int removeBoxes(int[] boxes) {
        memo = new int[100][100][100];
        return helper(boxes, 0, boxes.length - 1, 0);
    }

    private int[][][] memo;

    private int helper(int[] boxes, int l, int r, int k) {
        if (l > r) {
            return 0;
        }
        if (memo[l][r][k] != 0) {
            return memo[l][r][k];
        }
        // 优化
        while (l < r && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }
        memo[l][r][k] = helper(boxes, l, r - 1, 0) + (k + 1) * (k + 1);
        for (int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                memo[l][r][k] = Math.max(memo[l][r][k], helper(boxes, l, i, k + 1) + helper(boxes, i + 1, r - 1, 0));
            }
        }
        return memo[l][r][k];
    }
}
