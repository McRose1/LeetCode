package BinarySearch;

/*  349. Insertion of Two Arrays
    Given two arrays, write a function to compute their intersection.

    Example 1:
    Input: nums1 = [1,2,2,1], nums2 = [2,2]
    Output: [2]

    Example 2:
    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    Output: [9,4]

    Note:
    Each element in the result must be unique.
    The result can be in any order.
 */

import java.util.Arrays;
import java.util.HashSet;

//  Binary Search: Time = O(nlon) Space = O(n)
public class IntersectionofTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (Integer num : nums1) {
            if (binarySearch(nums2, num)) {
                set.add(num);
            }
        }
        int idx = 0;
        int[] res = new int[set.size()];
        for (Integer num : set) {
            res[idx++] = num;
        }
        return res;
    }
    public boolean binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return true;
            else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target || nums[end] == target) return true;
        return false;
    }
}

/*  Two Sets (Recursion): Time = O(n + m) Space = O(n + m)
    The idea is to convert arrays into sets, and then iterate ove the smallest set
    checking the presence of each element in the larger set.

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) set2.add(n);

        if (set1.size() < set2.size()) return set_intersection(set1, set2);
        else return set_intersection(set2, set1);
    }

    public int[] set_intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        int[] output = new int[set1.size()];
        int idx = 0;
        for (Integer s : set1) {
            if (set2.contains(s)) output[idx++] = s;
        }
        // [4,9,5] & [9,4,9,8,4] -> [9,4] not [9,4,0]
        return Arrays.copyOf(output, idx);
    }
 */

/*  Two Sets (Iteration): Time = O(n) Space = O(n)

        HashSet<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }

        HashSet<Integer> intersection = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                intersection.add(i);
            }
        }
        int[] res = new int[intersection.size()];
        int index = 0;
        for (int i : intersection) {
            res[index++] = i;
        }
        return res;
 */

/*  Built-in Set Intersection: Time = O(n + m) Space = O(n + m)
    There are built-in intersection facilities, which provide O(n + m) time complexity in the average case
    and O(n x m) time complexity in the worst case.

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) set2.add(n);

        set1.retainAll(set2);

        int[] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) output[idx++] = s;
        return output;
    }
 */

/*  One Set (Array.sort): Time = O(nlogn) Space = O(n)

        HashSet<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Array2.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] output = new int[set.size()];
        int idx = 0;
        for (int s : set) output[idx++] = s;
        return output;
 */