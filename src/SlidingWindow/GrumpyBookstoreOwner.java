package SlidingWindow;

/*  1052. Grumpy Bookstore Owner
    Today, the bookstore owner has a store open for customers.length minutes.
    Every minute, some number of customers (customers[i]) enter the store,
    and all those customers leave after the end of that minute.
    On some minutes, the bookstore owner is grumpy.
    If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.
    When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.
    The bookstore owner knows a secret technique to keep themselves not grumpy for x minutes straight, but can only use it once.
    Return the maximum number of customers that can be satisfied throughout the day.

    Example 1:
    Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
    Output: 16
    Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
    The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.

    Note:
    1 <= X <= customers.length == grumpy.length <= 20000
    0 <= customers[i] <= 1000
    0 <= grumpy[i] <= 1

    Hint:
    Say the store owner uses their power in minute 1 to X and we have some answer A.
    If they instead use their power from minute 2 to X+1,
    we only have to use data from minutes 1, 2, X and X+1 to update our answer A.
 */
/*  Sliding Window
    [1,0,1,2,1,1,7,5] -> 1 + 1 + 1 + 7 = 10
    [0,1,0,1,0,1,0,1]
    [0,0,0,2,0,1,0,5] - > 1 + 0 + 5 = 6
 */
public class GrumpyBookstoreOwner {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int cusLen = customers.length;
        int sum = 0;

        for (int i = 0; i < cusLen; i++) {
            if (grumpy[i] != 1) {
                // 恒定的顾客满意数量
                sum += customers[i];
            } else {
                grumpy[i] = customers[i];
            }
        }
        // 初始化 Sliding Window
        int save = 0;
        for (int i = 0; i < X; i++) {
            save += grumpy[i];
        }
        int max = save;
        // Window Sliding
        for (int i = X; i < cusLen; i++) {
            // 每滑动一位减去最前面的元素
            save -= grumpy[i - X];
            // 同时加上后面一位新元素
            save += grumpy[i];
            max = Math.max(max, save);
        }
        return max + sum;
    }
}
