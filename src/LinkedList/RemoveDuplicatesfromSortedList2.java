package LinkedList;

/*  82. Remove Duplicates from Sorted List 2
    Given a sorted linked list, delete all nodes that have duplicate numbers,
    leaving only distinct numbers from the original list.

    Example 1:
    Input: 1->2->3->3->4->4->5
    Output: 1->2->5

    Example 2:
    Input: 1->1->1->2->3
    Output: 2->3
 */

public class RemoveDuplicatesfromSortedList2 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        boolean equal = false;
        while (cur != null && cur.next != null) {
            // cur 和 cur.next 相等，cur 不断后移
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                equal = true;
            }
            // 发生相等的情况，prev.next 直接指向 cur.next，删除所有重复数字
            if (equal) {
                pre.next = cur.next;
                equal = false;
            }
            // 没有发生相等的情况，pre 移到 cur 的位置
            else {
                pre = cur;
            }
            // 每一次 cur 都要后移
            cur = cur.next;
        }
        return dummy.next;
    }
}
