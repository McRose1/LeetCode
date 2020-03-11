package LinkedList;

/*  19. Remove Nth Node From End of List
    Given a linked list, remove the n-th node from the end of list and return its head.

    Example:
    Given linked list: 1->2->3->4->5, and n = 2.
    After removing the second node from the end, the linked list becomes 1->2->3->5.

    Note: Given n will always be valid.

    Follow up: Could you do this in one pass?

    Hint: Maintain two pointers and update one with a delay of n steps
 */
/*  One pass (Two Pointers): Time = O(L) Space = O(1)
    The first pointer advances the list by n+1 steps from the beginning,
    while the second pointer starts from the beginning of the list.
    让 slow 和 fast 之间产生 n nodes 的距离，当 fast 到达末尾时，slow 正好到达 n-th node from the end.
 */
public class RemoveNthNodeFromEndofList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        ListNode slow = dummy;
        ListNode fast = dummy;
        dummy.next = head;
        // Advances fast pointer so that the gap between fast and slow is n nodes apart
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        // Move fast to the end, maintaining the gap
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}

/*  Two Pass: Time = O(L) Space = O(1)
    the problem could be simply reduced to another one: Remove the (L-n+1)th node from the beginning in the list.
    The "dummy" node is used to simplify some corner cases such as a list with only one node, or removing the head of the list.
    On the first pass, we find the list length L.
    Then we set a pointer to the dummy node and start to move it through the list till it comes to the (L-n)th node.

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
 */
