package BinarySearch;

import Array.PascalsTriangle;

import java.util.Map;

/*  4. Median of Two Sorted Arrays
    There are two sorted arrays nums1 and nums2 of size m and n respectively.
    Find the median of the two sorted arrays. The overall run time complexity should be O(log(m+n)).
    You may assume nums1 and nums2 cannot be both empty.

    Example 1:
    nums1 = [1, 3]
    nums2 = [2]
    The median is 2.0

    Example 2:
    nums1 = [1, 2]
    nums2 = [3, 4]
    The median is (2 + 3)/2 = 2.5
 */
/*  Binary Search: Time = O(log(min(m,n))) Space = O(1)
    A:  1   3  |  5   7
           leftA  rightA
    B:  0   2   4  |  6   8   10
             leftB  rightB
    0   1   2   3   4  |  5   6   7   8   10
    leftA <= rightB
    leftB <= rightA
 */
public class MedianofTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {                // to ensure m<=n
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            // 只要知道半边的一半长度，就能知道另外半边的一半长度
            int j = halfLen - i;
            if (i < iMax && nums2[j - 1] > nums1[i]) {
                iMin = i + 1;           // i is too small
            } else if (i > iMin && nums1[i - 1] > nums2[j]) {
                iMax = i - 1;           // i is too big
            } else {                    // i is perfect
                int maxLeft;
                if (i == 0) {   // A 分成的 leftA（空集）和 rightA（A 的全部）所以 leftPart = leftA（空）+ leftB -> maxLeft = B[j-1]
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {    // B 分成的 leftB（空集）和 rightB（B 的全部）所以 leftPart = leftA + leftB（空） -> maxLeft = A[j-1]
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) {     // 奇数，中位数正好是 maxLeft
                    return maxLeft;
                }
                // 偶数
                int minRight;
                if (i == m) {   // A 分成的 leftA（A 的全部）和 rightA（空集）所以 leftPart = leftA（空集）+ leftB -> minRight = B[j]
                    minRight = nums2[j];
                } else if (j == n) {    // B 分成的 leftB（B 的全部）和 rightB（空集）所以 rightPart = rightA + rightB（空集） -> minRight = A[i]
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
