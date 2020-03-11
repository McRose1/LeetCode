package LinkedList;

/*  92. Reverse Linked List 2
    Reverse a linked list from position m to n. Do it one-pass.

    Note: 1 ≤ m ≤ n ≤ length of list.

    Example:
    Input: 1->2->3->4->5->NULL, m = 2, n = 4
    Output: 1->4->3->2->5->NULL
 */

/*  Iterative Link Reversal: Time = O(n) Space = O(1)
    核心在于取出需要反转的这一小段链表，反转完后再插入到原先的链表中
    A  -> B -> C --> A<-B<-C
    prev cur    -> cur.next = prev: A<-B->C we don't have a way of progressing further since we can't reach the node C
    That's why we need a third pointer that will help us continue the link reversal process:
    third = cur.next;   // third = C
    cur.next = prev;    // B->A
    prev = cur;         // prev = B
    cur = third;        // cur = C
    We do the above iteratively.

    An important thing to note is the usage of two additional pointers which we will call as tail and con:
    The tail pointer points to the mth node and we call it a tail pointer since this node becomes the tail of the reverse sublist.
    The con points to the node one before mth node and this connects to the new head of the reverse sublist.
 */
public class ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {

        // Empty list
        if (head == null) {
            return null;
        }

        // Keep progressing the two pointers until the cur pointer reaches the mth node.
        // This is the point from where we start reversing our linked list.
        ListNode cur = head;    // cur = 1
        ListNode prev = null;
        while (m > 1) {     // m = 2; m = 1
            prev = cur;     // prev = 1
            cur = cur.next; // cur = 2
            m--;            // m = 1
            n--;            // n = 3
        }

        // The two pointers that will fix the final connections.
        ListNode con = prev;    // con = 1
        ListNode tail = cur;    // tail = 2

        // Iteratively reverse the nodes until n becomes 0.
        ListNode third = null;
        while (n > 0) {         // n = 3; n = 2; n = 1
            third = cur.next;   // third = 3; third = 4; third = 5
            cur.next = prev;    // 2->1; 3->2->1; 4->3->2->1
            prev = cur;         // prev = 2; prev = 3; prev = 4
            cur = third;        // cur = 3; cur = 4; cur = 5
            n--;                // n = 2; n = 1; n = 0
        }

        // Adjust the final connections as explained in the algorithm
        if (con != null) {      // con = 1
            con.next = prev;    // 1 -> 4
        } else {
            head = prev;
        }

        tail.next = cur;        // 2 -> 5 --> 1->4->3->2->5
        return head;
    }
}

/*  Recursion: Time = O(n) Space = O(n)
    We repeatedly swap elements pointed to by these two pointers
    and we move both the pointers towards the center of the array.
    However, we don't have any backward pointers in our linked list and neither do we have any indexes.
    So, we rely on recursion to simulate the backward pointer.
    Essentially, the backtracking process in a recursion will help us in simulating the backward movement
    of the pointer from the nth node in the linked list towards the center.

    after swapping out the data, we need to move the left pointer one step forward using left = left.next.
    We need this change to persist across the backtracking process.
    From there on, every time we backtrack, the right pointer moves one step backwards.
    The backward movement is simulated by backtracking.

    public class ReverseLinkedList2 {

    // Object level variables since we need the changes to persist across recursive calls and Java is pass by value.
    private boolean stop;
    private ListNode left;

    public ListNode reverseBetween(ListNode head, int m, int n) {
        this.left= head;
        this.stop = false;
        this.recurseAndReverse(head, m, n);
        return head;
    }

    public void recurseAndReverse(ListNode right, int m, int n) {

        // base case. Don't proceed any further
        if (n == 1) {
            return;
        }

        // Keep moving the right pointer one step forward until (n == 1)
        right = right.next;

        // Keep moving left pointer to the right until we reach the proper node from where the reversal is to start.
        if (m > 1) {
            this.left = this.left.next;
        }

        // Recurse with m and n reduced.
        this.recurseAndReverse(right, m - 1, n - 1);

        // In case both the pointers cross each other or become equal, we stop i.e. don't swap data any further.
        // We are done reversing at this point.
        if (this.left == right || right.next == this.left) {    //
            this.stop = true;
        }

        // Until the boolean stop is false, swap data between the two pointers.
        if (!this.stop) {               // 4->3->2; 2->3->4
            int t = this.left.val;      // t = 4; t = 3
            this.left.val = right.val;  // left = 2; left = 4
            right.val = t;              // right = 4; right = 3

            // Move left one step to the right.
            // The right pointer moves one step back via backtracking.
            this.left = this.left.next; // left = 2.next = 3; left = 4.next = 5
        }
    }
}
 */