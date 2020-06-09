package TwoPointers;

/*  287. Find the Duplicate Number
    Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
    prove that at least one duplicate number must exist.
    Assume that there is only one duplicate number, find the duplicate one.

    Example 1:
    Input: [1,3,4,2,2]
    Output: 2

    Example 2:
    Input: [3,1,3,4,2]
    Output: 3

    Note:
    You must not modify the array (assume the array is read only).
    You must use only constant, O(1) extra space.
    Your runtime complexity should be less than O(n^2).
    There is only one duplicate number in the array, but it could be repeated more than once.
 */

/*  Two Pointer + Cycle Detection: Time = O(n) Space = O(1)
    Interpret nums such that for each pair of index i and value vi, the "next" value vj is at index vi,
    we can reduce this problem to cycle detection.
    Since 0 cannot appear as a value in num, nums[0] cannot be part of the cycle.
 */
public class FindtheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners (cycle exists)
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Find the "entrance" to the cycle
        int ptr1 = nums[0];
        int ptr2 = slow;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }
        return ptr1;
    }
}

/*  Sorting: Time = O(nlogn) Space = O(1)

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
 */

/*  Set: Time = O(n) Space = O(n)

        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }
        return -1;
 */