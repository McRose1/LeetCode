package DP;

/*  70. Climbing Stairs
    You are climbing a stair case. It takes n steps to reach to the top.
    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

    Note: Given n will be a positive integer.

    Example 1:
    Input: 2
    Output: 2

    Example 2:
    Input: 3
    Output: 3
 */

/*  DP: Time = O(n) Space = O(n)
    As we can see this problem can be broken into subproblems, and it contains the optimal substructure property.
    its optimal solution can be constructed efficiently from optimal solutions of its subproblems.
    One can reach ith step in one of the two ways:
    1. Taking a single step from (i-1)th step.
    2. Taking a step of 2 from (i-2)th step.
    So the total number of ways to reach ith is equal to sum of ways of reaching (i-1)th step and ways of reaching (i-2)th step.
    dp[i] = dp[i - 1] + dp[i - 2]
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        if(n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

/*  Brute Force: Time = O(2^n) Space = O(n)
    climbStairs(i, n) = (i + 1, n) + climbStairs(i + 2, n)

    public int climbStairs(int n) {
        return climb_Stairs(0, n);
    }
    public int climb_Stairs(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
    }
 */

/*  Recursion with Memoization: Time = O(n) Space = O(n)
    In the previous approach we are redundantly calculating the result for every step.
    Instead, we can store the result at each step in memo array and directly returning the result from the memo array

    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        return climb_Stairs(0, n, memo);
    }
    public int climb_Stairs(int i, int n, int[] memo) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }
 */

/*  Fibonacci Number: Time = O(n) Space = O(1)
    dp[i] can be easily analysed to fibonacci number.
    Fib(n) = Fib(n - 1) + Fib(n - 2)

        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <=n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
 */