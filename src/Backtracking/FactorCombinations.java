package Backtracking;

/*  254. Factor Combinations
    Numbers can be regarded as product of its factors. For example,
    8 = 2 x 2 x 2;
    = 2 x 4.
    Write a function that takes an integer n and return all possible combinations of its factors.

    Note:
    1. You may assume that n is always positive.
    2. Factors should be greater than 1 and less than n.

    Example 1:
    Input: 1
    Output: []

    Example 2:
    Input: 37
    Output: []

    Example 3:
    Input: 12
    Output:
    [
      [2, 6],
      [2, 2, 3],
      [3, 4]
    ]

    Example 4:
    Input: 32
    Output:
    [
      [2, 16],
      [2, 2, 8],
      [2, 2, 2, 4],
      [2, 2, 2, 2, 2],
      [2, 4, 4],
      [4, 8]
    ]
 */
//  Backtracking
import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < 4) return res;
        backtrack(n, 2, res, new ArrayList<>());
        return res;
    }

    // 1. 定义
    public void backtrack(int n, int start, List<List<Integer>> res, List<Integer> list) {
        // 3. 出口（base）
        if (n == 1) {
            if (list.size() > 1) {
                res.add(new ArrayList<Integer>(list));
                return;
            }
        }
        // 2. 拆解（recursion rule）
        for (int i = start; i <= n; i++) {
            if (i > n / i) break;   // 剪枝，防止乱序
            if (n % i == 0) {
                list.add(i);
                backtrack(n / i, i, res, list);
                list.remove(list.size() - 1);
            }
        }
        // 8 = 2 * 4 剪枝后会误伤，需要加回来
        list.add(n);
        backtrack(1, n, res, list);
        list.remove(list.size() - 1);
    }
}
