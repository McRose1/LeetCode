# 442. Find All Duplicates in an Array
很容易想到 HashSet，但是题目又要求不要有 extra space

## HashSet
很简单，无需赘述

## 巧用 index
注意题目给的条件：**1 ≤ a[i] ≤ n (n = size of array)**

也就是说如果 n = 8, index = 0 ~ 7，那么 a[i] 是 1 ~ 8

所以我们碰到值为 i 的数字，就可以将它所在 index，也就是 (i - 1) 的位置上的值取反，这样一来，如果我们碰到的 index 还是这个（注意接下去的 index 需要 Math.abs(nums[i]) - 1 来算），就可以确定它是出现 2 次的
