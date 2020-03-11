package Backtracking;

/*  47. Permutations 2
    Given a collection of numbers that might contain duplicates, return all possible unique permutations.

    Example:
    Input: [1,1,2]
    Output:
    [
      [1,1,2],
      [1,2,1],
      [2,1,1]
    ]
 */
//  Backtracking: Time = O(n!) Space = O(n)
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation2 {
    public List<List<Integer>> permuteUniques(int[] nums) {
        List<List<Integer>> res= new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        backtrack(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] visited) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        // enumerate possible numbers for current position
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            // skip same number for current position
            if (i >= 1 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;  // 重复数字只取第一个，没有!visited[i]会删掉[1,1,2]
            list.add(nums[i]);
            visited[i] = true;
            backtrack(res, list, nums, visited);
            // restore
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}

/*  Swap: Time = O(n!) Space = O(n)
    Enumerate positions for a number

    public List<List<Integer>> permuteUniques(int[] nums) {
        List<List<Integer>> res= new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        backtrack(res, nums, 0);
        return res;
    }

    public void backtrack(List<List<Integer>> res, int[] nums, int start) {
        if (start == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(list);
            return;
        }
        HashSet<Integer> set = new HashSet<>();     // 保存当前要交换的位置已经有过哪些数字了
        for (int i = start; i < nums.length; i++) {
            if (set.contains(nums[i])) {    // 如果存在了就跳过，不用去交换
                continue;
            }
            set.add(nums[i]);
            swap(nums, i, start);
            backtrack(res, nums, start + 1);
            swap(nums, i, start);
        }
    }

    public void swap(int[] nums, int i, int start) {
        int temp = nums[i];
        nums[i] = nums[start];
        nums[start] = temp;
    }
 */
