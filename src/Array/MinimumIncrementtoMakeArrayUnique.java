package Array;

/*  945. Minimum Increment to Make Array Unique
    Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.
    Return the least number of moves to make every value in A unique.

    Example 1:
    Input: [1,2,2]
    Output: 1
    Explanation:  After 1 move, the array could be [1, 2, 3].

    Example 2:
    Input: [3,2,1,2,1,7]
    Output: 6
    Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
    It can be shown with 5 or less moves that it is impossible for the array to have all unique values.

    Note:
    1. 0 <= A.length <= 40000
    2. 0 <= A[i] < 40000
 */

/*  Counting: Time = O(n) Space = O(n)
    遇到超过 1 个的数字，保留 1 个，其他的都先加 1，放到下一个位置继续处理；
    一直到最后一个位置，把多余的次数依次向后延伸
 */
public class MinimumIncrementtoMakeArrayUnique {
    public int minIncrementForUnique(int[] A) {
        // counter 数组统计每个数字的个数
        int[] counter = new int[40001];
        int max = -1;
        for (int num : A) {
            counter[num]++;
            max = Math.max(max, num);
        }

        // 遍历 counter 数组，若当前数字的个数大于 1 个，则只留下 1 个，其他的向后移一位
        int move = 0;
        for (int num = 0; num <= max; num++) {
            if ((counter[num] > 1)) {
                int d = counter[num] - 1;
                move += d;
                counter[num + 1] += d;
            }
        }
        // 最后，counter[max + 1] 里可能会有从 counter[max] 后移过来的，counter[max + 1]里留下 1 个，其他的d个后移
        // 设 max + 1 = x，那么后面的 d 个数就是 [x+1, x+2, x+3, ..., x+d],
        // 因此操作次数是 [1,2,3,..., d]，用求和公式求和
        int d = counter[max + 1] - 1;
        move += (1 + d) * d / 2;
        return move;
    }
}

/*  Counting (LC Version): Time = O(n) 但实际上比上面这个慢很多
    if there are 2 or more values x in A, save the extra duplicated values to increment later;
    if there are 0 values x in A, then a saved value v gets incremented to x.
    slight optimization: record only the number of saved values, and subtract from the answer in advance.

        int[] count = new int[1000000];
        for (int x : A) {
            count[x]++;
        }
        int ans = 0, taken = 0;
        for (int x = 0; x < 1000000; x++) {
            if (count[x] >= 2) {
                taken += count[x] - 1;
                ans -= x * (count[x] - 1);
            } else if (taken > 0 && count[x] == 0) {
                // 有空位就消耗掉一个重复的
                taken--;
                // 因为之前提前减过了原值，所以现在加上最终值，就是差值，也就是增加值
                ans += x;
            }
        }
        return ans;
 */

/*  Maintain Duplicate Info: Time = O(nlogn) Space = O(n)
    if A[i - 1] == A[i], we have a duplicate to take
    if A[i - 1] < A[i], we might be able to place our taken values into those free positions.
    Specifically, we have give = min(taken, A[i] - A[i - 1] - 1) possible values to release,
    and they will have final values A[i-1]+1, A[i-1]+2,..., A[i-1]+give.
    This has a sum of A[i - 1] * give + (1+2+...+give)

        Arrays.sort(A);
        int ans = 0, taken = 0;

        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] == A[i]) {
                taken++;
                ans -= A[i];
            } else {
                int give = Math.min(taken, A[i] - A[i - 1] - 1);
                ans += give * (give + 1) / 2 + give * A[i - 1];
                taken -= give;
            }
        }
        // 处理最后一个位置元素的重复情况
        if (A.length > 0) {
            ans += taken * (taken + 1) / 2 + taken * A[A.length - 1];
        }
        return ans;
 */

/*  Brute Force (Sorting): Time = O(nlogn) Space = O(1)

        Arrays.sort(A);
        int move = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= A[i - 1]) {
                int pre = A[i];
                A[i] = A[i - 1] + 1;
                move += A[i] - pre;
            }
        }
        return move;
 */