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

/*  Reservoir Sampling
    当不清楚物体总量但又想计算其被选中概率时，使用水塘采样
 */
public class RandomPickIndex {
    private Random rand;
    private int[] array;

    public RandomPickIndex(int[] nums) {
        array = nums;                       // {1,2,3,3,3}
        rand = new Random();
    }

    public int pick(int target) {
        int count = 0;
        int res = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                count++;
                // nextInt(1)->P=1; nextInt(2)->P=1/2; nextInt(3)->P=1/3
                if (rand.nextInt(count) == 0) {
                    res = i;
                }
            }
        }
        return res;
    }
}

/*  HashMap

    private Random rand;
    private HashMap<Integer, List<Integer>> map;
    public Solution(int[] nums) {
        rand = new Random();
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                List<Integer> temp = map.get(nums[i]);
                temp.add(i);
                map.put(nums[i], temp);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                map.put(nums[i], temp);
            }
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);
        int size = list.size();
        int i = rand.nextInt(size);
        return list.get(i);
    }
 */

/*  Brute Force
    直接模拟随机过程，一直随机取，直到取到对应值，则输出位置

    private Random rand;
    private int[] array;

    public RandomPickIndex(int[] nums) {
        array = nums;                       // {1,2,3,3,3}
        rand = new Random();
    }

    public int pick(int target) {
        int i = rand.nextInt(array.length);
        while (array[i] != target) {
            i = rand.nextInt(array.length);
        }
        return i;
    }
 */
