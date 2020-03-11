package BitManipulation;

/*  268. Missing Number
    Given an array containing n distinct numbers taken from 0 ,1, 2, ..., n,
    find the one that is missing from the array.

    Example 1:
    Input: [3,0,1]
    Output: 2

    Example 2:
    Input: [9,6,4,2,3,5,7,0,1]
    Output: 8

    Note:
    Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */

// XOR: Time = O(n) Space = O(1)
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }
}

/*  Gauss' Formula - Sum up: Time = O(n) Space = O(1)

        int expectedSum = nums.length * (nums.length + 1) / 2;  // 高斯求和
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;
 */

/*  Sorting: Time = O(nlogn) Space = O(1)

        Arrays.sort(nums);
        // Ensure that n is at the last index
        if (nums[nums.length - 1] != nums.length) {
            return nums.length;
        }
        // Ensure that 0 is at the first index
        else if (nums[0] != 0) {
            return 0;
        }
        // If we get here, then the missing number is on the range (0, n)
        for (int i = 1; i < nums.length; i++) {
            int expectNum = nums[i - 1] + 1;
            if (nums[i] != expectNum) {
                return expectNum;
            }
        }
        // Array was not missing any numbers
        return -1;
 */

/*  HashSet: Time = O(n) Space = O(n)

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) numSet.add(num);

        for (int number = 0; number < nums.length + 1; number++) {
            if (!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
 */