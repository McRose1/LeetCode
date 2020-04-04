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
 */
public class FindMinimuminRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;                          /* 左闭右闭区间，如果用右开区间则不方便判断右值 */
        while (left < right) {                      /* 循环不变式，如果left == right，则循环结束 */
            int mid = left + (right - left) / 2;    /* 地板除，mid更靠近left */
            if (nums[mid] > nums[right]) {          /* 中值 > 右值，最小值在右半边，收缩左边界 */
                left = mid + 1;                     /* 因为中值 > 右值，中值肯定不是最小值，左边界可以跨过mid */
            } else if (nums[mid] < nums[right]) {   /* 明确中值 < 右值，最小值在左半边，收缩右边界 */
                right = mid;                        /* 因为中值 < 右值，中值也可能是最小值，右边界只能取到mid处 */
            }
        }
        return nums[left];    /* 循环结束，left == right，最小值输出nums[left]或nums[right]均可 */
    }
}

/*  my version
    这题最关键的地方就是想清楚中值小于右值的时候，最小值是在左半边
    [5, 6, 7, 0, 1, 2, 3] -> [5, 6, 7, 0]
    [5, 6, 0, 1, 2, 3, 4] -> [5, 6, 0, 1]

        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return Math.min(nums[left], nums[right]);
    }
 */