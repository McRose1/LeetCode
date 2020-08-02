package Sort;

/*  354. Russian Doll Envelope
    You have a number of envelopes with width and heights given as a pair of integers (w, h).
    One envelope can fit into another if and only if both the width and height of one envelope is greater than
    the width and height of the other envelope.

    What is the maximum number of envelops can you Russian doll? (put one inside other)

    Note: Rotation is not allowed.

    Example:
    Input: [[5,4],[6,4],[6,7],[2,3]]
    Output: 3
    Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */

import java.util.Arrays;
import java.util.Comparator;

/*  Sort + DP: Time = O(nlogn) Space = O(n)
    1. Sort the array. Ascend on width and descend on height if width are same.
    2. Find the longest increasing subsequence based on height.
 */
public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length == 0) return 0;

        // sort on increasing in first dimension and decreasing in second
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[0] == arr2[0]) {
                    return arr2[1] - arr1[1];
                } else {
                    return arr1[0] - arr2[0];
                }
            }
        });
        // [[2,3],[5,4],[6,7],[6,4]]

        // LIS
        int[] dp = new int[envelopes.length];
        int count = 0;
        for (int[] envelope : envelopes) {
            int index = Arrays.binarySearch(dp, 0, count, envelope[1]);
            // index = -1 when not found
            if (index < 0) {
                index = -(index + 1);
            }
            dp[index] = envelope[1];
            // 这一步非常关键
            if (index == count) {
                count++;
            }
        }
        return count;
    }
}

/*  Binary Search（自己实现）和 #300 一模一样

        int[] height = new int[envelopes.length];
        for (int i = 0; i < height.length; i++) {
            height[i] = envelopes[i][1];
        }

        int[] dp = new int[envelopes.length + 1];
        int count = 1;
        dp[count] = height[0];
        for (int i = 1; i < height.length; i++) {
            if (height[i] > dp[count]) {
                dp[++count] = height[i];
            } else {
                int left = 1;
                int right = count;
                int pos = 0;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (dp[mid] < height[i]) {
                        pos = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                dp[pos + 1] = height[i];
            }
        }
 */

/*  DP: Time = O(n^2) 慢！

        int[] dp = new int[height.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < height.length; i++) {
            // 局部最大长度
            int len = 1;
            for (int j = 0; j < i; j++) {
                if (height[i] > height[j]) {
                    len = Math.max(len, dp[j] + 1);
                }
            }
            dp[i] = len;
            // 全局最大长度
            max = Math.max(max, dp[i]);
        }
        return max;
 */