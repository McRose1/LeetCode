package Tree;

/*  117. Populating Next Right Pointers in Each Node 2
    Given a binary tree
    struct Node {
        int val;
        Node *left;
        Node *right;
        Node *next;
    }
    Populate each next pointer to point to its next right node.
    If there is no next right node, the next pointer should be set to NULL.
    Initially, all next pointers are set to NULL.

    Follow up:
    You may only use constant extra space.
    Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.

    Example:
    Input: root = [1,2,3,4,5,null,7]
    Output: [1,#,2,3,#,4,5,7,#]
    Explanation: Given the above binary tree (Figure A),
    your function should populate each next pointer to point to its next right node, just like in Figure B.
    The serialized output is in level order as connected by the next pointers,
    with '#' signifying the end of each level.

    Constraints:
    The number of nodes in the given tree is less than 6000.
    -1000 <= node.val <= 1000
 */

public class PopulatingNextRightPointersinEachNode2 {
    public Node connect(Node root) {
        // 每层的头结点
        Node head = null;
        // 当前节点，用于连接 next
        Node pre = null;
        // 当前层
        Node cur = root;
        while (cur != null) {
            // 遍历 cur 当前层
            while (cur != null) {           // cur=1; 2; 3
                if (cur.left != null) {
                    if (pre != null) {
                        pre.next = cur.left;
                    } else {
                        head = cur.left;      // head=2; 4
                    }
                    pre = cur.left;            // pre=2; 4
                }
                if (cur.right != null) {
                    if (pre != null) {          // 2; 4; 5
                        pre.next = cur.right;   // 2->3; 4->5; 5->7
                    } else {
                        head = cur.right;
                    }
                    pre = cur.right;            // pre=3; 5; 7
                }
                cur = cur.next;                 // cur=null; 3; null
            }
            // 更新 cur 到下一层
            cur = head;                         // cur=2; 4
            pre = null;
            head = null;
        }
        return root;
    }
}
