package BitManipulation;

/*  137. Single Number 2
    Given a non-empty array of integers, every element appears three except for one. Find that single one.
    Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

    Example 1:
    Input: [2,2,3,2]
    Output: 3

    Example 2:
    Input: [0,1,0,1,0,1,99]
    Output: 99
 */

/*  Bit Manipulation: Time = O(n) Space = O(1)
    对所有的数，每一个数的第 i 位累加然后模 3
    然后模的结果返还 i 位给 res
    1 -- 001
    1 -- 001
    5 -- 101
    1 -- 001
    --------
         101 -> 5
 */
public class SingleNumber2 {
    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : nums) {
                sum += (num >> i) & 1;
                sum %= 3;
            }
            res = res | (sum << i);
        }
        return res;
    }
}

/*  my version: Math: Time = O(n) Space = O(n)

        long sumOfSet = 0;
        long sumOfNums = 0;
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
                sumOfSet += num;
            }
            sumOfNums += num;
        }
        return (int) ((3 * sumOfSet - sumOfNums) / 2);
 */