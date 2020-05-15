package BitManipulation;

/*  136. Single Number
    Given a non-empty array of integers, every element appears twice except for one. Find that single one.
    Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

    Example 1:
    Input: [2,2,1]
    Output: 1

    Example 2:
    Input: [4,1,2,1,2]
    Output: 4
 */

/*  Bit Manipulation
    ^ : 异或：相同为 0，不同为 1
    If we take XOR of zero and some bit, it will return that bit:
        a ^ 0 = a
    If we take XOR of two same bits, it will return 0
        a ^ a = 0
    a ^ b ^ a = a ^ a ^ b = 0 ^ b = b
    So we can XOR all bits together to find the unique number
 */

public class SingleNumber {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}

/*  HashSet

    public int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                set.remove(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }
        return set.iterator().next();
    }
 */

/*  Math: Time = O(n) Space = O(n)
    2 * (a + b + c) - (a + a + b + b + c) = c

        int sumOfSet = 0;
        int sumOfNums = 0;
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
                sumOfSet += num;
            }
            sumOfNums += num;
        }
        return 2 * sumOfSet - sumOfNums;
 */