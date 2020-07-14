# 226. Invert Binary Tree

将二叉树左右交换

得物面试被问到过这题

树的题基本上就是 recursion

看看能不能调用自身

第二层：root.left.val 和 root.right.val 互换

第三层：root.left.left.val 和 root.right.right.val 互换 root.left.right.val 和 root.right.left.val 互换

错错错！！！

事实证明。。。想错了，不应该交换 value，而是直接把 TreeNode 交换

可以用recursion来做
也可以用iteration来做，分别是 BFS+Queue 和 DFS+Stack

## Recursion 
```java
TreeNode temp = root.left;
root.left = root.right;
root.right = temp;
```

## Iteration 
这题迭代方法的关键点在于，不论用 queue 还是 stack，加入新 node 之前必须先判断是否不为 null

### BFS + Queue 
invert 过程和递归一模一样

从上层到下一层需要先判断 cur.left 和 cur.right 是否 != null

### DFS + Stack
和 BFS 基本一样，唯一的区别在于 Stack 先进后出，所以先 push 右结点，再 push 左结点