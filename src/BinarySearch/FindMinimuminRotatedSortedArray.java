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
    You can divide the search space into two and see which direction to go.
    1. All the elements to the left of inflection point > first element of the array.
    2. All the elements to the right of inflection point < first element of the array.
 */

/*  Binary Search: Time = O(logn) Space = O(1)
 */
public class FindMinimuminRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0;
        // 左闭右闭区间，如果用右开区间则不方便判断右值
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 中值 > 右值，最小值在右半边，收缩左边界
            if (nums[mid] > nums[right]) {
                // 因为中值 > 右值，中值肯定不是最小值，左边界可以跨过mid
                left = mid + 1;
            } else {
                // 因为中值 < 右值，中值也可能是最小值，右边界只能取到mid处
                right = mid;
            }
        }
        // 循环结束，left == right，最小值输出nums[left]或nums[right]均可
        return nums[left];
    }
}

/*  LC

        // If the list has just 1 element then return that element
        if (nums.length == 1) return nums[0];

        // initializing left and right pointers
        int left = 0, right = nums.length - 1;

        // if the last element is greater than the first element then there is no rotation
        // e.g. 1 < 2 < 3 < 4 < 5 < 7. Already sorted array
        // Hence the smallest element is first element. A[0]
        if (nums[right] > nums[0]) return nums[0];

        // Binary search
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // if the mid element is greater than its next element then mid+1 element is the smallest
            // This point would be the point of change. From higher to lower value.
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }

            // if the mid element is lesser than its previous element then mid element is the smallest
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }

            // if the mid element value is greater than 0th element
            // this means the least value is still somewhere to the right as we are still dealing with elements greater then nums[0]
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                // if nums[0] is greater than the mid value then this means the smallest value is somewhere to the left
                right = mid - 1;
            }
        }
        return -1;
 */

/*  my version
    这题最关键的地方就是想清楚中值小于右值的时候，最小值是在左半边
    [5, 6, 7, 0, 1, 2, 3] -> [5, 6, 7, 0]
    [5, 6, 7, 8, 1, 2, 3] -> [8, 1, 2, 3]

        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            // 中值大于最右值，说明中值此时在左半部分，左半部分最小也比右半部分最大来得大，所以最小值在右边
            if (nums[mid] > nums[right]) {
                left = mid;
            }
            // 中值小于等于最右值，说明中值此时在右半部分，右半部分单调递增，所以最小值在左边
            else {
                right = mid;
            }
        }
        return Math.min(nums[left], nums[right]);
    }
 */