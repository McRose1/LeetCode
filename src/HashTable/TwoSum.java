package HashTable;

/*  1. Two Sum
    Given an array of integers, return indices of the two numbers such that they add up to a specific target.
    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    Example:
    Given nums = [2, 7, 11, 15], target = 9
    return [0, 1]
 */
import java.util.HashMap;
//      One-Pass Hash Table: Time = O(n) Space = O(n)
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // While we iterate and inserting elements into the table, we also look back to check
        // if current element's complement already exists in the table
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

/*  my version

        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                res = new int[] {i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return res;
 */


/*  Two-pass Hash Table: Time = O(n) Space = O(n)

        // {value, index}
        HashMap<Integer, Integer> map = new HashMap<>();
        // In the first iteration, we add each element's value and its index to the table
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        // In the second iteration, we check if each element's complement (target - nums[i]) exists in the table.
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            // Beware that the complement must not be nums[i] itself!
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
 */

/*  Brute Force: Time = O(n^2) Space = O(1)

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] {i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
 */