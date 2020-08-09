package TwoPointers;

/*  905. Sort Array By Parity
    Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
    You may return any answer array that satisfies this condition.

    Example:
    Input: [3,1,2,4]
    Output: [2,4,3,1]
    The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.

    Note:
    1. 1 <= A.length <= 5000
    2. 0 <= A[i] <= 5000
 */

/*  In-place (Quick Sort): Time = O(n) Space = O(1)
    维护两个指针 i 和 j，循环保证每刻小于 i 的变量都是偶数（也就是 A[k] % 2 == 0 当 k < i），所有大于 j 的都是奇数。
    所以， 4 种情况针对 (A[i] % 2, A[j] % 2)：
        o 如果是 (0, 1)，那么万事大吉 i++ 并且 j--。
        o 如果是 (1, 0)，那么交换两个元素，然后继续。
        o 如果是 (0, 0)，那么说明 i 位置是正确的，只能 i++。
        o 如果是 (1, 1)，那么说明 j 位置是正确的，只能 j--。
 */
public class SortArrayByParity {
    public int[] sortArrayByParity(int[] A) {
        int i = 0;
        int j = A.length - 1;
        while (i < j) {
            // i 所在位置是奇数，j 所在位置是偶数，swap
            if (A[i] % 2 > A[j] % 2) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
            // i 所在位置是偶数，右移
            if (A[i] % 2 == 0) {
                i++;
            }
            // j 所在位置是奇数，左移
            if (A[j] % 2 == 1) {
                j--;
            }
        }
        return A;
    }
}

/*  One Pass: Time = O(n) Space = O(n)

        int[] res = new int[A.length];
        int i = 0, j = A.length - 1;
        for (int idx = 0; idx < A.length; idx++) {
            if (A[idx] % 2 == 0) {
                res[i++] = A[idx];
            } else {
                res[j--] = A[idx];
            }
        }
        return res;
 */

/*  Two Pass: Time = O(n) Space = O(n)

        int[] res = new int[A.length];
        int i = 0;
        for (int num : A) {
            if (num % 2 == 0) {
                res[i++] = num;
            }
        }
        for (int num : A) {
            if (num % 2 == 1) {
                res[i++] = num;
            }
        }
        return res;
 */

/*  Sorting: Time = O(nlogn) Space = O(n)
    按照模 2 的结果排序。

        Integer[] B = new Integer[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        Arrays.sort(B, (a, b) -> Integer.compare(a % 2, b % 2));
        for (int i = 0; i < A.length; i++) {
            A[i] = B[i];
        }
        return A;
 */