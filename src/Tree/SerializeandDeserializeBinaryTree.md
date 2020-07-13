# 297. Serialize and Deserialize Binary Tree
将二叉树序列化为 string 并且可以从 string 反序列化回二叉树

## DFS
**从一个根开始，一直延伸到某个叶，然后回到根，到达另一个分支

用 Pre-order 和 recursion

根 左 右

每个节点之间用逗号分隔，null 节点用 # 表示

实质上还是用递归来做

需要 2 个 helper function 

## BFS
**按照层次的顺序从上到下遍历所有的节点

用 iteration 的方法做

层次遍历





