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

    Constraints:
    0 <= nums.length <= 10^5
    -10^9 <= nums[i] <= 10^9
    nums is a non decreasing array.
    -10^9 <= target <= 10^9
 */

/*  Binary Search（左右边界分开版）: Time = O(logn) space = O(1)
    两次 binary search
    一次找起点
    一次找终点
 */
public class FindFirstandLastPositionofElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        // corner case
        if (nums == null || nums.length == 0) return res;

        res[0] = findLeft(nums, target);
        if (res[0] == -1) return res;

        res[1] = findRight(nums, target);

        return res;
    }
    private int findLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (left == nums.length || nums[left] != target) {
            return -1;
        } else {
            return left;
        }
    }

    private int findRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                // 等于的时候也是 left = mid + 1 所以最后我们需要 return left - 1
                left = mid + 1;
            } else {
                // 左闭右开，所以 nums[mid] > target 时 right = mid 而不是 right = mid - 1
                right = mid;
            }
        }
        return left - 1;
    }
}

/*  Binary Search（左右边界融合版）: Time = O(logn) Space = O(1)
    Modified binary search: do not terminate as soon as we find a match
    Instead, we continue to search until lo == hi and they contains some index at which target can be found.
    The other change is the introduction of the left parameter, which is a boolean indicating what to do
    in the event that target == nums[mid]; if left is true, then we "recurse" on the left subarray on ties.
    Otherwise, we go right.

    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // leftIdx = nums.length -> target 可能比 nums 里的最大数还要大，此时会导致 nums[leftIdx] 越界，所以需要先判断
        // nums[leftIdx] != target -> 在不越界的前提下如果找不到 target
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return res;
        }

        res[0] = leftIdx;
        // 因为 nums[mid] == target 时 lo = mid + 1，也就是 mid = lo - 1
        res[1] = extremeInsertionIndex(nums, target, false) - 1;

        return res;
    }
    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        // 左闭右开
        int hi = nums.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            // nums[mid] > target -> 右端点向左移，因为左闭右开，所以 hi = mid 而不是 hi = mid - 1
            // left && nums[mid] == target -> 如果是找左边界，找到 target 以后还要继续左移
            if (nums[mid] > target || (left && nums[mid] == target) ) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
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
    从左往右扫找到起始点
    从右往左扫找到终止点

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