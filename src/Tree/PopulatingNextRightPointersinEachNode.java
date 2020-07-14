package Tree;

/*  116. Populating Next Right Pointers in Each Node
    You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
    The binary tree has the following definition:
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
    Input: root = [1,2,3,4,5,6,7]
    Output: [1,#,2,3,#,4,5,6,7,#]
    Explanation: Given the above perfect binary tree (Figure A),
    your function should populate each next pointer to point to its next right node, just like in Figure B.
    The serialized output is in level order as connected by the next pointers,
    with '#' signifying the end of each level.

    Constraints:
    The number of nodes in the given tree is less than 4096.
    -1000 <= node.val <= 1000
 */

/*  BFS: Time = O(n) Space = O(1)
    Layer by layer using next pointer（充分利用题目给定的 next 指针）
 */
public class PopulatingNextRightPointersinEachNode {
    public Node connect(Node root) {
        if (root == null) return null;

        // Start with the root node. There are no next pointers that need to be set up on the first level
        Node leftmost = root;

        // Once we reach the final level, we are done
        while (leftmost.left != null) {

            // Iterate the "linked list" starting from the head node and using the next pointers,
            // establish the corresponding links for the next level
            Node curNode = leftmost;

            while (curNode != null) {

                // CONNECTION 1
                curNode.left.next = curNode.right;

                // CONNECTION 2
                if (curNode.next != null) {
                    curNode.right.next = curNode.next.left;
                }

                // Progress along the list (nodes on the current level)
                // 从左往右移动
                curNode = curNode.next;
            }
            // Move onto the next level
            // 从上至下移动
            leftmost = leftmost.left;
        }
        return root;
    }
}

/*  DFS: Time = O(n) Space = O(logn)
    Preorder：root -> left -> right

        if (root == null) return null;

        if (root.left != null) {
            root.left.next = root.right;

            // 充分利用题目给的 perfect binary tree 这一前提条件
            if (root.next != null) {
                root.right.next = root.next.left;
            }
        }

        *
        if (root.right != null && root.next != null) {
            root.right.next = root.next.left;
        }
        *

        connect(root.left);
        connect(root.right);

        return root;
 */

/*  BFS（Using queue）: Time = O(n) Space = O(n)

        if (root == null) return root;

        // Initialize a queue data structure which contains just the root of the tree
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        // Outer while loop which iterates over each level
        while (queue.size() > 0) {

            // Note the size of the queue
            int size = queue.size();

            // Iterate over all the nodes on the current level
            for (int i = 0; i < size; i++) {
                // Pop a node from the front of the queue
                Node cur = queue.poll();

                // This check is important. We don't want to establish any wrong connections.
                // The queue will contain nodes from 2 levels at most at any point in time.
                // This check ensures we only don't establish next pointer beyond the end of a level
                if (i < size - 1) {
                    cur.next = queue.peek();
                }

                // Add the children, if any, to the back of the queue
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return root;
 */
