package DP;

/*  368. Largest Divisible Subset
    Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
    Si % Sj = 0 or Sj % Si = 0.
    If there are multiple solutions, return any subset is fine.

    Example 1:
    Input: [1,2,3]
    Output: [1,2] (of course, [1,3] will also be ok)

    Example 2:
    Input: [1,2,4,8]
    Output: [1,2,4,8]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*  DP: Time = O(n^2) Space = O(n)

 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;

        // Sort the original list in ascending order.
        Arrays.sort(nums);

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        if (max == 1) {
            res.add(nums[0]);
        } else {
            for (int i = n - 1; i >= 0; i--) {
                if (max == 0) {
                    break;
                }
                if (dp[i] == max) {
                    if (res.size() != 0 && res.get(res.size() - 1) % nums[i] != 0) {
                        continue;
                    }
                    res.add(nums[i]);
                    max--;
                }
            }
        }
        Collections.reverse(res);
        return res;
    }
}

/*  LC: Time = O(n^2) Space = O(n)

        int n = nums.length;
        if (n == 0) return new ArrayList<>();

        int[] dp = new int[n];

        Arrays.sort(nums);

        int maxSize = -1;
        int maxIdx = -1;

        for (int i = 0; i < n; i++) {
            int subSize = 0;

            for (int k = 0; k < i; k++) {
                if (nums[i] % nums[k] == 0 && subSize < dp[k]) {
                    subSize = dp[k];
                }
            }
            // Extend the found subset with the element itself
            dp[i] = subSize + 1;

            // We reuse this loop to obtain the largest subset size in order to prepare for the reconstruction of subset
            if (maxSize < dp[i]) {
                maxSize = dp[i];
                maxIdx = i;
            }
        }

        // Reconstruct the largest divisible subset
        LinkedList<Integer> subset = new LinkedList<>();
        int curSize = maxSize;
        int curTail = nums[maxIdx];
        for (int i = maxIdx; i >= 0; i--) {
            if (curSize == 0) {
                break;
            }

            if (curTail % nums[i] == 0 && curSize == dp[i]) {
                subset.addFirst(nums[i]);
                curTail = nums[i];
                curSize -= 1;
            }
        }
        return subset;
 */

/*  LC: Time = O(n^2) Space = O(n^2)

        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;

        // Container to keep the largest divisible subset that ends with each of the nums.
        List<List<Integer>> EDS = new ArrayList<>();
        for (int num : nums) {
            EDS.add(new ArrayList<>());
        }

        // Sort the original list in ascending order.
        Arrays.sort(nums);

        // Calculate all the values of EDS(X_i)
        for (int i = 0; i < n; i++) {
            List<Integer> maxSubset = new ArrayList<>();

            // Find the largest divisible subset of previous elements.
            for (int k = 0; k < i; k++) {
                if (nums[i] % nums[k] == 0 && maxSubset.size() < EDS.get(k).size()) {
                    maxSubset = EDS.get(k);
                }
            }

            // Extend the found subset with the element itself.
            EDS.get(i).addAll(maxSubset);
            EDS.get(i).add(nums[i]);
        }

        // Find the largest of EDS values
        for (int i = 0; i < n; i++) {
            if (res.size() < EDS.get(i).size()) {
                res = EDS.get(i);
            }
        }
        return res;
 */
