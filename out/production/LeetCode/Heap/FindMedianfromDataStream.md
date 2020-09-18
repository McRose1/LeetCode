# 295. Find Median from Data Stream
设计一个方法，可以从数据流中加数字和找到当前数据流的中位数

## Heap

想起来第一次做的时候答案的方法了

就是 1 个 minHeap，1 个 maxHeap，这样 2 个 heap 的堆顶元素 / 2 （或者奇数个就是 maxHeap 的堆顶）就是中位数

也就是把数据流前半部分的数据都放在 maxHeap 里，后半部分数据都放在 minHeap 里

**最重要的是要维持两个 heap 的 size**
- minHeap size 最多比 maxHeap size 大 1
- maxHeap size 不能超过 minHeap size
这是本题的难想点
