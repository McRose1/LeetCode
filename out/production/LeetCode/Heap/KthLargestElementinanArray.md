# 215. Kth Largest Element in an Array

从一个无序的数组里找出排好序后的第 K 大的数字，存在重复

第一反应就想到了 quick select

tag 是 divide&conquer 和 heap

heap 很容易相当用 Heap

Heap 的默认排序是从小到大，也就是 minHeap

先全部 offer 进去

一个一个 poll 出来直到 queue 的 size 等于 k 时

这个想法有点浪费空间了。。。

因为 minHeap 自动维护最小值，所以只用把 PQ 的 size 限定为 k 即可

不断 offer element 只要超过 k 了就自动 poll 出最小值

最后留在堆顶的就是 K 个元素里面最小的，也就是第 K 大的元素

quick select 会快很多

其实就是 divide&conquer 的思想

这题的快排方法还是非常经典，细节非常复杂，需要回来经常复习！

