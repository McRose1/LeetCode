package Array;

/*  80. Remove Duplicates from Sorted Array 2 (LC26 follow up)
    Given a sorted array nums,
    remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
    Do not allocate extra space for another array,
    you must do this by modifying the input array in-place with O(1) extra memory.

    Example 1:
    Given nums = [1,1,1,2,2,3],
    Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

    Example 2:
    Given nums = [0,0,1,1,1,1,2,3,3],
    Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

    [0,0,1,1,1,1,2,3,3]
         s
         f
    [0,0,1,1,1,1,2,3,3]
           s
           f
    [0,0,1,1,1,1,2,3,3]
             s
             f
    [0,0,1,1,1,1,2,3,3]
             s f
    [0,0,1,1,1,1,2,3,3]
             s   f
    [0,0,1,1,2,1,2,3,3]
               s   f
    [0,0,1,1,2,3,2,3,3]
                 s   f
    [0,0,1,1,2,3,3,3,3]
                   s   f
 */

public class RemoveDuplicatesfromSortedArray2 {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;
        int slow = 2;
        for (int fast = 2; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow - 2]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
