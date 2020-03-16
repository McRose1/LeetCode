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
    the key is to work backwards
    Assume that the rest of the list had already been reversed, how do I reverse the front part?
    Assume from node nk+1 to nm had been reversed and you are at nk:
    n1 -> ... -> nk-1 -> nk -> nk+1 <- ... <- nm
    we want nk+1's next node to point to nk:
    nk.next.next = nk;
    Be very careful that n1's next must point to null
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        // 调用递归一直到链表的尾部，再一个一个地往前 return
        ListNode new_head = reverseList(head.next); // head = 4

        head.next.next = head;  // 4->5->null --> 5->4
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
            ListNode nextTemp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextTemp;
        }
        return prev;
    }
*/
