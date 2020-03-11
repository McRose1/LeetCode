package LinkedList;

/*  2. Add Two Numbers
    You are given two non-empty linked lists representing two non-negative integers.
    The digits are stored in reverse order and each of their nodes contain a single digit.
    Add the two numbers and return it as a linked list.
    You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Example:
    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8
    Explanation: 342 + 465 = 807.

    Follow up:
    What if the digits in the linked list are stored in non-reversed order? For example:
    (3 -> 4 -> 2) + (4 -> 6 -> 5) = 8 -> 0 -> 7
 */

import java.util.List;

/*  Math: TIme = O(max(m,n)) Space = O(max(m,n))
    1. Initialize current node to dummy head of the returning list.
    2. Initialize carry to 0.
    3. Initialize p and q to head of l1 and l2 respectively.
    4. Loop through lists l1 and l2 until you reach both ends.
        1. Set x to node p's value. If p has reached the end of l1, set to 0.
        2. Set y to node q's value. If q has reached the end of l2, set to 0.
        3. Set sum = x + y + carry.
        4. Update carry = sum/10.
        5. Create a new node with the digit value of (sum mode 10) and set it to current node's next, then advance current node to next.
        6. Advance both p and q.
    5. Check if carry = 1, if so append a new node with digit 1 to the returning list.
    6. Return dummy head's next node.
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);   // 可以迅速找到 head 的位置
        int carry = 0;
        ListNode cur = dummy;
        ListNode p = l1, q = l2;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }
}
