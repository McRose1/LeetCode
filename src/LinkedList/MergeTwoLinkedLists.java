package LinkedList;

/*  21. Merge Two Sorted Lists
    Merge two sorted linked lists and return it as a new list.
    The new list should be made by splicing together the nodes of the first two lists.

    Example:
    Input: 1->2->4, 1->3->4
    Output: 1->1->2->3->4->4
 */

//  Iteration: Time = O(n) Space = O(1)
public class MergeTwoLinkedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (l1 != null && l2 != null) {
             if (l1.val < l2.val) {
                 head.next = l1;
                 l1 = l1.next;
             } else {
                 head.next = l2;
                 l2 = l2.next;
             }
             head = head.next;
        }
        // l1 有剩余
        if (l1 != null) {
            head.next = l1;
        }
        // l2 有剩余
        else {
            head.next = l2;
        }
        return dummy.next;
    }
}

/*  Recursion: Time = O(n) Space = O(n)

        // If one of the list is empty, return the other one
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // The smaller one becomes the head
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        }
 */