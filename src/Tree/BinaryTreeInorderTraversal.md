# 94. Binary Tree Inorder Traversal
Inorder：左 - 根 - 右

## 递归
最简单的就是递归，但是别忘了，树 traverse 的递归都是需要再来一个 helper 函数的，不能直接递归给定的函数。

但是面试里肯定不让用递归，所以还是得用迭代的方法。

## 迭代，用 stack 实现
先一直往左子树遍历，不断压栈，相当于保留了每个子树的根节点

遍历到底了（碰到 null），开始回退也就是出栈，每次拿出一个节点作为根节点，加到 list 里，再遍历它的右子树

## Morris Traversal 
看起来好难，下次回来看的时候学一下这个方法

