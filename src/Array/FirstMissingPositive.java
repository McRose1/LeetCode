package Array;

/*  41. Missing Positive
    Given an unsorted integer array, find the smallest missing positive integer.

    Example 1:
    Input: [1,2,0]
    Output: 3

    Example 2:
    Input: [3,4,-1,1]
    Output: 2

    Example 3:
    Input: [7,8,9,11,12]
    Output: 1

    Note: Your algorithm should run in O(n) time and uses constant extra space.
 */

/*  Bucket Sort: 每当 nums[i] != i+1 的时候，将 nums[i] 和 nums[nums[i] - 1]交换，直到无法交换为止
    index: 0 1 2 3 4 5 ...
    num:   1 2 3 4 5 6 ...
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {       // [3,4,-1,1]
        if (nums == null || nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++) {         // i = 0
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) { // nums[nums[0]-1]=nums[2] != nums[0]
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];            // nums[2] = 3; nums[3] = 4; nums[0] = 1
                nums[i] = temp;                         // nums[0] = -1; nums[1] = 1; nums[1] = -1
            }
        }
        for (int i = 0; i < nums.length; i++) {         // [1, -1, 3, 4]
            if (nums[i] != i + 1) {
                return i + 1;                           // return 2
            }
        }
        return nums.length + 1;
    }
}

/*  HashSet(my version): Time = O(n) Space = O(n)

        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int i = 1;
        while (set.contains(i)) {
            i++;
        }
        return i;
 */