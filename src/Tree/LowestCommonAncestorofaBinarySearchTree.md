# 235. Lowest Common Ancestor of a Binary Search Tree

在 BST 里寻找两个 node 的 lowest common ancestor (LCA)

LAC: lowest node that both p and q as descendants (allow a node to be a descendant of itself)

树的题目每次都知道是要用 recursion，但是每次就是想不到这个 recursion 应该怎么调

## LC 官方：
哦。。。忘记利用 BST 的特性了

就遵循一个准则就行：
- 两个 node 的 val 都小于当前 root 的 val，那就往左子树继续找
- 两个 node 的 val 都大于当前 root 的 val，那就往右子树继续找
- else，也就是两个 node 在当前 root 的两边，那么当前 root 就是 LCA node

**向左走向右走**