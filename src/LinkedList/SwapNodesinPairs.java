package LinkedList;

/*  24. Swap Nodes in Pairs
    Given a linked list, swap every two adjacent nodes and return its head.
    You may not modify the values in the list's nodes, only nodes itself may be changed.

    Example:
    Given 1->2->3->4, you should return the list as 2->1->4->3.
 */

/*  Iteration: Time = O(n) Space = O(1)
    为了避免单独讨论头结点的情况，申请一个 dummy node 指向头结点，然后再用一个指针来遍历整个链表
    dummy->1->2->3->4
 */
public class SwapNodesinPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            ListNode nextNode = cur.next.next;  // nextNode = 3
            prev.next = cur.next;   // dummy->2
            cur.next.next = cur;    // 1->2 --> 2->1 --> dummy->2->1
            cur.next = nextNode;    // 1->3 --> dummy->2->1->3
            prev = cur;             // prev = 1
            cur = cur.next;         // cur = 2
        }
        return dummy.next;      // return the new head
    }
}

/*  Recursion: Time = O(n) Space = O(n)

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode n = head.next;     // head = 1, n = 2; head = 3, n = 4
        head.next = swapPairs(head.next.next);  //  1.next = swap(3) --> 3.next = swap(null) --> 3->null; 1.next = swap(3) = 4 --> 1->4->3->null
        n.next = head;      // 4.next = 3 --> 4->3->null; 2.next = 1 --> 2->1->4->3->null
        return n;
    }
 */