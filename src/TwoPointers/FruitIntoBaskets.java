package TwoPointers;

/*  904. Fruit Into Baskets
    In a row of trees, the i-th tree produces fruit with type tree[i].
    You start at any tree of your choice, then repeatedly perform the following steps:
        1. Add one piece of fruit from this tree to your baskets. If you cannot, stop.
        2. Move to the next tree to the right of the current tree. If there is no tree at the right, stop.

    Note that you do not have any choice after the initial choice of starting tree:
    you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

    You have two baskets, and each basket can carry any quantity of fruit,
    but you want each basket to only carry one type of fruit each.

    What is the total amount of fruit you can collect with this procedures?

    Example 1:
    Input: [1,2,1]
    Output: 3
    Explanation: We can collect [1,2,1].

    Example 2:
    Input: [0,1,2,2]
    Output: 3
    Explanation: We can collect [1,2,2].
    If we started at the first tree, we would only collect [0, 1].

    Example 3:
    Input: [1,2,3,2,2]
    Output: 4
    Explanation: We can collect [2,3,2,2].
    If we started at the first tree, we would only collect [1, 2].

    Example 4:
    Input: [3,3,3,1,2,1,1,2,3,3,4]
    Output: 5
    Explanation: We can collect [1,2,1,1,2].
    If we started at the first tree or the eighth tree, we would only collect 4 fruits.

    Note:
        1. 1 <= tree.length <= 40000
        2. 0 <= tree[i] < tree.length
 */

import java.util.HashMap;
import java.util.Map;

/*  Two pointers + HashMap: Time = O(n) Space = O(1)
    转换问题 -> longest substring with 2 characters
    HashMap 存的是每个数字在数组中的 last index
 */
public class FruitIntoBaskets {
    public int totalFruit(int[] tree) {
        int max = 1;
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0;
        int j = 0;
        while (j < tree.length) {
            if (map.size() <= 2) {
                // map 存该数字的 last index
                map.put(tree[j], j);
                // 更新右指针
                j++;
            }

            // 超出范围，remove 最前面的数字
            if (map.size() > 2) {
                int min = tree.length - 1;
                for (int value : map.values()) {
                    min = Math.min(min, value);
                }

                // 更新左指针
                i = min + 1;
                map.remove(tree[min]);
            }

            max = Math.max(max, j - i);
        }
        return max;
    }
}

/*  Version 2
    HashMap 存的是数字的出现个数

        int max = 1;
        Map<Integer, Integer> count = new HashMap<>();
        int i = 0;
        int j = 0;
        while (j < tree.length) {
            if (count.size() <= 2) {
                // map 存该数字的 count
                count.put(tree[j], count.getOrDefault(tree[j], 0) + 1);
                // 更新右指针
                j++;
            }

            while (count.size() > 2) {
                if (count.get(tree[i]) == 1) {
                    count.remove(tree[i]);
                } else {
                    count.put(tree[i], count.get(tree[i]) - 1);
                }
                i++;
            }

            max = Math.max(max, j - i);
        }
        return max;
 */