# 两两交换链表中的节点

Input: 1->2->3->4 

Output: 2->1->4->3

七牛云笔试碰到这题没做出来。。。

分别可以用迭代和递归来做

LC 官方：

- 迭代：
firstNode 用来遍历奇数结点，secondNode 用来遍历偶数结点

记得每次交换完更新 prevNode 指向交换完的新头：prevNode.next = secondNode

并且进行下次交换之前，prevNode 要移到这次交换完的尾部，指向下一个 pair 的头：prevNode = firstNode; 

- 递归：
每次递归负责一对节点，返回交换后的新头

注意递归的出口：if (head == null || head.next == null)

```java
firstNode.next  = swapPairs(secondNode.next);
secondNode.next = firstNode;
```

篮子王：

虽然题意是 swap pair，乍一看好像只有 2 个节点受影响，但是其实是 3 个节点受影响

比如：1->2->3->4->5，要交换的是 3->4 => 4->3

2->3->4 这一段的指针都要改变 => (2->4->3->)5

