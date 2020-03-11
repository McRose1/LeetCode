package Array;

/*  27
    Given an array nums and a value val,
    remove all instances of that value in-place and return the new length.
    Do not allocate extra space for another array,
    you must do this by modifying the input array in-place with O(1) extra memory.
    The order of elements can be changed.
    It doesn't matter what you leave beyond the new length.

    Example 1:
    Given nums = [3,2,2,3], val = 3,
    Your function should return length = 2, with the first two elements of nums being 2.

    Example 2:
    Given nums = [0,1,2,2,3,0,4,2], val = 2,
    Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.
 */
/*
    Two Pointers - when elements to remove are rare

    Consider cases where the array contains few elements to remove.
    nums = [1, 2, 3, 5, 4], val = 4
    nums = [4, 1, 2, 3, 5], val = 4

    Time = O(n). Both left and right traverse at most n steps.
    The number of assignment operations is equal to the number of elements to remove.
    Space = O(1)

    [0,1,2,2,3,0,4,2], val = 2
     l              r
    [0,1,2,2,3,0,4,2]
       l            r
    [0,1,2,2,3,0,4,2]
         l          r
    [0,1,2,2,3,0,4,2]
         l         r
    [0,1,4,2,3,0,4,2]
         l       r
    [0,1,4,2,3,0,4,2]
           l     r
    [0,1,4,0,3,0,4,2]
           l   r
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                // reduce array size by one
                right--;
            } else {
                left++;
            }
        }
        return right;
    }
}
/*
    Two Pointers:

    [0,1,2,2,3,0,4,2], val = 2
     s
     f
    [0,1,2,2,3,0,4,2]
       s
       f
    [0,1,2,2,3,0,4,2]
         s
         f
    [0,1,2,2,3,0,4,2]
         s f
    [0,1,2,2,3,0,4,2]
         s   f
    [0,1,3,2,3,0,4,2]
           s   f
    [0,1,3,0,3,0,4,2]
             s   f
    [0,1,3,0,4,0,4,2]
               s   f

        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;

    Time: O(n). Assume the array has a total of n elements, both left and right traverse at most 2n steps.
    Space: O(1).
 */