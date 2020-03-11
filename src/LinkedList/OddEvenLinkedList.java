package LinkedList;

/*  328. Odd Even Linked List
    Given a singly linked list, group all odd nodes together followed by the even nodes.
    Please note here we are talking about the node number and not the value in the nodes.
    You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

    Example 1:
    Input: 1->2->3->4->5->NULL
    Output: 1->3->5->2->4->NULL

    Example 2:
    Input: 2->1->3->5->6->4->7->NULL
    Output: 2->3->6->7->1->5->4->NULL

    Note:
    The relative order inside both the even and odd groups should remain as it was in the input.
    The first node is considered odd, the second node even and so on ...
 */

/*  Pointers: Time = O(n) Space = O(1)
    Put the odd nodes in a linked list and the even nodes in another.
    Then link the evenList to the tail of the oddList.
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head;    // odd = 1
        ListNode even = head.next;  // even = 2
        ListNode evenHead = even;   // evenHead = 2
        while (even != null && even.next != null) { // 2; 4; null --> break
            odd.next = even.next;   // 1.next = 2.next --> 1->3; 3.next = 4.next = 5 --> 3->5
            odd = odd.next;         // odd = 1.next = 3; odd = 3.next = 5
            even.next = odd.next;   // 2.next = 3.next = 4 --> 2->4; 4.next = 5.next = null --> 4->null
            even = even.next;       // even = 2.next = 4; even = 4.next = null
        }
        odd.next = evenHead;        // 5.next = 2 --> 1->3->5->2->4->null
        return head;
    }
}
