# 116. Populating Next Right Pointers in Each Node
注意这题的前提条件是 **perfect binary tree**:

all leaves are on the same level, and every parent has two children

这样的前提使得这题比 #117 要简单一些

花花酱：

Tree 90% -> Recursion 

Inorder or Preorder or Postorder 

Preorder 

Local view 

存在 2 种类型的 next 指针：
1. 连接同一父节点的 2 个子节点，它们可以通过同一个节点直接访问到：
```java
cur.left.next = cur.right;
```
通过结论来倒推条件：

cur.left.next -> 首先必须满足 cur.left != null

由于本题给的是 perfect binary tree，所以只要满足 cur.left != null，那么 cur.right 必定也 != null

所以这一条件分支为：
```java
if (cur.left != null) {
    cur.left.next = cur.right;
}
```

2. 不同父亲的子节点之间建立连接：
```java
cur.right.next = cur.next.left（难点）
```
通过结论来倒推条件：

cur.right.next -> 首先必须满足 cur.left != null || cur.right != null

cur.next.left -> 首先必须满足 cur.next != null

由于本题给的是 perfect binary tree，所以只要满足 cur.left != null，那么 cur.right 必定也 != null

所以这一条件分支可和上面分支结合起来：
```java
if (root.left != null) {
    root.left.next = root.right;
    
    if (root.next != null) {
        root.right.next = root.next.left;
    }
}
```

当然，也可以单独多写 1 条分支，代码阅读友好些：
```java
if (root.right != null && root.next != null) {
    root.right.next = root.next.left;
}
```

![116](/src/images/%23116.png)
    
可以 DFS：recursion，preorder
也可以 BFS：iteration，layer by layer 

## DFS
根 左 右

递归调用 connect 自身

## BFS

### Layer by layer 
层序遍历，从左往右，从上至下

```java
curNode = curNode.next;
leftmost = leftmost.left;
```

### Queue 
把每一层的 node 都 offer 到 queue 中做 BFS

特别注意每一层的最后一个 node，此时 cur.next = null

所以在循环 cur.next = queue.peek() 的时候需要特殊处理最后一个 node

因为此时的 queue.peek() 为下一层的 leftmost
