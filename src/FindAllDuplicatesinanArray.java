/*
    Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array),
    some elements appear twice and others appear once.
    Find all the elements that appear twice in this array.
    Could you do it without extra space and O(n) runtime?

    Example:
    Input:
    [4,3,2,7,8,2,3,1]
    Output:
    [2,3]
 */
// hint: 1 ≤ a[i] ≤ n (n = size of array) index: 0-7, nums: 1-8
// when find a number i, negate the number at the index of i - 1 (which records i) -- like the bit method
import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicatesinanArray {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> dup = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                dup.add(Math.abs(index) + 1);
            } else {
                nums[index] = -nums[index];
            }
        }
        return dup;
    }
}

/*
    HashSet (O(n), O(n))

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FindAllDuplicatesinanArray {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> dup = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                dup.add(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }
        return dup;
    }
}
 */
