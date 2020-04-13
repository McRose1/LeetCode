package HashTable;

/*  217. Contains Duplicate
    Given an array of integers, find it the array contains any duplicates
    Your function should return true if any value appears at least twice in the array,
    and it should return false if every element is distinct.

    Example 1:
    Input: [1,2,3,1]
    Output: true

    Example 2:
    Input: [1,2,3,4]
    Output: false

    Example 3:
    Input: [1,1,1,3,3,4,3,2,4,2]
    Output: true
 */
import java.util.HashSet;
//  HashSet
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) return true;
        }
        return false;
    }
}
/*
    Sorting:
    public boolean containsDuplicate(int[] nums) {
        Array.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }
 */