# 216. Combination Sum 3 
组合求和系列第 3 题，先来回忆一下前两题

第 1 题，给定一个 candidate 数组，从中选取元素组合形成加和为 target，不存在 duplicate，可以反复使用某个元素

第 2 题，同样的设定，但是 candidate 数组存在 duplicate，并且每个元素只能使用一次

今天这题，从 candidate 数组变成了从 1-9 里边选取 k 个数字，组成加和为 n 的组合，组合里没有 duplicate

还是用回溯

每次 DFS 先规定 start index，然后调用递归的时候让 start == num + 1，确保组合都是升序的，不会重复，免去了设置 visited 数组

一开始想错了，想成了 start = start + 1（傻掉了），这样就会出现 [2,3,4] [4,3,2] 这种情况，**应该是每次把 start 赋值为 i+1**

直接起始位置设为当前数字加 1 就可以实现剪枝

也不用记录当前的 sum 和当前的 count，因为直接 k - 1，n - i