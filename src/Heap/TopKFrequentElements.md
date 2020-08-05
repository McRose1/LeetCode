# 347. Top K Frequent Elements
给定一个数组，通过出现频率由大到小排序，输出前 k 个数字

很容易想到要为每个数字建立一个与之对应的出现次数的 hash

## (minHeap or maxHeap) + HashMap
原理都是维护一个 size = k 的 Heap，可以是最小堆，也可以是最大堆

- 最小堆，minHeap.poll() 出来的是 Top K 中最小的数字，所以我们最后写入数组的时候从右往左写
- 最大堆，minHeap.poll() 出来的是 Top K 中最大的数字，最后正常写入数组就行

## Bukcet Sort || Quick Select 
sort 的方法比上述方法难写，速度快一些，下次再回来完善
