package Backtracking;

/*  78. Subsets
    Given a set of distinct integers, nums, return all possible subsets (the power set).
    Note: The solution set must not contain duplicate subsets.

    Example:
    Input: nums = [1,2,3]
    Output:
    [
      [3],
      [1],
      [2],
      [1,2,3],
      [1,3],
      [2,3],
      [1,2],
      []
    ]
 */

/*  Backtracking: Time = O(N*2^N) to generate all subsets and then copy them into output list. Space = O(2^N)
    Let us loop over the length of combination, rather than the candidate numbers,
    and generate all combinations for a given length with the help of backtracking technique.
    We define a backtrack function named backtrack(first, curr)
    which takes the index of first element to add and a current combination as arguments
    If the current combination is done, we add the combination to the final output.
    Otherwise, we iterate over the indexes i from first to the length to the entire sequence n.
        Add integer nums[i] into the current combination curr.
        Proceed to add more integers into the combination: backtrack(i + 1, curr).
        Backtrack by removing nums[i] from curr.
 */
import java.util.ArrayList;
import java.util.List;

public class Subsets {
    List<List<Integer>> output = new ArrayList<>();
    int n, k;   // k = size of subset

    public List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        for (k = 0; k < n + 1; ++k) {
            backtrack(0, new ArrayList<Integer>(), nums);
        }
        return output;
    }
    public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
        // if the combination is done
        if (curr.size() == k) {
            output.add(new ArrayList<>(curr));
            return;
        }
        for (int i = first; i < n; ++i) {
            // add i into the current combination
            curr.add(nums[i]);
            // use next integers to complete the combination
            backtrack(i + 1, curr, nums);
            // backtrack
            curr.remove(curr.size() - 1);
        }
    }
}

/*  Iteration 1 (DP): Time = O(n * 2^n) Space = O(2^n)
    Choose or not choose
    从条件入手，先只考虑给定数组的 1 个元素的所有子数组，然后再考虑数组的 2 个元素的所有子数组...最后再考虑数组的 n 个元素的所有子数组
    求 k 个元素的所有子数组，只需要在 k - 1 个元素的所有子数组里边加上 nums[k] 即可
    At each step one takes new integer into consideration and generates new subsets from the existing one.

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());  // 初始化空数组

        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList();
            // 遍历之前的所有结果
            for (List<Integer> curr : res) {
                // 为了不改变 curr
                List<Integer> tmp = new ArrayList<Integer>(curr);
                tmp.add(num);
                newSubsets.add(tmp);
            }
            for (List<Integer> curr : newSubsets) {
                res.add(curr);
            }
        }
        return res;
 */

/*  Iteration 2:
    Enumerate numbers for a position
    直接从结果进行分类，将子数组的长度分为 1，2，...n
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        ans.add(new ArrayList<>());
        res.add(new ArrayList<>());
        int n = nums.length;
        // 第一层循环，子数组长度从 1 到 n
        for (int i = 1; i <= n; i++) {
            // 第二层循环，遍历上次的所有结果
            List<List<Integer>> tmp = new ArrayList<List<Integer>>();
            for (List<Integer> list : res) {
                // 第三层循环，对每个结果进行扩展
                for (int m = 0; m < n; m++) {
                    // 只添加比末尾数字大的数字，防止重复
                    if (list.size() > 0 && list.get(list.size() - 1) >= nums[m]) {
                        continue;
                    }
                    List<Integer> newList = new ArrayList<>(list);
                    newList.add(nums[m]);
                    tmp.add(newList);
                    ans.add(newList);
                }
            }
            res = tmp;
        }
        return ans;
 */

/*  Lexicographic (Binary Sorted) Subsets: Time = O(N*2^N) Space = O(2^N)
    The idea is that we map each subset to a bitmask of length n,
    where 1 on the ith position in bitmask means the presence of nums[i] in the subset,
    and 0 means its absence.
    Hence to solve the initial problem, we just need to generate n bitmasks from 0..00 to 1..11.

        List<List<Integer>> output = new ArrayList<>();
        int n = nums.length;

        for (int i = (int)Math.pow(2, n); i < (int)Math.pow(2, n + 1); ++i) {
            // generate bitmask, from 0.00 to 1.11
            String bitmask = Integer.toBinaryString(i).substring(1);

            // append subset corresponding to that bitmask
            List<Integer> curr = new ArrayList();
            for (int j = 0; j < n; ++j) {
                if (bitmask.charAt(j) == '1') curr.add(nums[j]);
            }
            output.add(curr);
        }
        return output;
 */
