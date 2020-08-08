# 525. Contiguous Array
给定一个二元数组（只包含 0 和 1），输出满足 0 和 1 数量相同的最长 subarray 的长度

此题用暴力解试了一下，TLE

count 遇到 1 加 1，遇到 0 减 1

主要是找到一个规律，那就是：

如果遍历数组遇到相同的 count，那么这 2 个 index 之间的 subarray 肯定是满足题目要求的 0 和 1 数量相同的区间

而为了求最长，我们只需要记录第一次出现这个 count 的 index，然后只要再次碰到这个 count，就不断更新 maxLen

## HashMap || int[2 * nums.length + 1]
HashMap 实现更为简单

用数组作为哈希表速度更快
