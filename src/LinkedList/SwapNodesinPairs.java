package LinkedList;

/*  24. Swap Nodes in Pairs
    Given a linked list, swap every two adjacent nodes and return its head.
    You may not modify the values in the list's nodes, only nodes itself may be changed.

    Example:
    Given 1->2->3->4, you should return the list as 2->1->4->3.
 */

/*  Iteration（LC Version）: Time = O(n) Space = O(1)
    重点是需要用一个 prevNode 记录一对 pair 里前面的节点的前驱结点
 */
public class SwapNodesinPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevNode = dummy;

        while (head != null && head.next != null) {

            // Nodes to be swapped
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            // Swapping
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;
            // 还需要更新 prevNode.next 指向交换后的头，因为 prev 本来指向的是第一个节点
            prevNode.next = secondNode;

            // Reinitializing the head and prev for next swap
            prevNode = firstNode;
            head = firstNode.next;  // jump
        }
        return dummy.next;
    }
}

/*  Iteration（Version2）
    交换一对 pair 其实影响到 3 个节点

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            // 初始化 pair 里的两个 node
            ListNode oddNode = prev.next;
            ListNode evenNode = prev.next.next;

            // 交换
            prev.next = evenNode;
            oddNode.next = evenNode.next;
            evenNode.next = oddNode;

            // 更新 prev 指针，准备下一个 pair 的交换
            prev = oddNode;
        }
        return dummy.next;
 */

/*  Recursion: Time = O(n) Space = O(n)
    每次递归都负责交换一对节点：firstNode 和 secondNode
    交换了两个节点以后，返回 secondNode，因为它是交换后的新头

    public ListNode swapPairs(ListNode head) {
        // recursion exit
        // If the list has no node or has only one node left
        if (head == null || head.next == null) {
            return head;
        }

        // Nodes to be swapped
        ListNode firstNode = head;
        ListNode secondNode = head.next;

        // Swapping
        firstNode.next = swapPairs(secondNode.next);
        secondNode.next = firstNode;

        // Now the head is the second node
        return secondNode;
    }
 */