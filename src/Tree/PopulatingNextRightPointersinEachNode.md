116. Populating Next Right Pointers in Each Node

花花酱：

Tree 90% -> Recursion 

Inorder or Preorder or Postorder 

Preorder 

Local view 

cur.left.next = cur.right
cur.right.next = cur.next.left（难点）

![116](src/images/%23116.png)
    
可以 DFS：recursion，preorder
也可以 BFS：iteration，layer by layer 

