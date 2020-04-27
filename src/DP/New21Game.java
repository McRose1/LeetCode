package DP;

/*  837. New 21 Game
    Alice plays the following game, loosely based on the card game "21".
    Alice starts with 0 points, and draws numbers while she has less than K points.
    During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.
    Each draw is independent and the outcomes have equal probabilities.

    Alice stops drawing numbers when she gets K or more points.
    What is the probability that she has N or less points?

    Example 1:
    Input: N = 10, K = 1, W = 10
    Output: 1.00000
    Explanation:  Alice gets a single card, then stops.

    Example 2:
    Input: N = 6, K = 1, W = 10
    Output: 0.60000
    Explanation:  Alice gets a single card, then stops.
    In 6 out of W = 10 possibilities, she is at or below N = 6 points.

    Example 3:
    Input: N = 21, K = 17, W = 10
    Output: 0.73278

    Note:
    0 <= K <= N <= 10000
    1 <= W <= 10000
    Answers will be accepted as correct if they are within 10^-5 of the correct answer.
    The judging time limit has been reduced for this question.
 */

/*  DP
    当游戏结束时，分数的范围在[K, K+W]之间
    N >= K-1+W -> 1：K=17,W=10 -> 获得最大分数为 26
    N < K -> 0
    如果 W 是 3，那么范围是[1-3]，K 是 5
    如果现在分数是 4，那么下一步选 1：p(4) * 1/3
    如果现在分数是 3，那么下一步选 2：p(3) * 1/3
    如果现在分数是 2，那么下一步选 3：p(2) * 1/3
    p(5) = (p(4) + p(3) + p(2)) / 3
    p[K] = (p[K-1] + p[K-2] +...+ p[K-W]) / W = Wsum / W
    dp is storing p(i) for i in [0-N]
 */
public class New21Game {
    public double new21Game(int N, int K, int W) {
        if (K == 0 || N >= K - 1 + W) {
            return 1;
        }
        if (N < K) {
            return 0;
        }

        double[] dp = new double[N + 1];
        double Wsum = 1;
        double res = 0;
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            dp[i] = Wsum / W;

            // all previous card could go to current i by only drawing one card
            if (i < K) {
                Wsum += dp[i];
            }
            // 达到游戏结束目标，将概率累加
            else {
                res += dp[i];
            }
            // move the window
            if (i - W >= 0) {
                Wsum -= dp[i - W];
            }
        }
        return res;
    }
}
