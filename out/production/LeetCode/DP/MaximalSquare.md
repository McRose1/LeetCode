85题 Maximum Rectangle 的弱化题，但是解法不一样，求最大面积的正方形，只用找到最大边长就行

也是 DP

看答案吧

## Brute Force
enumerate all top-left corner and try all possible sizes 

## DP

dp[x][y] = max size can achieve at (x, y) as bottom right 

```java
if (matrix[x][y] == 0) {
  dp[x][y] = 0;
} else if (matrix[x][y] == 1) {
  dp[x][y] = min(dp[x - 1][y], dp[x][y - 1], dp[x- 1][y - 1]) + 1;
}
```

主要就是看左上角、右上角和对角线的正方形

二维 DP 其实不难想，难想的时优化了的一维 DP

这种矩阵用一维 DP 来做的非常普遍，以后要多回来复习！
