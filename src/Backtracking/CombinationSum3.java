package Backtracking;

/*  216. Combination Sum 3
    Find all possible combinations of k numbers that add up to a number n,
    given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

    Note:
    All numbers will be positive integers.
    The solution set must not contain duplicate combinations.

    Example 1:
    Input: k = 3, n = 7
    Output: [[1,2,4]]

    Example 2:
    Input: k = 3, n = 9
    Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
import java.util.ArrayList;
import java.util.List;
//  Backtracking
public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), k, n, 1);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> list, int count, int target, int start) {
        if (count == 0 && target == 0) {
            //res.add(list);     // shallow copy 因为每次递归退出都会改变 list 的元素，所以为了保存最后的结果必须用深拷贝
            res.add(new ArrayList<>(list));     // deep copy
            return;
        }
        for (int i = start; i <= 9; i++) {
            list.add(i);
            backtrack(res, list, count - 1, target - i, i + 1);
            list.remove(list.size() - 1);   // 这一行加上每次循环的 i++ 实现了 backtrack
        }
    }
}

/*  Bit Manipulation: Time = O(2^m) Space = O(k + k * #of ans)
    use 9 bit binary string to represent a combination
    000000000 -> []
    000000001 -> [1]
    ...
    000100011 -> [1,2,6]
    ...
    111111110 -> [2,3,4,5,6,7,8,9]
    111111111 -> [1,2,3,4,5,6,7,8,9]
    1. if i-th bit is 1, then number (i + 1) is used
    2. total # of 1s should be k

        List<List<Integer>> ans = new ArrayList<>();
        for (int mask = 1; mask < (1 << 9); mask++) {
            int sum = 0, count = 0;
            List<Integer> curr = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                if ((mask & 1 << i) != 0) {
                    count++;
                    if (count > k || sum > n) break;
                    sum += (i + 1);
                    curr.add(i + 1);
                }
            }
            if (count == k && sum == n) ans.add(curr);
        }
        return ans;
 */