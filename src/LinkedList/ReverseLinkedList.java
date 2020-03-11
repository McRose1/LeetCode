package LinkedList;

/*  206. Reverse Linked List
    Reverse a singly linked list.

    Example:
    Input: 1->2->3->4->5->NULL
    Output: 5->4->3->2->1->NULL

    Follow up:
    A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

/*  Recursion: Time = O(n) Space = O(n)

 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode new_head = reverseList(head.next);

        head.next.next = head;  // 4->5 --> 5->4
        head.next = null;       // 5->4->null
        return new_head;        // return 5
    }
}

/*  Iteration: Time = O(n) Space = O(1)
    Change the current node's next pointer to point to its previous element.
    Since a node does not have reference to its previous node, you must store its previous element beforehand.
    You also need another pointer to store the next node before changing the reference.

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextNode = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextNode;
        }
        return prev;
    }
*/
