# 77. Combinations

Backtracking 经典题

排列组合里面不存在重复的情况

比如 [1, 2] 和 [2, 1] 算一种情况

这题把 index 初始化为 1，每次递归调用是 index + 1 就行，**用不到 visited 数组**
