package Greedy;

/*  670. Maximum Swap
    Given a non-negative integer, you could swap two digits at most once to get the maximum valued number.
    Return the maximum valued number you could get.

    Example 1:
    Input: 2736
    Output: 7236
    Explanation: Swap the number 2 and the number 7.

    Example 2:
    Input: 9973
    Output: 9973
    Explanation: No swap.

    Note: The given number is in the range [0, 10^8]
 */

/*  Greedy: Time = O(n) Space = O(n)
    last[d] = i, the index i of the last occurrences of digit d
    if there is a larger digit in the future, we will swap it with the largest such digit;
    if there are multiple such digits, we will swap it with the one that occurs the latest.
 */
public class MaximumSwap {
    public int maximumSwap(int num) {
        char[] A = Integer.toString(num).toCharArray();

        int[] last = new int[10];
        for (int i = 0; i < A.length; i++) {
            last[A[i] - '0'] = i;
        }

        for (int i = 0; i < A.length; i++) {
            for (int d = 9; d > A[i] - '0'; d--) {
                if (last[d] > i) {
                    char temp = A[i];
                    A[i] = A[last[d]];
                    A[last[d]] = temp;
                    return Integer.parseInt(new String(A));
                }
            }
        }
        return num;
    }
}

/*  Brute Force: Time = O(n^2) Space = O(n)

        char[] A = Integer.toString(num).toCharArray();
        char[] ans = Arrays.copyOf(A, A.length);
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                char temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                for (int k = 0; k < A.length; k++) {
                    if (A[k] != ans[k]) {
                        if (A[k] > ans[k]) {
                            ans = Arrays.copyOf(A, A.length);
                        }
                        break;
                    }
                }
                A[j] = A[i];
                A[i] = temp;
            }
        }
        return Integer.parseInt(new String(ans));
 */