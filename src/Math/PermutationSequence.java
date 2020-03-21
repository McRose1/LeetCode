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
import java.util.ArrayList;
import java.util.List;
/*  Cantor Expansion（康拓展开）: Time = O(n^2) Space = O(1)
    分为 n 组，每组都有 (n - 1)! 个数字，这样确定下最高位的组数，然后继续分组 (n - 1)组，(n - 2)!个数字...
    有点像 n 分法
 */
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int factorial = 1;
        // 构建可用数字的 list
        for (int i = 0; i < n; i++) {
            nums.add(i + 1);
            if (i != 0) {
                factorial *= i;
            }
        }                                           // [1, 2, 3, 4]
        // 先求出 n 的阶乘
        factorial *= n;                             // 4! = 24
        StringBuilder sb = new StringBuilder();
        // 从第 k 个数转换为 index
        k = k - 1;                                  // 9-1=8
        for (int i = n; i > 0; i--) {
            // 更新为 n-1 的阶乘
            factorial /= (nums.size());             // 24/4=6; 6/3=2; 2/2=1; 1/1=1
            // 判断此时落在哪一组
            int groupNum = k / factorial;           // 9/6=1; 2/2=1; 0/1=0/ 0/1=0
            // 根据 groupNum 得到当前位
            int num = nums.get(groupNum);           // nums.get(1)=2; nums.get(1)=3; nums.get(0)=1; nums.get(0)=4
            // 去掉当前数字，防止重复利用 list.remove() -> O(n)
            nums.remove(groupNum);                  // [1, 3, 4]; [1, 4]; [4]; []
            // 更新下一次的 k
            k = k % factorial;                      // 8%6=2; 2%2=0; 0%1=0; 0%1=0
            sb.append(num);                         // "2"; "23"; "231"; "2314"
        }
        return sb.toString();
    }
}

/*  Backtracking: Time = O(n!) Space = O(n)

    public String getPermutation(int n, int k) {
        List<String> res = new ArrayList<>();
        backtrack(n, "", res, new boolean[n + 1], k);
        return res.get(k - 1);
    }

    private void backtrack(int n, String temp, List<String> res, boolean[] visited, int k) {
        if (temp.length() == n) {
            res.add(new String(temp));
        }

        for (int i = 1; i <= n; i++) {
            if (res.size() == k) break;
            if (!visited[i]) {
                String newStr = temp + i;
                visited[i] = true;
                backtrack(n, newStr, res, visited, k);
                visited[i] = false;
            }
        }
    }
 */
