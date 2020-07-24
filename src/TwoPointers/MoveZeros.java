package TwoPointers;

/*  283. Move Zeroes
    Given an array nums, write a function to move all 0's to the end of it
    while maintaining the relative order of the non-zero elements

    Example:
    Input: [0,1,0,3,12]
    Output: [1,3,12,0,0]

    Note:
    1. You must do this in-place without making a copy of the array.
    2. Minimize the total number of operations.

    Hint 1:
    In-place means we should not allocating any space for extra array. But we are allowed to modify the existing array.
    Hint 2:
    A two-pointer approach could be helpful here.
    The idea would be to have one pointer for iterating the array and another pointer that just works on the non-zero elements of the array.
 */
/*  Two Pointers (1-Pass): Time = O(n) Space = O(1)
    1 次遍历，遍历过程中直接交换 0 和非 0 的位置
 */
public class MoveZeros {
    public void moveZeros(int[] nums) {
        int slow = 0;
        int fast = 1;
        while (fast < nums.length) {
            if (nums[slow] == 0 && nums[fast] != 0) {
                nums[slow] = nums[fast];
                nums[fast] = 0;
                slow++;
            } else if (nums[slow] != 0) {
                slow++;
            }
            fast++;
        }
     }
}
/*  Two Pointers (2-Pass): Time = O(n) Space = O(1)
    1. fast pointer 'i' to scan input array
    2. slow pointer 'index' to rewrite input array
    遍历 2 遍，第 1 遍先把非 0 数字都覆盖到数组的头部，第 2 遍再把数组剩余的位置用 0 覆盖

    public void moveZeroes(int[] nums) {
        // pointer works on the non-zero elements
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // no need to swap
                nums[index++] = nums[i];
            }
        }
        // now we have all non-zero elements to the index, remaining are all 0
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
 */