# 189. Rotate Array 

将数组旋转 K 次

提示竟然说有起码 3 种不同做法。。。

并且希望我们不用额外空间

Input: [-1,-100,3,99] and k = 2

Output: [3,99,-1,-100]

Explanation: 

rotate 1 steps to the right: [99,-1,-100,3]

rotate 2 steps to the right: [3,99,-1,-100]

其实就是将数组看作是 cycle，然后右移

提示让我们想一想是否能利用 reverse 任何一部分数组

先 reverse 整个数组

然后分别 reverse 两部分数组

果然思路是对的哈哈，easy 题现在做做还是没啥问题

不过没有想到用余数来求当前 index：(i+k) % nums.length

还有一种做法没想出来

就是利用前面所说的循环特性

让每个元素都向右移动 k 位，将被替换掉的元素用 temp 存起来

如果形成了 cycle，就退出当前循环，将起始位置加 1，重复上述步骤
