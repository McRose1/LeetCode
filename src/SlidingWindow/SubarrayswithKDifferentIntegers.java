package SlidingWindow;

/*  992. Subarrays with K Different Integers
    Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good
    if the number of different integers in that subarray is exactly K.
    (For example, [1, 2, 3, 1, 2] has 3 different integers: 1, 2, and 3.)
    Return the number of good subarrays of A.

    Example 1:
    Input: A = [1,2,1,2,3], K = 2
    Output: 7
    Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].

    Example 2:
    Input: A = [1,2,1,3,4], K = 3
    Output: 3
    Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].

    Note:
        1. 1 <= A.length <= 20000
        2. 1 <= A[i] <= A.length
        3. 1 <= K <= A.length
 */

/*  Sliding window(Two Pointers) / Reduction (LC 793): Time = O(n) Space = O(n)
    转换问题为 "Subarrays with at most K different nums"
    Ans = f(K) - f(K-1) # exact K different nums
 */
public class SubarrayswithKDifferentIntegers {
    public int subarraysWithKDistinct(int[] A, int K) {
        return helper(A, K) - helper(A, K - 1);
    }

    private int helper(int[] A, int K) {
        int[] count = new int[A.length + 1];
        int res = 0;
        int i = 0;
        for (int j = 0; j < A.length; j++) {
            if (count[A[j]] == 0) {
                K--;
            }
            count[A[j]]++;

            while (K < 0) {
                count[A[i]]--;
                if (count[A[i]] == 0) {
                    K++;
                }
                i++;
            }
            res += j - i + 1;
        }
        return res;
    }
}
