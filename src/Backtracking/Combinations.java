package Backtracking;

/*  77. Combinations
    Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

    Example:
    Input: n = 4, k = 2
    Output:
    [
      [2,4],
      [3,4],
      [2,3],
      [1,2],
      [1,3],
      [1,4],
    ]

 */
import java.util.ArrayList;
import java.util.List;
//  Backtracking
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), n, k, 1);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> list, int n, int k, int start) {
        if (k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            backtrack(res, list, n, k - 1, i + 1);
            list.remove(list.size() - 1);
        }
    }
}

/*  Iteration

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            temp.add(0);
        }
        int i = 0;
        while (i >= 0) {
            temp.set(i, temp.get(i) + 1);   // 当前数字加 1
            if (temp.get(i) > n) {
                i--;
                //  当前数字个数够了
            } else if (i == k - 1) {
                ans.add(new ArrayList<>(temp));
            // 进入更新下一个数字
            } else {
                i++;
                // 把下一个数字置为上一个数字，类似于回溯法的 start
                temp.set(i, temp.get(i - 1));
            }
        }
        return ans;
 */