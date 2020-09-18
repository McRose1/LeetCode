# 104. Maximum Depth of Binary Tree

求二叉树的最大深度，这一系列还有求：最小深度、N 叉树的最大深度

## Recursion (DFS)

就一句话。。。

return Math.max(maxDepth(root.left), maxDepth(root.right) + 1);

完全可以自己调用自己的呀

## Iteration (BFS)
层级遍历相当于每一个深度在队列里求一次，每次先固定这一深度的 size
