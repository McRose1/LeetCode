package BinarySearch;

/*  162. Find Peak Element
    A peak element is an element that is greater than its neighbors.
    Given an input array nums, nums[i] ≠ nums[i+1], find a peak element and return its index.
    The array may contain multiple peaks, in tha case return the index of any one of the peaks is fine.
    You may imagine that nums[-1] = nums[n] = -∞.

    Example 1:
    Input: nums = [1,2,3,1]
    Output: 2

    Example 2:
    Input: nums = [1,2,1,3,5,6,4]
    Output: 1 or 5

    Note: Your solution should be in logarithmic complexity.
 */

//  Iterative Binary Search: Time = O(logn) Space = O(1)
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

/*  Linear Scan: Time = O(n) Space = O(1)

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
 */

/*  Recursive Binary Search: Time = O(logn) Space = O(logn)
    If mid happens to be lying in a descending sequence of numbers,
    or a local falling slope (found by comparing nums[i] to its right neighbor),
    it means that the peak will always lie towards the left of this element.
    The same as ascending ...
    In this way, we keep on reducing the search space till we eventually reach a state
    where only one element is remaining in the search space. This single element is the peak element.

    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }
    public int search(int[] nums, int l, int r) {
        if (l == r) {
            return l;
        }
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1]) {
            return search(nums, l, mid);
        }
        return search(nums, mid + 1, r);
    }
 */