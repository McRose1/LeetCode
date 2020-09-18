# 111. Minimum Depth of Binary Tree

算过二叉树的 maximum depth，这次来算 minimum depth

The minimum depth is the number of nodes along the shortest path from the **root node down to the nearest leaf node**.

A leaf is a node with no children

照搬 maximum 就错了

比如：
        
        1
        
       /
      
      2
      
如果是 Math.min(minDepth(root.left), minDepth(root.right)) + 1 -> 0 + 1 -> 1 

实际上答案应该是 2，因为此时只有一个叶子节点

其实本质上这题 DFS/BFS 的做法和 104 一模一样，唯一改变的就是对于条件的判断

这题的关键在于读懂题目中关于根节点到叶子节点的界定，答案的前提在于这个节点必须是叶子节点，不能为空

## DFS
还是可以调用自身，只是需要判断左右子树是否为空

- 如果都为空，很简单，这一层只累加 1 即可
- 如果左子树为空，那么在当前深度 root 没有左叶子节点，所以只能去右子树找
- 如果右子树为空，同理
- 如果都不为空，那么就是正常的 104 的 return 模式，只不过是 min 

## BFS
条件判断和 DFS 一模一样，只不过形式从 return 变成了往 queue 中 offer 左右节点 

______________
3.26

做出来了哈哈哈

其实就是多加一个判断

Math.min(minDepth(root.left), minDepth(root.right)) + 1 仅适用于左右子树都不会 null 的时候，所谓的公平竞争

如果有一方为 null 或者都为 null：

首先我们先考虑简单的都为 null 的情况，那么此时返回的就是当前层数 + 1

如果一方为 null，因为此时只要一个叶节点，所以直接返回该叶节点到 root 的距离

综合起来 Math.max(minDepth(root.left), minDepth(root.right)) + 1 可以满足

