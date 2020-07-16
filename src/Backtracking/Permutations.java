package Backtracking;

/*  46. Permutations
    Given a collection of distinct integers, return all possible permutations.

    Example:
    Input: [1,2,3]
    Output:
    [
      [1,2,3],
      [1,3,2],
      [2,1,3],
      [2,3,1],
      [3,1,2],
      [3,2,1]
    ]
 */
import java.util.ArrayList;
import java.util.List;
// Backtracking: Time = O(n!) Space = O(n)
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        backtrack(nums, res, new ArrayList<>(), new boolean[nums.length]);
        return res;
    }

    public void backtrack(int[] nums, List<List<Integer>> res, List<Integer> list, boolean[] visited) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
        }
        for (int i = 0; i < nums.length; i++) {
            //if (list.contains(nums[i])) continue;   // O(n) 也可以用 HashSet，用空间换时间
            if (!visited[i]) {
                list.add(nums[i]);
                visited[i] = true;
                backtrack(nums, res, list, visited);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }
}
/*  Swap-swap(Recursion): time = O(n!) space = O(n);

    public List<List<Integer>> permute2(int[] nums) {   // [1,2,3]
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        helper2(res, nums, 0);
        return res;
    }
    public void helper2(List<List<Integer>> res, int[] nums, int start) {
        if (start == nums.length) {                         // 3 == 3
            List<Integer> list = new ArrayList<>();
            // 这里实现 permutation
            for (int num : nums) {
                list.add(num);
            }
            res.add(new ArrayList<>(list));                 // [1,2,3], [1,3,2]
        }
        for (int i = start; i < nums.length; i++) {     // i = 0 -> 1 -> 2   -> i = 2
            swap(nums, start, i);                                            // swap(1,2) -> [1,3,2]
            helper2(res, nums, start + 1);              // (start=1)          (start=2, [1,3,2])
            // swap back, backtrack recovery
            swap(nums, i, start);                       // swap(2,2) -> swap(1,1)
        }
    }
    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
 */