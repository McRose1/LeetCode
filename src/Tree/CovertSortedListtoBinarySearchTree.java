package Tree;

/*  109. Convert Sorted List to Binary Search Tree
    Given a singly list where elements are sorted in ascending order, covert it to a height balanced BST.
    For this problem, a height-balanced binary tree is defined as a binary tree in which the depth
    of the two subtrees of every node never differ by more than 1
    Example:
    Given the sorted linked list: [-10, -3, 0, 5, 9],
    One possible answer is: [0, -3, 9, -10, null, 5], which represents the following height balanced BST:
            0
           / \
         -3   9
         /    /
       -10   5
 */

import LinkedList.ListNode;

/*  Two Pointers
    通过快慢指针每次找到链表的中点，二分法分而治之
 */
public class CovertSortedListtoBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        ListNode preSlow = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            preSlow = slow;
            slow = slow.next;
        }
        TreeNode cur = new TreeNode(slow.val);

        if (preSlow != null) {
            // 将链表一分为二
            preSlow.next = null;
            cur.left = sortedListToBST(head);
            cur.right = sortedListToBST(slow.next);
        }
        return cur;
    }
}
