package Array;

/*  228. Summary Ranges
    Given a sorted integer array without duplicates, return the summary of its ranges.

    Example 1:
    Input:  [0,1,2,4,5,7]
    Output: ["0->2","4->5","7"]
    Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.

    Example 2:
    Input:  [0,2,3,4,6,8,9]
    Output: ["0","2->4","6","8->9"]
    Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 */

import java.util.ArrayList;
import java.util.List;
/*  LC 官方: Time = O(n) Space = O(1)
    不要忘记把最后一段区间也放进结果里：在循环里通过一个特定的判断条件来加入或者循环结束后加入
    这里用的是前者，我一开始写的是后者
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        // i is the start index, j is the iteration pointer
        for (int i = 0, j = 0; j < n; j++) {
            // check if j + 1 extends the range [nums[i], nums[j]]
            // 如果相邻两数差 1，就继续遍历
            if (j + 1 < n && nums[j + 1] == nums[j] + 1) {
                continue;
            }
            // 两种情况可以输出结果：1.连续序列中断 2.最后一段区间
            // put the range [nums[i], nums[j]] into the list
            if (i == j) {
                res.add(nums[i] + "");
            } else {
                res.add(nums[i] + "->" + nums[j]);
            }
            // 更新 start index
            i = j + 1;
        }
        return res;
    }
}

/*  LC 官方 version2：

        List<String> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0, j = 0; j < n; j++) {
            i = j;
            // try to extend the range [nums[i], nums[j]]
            while (j + 1 < n && nums[j + 1] == nums[j] + 1) {
                j++;
            }
            // put the range into the list
            if (i == j) {
                res.add(nums[i] + "");
            } else {
                res.add(nums[i] + "->" + nums[j]);
            }
        }
        return res;
 */

/*  my version

        List<String> res = new ArrayList<>();
        int n = nums.length;
        if (nums == null || nums.length == 0) {
            return res;
        }

        int start = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                continue;
            } else {
                if (i - start == 1) {
                    res.add(nums[start] + "");
                } else {
                    res.add(nums[start] + "->" + nums[i - 1]);
                }
                start = i;
            }
        }
        // 加入最后一段区间
        if (start == n - 1) {
            res.add(nums[start] + "");
        } else {
            res.add(nums[start] + "->" + nums[n - 1]);
        }
        return res;
 */