# 206. Reverse Linked List
最最最经典也是最最最基础的链表题，链表翻转

## 迭代: Time = O(n) Space = O(1)
个人认为比递归要好想一些

要记得除了 head (cur) 节点外，还需要维护 head 前面的 pre 节点和 head 后面的 **next 节点**

pre = null;

在一次 while 循环中，**首先声明一个临时的 next 节点，为原链表 head 节点的下一个节点，为了后续能继续沿着这个原链表遍历下去**

接着就是翻转链表的操作，把 head 的 next 指针指向 prev 节点：head.next = prev;

紧接着需要更新 head 和 prev 为下一次翻转做准备：prev = head; head = next;

遍历到原链表的尾部，**记得是 return prev**，因为最后一个节点的时候：head = null, prev = 原链表尾部节点（也就是翻转链表的头节点）

## 递归: Time = O(n) Space = O(n)
先从前往后不断调用函数本身，不断压栈，直到到达原链表的尾部节点

递归回退时每次都 return 翻转链表的头节点，也就是原链表的尾部节点，是永远保持不变的

每次递归内部只需要使得 head 的 next 节点指向 head 本身：head.next.next = head;       head <-> next 

并且由于 head 本身在原链表里是指向下一个节点的，为了翻转它，就要使得 head.next = null： head <- next

如果想不通，直接考虑头节点的情况：null -> head -----> head -> null





