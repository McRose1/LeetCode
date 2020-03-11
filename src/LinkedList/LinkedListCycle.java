package LinkedList;

/*  141. Linked List Cycle
    Given a linked list, determine if it has a cycle in it.
    To represent a cycle in the given linked list, we use an integer pos which represents the position (0 - indexed)
    in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

    Example 1:
    Input: head = [3,2,0,-4], pos = 1
    Output: true

    Example 2:
    Input: head = [1,2], pos = 0
    Output: true

    Example 3:
    Input: head = [1], pos = -1
    Output: false

    Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */

/*  Two Pointers: Time = O(n) Space = O(1)
    有循环的话快指针肯定会赶上慢指针
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}

/*  HashTable: Time = O(n) Space = O(n)
    To detect if a list is cyclic, we can check whether a node had been visited before.

        Set<ListNode> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
 */