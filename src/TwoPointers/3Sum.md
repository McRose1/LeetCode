# 15. 3Sum

shopee 笔试里碰到了，居然看成了 permutation 用 backtracking。。。

其实我感觉和 2 sum 没啥关系，一个无脑用 HashMap，而这个用双指针还要考虑会出现重复的细节

晕死了。。。应该是双指针啊啊啊啊
其实是三指针

山景城一姐：
1. Sort the array 
2. Lock one pointer and do two sum with the other two

**这题去除 duplicate 的各个细节一点要多多注意**
- Arrays.sort() -> 这是一切取出 duplicate 的前提
- if (i > 0 && nums[i] == nums[i - 1]) continue; -> 排除第一个元素的 duplicate 可能
- while (low < high && nums[low] == nums[low + 1]) low++; -> 排除第二个元素的 duplicate 可能
- while (low < high && nums[high] == nums[high - 1]) low++; -> 排除第三个元素的 duplicate 可能
