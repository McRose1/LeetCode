package Random;

/*  398. Random Pick Index
    Given an array of integers with possible duplicates, randomly output the index of a given target number.
    You can assume that the given target number must exist in the array.

    Note: The array size can be very large. Solution that uses too much extra space will not pass the judge.

    Example:
    int[] nums = new int[] {1,2,3,3,3};
    Solution solution = new Solution(nums);

    // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
    solution.pick(3);

    // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
    solution.pick(1);
 */

import java.util.Random;
//  Reservoir Sampling
public class RandomPickIndex {
    private Random rand;
    private int[] array;
    public RandomPickIndex(int[] nums) {
        array = nums;                       // {1,2,3,3,3}
        rand = new Random();
    }

    public int pick(int target) {
        int count = 0;
        int res = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {               // i = 2; i = 3; i = 4
                count++;                            // count = 1; count = 2; count = 3
                if (rand.nextInt(count) == 0) {     // nextInt(1)->P=1; nextInt(2)->P=1/2; nextInt(3)->P=1/3
                    res = i;
                }
            }
        }
        return res;
    }
}
