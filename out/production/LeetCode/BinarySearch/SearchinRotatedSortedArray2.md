# 81. Search in Rotated Sorted Array 2

33 题的 follow up

比 33 题多了存在 duplicate 的情况

33 题要求输出 target 坐标，而这题只需要输出是否存在 target（其实是有原因的）

旋转数组的二分法套路还是没弄清楚

总结一下：

首先算出 mid 以后，肯定直接判断 nums[mid] 是否等于 target

接下去的条件分支，应该先判断 nums[mid] 和 nums[left] 的关系，也就是 mid 现在在旋转数组的左边还是右边

如果旋转数组左右两边都是有序 ascending 数组，那么如果 num[mid] 比 num[left] 大，很明显，mid 一定在旋转数组的左半部分

反之，则在右半部分

存在 duplicate 以后需要考虑的就是，原先未旋转的数组：[1, 1, 1, 3] （没有duplicate：[1, 2, 3, 4]） -> [2, 3, 4, 1]

但是旋转以后：[1, 1, 3, 1] 我们无法判断此时的 mid 在左半部分还是右半部分，因为我们只是知道 nums[mid] == 1

所以我们就把 left++，反正我们已经知道 nums[left] != target

还有一个注意点是，后面判断 target 和 nums[left] 以及 nums[mid] 的关系的时候

这里的判断一定是 >= 和 <=
