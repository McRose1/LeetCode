package Backtracking;

/*  90. Subsets 2
    Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
    Note: The solution set must not contain duplicate subsets.

    Example:
    Input: nums = [1,2,2]
    Output:
    [
      [2],
      [1],
      [1,2,2],
      [2,2],
      [1,2],
      []
    ]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//  Backtracking
public class Subsets2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int size = 0; size <= nums.length; size++) {
            backtrack(nums, res, new ArrayList<Integer>(), size, 0);
        }
        return res;
    }
    public void backtrack(int[] nums, List<List<Integer>> res, ArrayList<Integer> list, int size, int start) {
        // if the combination is done
        if (list.size() == size) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            // add i into the current combination
            list.add(nums[i]);
            // use next integers to complete the combination
            backtrack(nums, res, list, size, i + 1);
            // backtrack
            list.remove(list.size() - 1);
        }
    }
}

/*  Iteration1:
    Choose or not choose
    当有重复数字的时候，我们只考虑上一步的新解，算法中用一个指针start保存每一步的新解开始的位置即可
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());     // 初始化空数组
        Arrays.sort(nums);
        int start = 1;      // 保存新解的开始位置
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> ans_tmp = new ArrayList<>();
            // 遍历之前的所有结果
            for (int j = 0; j < ans.size(); j++) {
                List<Integer> list = ans.get(j);
                // 如果出现重复数字，就跳过所有旧解
                if (i > 0 && nums[i] == nums[i - 1] && j < start) {
                    continue;
                }
                List<Integer> tmp = new ArrayList<>(list);
                tmp.add(nums[i]);
                ans_tmp.add(tmp);
            }
            start = ans,size();     // 更新新解的开始位置
            ans.addAll(ans_tmp);
        }
        return ans;
 */

/*  Iteration2:
    当有重复数字出现的时候我们不再按照之前的思路走，而是单独考虑这种情况。
    当有 n 个重复数字出现，其实就是在出现重复数字之前的所有解中，分别加 1 个重复数字，2 个重复数字，3 个重复数字...
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        res.add(curr);
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int dupCount = 0;
            // 判断当前是否是重复数字，并且记录重复的次数
            while ((i + 1) < nums.length && nums[i + 1] == nums[i]) {
                dupCount++;
                i++;
            }
            int prevNum = res.size();
            // 遍历之前几个结果的解
            for (int j = 0; j < prevNum; j++) {
                List<Integer> element = new ArrayList<>(res.get(j));
                // 每次在上次的结果中多加 1 个重复数字
                for (int t = 0; t <= dupCount; t++) {
                    element.add(nums[i]);   // 加入当前重复的数字
                    res.add(new ArrayList<>(element));
                }
            }
        }
        return res;
 */
