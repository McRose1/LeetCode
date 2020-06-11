# 206. Reverse Linked List
最最最经典也是最最最基础的链表题，链表翻转

## 迭代: Time = O(n) Space = O(1)
个人认为比递归要好想一些

要记得除了 cur 节点外，还需要维护 cur 前面的 pre 节点和 cur 后面的 next 节点

初始化 cur = head; pre = null;

在一次 while 循环中，首先声明一个临时的 next 节点，为原链表 cur 节点的下一个节点，为了后续能继续沿着这个原链表遍历下去

接着就是翻转链表的操作，把 cur 的 next 指针指向 pre 节点：cur.next = pre;

紧接着需要更新 cur 和 pre 为下一次翻转做准备：pre = cur; cur = next;

遍历到原链表的尾部，记得是 return pre，因为最后一个节点的时候：cur = null, pre = 原链表尾部节点（也就是翻转链表的头节点）

## 递归: Time = O(n) Space = O(n)
先从前往后不断调用函数本身，不断压栈，直到到达原链表的尾部节点

递归回退时每次都 return 翻转链表的头节点，也就是原链表的尾部节点，是永远保持不变的

每次递归内部只需要使得 head 的 next 节点指向 head 本身：head.next.next = head;       head <-> next 

并且由于 head 本身在原链表里是指向下一个节点的，为了翻转它，就要使得 head.next = null： head <- next

如果想不通，直接考虑头节点的情况：null -> head -----> head -> null





