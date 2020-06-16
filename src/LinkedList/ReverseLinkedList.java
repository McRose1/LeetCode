package LinkedList;

/*  206. Reverse Linked List
    Reverse a singly linked list.

    Example:
    Input: 1->2->3->4->5->NULL
    Output: 5->4->3->2->1->NULL

    Follow up:
    A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

/*  Iteration: Time = O(n) Space = O(1)
    Change the current node's next pointer to point to its previous element.
    Since a node does not have reference to its previous node, you must store its previous element beforehand.
    You also need another pointer to store the next node before changing the reference.
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}

/*  Recursion: Time = O(n) Space = O(n)
    the key is to work backwards
    Assume that the rest of the list had already been reversed, how do I reverse the front part?
    Assume from node nk+1 to nm had been reversed and you are at nk:
    n1 -> ... -> nk-1 -> nk -> nk+1 <- ... <- nm
    we want nk+1's next node to point to nk:
    nk.next.next = nk;
    Be very careful that n1's next must point to null

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        // 调用递归一直到链表的尾部，再一个一个地往前 return
        ListNode newHead = reverseList(head.next); // head = 4, newHead = 5

        head.next.next = head;  // 4->5->null --> 5->4
        head.next = null;       // 5->4->null
        // 递归回退返回翻转链表的头节点
        return newHead;        // return 5
    }
*/

/*  Recursion 2

    public ListNode reverseList(ListNode head) {
        return reverse(head, null);
    }

    private ListNode reverse(ListNode head, ListNode newHead) {
        if (head == null) return newHead;

        ListNode next = head.next;
        head.next = newHead;
        newHead = head;
        head = next;

        return reverse(head, newHead);
    }
 */