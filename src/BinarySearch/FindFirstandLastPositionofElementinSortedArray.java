package BinarySearch;

/*  34. Find First and Last Position of Element in Sorted Array
    Given an array of integers nums sorted in ascending order,
    find the starting and ending position of a given target value.

    Your algorithm's runtime complexity must be in the order of O(logn).

    If the target is not found in the array, return [-1, -1].

    Example 1:
    Input: nums = [5,7,7,8,8,10], target = 8
    Output: [3,4]

    Example 2:
    Input: nums = [5,7,7,8,8,10], target = 6
    Output: [-1,-1]
 */

/*  Binary Search: Time = O(logn) Space = O(1)
    Modified binary search: do not terminate as soon as we find a match
    Instead, we continue to search until lo == hi and they contains some index at which target can be found.
    The other change is the introduction of the left parameter, which is a boolean indicating what to do
    in the event that target == nums[mid]; if left is true, then we "recurse" on the left subarray on ties.
    Otherwise, we go right.
 */
public class FindFirstandLastPositionofElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that 'leftIdx' is within the array bounds and that 'target' is actually in 'nums'.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;

        return targetRange;
    }
    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target || (left && target == nums[mid]) ) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}

/*  Binary Search 2: Time = O(logn) space = O(1)
    两次 binary search
    一次找起点
    一次找终点

    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        // corner case
        if (nums == null || nums.length == 0) return res;

        res[0] = findFirst(nums, target);
        res[1] = findLast(nums, target);

        return res;
    }
    private int findFirst(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) {
                hi = mid;       // 往左边找 index 更小的 target
            } else {
                lo = mid;
            }
        }
        if (nums[lo] == target) return lo;      // 尽可能取到小的那个 index
        else if (nums[hi] == target) return hi;
        return -1;
    }

    private int findLast(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] <= target) {
                lo = mid;       // 往右边找 index 更小的 target
            } else {
                hi = mid;
            }
        }
        if (nums[hi] == target) return hi;      // 尽可能取到大的那个 index
        else if (nums[lo] == target) return lo;
        return -1;
    }
 */

/*  my version

    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};
        if (nums == null || nums.length == 0) return targetRange;

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that 'leftIdx' is within the array bounds and that 'target' is actually in 'nums'.
        if (leftIdx == -1) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false);

        return targetRange;
    }
    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target || (left && target == nums[mid]) ) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        if (left && nums[lo] == target) return lo;
        else if (left && nums[hi] == target) return hi;
        else if (!left && nums[hi] == target) return hi;
        else if (!left && nums[lo] == target) return lo;
        else return -1;
    }
 */

/*  Linear Scan (Brute Force): Time = O(n) Space = O(1)

        int[] targetRange = {-1, -1};

        // find the index of the leftmost appearance of 'target'.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                targetRange[0] = i;
                break;
            }
        }

        // if the last loop did not find any index, then there is no valid range and we return [-1, -1].
        if (targetRange[0] == -1) {
            return targetRange;
        }

        // find the index of the rightmost appearance of 'target' (by reverse iteration). it is guaranteed to appear.
        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] == target) {
                targetRange[1] = j;
                break;
            }
        }
        return targetRange;
 */