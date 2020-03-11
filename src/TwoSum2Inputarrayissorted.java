/*
    Given an array of integers that is already sorted in ascending order,
    find two numbers such that they add up to a specific target number.
    The function twoSum should return indices of the two numbers such that they add up to the target,
    where index1 must be less than index2.
    Note:
        Your returned answers (both index1 and index2) are not zero-based.
        You may assume that each input would have exactly one solution and you may not use the same element twice.

    Example:
    Input: numbers = [2,7,11,15], target = 9
    Output: [1,2]
 */

/*
    头尾指针，如果 sum 小了，那就是头指针小了，如果 sum 大了，那就是尾指针大了
 */

public class TwoSum2Inputarrayissorted {
    public int[] twoSums(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length - 1;
        int sum = 0;

        while (start != end) {
            sum = numbers[start] + numbers[end];
            if (sum > target) {
                end -= 1;
            } else if (sum < target) {
                start += 1;
            } else {
                return new int[]{start + 1, end + 1};
            }
        }
        return new int[]{-1, -1};
    }
}

/*
    2 pointers
 */
