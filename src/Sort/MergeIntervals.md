# 56. Merge Intervals 
Sort intervals by its start 以 interval 的开始位置升序排列

if last[end] >= cur[start] : 有交集 -> merge intervals
  
else: (没有交集) -> insert a new interval 
  
不知道合并后一共有多少个区间？ 输出为 int[][] 要求根据 size 初始化

所以我们先用 list，最后再转成 int[][]

LeetCode 用 graph 做的方法看起来真的太复杂了。。。 先放着不管了
