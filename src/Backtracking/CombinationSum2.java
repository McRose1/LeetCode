package Backtracking;

/*  40. Combination Sum 2
    Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
    find all unique combinations in candidates where the candidate numbers sums to target.
    Each number in candidates may only be used once in the combination.

    Note:
    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

    Example 1:
    Input: candidates = [10,1,2,7,6,1,5], target = 8,
    A solution set is:
    [
      [1, 7],
      [1, 2, 5],
      [2, 6],
      [1, 1, 6]
    ]

    Example 2:
    Input: candidates = [2,5,2,1,2], target = 5,
    A solution set is:
    [
      [1,2,2],
      [5]
    ]
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*  Backtrack: Time = O(2^n) Space = O(n)
    How to remove duplicates?
    1. Disallow same number in same depth
    2. Use set（很少人选这种）
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    public void backtrack(int[] candidates, int start, int target, List<Integer> list, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
        }
        else if (target > 0) {
            for (int i = start; i < candidates.length; i++) {
                // 跳过重复的数字
                if (i != start && candidates[i] == candidates[i - 1]) continue;
                list.add(candidates[i]);
                // i -> i + 1 ，因为每个数字只能用一次，所以下次遍历的时候不从自己开始
                backtrack(candidates, i + 1, target - candidates[i], list, res);
                list.remove(list.size() - 1);
            }
        }
    }
}

/*  my version
    相当于多了一个 boolean 数组判断，只要每次递归 i+1 然后再递归前再判断就可以避免 duplicate

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        boolean[] used = new boolean[candidates.length];
        backtrack(res, new ArrayList<>(), candidates, used, target, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> list, int[] candidates, boolean[] used, int target, int start) {
        if (target < 0) return;
        else if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i != start && !used[i - 1] && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (!used[i]) {
                list.add(candidates[i]);
                used[i] = true;
                backtrack(res, list, candidates, used, target - candidates[i], i);
                used[i] = false;
                list.remove(list.size() - 1);
            }
        }
        return;
    }
 */