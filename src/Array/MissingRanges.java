package Array;

/*  163. Missing Ranges
    Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper],
    return its missing ranges.

    Example:
    Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
    Output: ["2", "4->49", "51->74", "76->99"]
 */

import java.util.ArrayList;
import java.util.List;
//  Pointer
public class MissingRanges {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        long l = (long) lower;
        long u = (long) upper;
        List<String> res = new ArrayList<>();

        // 每次遍历更新 lower 的值
        for (int num : nums) {
            if (num == l) {
                l++;
            } else if (l < num) {
                if (l + 1 == num) {
                    res.add(String.valueOf(l));
                } else {
                    res.add(l + "->" + (num - 1));
                }
                // 如果不用 long，这里如果 num 等于 Integer.MAX_VALUE 会发生整形溢出
                l = (long) num + 1;
            }
        }
        if (l == u) res.add(String.valueOf(l));
        else if (l < u) res.add(l + "->" + u);
        return res;
    }
}

/*  happyGirl

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        long l = (long) lower;
        long u = (long) upper;
        List<String> res = new ArrayList<>();
        int n = nums.length;

        if (n == 0) {
            add(res, l - 1, u + 1);
            return res;
        }
        add(res, l - 1, nums[0]);
        for (int i = 1; i < n; i++) {
            add(res, nums[i - 1], nums[i]);
        }
        add(res, nums[n - 1], u + 1);
        return res;
    }

    private void add(List<String> res, long lo, long hi) {
        if (lo == hi) {
            return;
        } else if (lo + 1 == hi) {
            return;
        } else if (lo + 1 == hi - 1) {
            res.add(String.valueOf(lo + 1));
        } else {
            res.add((lo + 1) + "->" + (hi - 1));
        }
    }
 */

/*  my version

        List<String> res = new ArrayList<>();
        int n = nums.length;
        if (nums == null || n == 0) {
            if (lower == upper) {
                res.add(String.valueOf(lower));
            } else {
                res.add(String.valueOf(lower) + '->' + upper);
            }
            return res;
        }
        if (n == 1) {
            if (nums[0] == lower && nums[0] == upper) {
                return res;
            } else if (nums[0] == lower) {
                res.add(String.valueOf(nums[0] + 1) + '->' + upper);
            } else if (nums[0] == upper) {
                res.add(String.valueOf(lower) + '->' + (nums[0] - 1));
            } else {
                res.add(String.valueOf(lower) + '->' + (nums[0] - 1));
                res.add(String.valueOf(nums[0] + 1) + '->' + upper);
            }
            return res;
        }

        if (nums[0] != lower) {
            if (lower + 1 == nums[0]) {
                res.add(String.valueOf(lower));
            } else {
                res.add(String.valueOf(lower) + '->' + (nums[0] - 1));
            }
        }

        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                continue;
            }
            // 0, 1, 3 -> "2"
            else if (nums[i] + 1 != nums[i + 1] && nums[i] + 2 == nums[i + 1]) {
                res.add(String.valueOf(nums[i] + 1));
            } else if (nums[i] + 1 != nums[i + 1]) {
                res.add(String.valueOf(nums[i] + 1) + '->' + (nums[i + 1] - 1));
            }
        }

        if (nums[n - 1] != upper) {
            if (nums[n - 1] + 1 == upper) {
                res.add(String.valueOf(upper));
            } else {
                res.add(String.valueOf(nums[n - 1] + 1) + '->' + upper);
            }
        }
        return res;
 */

