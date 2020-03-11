package LinkedList;

/*  369. Plus One Linked List
    Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
    You may assume the integer do not contain any leading zero, except the number 0 itself.
    The digits are stored such that the most significant bit is at the head of the list.

    Example:
    Input: [1,2,3]
    Output: [1,2,4]
 */

/*  One Pass: Time = O(n) Space = O(1)
    Using two pointers, detect the consecutive '9's while recording the last non-'9' digit position.
    Add 1 to the last non-'9' digit.
 */
public class PlusOneLinkedList {
    public ListNode plusOne(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        ListNode i = dummy;
        ListNode j = dummy;

        while (j.next != null) {
            // j traverse the whole linked list
            j = j.next;
            // i record the last non-'9' digit position
            if (j.val != 9) {
                i = j;
            }
        }
        // non-'9' digit need to plus 1
        i.val++;
        // move to the first '9' if it exists
        i = i.next;
        while (i != null) {
            // set '9' to '0'
            i.val = 0;
            // move to the next '9' if it exists
            i = i.next;
        }
        // 数字位数没有改变
        if (dummy.val == 0) {
            return dummy.next;
        }
        // 999 -> 1000
        return dummy;
    }
}

/*  Reverse: Time = O(n) Space = O(n)
    链表翻转，链尾变成高位，进行加 1 运算后再翻转回来

    public ListNode plusOne(ListNode head) {
        head = reverse(head);   // 1->2->3 --> 3->2->1; 129 -> 921; 999 -> 999
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        int carry = 1;
        while (carry > 0 || cur.next != null) {
            if (cur.next != null) {
                cur = cur.next;        // cur = 3; cur = 9 -> cur = 2; cur = 9 -> cur = 9 -> cur = 9 -> cur.next = null
                carry += cur.val;       // carry = 4; carry = 10 -> 3; carry = 10 -> carry = 10 - > carry = 10
                cur.val = carry % 10;   // cur = 4; cur = 0 -> cur = 3; cur = 0 -> cur = 0 -> cur = 0
                carry /= 10;            // carry = 0; carry = 1 -> carry = 0; carry = 1 -> carry = 1 -> carry = 1
            } else {
                cur.next = new ListNode(carry);     // cur.next = 1
                cur = cur.next;                     // cur = 1
                carry = 0;
            }
        }
        return reverse(dummy.next);     // 4->2->1 --> 1->2->4

        public ListNode reverse(ListNode head) {
            ListNode prev = null;
            while (head != null) {
                ListNode temp = head.next;
                head.next = prev;
                prev = head;
                head = temp;
            }
            return prev;
        }
    }
 */