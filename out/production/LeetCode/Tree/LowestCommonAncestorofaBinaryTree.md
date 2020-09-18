# 236 Lowest Common Ancestor of a Binary Tree

235 的 follow-up 题，没有了 BST 左小右大的特性，需要单单从 LCA 的特性来解决问题

## Recursion
树的问题往往都是递归，这题可以直接递归调用本身，先用 DFS 的思想一直遍历到底，然后再往上回退，回退的过程中判断是否满足递归的出口

先考虑递归的出口，有 3 种情况：
1. 遍历到的 root 为空，返回空
2. 遍历到的 root 是 p，返回该 root 
3. 遍历到的 root 是 q，返回该 root

如果我们在左子树找不到 LCA，那么说明 LCA 一定存在于右子树

同理，如果我们在右子树找不到 LCA，那么说明 LCA 一定存在于左子树

如果在左子树和右子树都找不到 LCA，那么 LCA 只能是根节点

直接调用主函数本身

## LC
f 代表包括 p 或者 q

(flson && frson) || ((x == p || x == q) && (flson || frson))

boolean dfs()

## HashMap
用 HashMap 存 p 和 q 的所有 从 root 到本身所经过的 parents，用 HashSet 来判断 p 和 q 是否有 parents 重合的部分

这个方法挺巧妙的，