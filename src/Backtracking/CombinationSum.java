package Backtracking;

/*  39. Combination Sum
    Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
    find all unique combinations in candidates where the candidate numbers sums to target.
    The same repeated number may be chosen from candidates unlimited number of times.

    Note:
    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

    Example 1:
    Input: candidates = [2,3,6,7], target = 7,
    A solution set is:
    [
      [7],
      [2,2,3]
    ]

    Example 2:
    Input: candidates = [2,3,5], target = 8,
    A solution set is:
    [
      [2,2,2,2],
      [2,3,3],
      [3,5]
    ]
 */
import java.util.ArrayList;
import java.util.List;
/*  Backtracking: Time = O(2^n) Space = O(kn)
    backtrack 返回值类型为 void
    构造 List<List<Integer>> 一般需要 add 多个 List<Integer>，切记需要深拷贝
    防止 duplicate 的方法就是规定递归的起点
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> tempList, int[] candidates, int target, int start) {
        if (target < 0) return;
        else if (target == 0) {
            // 不能写成 res.add(tempList) 因为这样就是 pass by reference，值会随着 tempList 的改变而改变
            // 应该 pass by value, make a deep copy of tempList
            res.add(new ArrayList<>(tempList));
            return;
        } else {
            // i = start 避免了结果出现 duplicate
            for (int i = start; i < candidates.length; i++) {
                tempList.add(candidates[i]);
                backtrack(res, tempList, candidates, target - candidates[i], i);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}

/*
    DP
 */