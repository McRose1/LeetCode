package BinarySearch;

/*  153. Find Minimum in Rotated Sorted Array
    Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
    (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
    Find the minimum element.
    You may assume no duplicate exists in the array.

    Example 1:
    Input: [3,4,5,1,2]
    Output: 1

    Example 2:
    Input: [4,5,6,7,0,1,2]
    Output: 0

    Hint:
    You an divide the search space into two and see which direction to go.
    1. All the elements to the left of inflection point > first element of the array.
    2. All the elements to the right of inflection point < first element of the array.
 */

/*  Binary Search: Time = O(logn) Space = O(1)
    [increasing ... MAX MIN increasing ...]
    1. Find the mid element of the array.
    2. If mid element > first element of array this means that we need to look for the inflection point on the right of mid.
    3. If mid element < first element of array this means that we need to look for the inflection point on the left of mid.
    4. We stop our search when we find the inflection point, when either of the two conditions is satisfied:
    nums[mid] > nums[mid + 1] Hence, mid+1 is the smallest.
    nums[mid - 1] > nums[mid] Hence, mid is the smallest.
 */
public class FindMinimuminRotatedSortedArray {
    public int findMin(int[] nums) {
        // If the list has just one element then return that element.
        if (nums.length == 1) return nums[0];

        // Initializing left and right pointers.
        int left = 0, right = nums.length - 1;

        // If the last element is greater than the first element then there is no rotation.
        // Hence the smallest element is first element.
        if (nums[right] > nums[0]) return nums[0];

        // Binary search way
        while (right >= left) {
            // Find the mid element
            int mid = left + (right - left) / 2;

            // If the mid element is greater than its next element then mid+1 element is the smallest
            // This point would be the point of change. From higher to lower value.
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }

            // If the mid element is lesser than its previous element then mid element is the smallest
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }

            // If the mid elements value is greater than the 0th element this means
            // the least value is still somewhere to the right as we are still dealing with elements greater than nums[0]
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                // if nums[0] is greater than the mid value then this means the smallest value is somewhere to the left
                right = mid - 1;
            }
        }
        return -1;
    }
}
