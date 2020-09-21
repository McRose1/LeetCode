package LinkedList;

/*  142. Linked List Cycle 2
    Given a linked list, return the node where the cycle begins.
    If there is no cycle, return null.

    To represent a cycle in the given linked list,
    we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to.
    If pos is -1, then there is no cycle in the linked list.

    Note: Do not modify the linked list.

    Example 1:
    Input: head = [3,2,0,-4], pos = 1
    Output: tail connects to node index 1
    Explanation: There is a cycle in the linked list, where tail connects to the second node.

    Example 2:
    Input: head = [1,2], pos = 0
    Output: tail connects to node index 0
    Explanation: There is a cycle in the linked list, where tail connects to the first node.

    Example 3:
    Input: head = [1], pos = -1
    Output: no cycle
    Explanation: There is no cycle in the linked list.

    Follow-up: Can you solve it without using extra space?
 */

/*  Two Pointers: Time = O(n) Space = O(1)
    先按第一题的方法找快慢指针的相遇点；
    然后把快指针变成慢指针，从头开始每次一步，慢指针从相遇点开始每次走一步，下一次的相遇点就是循环链表的循环起始点
 */
public class LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                ListNode slow2 = head;
                while (slow != slow2) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }
}

/*  HashSet: Time = O(n) Space = O(n)
    思路和 happy number 一样，通过 HashSet 来判断是否存在 cycle

        HashSet<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (set.contains(node)) {
                return node;
            }
            set.add(node);
            node = node.next;
        }
        return null;
 */
