package TwoPointers;

/*  88. Merge Sorted Array
    Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array

    Node:
    The number of elements initialized in nums1 and nums2 are m and n respectively
    You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2

    Example:
    Input:
    nums1 = [1, 2, 3, 0, 0, 0], m = 3
    nums2 = [2, 5, 6], n =3
    Output: [1, 2, 2, 3, 5, 6]
 */
//  Two Pointers: Time = O(n) Space = O(1)
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[index] = nums1[i];
                i--;
            } else {
                nums1[index] = nums2[j];
                j--;
            }
            index--;
        }
        while (j >= 0) {
            nums1[index] = nums2[j];
            j--;
            index--;
        }
    }
}

/*  my version

        int p1 = m - 1;
        int p2 = n - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (nums2[p2] >= nums1[p1]) {
                nums1[p1 + p2 + 1] = nums2[p2];
                p2--;
            } else {
                nums1[p1 + p2 + 1] = nums1[p1];
                p1--;
            }
        }
        while (p2 >= 0) {
            nums1[p2] = nums2[p2];
            p2--;
        }
 */