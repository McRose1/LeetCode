package Array;
/*  169. Majority Element
    Given an array of size n, find the majority element.
    The majority element is the element that appears more than [n/2] times.
    You may assume that the array is non-empty and the majority element always exist in the array.

    Example 1:
    Input: [3,2,3]
    Output: 3

    Example 2:
    Input: [2,2,1,1,1,2,2]
    Output: 2
 */

//  HashMap: Time = O(n) Space = O(n)

import java.util.HashMap;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i: nums) {
            if (map.containsKey(i) && map.get(i) + 1 > nums.length / 2) {
                return i;
            } else {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
        }
        return -1;
    }
}
/*
    Sorting: Time = O(n) Space = O(1) or O(n)
    if the elements are sorted in monotonically increasing order,
    the majority element can be found at index [n/2].

    Arrays.sort(nums);
    return nums[nums.length / 2];
 */

/*
    Boyer-Moore Voting Algorithm: Time = O(n) Space = O(1)
    每次都找出一对不同的元素，从数组中删掉，直到数组为空或只有一种元素
    如果存在元素 e 出现频率超过半数，那么数组中最后剩下的就只有 e
    [7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
    
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;

 */