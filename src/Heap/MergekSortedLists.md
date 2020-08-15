# 23. Merge k Sorted Lists
给定 k 个排好序的链表，将它们合并

## Heap
minHeap 堆顶永远是最小值

用优先队列其实挺简单的，2 种选择，PQ 里存 ListNode 本身和存 ListNode.val

存 ListNode.val 会快一点

还有别忘了初始化一个 dummy 为 0，然后用 cur 指针指向 dummy 不断 next
