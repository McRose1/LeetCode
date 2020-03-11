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
/*  Backtrack: Time = O(2^n) Space = O(n)
    How to remove duplicates?
    1. Use set
    2. Disallow same number in same depth
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        findCombinations(candidates, 0, target, new ArrayList<Integer>(), res);
        return res;
    }

    public void findCombinations(int[] candidates, int start, int target, List<Integer> current, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(current));
            return;
        }
        if (target < 0) return;
        for (int i = start; i < candidates.length; i++) {
            if (i != start && candidates[i] == candidates[i - 1]) continue;
            current.add(candidates[i]);
            findCombinations(candidates, i + 1, target - candidates[i], current, res);
            current.remove(current.size() - 1);
        }
    }
}
