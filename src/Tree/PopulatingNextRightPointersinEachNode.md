# 116. Populating Next Right Pointers in Each Node

花花酱：

Tree 90% -> Recursion 

Inorder or Preorder or Postorder 

Preorder 

Local view 

存在 2 种类型的 next 指针：
1. 连接同一父节点的 2 个子节点，它们可以通过同一个节点直接访问到：
```java
cur.left.next = cur.right
```
2. 不同父亲的子节点之间建立连接：
```java
cur.right.next = cur.next.left（难点）
```

![116](https://github.com/McRose1/LeetCode/blob/master/src/images/%23116.png)
    
可以 DFS：recursion，preorder
也可以 BFS：iteration，layer by layer 

