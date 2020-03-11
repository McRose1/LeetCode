package BinarySearch;

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

/*  Binary Search: Time = O(nlogn) Space = O(n)
    1. Sort the array. Ascend on width and descend on height if width are same.
    2. Find the longest increasing subsequence based on height.
 */
public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length == 0) return 0;
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
        int dp[] = new int[envelopes.length];
        int count = 0;
        for (int[] envelope : envelopes) {
            int index = Arrays.binarySearch(dp, 0, count, envelope[1]);
            if (index < 0) {
                index = -(index + 1);
            }
            dp[index] = envelope[1];
            if (index == count) {
                count++;
            }
        }
        return count;
    }
}
