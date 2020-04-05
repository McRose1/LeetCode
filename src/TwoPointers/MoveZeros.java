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
    In-place means we should not allocating any space for extra array.
    But we are allowed to modify the existing array.

    Hint 2:
    A two-pointer approach could be helpful here.
    The idea would be to have one pointer for iterating the array and another pointer that just works on the non-zero elements of the array.
 */
//  Two Pointers
public class MoveZeros {
    public void moveZeros(int[] nums) {
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
}
/*  my version

    public void moveZeroes(int[] nums) {
        int zero = 0;
        int search = 1;
        while (search < nums.length) {
            if (nums[zero] == 0 && nums[search] != 0) {
                swap(nums, zero, search);
                zero++;
            } else if (nums[zero] != 0) {
                zero++;
            }
            search++;
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
 */