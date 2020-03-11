package Math;

/*  60. Permutation Sequence
    The set [1,2,3,,...,n] contains a total of n! unique permutations.
    By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
    1. "123"
    2. "132"
    3. "213"
    4. "231"
    5. "312"
    6. "321"
    Given n and k, return the kth permutation sequence.

    Note:
    Given n will be between 1 and 9 inclusive.
    Given k will be between 1 and n! inclusive.

    Example 1:
    Input: n = 3, k = 3
    Output: "213"

    Example 2:
    Input: n = 4, k = 9
    Output: "2314"
 */

//  Cantor Expansion
import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int factorial = 1;
        for (int i = 0; i < n; i++) {
            nums.add(i + 1);
            if (i != 0) {
                factorial *= i;
            }
        }
        factorial *= n;     // 先求出 n 的阶乘
        StringBuilder sb = new StringBuilder();
        k = k - 1;
        for (int i = n; i > 0; i--) {
            factorial /= (nums.size());     // 更新为 n-1 的阶乘
            int groupNum = k / factorial;   // 判断此时落在哪一组
            int num = nums.get(groupNum);   // 根据 groupNum 得到当前位
            nums.remove(groupNum);      // 去掉当前数字
            k = k % factorial;      // 更新下一次的 k
            sb.append(num);
        }
        return sb.toString();
    }
}
