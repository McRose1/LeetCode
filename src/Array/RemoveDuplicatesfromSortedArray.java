package Array;

/*  26. Remove Duplicates from Sorted Array
    Given an sorted array nums,
    remove the duplicates in-place such that each element appear only once and return the new length.
    Do not allocate extra space for another array,
    you must do this by modifying the input array in-place with O(1) extra memory.

    Example 1:
    Given nums = [1,1,2],
    Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

    Example 2:
    Given nums = [0,0,1,1,1,2,2,3,3,4],
    Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

    Clarification:
    Confused why the returned value is an integer but your answer is an array?
    Note that the input array is passed in by reference,
    which means modification to the input array will be known to the caller as well.
 */
/*  Two Pointers: Time = O(n) Space = O(1).
    [0,0,1,1,1,2,2,3,3,4]
     s f
    [0,0,1,1,1,2,2,3,3,4]
     s   f
    [1,0,1,1,1,2,2,3,3,4]
       s   f
    [1,0,1,1,1,2,2,3,3,4]
       s     f
    [1,0,1,1,1,2,2,3,3,4]
       s       f
    [1,2,1,1,1,2,2,3,3,4]
         s       f
 */
public class RemoveDuplicatesfromSortedArray {
    public int removeDuplicates(int[] nums){
        if (nums.length == 0) return 0;
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++){
            if (nums[fast] != nums[slow]){
                slow++;
                // change the duplicate number to unique number
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }
}
