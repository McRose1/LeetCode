package Array;

/*  Create Target Array in the Given Order
    Given two arrays of integers nums and index.
    You task is to create target array under the following rules:
        o Initially target array is empty.
        o From left to right read nums[] and index[i], insert at index index[i] the value nums[i] in target array.
        o Repeat the previous step until there are no elements to read in nums and index.
    Return the target array.
    It is guaranteed that the insertion operations will be valid.

    Example 1:
    Input: nums = [0,1,2,3,4], index = [0,1,2,2,1]
    Output: [0,4,1,3,2]
    Explanation:
    nums       index     target
    0            0        [0]
    1            1        [0,1]
    2            2        [0,1,2]
    3            2        [0,1,3,2]
    4            1        [0,4,1,3,2]

    Example 2:
    Input: nums = [1,2,3,4,0], index = [0,1,2,3,0]
    Output: [0,1,2,3,4]
    Explanation:
    nums       index     target
    1            0        [1]
    2            1        [1,2]
    3            2        [1,2,3]
    4            3        [1,2,3,4]
    0            0        [0,1,2,3,4]

    Example 3:
    Input: nums = [1], index = [0]
    Output: [1]

    Constraints:
        • 1 <= nums.length, index.length <= 100
        • nums.length == index.length
        • 0 <= nums[i] <= 100
        • 0 <= index[i] <= i

    Hint: Simulate the process and fill corresponding numbers in the designated spots.
 */

/*  Divide & Conquer: Time = O(nlogn) Space = O(n)

 */
public class CreateTargetArrayintheGivenOrder {
    public int[] createTargetArray(int[] nums, int[] index) {
        int n = nums.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        helper(a, 0, n, index, new int[n]);
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[index[i]] = nums[i];
        }
        return res;
    }

    private void helper(int[] a, int i, int j, int[] index, int[] temp) {
        if (j - i <= 1) return;

        int mid = i + (j - i) / 2;
        helper(a, i, mid, index, temp);
        helper(a, mid, j, index, temp);
        int x = i;
        int y = mid;
        int z = 0;
        int count = 0;
        while (x < mid && y < j) {
            while (y < j && index[a[y]] <= index[a[x]] + count) {
                count++;
                temp[z++] = a[y++];
            }
            index[a[x]] += count;
            temp[z++] = a[x++];
        }
        while (x < mid) {
            index[a[x]] += count;
            temp[z++] = a[x++];
        }
        while (y < j) {
            temp[z++] = a[y++];
        }
        for (int p = i, q = 0; p < j; p++, q++) {
            a[p] = temp[q];
        }
    }
}

/*  ArrayList: Time = O(n^2) Space = O(n)

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(index[i], nums[i]);
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
 */

/*  Brute Force

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int idx = index[i];
            for (int j = i; j > idx; j--) {
                res[j] = res[j - 1];
            }
            res[idx] = nums[i];
        }
        return res;
 */